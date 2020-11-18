package tests;

import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import tests.utils.Test;
import tests.utils.TestResult;

public class TestsPool {
    
    /*******************************/
    /* DO NOT TOUCH METHODS BELLOW */
    /*******************************/
    
    private final TestInvokerGenerator invokerGenerator = new TestInvokerGenerator ();
    private final TestInputGenerator inputGenerator = new TestInputGenerator ();
    private final List <TaskTests> pool;
    
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
        
        final var methods = Arrays.stream (solution.getDeclaredMethods ())
            . filter (isPublic.and (isStatic.negate ()).and (isAbs).and (isTest))
            . sorted (Comparator.comparingInt (Test.GET_ORDER).thenComparing (Method::getName))
            . collect (Collectors.toList ());
        
        for (final var method : methods) {
            //System.out.println (method); // SYSOUT
            tests.add (prepareTestsForMethod (method, random, tests));
        }
        
        System.out.printf ("%n%d tests loaded%n%n", tests.size ());
        return tests;
    }
    
    public TaskTests prepareTestsForMethod (Method method, Random random, List <TaskTests> pool) {
        return prepareTestsForMethod (method, random, pool, -1, null, false);
    }
    
    public TaskTests prepareTestsForMethod (
        Method method, Random random, List <TaskTests> pool, 
        int index, Object parameterValue, boolean forImpl
    ) {
        if (method == null || index != -1) {
            method = pool.get (index).getMethod ();
        }
        
        final var result = method.getAnnotation (TestResult.class);
        final var parameters = method.getParameters ();
        
        final var inputs = Arrays.stream (parameters).map (
            p -> inputGenerator.prepareInputDataForParameter (p, random)
        ).collect (Collectors.toList ());
        
        var maxCases = inputs.stream ().mapToInt (List::size).max ().orElse (0);
        maxCases = maxCases == 0 ? result.repeat () : Math.max (maxCases, result.repeat ());
        
        final var tests = new TaskTests (method, inputs);
        
        int customParameterIndex = -1;
        if (parameterValue != null) {
            for (int i = 0; i < parameters.length; i++) {
                if (canAssignRightToLeft (parameters [i].getType (), parameterValue.getClass ())) {
                    customParameterIndex = i;
                    break;
                }
            }
        }
        
        if (customParameterIndex == -1) {  
            for (int i = 0; i < maxCases; i++) {
                try {                    
                    final var paramInput = prepareParametersInput (inputs, parameters.length, i);
                    
                    tests.addCase (invokerGenerator.prepareInvoker (
                        method, paramInput, random, result, this, pool
                    ));
                } catch (IllegalArgumentException iae) {
                    final var j = Integer.parseInt (iae.getMessage ());
                    
                    throw new IllegalArgumentException (String.format (
                        "Parameter `%s %s` of method `%s` should have at least 1 input values",
                        parameters [j].getType (), parameters [j].getName (), method.getName ()
                    ));
                }
            }
        } else {
            final var input = random.nextInt (inputs.size ());
            final var paramInput = prepareParametersInput (inputs, parameters.length, input);
            paramInput [customParameterIndex] = parameterValue;
            
            tests.addCase (invokerGenerator.prepareSingleInvoker (
                method, paramInput, random, result, this, pool, forImpl
            ));
        }
        
        return tests;
    }
    
    private boolean canAssignRightToLeft (Class <?> a, Class <?> b) {
        return (a == int.class && b == Integer.class) || (a == double.class && b == Double.class)
            || (a.isAssignableFrom (b));
    }
    
    private Object [] prepareParametersInput (List <? extends List <?>> inputs, int parameters, int index) {
        final var paramInput = new Object [parameters];
        for (int j = 0; j < parameters; j++) {
            final var inp = inputs.get (j);
            if (inp.isEmpty ()) {
                throw new IllegalArgumentException (String.valueOf (j));
            }
            
            paramInput [j] = inp.get (index % inp.size ());
        }
        
        return paramInput;
    }
    
    public int getTestsNumber () {
        return pool.size ();
    }
    
    public InvocationResult runTest (int index, StreamTasksTests implementation, StreamTasksTests reference) {
        return pool.get (index).runTests (implementation, reference);
    }
    
}
