package tests;

import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tests.presets.ConstantWithDescription;
import tests.presets.DataPreset;
import tests.presets.SequenceWithStatistics;
import tests.utils.Test;
import tests.utils.TestInputCollection;
import tests.utils.TestInputConstant;

public class TestsPool {
    
    /*******************************/
    /* DO NOT TOUCH METHODS BELLOW */
    /*******************************/
    
    private final Map <Class <? extends DataPreset <?>>, DataPreset <?>> presets = new HashMap <> ();
    private final List <TaskTests> pool;
    
    @SuppressWarnings ("unused")
    private final List <Function <?, ?>> functions = List.of (
        (String s) -> s.concat ("Solk")
    );
    
    public TestsPool (Class <?> solutions) {
        pool = loadTests (solutions);
    }
    
    private List <TaskTests> loadTests (Class <?> solution) {
        final var tests = new ArrayList <TaskTests> ();
        final var random = new Random (1L);
        
        final Predicate <Executable> isPublic = e -> Modifier.isPublic (e.getModifiers ());
        final Predicate <Executable> isStatic = e -> Modifier.isStatic (e.getModifiers ());
        final Predicate <Executable> isAbs = e -> Modifier.isAbstract (e.getModifiers ());
        final Predicate <Executable> isTest = e -> e.isAnnotationPresent (Test.class);
        
        Arrays.stream (solution.getDeclaredMethods ())
            . filter (isPublic.and (isStatic.negate ()).and (isAbs).and (isTest))
            . sorted (Comparator.comparingInt (Test.GET_ORDER))
            . peek (System.out::println)
            . map (method -> prepareTestsForMethod (method, random))
            . forEach (tests::add);
        
        return tests;
    }
    
    private TaskTests prepareTestsForMethod (Method method, Random random) {
        final var tests = new TaskTests ();
        
        final var parameters = method.getParameters ();
        
        final var inputs = Arrays.stream (parameters)
            . map (p -> prepareInputDataForParameter (p, random))
            . collect (Collectors.toList ());
        final var maxCases = inputs.stream ()
            . mapToInt (List::size).max ().orElse (0);
        
        //List <Object []> methodInput = new ArrayList <> ();
        for (int i = 0; i < maxCases; i++) {
            final var paramInput = new Object [parameters.length];
            for (int j = 0; j < parameters.length; j++) {
                final var inp = inputs.get (j);
                if (inp.isEmpty ()) {
                    throw new IllegalArgumentException (String.format (
                        "Parameter `%s %s` of method `%s` should have at least 1 input values",
                        parameters [j].getType (), parameters [j].getName (), method.getName ()
                    ));
                }
                
                paramInput [j] = inp.get (i % inp.size ());
            }
            
            tests.addCase (prepareCheckingConsumer (method, paramInput, random));
        }
        
        return tests;
    }
    
    private List <?> prepareInputDataForParameter (Parameter parameter, Random random) {
        if (parameter.isAnnotationPresent (TestInputCollection.class)) {
            final var annotation = parameter.getAnnotation (TestInputCollection.class);
            return prepareCollectionInputForParameter (parameter, annotation, random);
        } else if (parameter.isAnnotationPresent (TestInputConstant.class)) {
            final var annotation = parameter.getAnnotation (TestInputConstant.class);
            return prepareConstantInputForParameter (parameter, annotation, random);
        }
        
        return List.of ();
    }
    
    private List <SequenceWithStatistics <?>> prepareCollectionInputForParameter (
        Parameter parameter, TestInputCollection annotation, Random random
    ) {
        final var inputsCollector = new ArrayList <SequenceWithStatistics <?>> ();
        for (final var presetType : annotation.presets ()) {
            final var preset = presets.computeIfAbsent (presetType, type -> {
                try {
                    final var data = type.getConstructor ().newInstance ();
                    data.initialize (random);
                    return data;
                } catch (
                    InstantiationException | IllegalAccessException 
                    | IllegalArgumentException | InvocationTargetException 
                    | NoSuchMethodException | SecurityException 
                    e
                ) {
                    return null;
                }
            });
            
            final var varation = annotation.variation () + 1;
            final var unique = annotation.allUnique ();
            
            for (final var constantInput : annotation.constant ()) {
                final var length = constantInput + random.nextInt (varation);
                inputsCollector.add (preset.getRandomSequence (length, random, unique));
            }
            
            for (final var percentageInput : annotation.percentage ()) {
                final var percent = percentageInput + random.nextInt (varation) / 100.0;
                final var length = (int) Math.round (percent * preset.getSize ());
                inputsCollector.add (preset.getRandomSequence (length, random, unique));
            }
        }
        
        return inputsCollector;
    }
    
