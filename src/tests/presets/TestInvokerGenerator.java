package tests.presets;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import tests.OutputAssertions;
import tests.StreamTasksTests;
import tests.utils.TestResult;

public class TestInvokerGenerator {
    
    public BiConsumer <StreamTasksTests, StreamTasksTests> prepareInvoker (
        Method method, Object [] paramInput, Random random, TestResult result
    ) {
        return (implementation, reference) -> {
            final var seed = random.nextLong ();
            
            final var resultImpl = prepareAndInvokeImplementation (implementation, method, paramInput, seed);
            final var resultRef = prepareAndInvokeImplementation (reference, method, paramInput, seed);
            compareAnswers (resultImpl, wrapResult (result.wrap (), resultRef), result.parallel ());
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
                    final var sws = (SequenceWithStatistics <?>) paramInput [i];
                    input [i] = sws.isParallelStream () ? sws.data.parallelStream () : sws.data.stream ();
                } else {
                    throw new IllegalArgumentException (String.format (
                        "In method `%s` parameter #%d should be annotated with @TestInputCollection",
                        method.getName (), i
                    ));
                }
            } else if (parameters [i].getType () == Supplier.class) {
                if (paramInput [i] instanceof Function) {
                    @SuppressWarnings ("unchecked")
                    final var supplier = (Function <Random, Supplier <?>>) paramInput [i];
                    input [i] = supplier.apply (random);
                } else {
                    throw new IllegalArgumentException (String.format (
                        "In method `%s` parameter #%d should be annotated with @TestInputSupplier",
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
