package tests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import tests.inputs.ConstantValueProvider;
import tests.inputs.ConsumerGenerator;
import tests.inputs.SequenceWithStatistics;
import tests.utils.TestInputCollection;
import tests.utils.TestInputConstant;
import tests.utils.TestInputConsumer;
import tests.utils.TestInputFunction;
import tests.utils.TestInputPredicate;
import tests.utils.TestInputSupplier;
import tests.utils.TestResult;

public class TestInvokerGenerator {
    
    public BiFunction <StreamTasksTests, StreamTasksTests, InvocationResult> prepareInvoker (
        Method method, Object [] paramInput, Random random, TestResult result, 
        TestsPool pool, List <TaskTests> prepared
    ) {
        return (implementation, reference) -> {
            final var seed = random.nextLong ();
            
            final var customBuffer = new ByteArrayOutputStream ();
            System.setOut (new PrintStream (customBuffer, true, StandardCharsets.UTF_8));
            
            final var resultImpl = prepareSingleInvoker (
                method, paramInput, new Random (seed), result, pool, prepared, true
            ).apply (implementation, reference);
            
            final var output = new String (customBuffer.toByteArray (), StandardCharsets.UTF_8);
            resultImpl.addOutput (output);
            
            final var resultRef = prepareSingleInvoker (
                method, paramInput, new Random (seed), result, pool, prepared, false
            ).apply (implementation, reference);
            
            if (result != null) {                
                final var wrappedRef = wrapResult (result.wrap (), resultRef.result);
                if (method.getReturnType () != Void.class && method.getReturnType () != void.class
                        && (resultImpl.result != null && wrappedRef != null)) {
                    compareAnswers (resultImpl.result, wrappedRef, result.parallel ());
                }
            }
            
            final var consumersImpt = resultImpl.getConsumers ();
            final var consumersRef = resultRef.getConsumers ();
            
            assert consumersImpt.size () == consumersRef.size () : String.format (
                "Number of consumer values are different: %d and %d",
                consumersImpt.size (), consumersRef.size ()
            );
            
            for (int i = 0; i < consumersRef.size (); i++) {
                compareConsumerAnswers (i, consumersImpt.get (i), consumersRef.get (i));
            }
            
            return resultImpl;
        };
    }
    
    public BiFunction <StreamTasksTests, StreamTasksTests, InvocationResult> prepareSingleInvoker (
        Method method, Object [] paramInput, Random random, TestResult result, 
        TestsPool pool, List <TaskTests> prepared, boolean forImplementation
    ) {
        return (implementation, reference) -> {
            final var instance = forImplementation ? implementation : reference;
            final var seed = random.nextLong ();
            
            if (result == null || result.checkBy () == -1) {                
                return prepareAndInvokeImplementation (instance, method, paramInput, seed);
            } else {
                final var value = prepareAndInvokeImplementation (instance, method, paramInput, seed);
                
                if (value.result == null) {
                    throw new IllegalArgumentException ("You implementation should not return NULL as answer");
                }
                
                return pool.prepareTestsForMethod (
                    null, random, prepared, result.checkBy (), value.result, forImplementation
                ).runTests (implementation, reference).addAnotherResult (value);
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
            } else if (result instanceof IntStream) {
                resultWrapRef = ((IntStream) result).mapToObj (i -> i).collect (Collectors.toList ());
            } else {
                resultWrapRef = List.of (result);
            }
        } else if (wrapper == Set.class) {
            if (result instanceof Collection) {                    
                resultWrapRef = Set.copyOf ((Collection <?>) result);
            } else if (result instanceof Stream) {
                resultWrapRef = ((Stream <?>) result).collect (Collectors.toSet ());
            } else if (result instanceof IntStream) {
                resultWrapRef = ((IntStream) result).mapToObj (i -> i).collect (Collectors.toSet ());
            } else {
                resultWrapRef = Set.of (result);
            }
        }
        
        return resultWrapRef;
    }
    
    private final Set <Class <?>> acceptablePrimitives = Set.of (
        int.class, double.class, Integer.class, Double.class
    );
    
