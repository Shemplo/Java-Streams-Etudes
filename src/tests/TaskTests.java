package tests;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TaskTests {
    
    /*******************************/
    /* DO NOT TOUCH METHODS BELLOW */
    /*******************************/
    
    private final List <BiConsumer <StreamTasksTests, StreamTasksTests>> cases = new ArrayList <> ();
    
    public TaskTests addCase (BiConsumer <StreamTasksTests, StreamTasksTests> caze) {
        cases.add (caze);
        return this;
    }
    
    public int getCasesNumber () {
        return cases.size ();
    }
    
    public void runTests (StreamTasksTests implementation, StreamTasksTests reference) {
        cases.forEach (caze -> caze.accept (implementation, reference));
    }
    
    @SuppressWarnings ("unused")
    private static <A, E> void findAndRunAssertionMethod (A actual, E expected, Boolean parallel) throws AssertionError {
        final var aType = actual instanceof IntStream ? IntStream.class 
                        : actual instanceof Stream ? Stream.class 
                        : actual instanceof Integer ? Integer.class 
                        : actual instanceof List ? List.class 
                        : actual instanceof Map ? Map.class
                        : actual instanceof Set ? Set.class
                        : null;
        final var eType = expected instanceof IntStream ? IntStream.class 
                        : expected instanceof Stream ? Stream.class 
                        : expected instanceof Integer ? Integer.class 
                        : expected instanceof List ? List.class 
                        : expected instanceof Map ? Map.class
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
