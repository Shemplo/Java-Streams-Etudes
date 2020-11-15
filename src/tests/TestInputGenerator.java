package tests;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import tests.inputs.ConstantValueProvider;
import tests.inputs.ConsumerGenerator;
import tests.inputs.SequenceWithStatistics;
import tests.inputs.SupplierMode;
import tests.presets.DataPreset;
import tests.utils.TestInputCollection;
import tests.utils.TestInputConstant;
import tests.utils.TestInputConsumer;
import tests.utils.TestInputFunction;
import tests.utils.TestInputPredicate;
import tests.utils.TestInputSupplier;

public class TestInputGenerator {
    
    private final Map <Class <? extends DataPreset <?>>, DataPreset <?>> presets = new HashMap <> ();
    
    private final List <Function <?, ?>> functions = List.of (
        (String s) -> s.concat (" Solk"),
        (String s) -> Integer.parseInt (s)
    );
    
    private final List <Predicate <?>> predicates = List.of (
        (String s) -> s.toLowerCase ().contains ("a")
    );
    
    public List <?> prepareInputDataForParameter (Parameter parameter, Random random) {
        if (parameter.isAnnotationPresent (TestInputCollection.class)) {
            final var annotation = parameter.getAnnotation (TestInputCollection.class);
            return prepareCollectionInputForParameter (parameter, annotation, random);
        } else if (parameter.isAnnotationPresent (TestInputConstant.class)) {
            final var annotation = parameter.getAnnotation (TestInputConstant.class);
            return prepareConstantInputForParameter (parameter, annotation, random);
        } else if (parameter.isAnnotationPresent (TestInputSupplier.class)) {
            final var annotation = parameter.getAnnotation (TestInputSupplier.class);
            return prepareSupplierInputForParameter (parameter, annotation, random);
        } else if (parameter.isAnnotationPresent (TestInputPredicate.class)) {
            final var annotation = parameter.getAnnotation (TestInputPredicate.class);
            return preparePredicateInputForParameter (parameter, annotation, random);
        } else if (parameter.isAnnotationPresent (TestInputFunction.class)) {
            final var annotation = parameter.getAnnotation (TestInputFunction.class);
            return prepareFunctionInputForParameter (parameter, annotation, random);
        } else if (parameter.isAnnotationPresent (TestInputConsumer.class)) {
            final var annotation = parameter.getAnnotation (TestInputConsumer.class);
            return prepareConsumerInputForParameter (parameter, annotation, random);
        }
        
        return List.of ();
    }
    
    private List <SequenceWithStatistics <?>> prepareCollectionInputForParameter (
        Parameter parameter, TestInputCollection annotation, Random random
    ) {
        final var inputsCollector = new ArrayList <SequenceWithStatistics <?>> ();
        final var parallel = annotation.parallel ();
        
        for (final var presetType : annotation.presets ()) {
            final var preset = getPreset (presetType, random);
            
            final var varation = annotation.variation () + 1;
            final var unique = annotation.allUnique ();
            
            for (final var constantInput : annotation.constant ()) {
                final var length = constantInput + random.nextInt (varation);
                inputsCollector.add (preset.getRandomSequence (length, random, unique)
                    .setParallelStream (parallel));
            }
            
            for (final var percentageInput : annotation.percentage ()) {
                final var percent = percentageInput + random.nextInt (varation) / 100.0;
                final var length = (int) Math.round (percent * preset.getSize ());
                inputsCollector.add (preset.getRandomSequence (length, random, unique)
                    .setParallelStream (parallel));
            }
        }
        
        return inputsCollector;
    }
    
    private List <ConstantValueProvider> prepareConstantInputForParameter (
        Parameter parameter, TestInputConstant annotation, Random random
    ) {
        final var inputCollector = new ArrayList <ConstantValueProvider> ();
        final var variation = annotation.variation ();
        
        for (final var constantInput : annotation.constant ()) {
            inputCollector.add (new ConstantValueProvider (constantInput, variation));
        }
        
        final var sparam = annotation.parameter ();
        for (final var sequenceInput : annotation.sequence ()) {
            inputCollector.add (new ConstantValueProvider (sequenceInput, sparam, variation));
        }
        
        return inputCollector;
    }
    
    private List <Function <Random, Supplier <?>>> prepareSupplierInputForParameter (
        Parameter parameter, TestInputSupplier annotation, Random random
    ) {
        final var inputCollector = new ArrayList <Function <Random, Supplier <?>>> ();
        
        for (final var presetType : annotation.presets ()) {
            final var preset = getPreset (presetType, random);
            
            for (final var cycle : annotation.cycles ()) {
                if (annotation.mode () == SupplierMode.SEQUENTIAL) {
                    inputCollector.add (R -> {
                        final var sequence = cycle == -1 ? preset.getData () 
                                : preset.getRandomSequence (cycle, R, false).data;
                        final var counter = new AtomicInteger ();
                        
                        return () -> sequence.get (counter.getAndUpdate (
                            v -> (v + 1) % sequence.size ()
                        ));
                    });
                } else if (annotation.mode () == SupplierMode.SHUFFLED_SEQUENTIAL) {
                    inputCollector.add (R -> {
                        final var rawSequence = cycle == -1 ? preset.getData () 
                            : preset.getRandomSequence (cycle, R, false).data;                        
                        final var sequence = new ArrayList <> (rawSequence);
                        final var counter = new AtomicInteger ();
                        Collections.shuffle (sequence, R);
                        
                        return () -> sequence.get (counter.getAndUpdate (
                            v -> (v + 1) % sequence.size ()
                        ));
                    });
                } else if (annotation.mode () == SupplierMode.RANDOM) {
                    final var data = preset.getData ();
                    inputCollector.add (R -> () -> data.get (R.nextInt (data.size ())));
                }
            }
        }
        
        return inputCollector;
    }
    
    private List <Predicate <?>> preparePredicateInputForParameter (
        Parameter parameter, TestInputPredicate annotation, Random random
    ) {
        final var inputCollector = new ArrayList <Predicate <?>> ();
        for (final var index : annotation.indices ()) {
            inputCollector.add (predicates.get (index));
        }
        
        return inputCollector;
    }
    
    private List <Function <?, ?>> prepareFunctionInputForParameter (
        Parameter parameter, TestInputFunction annotation, Random random
    ) {
        final var inputCollector = new ArrayList <Function <?, ?>> ();
        for (final var index : annotation.indices ()) {
            inputCollector.add (functions.get (index));
        }
        
        return inputCollector;
    }
    
    private List <ConsumerGenerator <?>> prepareConsumerInputForParameter (
        Parameter parameter, TestInputConsumer annotation, Random random
    ) {
        return List.of (new ConsumerGenerator <> (annotation.mode ()));
    }
    
    private DataPreset <?> getPreset (Class <? extends DataPreset <?>> presetType, Random random) {
        return presets.computeIfAbsent (presetType, type -> {
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
    }
    
}