    @SuppressWarnings ("unchecked")
    private InvocationResult prepareAndInvokeImplementation (
        StreamTasksTests implementation, Method method, Object [] paramInput, long randomSeed
    ) {
        Object [] input = new Object [paramInput.length];
        final var consumersValues = new ArrayList <> ();
        final var parameters = method.getParameters ();
        final var random = new Random (randomSeed);
        
        for (int i = 0; i < parameters.length; i++) {
            final var isIntStream = parameters [i].getType () == IntStream.class;
            
            if (parameters [i].getType () == List.class) {
                if (paramInput [i] instanceof SequenceWithStatistics) {
                    final var data = ((SequenceWithStatistics <?>) paramInput [i]).data;
                    if (data instanceof List) {
                        input [i] = data;
                    } else if (data instanceof Collection) {
                        input [i] = List.copyOf ((Collection <?>) data);
                    } else {
                        requestCorrectType (method, i, List.class, data.getClass ());
                    }
                } else if (paramInput [i] instanceof List) {
                    input [i] = paramInput [i];
                } else if (paramInput [i] instanceof Collection) {
                    input [i] = List.copyOf ((Collection <?>) paramInput [i]);
                } else {
                    requestAnnotation (method, i, TestInputCollection.class);
                }
            } else if (parameters [i].getType () == Set.class) {
                if (paramInput [i] instanceof SequenceWithStatistics) {
                    final var data = ((SequenceWithStatistics <?>) paramInput [i]).data;
                    if (data instanceof Set) {
                        input [i] = data;
                    } else if (data instanceof Collection) {
                        input [i] = Set.copyOf ((Collection <?>) data);
                    } else {
                        requestCorrectType (method, i, Set.class, data.getClass ());
                    }
                } else {
                    requestAnnotation (method, i, TestInputCollection.class);
                }
            } else if (parameters [i].getType () == Map.class) {
                if (paramInput [i] instanceof SequenceWithStatistics) {
                    final var data = ((SequenceWithStatistics <?>) paramInput [i]).data;
                    if (data instanceof Map) {
                        input [i] = data;
                    } else {
                        requestCorrectType (method, i, Map.class, data.getClass ());
                    }
                } else {
                    requestAnnotation (method, i, TestInputCollection.class);
                }
            } else if (parameters [i].getType () == Stream.class || isIntStream) {
                if (paramInput [i] instanceof SequenceWithStatistics) {
                    final var sws = (SequenceWithStatistics <List <?>>) paramInput [i];
                    final var stream = sws.isParallelStream () ? sws.data.parallelStream () : sws.data.stream ();
                    if (isIntStream) {
                        input [i] = stream.mapToInt (num -> (Integer) num);
                    } else {
                        input [i] = stream;
                    }
                } else if (paramInput [i] instanceof Stream) {
                    input [i] = paramInput [i];
                } else {
                    requestAnnotation (method, i, TestInputCollection.class);
                }
            } else if (parameters [i].getType () == Supplier.class) {
                if (paramInput [i] instanceof Function) {
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
            } else if (parameters [i].getType () == Function.class) {
                if (paramInput [i] instanceof Function) {
                    input [i] = paramInput [i];
                } else {
                    requestAnnotation (method, i, TestInputFunction.class);
                }
            } else if (parameters [i].getType () == Consumer.class) {
                if (paramInput [i] instanceof ConsumerGenerator) {
                    final var consumerNresult = ((ConsumerGenerator <?>) paramInput [i]).get ();
                    consumersValues.add (consumerNresult.S);
                    input [i] = consumerNresult.F;
                } else {
                    requestAnnotation (method, i, TestInputConsumer.class);
                }
            } else if (parameters [i].getType () == int.class || parameters [i].getType () == double.class) {
                if (paramInput [i] instanceof ConstantValueProvider) {
                    final var cwd = (ConstantValueProvider) paramInput [i];
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
            final var start = System.currentTimeMillis ();
            final var result = method.invoke (implementation, input);
            final var runtime = System.currentTimeMillis () - start;
            
            return new InvocationResult (result, runtime, consumersValues);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ite) {
            if (ite.getCause () instanceof UnsupportedOperationException) {
                throw (UnsupportedOperationException) ite.getCause ();
            } else if (ite.getCause () instanceof AssertionError) {
                throw (AssertionError) ite.getCause ();
            }
            
            throw new AssertionError ("Failed to check results");
        }
    }
    
    private void requestAnnotation (Method method, int parameterIndex, Class <? extends Annotation> annotation) {
        throw new IllegalArgumentException (String.format (
            "In method `%s` parameter #%d should be annotated with @%s",
            method.getName (), parameterIndex, annotation.getSimpleName ()
        ));
    }
    
    private void requestCorrectType (Method method, int parameterIndex, Class <?> expected, Class <?> actual) {
        throw new IllegalArgumentException (String.format (
            "In method `%s` parameter #%d has type `%s` and can't be assigned from type `%s`",
            method.getName (), parameterIndex, expected.getSimpleName (), actual.getSimpleName ()
        ));
    }
    
    private void compareAnswers (Object implementation, Object reference, boolean parallel) {
        final var iType = implementation instanceof IntStream ? IntStream.class
                        : implementation instanceof Stream ? Stream.class
                        : implementation instanceof Integer ? Integer.class
                        : implementation instanceof Double ? Double.class
                        : implementation instanceof List ? List.class
                        : implementation instanceof Map ? Map.class 
                        : implementation instanceof Set ? Set.class : null;
        final var rType = reference instanceof IntStream ? IntStream.class
                        : reference instanceof Stream ? Stream.class
                        : reference instanceof Integer ? Integer.class
                        : reference instanceof Double ? Double.class
                        : reference instanceof List ? List.class
                        : reference instanceof Map ? Map.class
                        : reference instanceof Set ? Set.class : null;
        if (iType == null || rType == null) {
            throw new IllegalArgumentException (String.format (
                "Can't detect assertion types for values: `%s` and `%s`%n", 
                implementation, reference
            ));
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
    
    private void compareConsumerAnswers (int index, Object implementation, Object reference) {
        assert implementation.getClass () == reference.getClass () : String.format (
            "Consumer values should have same type: %s and %s (comparing pair #d)",
            implementation.getClass (), reference.getClass (), index
        );
        
        if (reference instanceof List) {
            compareAnswers (implementation, reference, false);
        } else if (reference instanceof AtomicInteger) {
            final var sumImpl = ((AtomicInteger) implementation).get ();
            final var sumRef = ((AtomicInteger) reference).get ();
            
            compareAnswers (sumImpl, sumRef, false);
        } else if (reference instanceof StringJoiner) {
            final var strImpl = ((StringJoiner) implementation).toString ();
            final var strRef = ((StringJoiner) reference).toString ();
            
            assert Objects.equals (strImpl, strRef) : String.format (
                "String `%s` was expected (actual given: %s)",
                strImpl, strRef
            );
        }
    }
    
}
