package tests;

import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import tests.presets.TestInputGenerator;
import tests.presets.TestInvokerGenerator;
import tests.utils.Test;
import tests.utils.TestResult;

public class TestsPool {
    
    /*******************************/
    /* DO NOT TOUCH METHODS BELLOW */
    /*******************************/
    
    private final TestInvokerGenerator invokerGenerator = new TestInvokerGenerator ();
    private final TestInputGenerator inputGenerator = new TestInputGenerator ();
    private final List <TaskTests> pool;
    
    @SuppressWarnings ("unused")
    private final List <Function <?, ?>> functions = List.of (
        (String s) -> s.concat ("Solk")
    );
    
    public TestsPool (Class <?> solutions) {
        pool = loadTests (solutions);
    }
    
    private List <TaskTests> loadTests (Class <?> solution) {
        System.out.println ("Loading tests...");
        final var tests = new ArrayList <TaskTests> ();
        final var random = new Random (1L);
        
        final Predicate <Executable> isPublic = e -> Modifier.isPublic (e.getModifiers ());
        final Predicate <Executable> isStatic = e -> Modifier.isStatic (e.getModifiers ());
        final Predicate <Executable> isAbs = e -> Modifier.isAbstract (e.getModifiers ());
        final Predicate <Executable> isTest = e -> e.isAnnotationPresent (Test.class);
        
        Arrays.stream (solution.getDeclaredMethods ())
            . filter (isPublic.and (isStatic.negate ()).and (isAbs).and (isTest))
            . sorted (Comparator.comparingInt (Test.GET_ORDER))
            . map (method -> prepareTestsForMethod (method, random))
            . forEach (tests::add);
        
        System.out.printf ("%n%d tests loaded%n%n", tests.size ());
        return tests;
    }
    
    private TaskTests prepareTestsForMethod (Method method, Random random) {
        final var tests = new TaskTests ();
        
        final var result = method.getAnnotation (TestResult.class);
        final var parameters = method.getParameters ();
        
        final var inputs = Arrays.stream (parameters)
            . map (p -> inputGenerator.prepareInputDataForParameter (p, random))
            . collect (Collectors.toList ());
        final var maxCases = inputs.stream ()
            . mapToInt (List::size).max ().orElse (0);
        
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
            
            tests.addCase (invokerGenerator.prepareInvoker (method, paramInput, random, result));
        }
        
        return tests;
    }
    
    public int getTestsNumber () {
        return pool.size ();
    }
    
    public void runTest (int index, StreamTasksTests implementation, StreamTasksTests reference) {
        pool.get (index).runTests (implementation, reference);
    }
    
}
