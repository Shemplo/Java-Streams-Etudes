package tests;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import tasks.StreamTasks;

public class TaskTests <R, O> {
    
    /*******************************/
    /* DO NOT TOUCH METHODS BELLOW */
    /*******************************/
    
    private final List <BiConsumer <StreamTasks, BiConsumer <R, O>>> cases = new ArrayList <> ();
    private final List <Boolean> parallel = new ArrayList <> ();
    
    public int getCasesNumber () {
        return cases.size ();
    }
    
    public TaskTests <R, O> add (BiConsumer <StreamTasks, BiConsumer <R, O>> testCase, boolean parallel) {
        this.parallel.add (parallel);
        cases.add (testCase);
        return this;
    }
    
    public TaskTests <R, O> add (BiConsumer <StreamTasks, BiConsumer <R, O>> testCase) {
        return add (testCase, false);
    }
    
    public void runCase (int index, StreamTasks implementation) throws AssertionError {
        cases.get (index).accept (implementation, (output, expected) -> {  
            findAndRunAssertionMethod (output, expected, parallel.get (index));
        });
    }
    
    private static <A, E> void findAndRunAssertionMethod (A actual, E expected, Boolean parallel) throws AssertionError {
        final var aType = actual instanceof IntStream ? IntStream.class 
                        : actual instanceof Stream ? Stream.class 
                        : actual instanceof Integer ? Integer.class 
                        : actual instanceof List ? List.class 
                        : actual instanceof Set ? Set.class
                        : null;
        final var eType = expected instanceof IntStream ? IntStream.class 
                        : expected instanceof Stream ? Stream.class 
                        : expected instanceof Integer ? Integer.class 
                        : expected instanceof List ? List.class 
                        : expected instanceof Set ? Set.class
                        : null;
        try {
            if (aType == IntStream.class || aType == Stream.class) {
                OutputAssertions.class.getMethod (
                    "assertOutput", aType, boolean.class, eType
                ).invoke (null, actual, parallel, expected);
            } else {                
                OutputAssertions.class.getMethod ("assertOutput", aType, eType).invoke (null, actual, expected);
            }
        } catch (
            NoSuchMethodException | SecurityException | IllegalAccessException re
        ) {
            re.printStackTrace ();
            throw new AssertionError ("Failed to check results");
        } catch (InvocationTargetException ite) {
            if (ite.getCause () instanceof AssertionError) {
                throw (AssertionError) ite.getCause ();
            }
            
            throw new AssertionError ("Failed to check results");
        }
    }
    
}