    private List <ConstantWithDescription> prepareConstantInputForParameter (
        Parameter parameter, TestInputConstant annotation, Random random
    ) {
        final var inputCollector = new ArrayList <ConstantWithDescription> ();
        final var variation = annotation.variation ();
        
        for (final var constantInput : annotation.constant ()) {
            inputCollector.add (new ConstantWithDescription (constantInput, variation));
        }
        
        final var sparam = annotation.parameter ();
        for (final var sequenceInput : annotation.sequence ()) {
            inputCollector.add (new ConstantWithDescription (sequenceInput, sparam, variation));
        }
        
        return inputCollector;
    }
    
    private BiConsumer <StreamTasksTests, StreamTasksTests> prepareCheckingConsumer (
        Method method, Object [] paramInput, Random random
    ) {
        return (implementation, reference) -> {
            final var seed = random.nextLong ();
            
            final var resultImpl = prepareAndInvokeImplementation (implementation, method, paramInput, seed);
            final var resultRef = prepareAndInvokeImplementation (reference, method, paramInput, seed);
            System.out.println ("Result: " + resultImpl + " ? " + resultRef); // SYSOUT
        };
    }
    
    private Object prepareAndInvokeImplementation (
        StreamTasksTests implementation, Method method, Object [] paramInput, long randomSeed
    ) {
        Object [] input = new Object [paramInput.length];
        final var parameters = method.getParameters ();
        final var random = new Random (randomSeed);
        
        for (int i = 0; i < parameters.length; i++) {
            if (parameters [i].getType () == List.class) {
                if (paramInput [i] instanceof SequenceWithStatistics) {
                    input [i] = ((SequenceWithStatistics <?>) paramInput [i]).data;
                } else {
                    throw new IllegalArgumentException (String.format (
                        "In method `%s` parameter #%d should be annotated with @TestInputCollection",
                        method.getName (), i
                    ));
                }
            } else if (parameters [i].getType () == Set.class) {
                if (paramInput [i] instanceof SequenceWithStatistics) {
                    input [i] = Set.copyOf (((SequenceWithStatistics <?>) paramInput [i]).data);
                } else {
                    throw new IllegalArgumentException (String.format (
                        "In method `%s` parameter #%d should be annotated with @TestInputCollection",
                        method.getName (), i
                    ));
                }
            } else if (parameters [i].getType () == Stream.class) {
                if (paramInput [i] instanceof SequenceWithStatistics) {
                    input [i] = ((SequenceWithStatistics <?>) paramInput [i]).data.stream ();
                } else {
                    throw new IllegalArgumentException (String.format (
                        "In method `%s` parameter #%d should be annotated with @TestInputCollection",
                        method.getName (), i
                    ));
                }
            } else if (parameters [i].getType () == int.class || parameters [i].getType () == double.class) {
                if (paramInput [i] instanceof ConstantWithDescription) {
                    final var cwd = (ConstantWithDescription) paramInput [i];
                    if (cwd.sequenceSrc != null) {
                        if (paramInput [cwd.sequenceSrc] instanceof SequenceWithStatistics) {
                            final var sws = (SequenceWithStatistics <?>) paramInput [cwd.sequenceSrc];
                            if (parameters [i].getType () == int.class) {
                                input [i] = (int) cwd.getValue (sws, random);
                            } else {                                
                                input [i] = cwd.getValue (sws, random);
                            }
                        } else {
                            throw new IllegalArgumentException (String.format (
                                "In method `%s` parameter #%d depends on parameter #%d that mast be "
                                + "a collection with @TestInputCollection annotation",
                                method.getName (), i, cwd.sequenceSrc
                            ));
                        }
                    } else {
                        if (parameters [i].getType () == int.class) {
                            input [i] = (int) cwd.getValue (null, random);
                        } else {                                
                            input [i] = cwd.getValue (null, random);
                        }
                    }
                } else {
                    throw new IllegalArgumentException (String.format (
                        "In method `%s` parameter #%d should be annotated with @TestInputConstant",
                        method.getName (), i
                    ));
                }
            }
        }
        
        try {
            return method.invoke (implementation, input);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException (e);
        }
    }
    
    public int getTestsNumber () {
        return pool.size ();
    }
    
    public void runTest (int index, StreamTasksTests implementation, StreamTasksTests reference) {
        pool.get (index).runTests (implementation, reference);
    }
    
}
