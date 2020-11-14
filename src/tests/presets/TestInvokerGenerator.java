package tests.presets;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import tests.OutputAssertions;
import tests.StreamTasksTests;
import tests.TaskTests;
import tests.TestsPool;
import tests.utils.TestInputCollection;
import tests.utils.TestInputConstant;
import tests.utils.TestInputPredicate;
import tests.utils.TestInputSupplier;
import tests.utils.TestResult;

public class TestInvokerGenerator {
    
    public BiFunction <StreamTasksTests, StreamTasksTests, Object> prepareInvoker (
        Method method, Object [] paramInput, Random random, TestResult result, 
        TestsPool pool, List <TaskTests> prepared
    ) {
        return (implementation, reference) -> {
            // final var seed = random.nextLong ();
            //final var resultImpl = prepareAndInvokeImplementation (implementation, method, paramInput, seed);
            //final var resultRef = prepareAndInvokeImplementation (reference, method, paramInput, seed);
            //compareAnswers (resultImpl, wrapResult (result.wrap (), resultRef), result.parallel ());
            
            final var seed = random.nextLong ();
            
            final var resultImpl = prepareSingleInvoker (
                method, paramInput, new Random (seed), result, pool, prepared, true
            ).apply (implementation, reference);
            
            final var resultRef = prepareSingleInvoker (
                method, paramInput, new Random (seed), result, pool, prepared, false
            ).apply (implementation, reference);
            
            final var wrappedRef = wrapResult (result.wrap (), resultRef);
            compareAnswers (resultImpl, wrappedRef, result.parallel ());
            return null;
        };
    }
    
    public BiFunction <StreamTasksTests, StreamTasksTests, Object> prepareSingleInvoker (
        Method method, Object [] paramInput, Random random, TestResult result, 
        TestsPool pool, List <TaskTests> prepared, boolean forImplementation
    ) {
        return (implementation, reference) -> {
            final var seed = random.nextLong ();
            
            if (result.checkBy () == -1) {                
                return prepareAndInvokeImplementation (reference, method, paramInput, seed);
            } else {
                final var instance = forImplementation ? implementation : reference;
                final var value = prepareAndInvokeImplementation (
                    instance, method, paramInput, seed
                );
                
                if (value == null) {
                    throw new IllegalArgumentException ("You implementation should not return NULL as answer");
                }
                
                return pool.prepareTestsForMethod (null, random, prepared, result.checkBy (), value, forImplementation)
                     . runTests (implementation, reference);
            }
        };
    }
    
    private Object wrapResult (Class <?> wrapper, Object result) {
        var resultWrapRef = result;
        
        if (wrapper == List.class) {
            if (result instanceof Collection) {                    
                resultWrapRef = List.copyOf ((Collection <?>) result);
            } else if (result instanceof Stream) {
                resultWrapRef = ((Stream <?>) result).collect (Collectors.toList ());
            } else {
                resultWrapRef = List.of (result);
            }
        } else if (wrapper == Set.class) {
            if (result instanceof Collection) {                    
                resultWrapRef = Set.copyOf ((Collection <?>) result);
            } else if (result instanceof Stream) {
                resultWrapRef = ((Stream <?>) result).collect (Collectors.toSet ());
            } else {
                resultWrapRef = Set.of (result);
            }
        }
        
        return resultWrapRef;
    }
    
    private final Set <Class <?>> acceptablePrimitives = Set.of (
        int.class, double.class, Integer.class, Double.class
    );
    
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
                    requestAnnotation (method, i, TestInputCollection.class);
                }
            } else if (parameters [i].getType () == Set.class) {
                if (paramInput [i] instanceof SequenceWithStatistics) {
                    input [i] = Set.copyOf (((SequenceWithStatistics <?>) paramInput [i]).data);
                } else {
                    requestAnnotation (method, i, TestInputCollection.class);
                }
            } else if (parameters [i].getType () == Stream.class) {
                if (paramInput [i] instanceof SequenceWithStatistics) {
                    final var sws = (SequenceWithStatistics <?>) paramInput [i];
                    input [i] = sws.isParallelStream () ? sws.data.parallelStream () : sws.data.stream ();
                } else {
                    requestAnnotation (method, i, TestInputCollection.class);
                }
            } else if (parameters [i].getType () == Supplier.class) {
                if (paramInput [i] instanceof Function) {
                    @SuppressWarnings ("unchecked")
                    final var supplier = (Function <Random, Supplier <?>>) paramInput [i];
                    input [i] = supplier.apply (random);
                } else {
                    requestAnnotation (method, i, TestInputSupplier.class);
                }
            } else if (parameters [i].getType () == Predicate.class) {
                if (paramInput [i] instanceof Predicate) {
                    input [i] = paramInput [i];
                } else {
                    requestAnnotation (method, i, TestInputPredicate.class);
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
                } else if (acceptablePrimitives.contains (paramInput [i].getClass ())) {
                    input [i] = paramInput [i];
                } else {
                    requestAnnotation (method, i, TestInputConstant.class);
                }
            }
        }
        
        try {
            return method.invoke (implementation, input);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException (e);
        }
    }
    
    private void requestAnnotation (Method method, int parameterIndex, Class <? extends Annotation> annotation) {
        throw new IllegalArgumentException (String.format (
            "In method `%s` parameter #%d should be annotated with @%s",
            method.getName (), parameterIndex, annotation.getSimpleName ()
        ));
    }
    
    private void compareAnswers (Object implementation, Object reference, boolean parallel) {
        final var iType = implementation instanceof IntStream ? IntStream.class
                        : implementation instanceof Stream ? Stream.class
                        : implementation instanceof Integer ? Integer.class
                        : implementation instanceof List ? List.class
                        : implementation instanceof Map ? Map.class 
                        : implementation instanceof Set ? Set.class : null;
        final var rType = reference instanceof IntStream ? IntStream.class
                        : reference instanceof Stream ? Stream.class
                        : reference instanceof Integer ? Integer.class
                        : reference instanceof List ? List.class
                        : reference instanceof Map ? Map.class
                        : reference instanceof Set ? Set.class : null;
        if (iType == null || rType == null) {
            System.err.println (implementation + " | " + reference);
        }
        
        try {
            if (iType == IntStream.class || iType == Stream.class) {
                OutputAssertions.class.getMethod ("assertOutput", iType, boolean.class, rType)
                    .invoke (null, implementation, parallel, reference);
            } else {
                OutputAssertions.class.getMethod ("assertOutput", iType, rType)
                    .invoke (null, implementation, reference);
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException re) {
            re.printStackTrace ();
            throw new IllegalStateException ("Failed to check results");
        } catch (InvocationTargetException ite) {
            if (ite.getCause () instanceof AssertionError) {
                throw (AssertionError) ite.getCause ();
            }
            
            throw new AssertionError ("Failed to check results");
        }
    }
    
}
