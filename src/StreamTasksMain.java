import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTasksMain {
    
    /*******************************/
    /* DO NOT TOUCH METHODS BELLOW */
    /*******************************/
    
    public static void main (String ... args) {
        try { 
            runTasks (new StreamTasks ()); 
            System.out.println ("All tests completed!");
        } catch (UnsupportedOperationException uoe) {
            System.out.printf ("Method `%s` is not implemened%n", uoe.getMessage ());
        } catch (AssertionError ae) {
            System.out.println ("Wrong answer given");
            System.out.println (ae.getMessage ());
        } catch (NullPointerException npe) {
            System.out.println ("Looks like you missed to check for NULL something");
            Optional.ofNullable (npe.getMessage ()).ifPresent (message -> {                
                System.out.println ("Message: " + message);
            });
        } catch (Exception e) {
            System.out.println ("You made something wrong");
            e.printStackTrace ();
        }
    }
    
    private static void runTasks (StreamTasks imp) throws UnsupportedOperationException {
        final var uniqueNames = List.of ("Andrey", "Boris", "Clavdij", "David", "Efim", "Feofan");
        assertOutput ("Task 1", imp.task1 (uniqueNames), uniqueNames);
        
        final var nonUniqueNames = List.of (
            "Andrey", "Boris", "Boris", "Clavdij", "David", "Efim", "Efim", "Efim", "Feofan", 
            "Efim", "Clavdij", "Andrey", "David", "David"
        );
        assertOutput ("Task 2", imp.task2 (nonUniqueNames), Set.copyOf (uniqueNames));
        assertOutput ("Task 3", imp.task3 (nonUniqueNames), uniqueNames.size ());
        
        assertOutput ("Task 4", imp.task4 (), Set.of (0, 1, 2, 3, 4, 5, 6, 7));
        assertOutput ("Task 5", imp.task5 (uniqueNames), false, Set.copyOf (uniqueNames));
        assertOutput ("Task 6", imp.task6 (uniqueNames), true, Set.copyOf (uniqueNames));
        
        final var nonUniqueNamesWithNulls = Arrays.asList (
            "Andrey", "Boris", null, "Boris", null, null, "Clavdij", "David", null, "Efim", 
            "Efim", null, null, "Efim", null, null, null, "Feofan", "Efim", "Clavdij", 
            "Andrey", "David", null, "David"
        );
        assertOutput ("Task 7", imp.task7 (nonUniqueNamesWithNulls), false, nonUniqueNames);
        
        final var uniqueNamesWithLastNames = List.of (
            "Andrey Smith", "Boris Smith", "Clavdij Smith", "David Smith", "Efim Smith", 
            "Feofan Smith"
        );
        assertOutput ("Task 8", imp.task8 (uniqueNames), false, uniqueNamesWithLastNames);
        
        final var nonUniqueNamesWithLastNames = List.of (
            "Andrey Smith", "Boris Smith", "Boris Smith", "Clavdij Smith", "David Smith", 
            "Efim Smith", "Efim Smith", "Efim Smith", "Feofan Smith", "Efim Smith", 
            "Clavdij Smith", "Andrey Smith", "David Smith", "David Smith"
        );
        assertOutput ("Task 9", imp.task9 (nonUniqueNamesWithNulls), false, nonUniqueNamesWithLastNames);
        
        final var shuffledNames = new ArrayList <> (uniqueNames);
        Collections.shuffle (shuffledNames);
        assertOutput ("Task 10", imp.task10 (shuffledNames), false, uniqueNames);
        
        assertOutput ("Task 11", imp.task11 (uniqueNames, s -> s.concat (" Smith")), false, uniqueNamesWithLastNames);
        assertOutput ("Task 12", imp.task12 (nonUniqueNames), false, uniqueNames);
        
        final var stringNumbers = List.of ("32", "542", "13", "-70");
        final var intNumbers = List.of (32, 542, 13, -70);
        assertOutput ("Task 13", imp.task13 (stringNumbers), false, intNumbers);
        assertOutput ("Task 14", imp.task14 (stringNumbers), false, List.of (-70, 13, 32, 542));
        
        assertOutput ("Task 15", imp.task15 (stringNumbers), false, intNumbers);
        assertOutput ("Task 16", imp.task16 (stringNumbers), 32 + 542 + 13 + -70);
        
        assertOutput ("Task 17", imp.task17 (), false, Stream.iterate (0, i -> i + 1).limit (9933).collect (Collectors.toList ()));
        assertOutput ("Task 18", imp.task18 (uniqueNames, 4), false, uniqueNames.subList (0, 4));
        assertOutput ("Task 19", imp.task19 (uniqueNames, 5), false, List.of ("Boris", "David"));
        
        assertOutput ("Task 20", imp.task20 (
            nonUniqueNamesWithNulls, (a, b) -> Integer.compare (a.length (), b.length ()
        )), false, List.of (
            "Efim", "Efim", "Efim", "Efim", "Boris", "Boris", "David", "David", "David", 
            "Andrey", "Feofan", "Andrey", "Clavdij", "Clavdij"
        ));
        
        assertOutput ("Task 21", imp.task21 (List.of ("A", "B", "C"), List.of ("D", "E", "X")), false, 
                Set.of ("A", "B", "C", "D", "E", "X"));
        
        final var name2value = new HashMap <> (Map.of ("Andrey", 43, "Boris", 12, "David", -9, "Efim", 78, "Feofan", 93));
        assertOutput ("Task 22", imp.task22 (uniqueNames, name2value), false, Arrays.asList (43, 12, null, -9, 78, 93));
        assertOutput ("Task 23", imp.task23 (uniqueNames, name2value), 43 + 12 + -9 + 78 + 93);
        
        final var randomSequence = List.of (0, 2, 1, 4, 5, 3, 6, 6, 8, 5, 1, 0, 0);
        assertOutput ("Task 24", imp.task24 (randomSequence.subList (0, 9), 3, 6), false, List.of (4, 5, 3, 6));
        assertOutput ("Task 25", imp.task25 (randomSequence, 3, 6, 9, 20), false, List.of (4, 5, 3, 6, 5, 1, 0, 0));
        
        assertOutput ("Task 26", imp.task26 (uniqueNames.stream (), uniqueNames.get (4)) ? 1 : 0, 1);
        assertOutput ("Task 26", imp.task26 (uniqueNames.stream (), "Xeon") ? 1 : 0, 0);
        
        assertOutput ("Task 27", imp.task27 (nonUniqueNamesWithNulls.stream (), uniqueNames.get (4)), 14);
        assertOutput ("Task 27", imp.task27 (nonUniqueNamesWithNulls.stream (), "Xeon"), 0);
        
        assertOutput ("Task 28", imp.task28 (uniqueNames, List.of ("Barlow", "Sill", "Matheu", "Throk")), false, List.of (
            "Andrey Barlow", "Boris Sill", "Clavdij Matheu", "David Throk"
        ));
        assertOutput ("Task 28", imp.task28 (uniqueNames, Arrays.asList ("Barlow", "Sill", null, "Throk")), false, List.of (
            "Andrey Barlow", "Boris Sill", "Clavdij null", "David Throk"
        ));
        
        assertOutput ("Task 29", imp.task29 (uniqueNames.stream ()), Set.copyOf (uniqueNames));
        assertOutput ("Task 29", imp.task29 (nonUniqueNamesWithNulls.stream ()), nonUniqueNamesWithNulls);
    }
    
    @SuppressWarnings ("unused")
    private static <T> void assertOutput (String test, Set <T> actual, Set <T> expected) {
        final var tmp = new HashSet <> (expected);
        tmp.removeAll (actual);
        
        assert tmp.isEmpty () : String.format (
            "Test: %s%nExpected: %s (unordered)%nActual: %s", test, expected, actual
        );
    }
    
    private static <T> void assertOutput (String test, Set <T> actual, List <T> expected) {
        for (final var item : expected) {
            assert actual.contains (item) : String.format (
                "Test: %s%nExpected value `%s` in %s", test, item, actual
            );
        }
    }
    
    private static <T> void assertOutput (String test, List <T> actual, List <T> expected) {
        assert actual.size () == expected.size () : String.format (
            "Test: %s%nResulted list should contains %d elements (actual size: %d)", 
            test, expected.size (), actual.size ()
        );
        
        for (int i = 0; i < expected.size (); i++) {
            assert Objects.equals (expected.get (i), actual.get (i)) : String.format (
                "Test: %s%nValue `%s` was expected on position %d (actual value: %s)",
                test, expected.get (i), i, actual.get (i)
            );
        }
    }
    
    private static <T> void assertOutput (String test, List <T> actual, Set <T> expected) {
        assert actual.size () == expected.size () : String.format (
            "Test: %s%nResulted list should contains %d elements (actual size: %d)", 
            test, expected.size (), actual.size ()
        );
        
        for (int i = 0; i < expected.size (); i++) {
            assert expected.contains (actual.get (i)) : String.format (
                "Test: %s%nValue `%s` on position %d should not be in answer",
                test, actual.get (i), i
            );
        }
    }
    
    private static void assertOutput (String test, Integer actual, Integer expected) {
        assert Objects.equals (actual, expected) : String.format (
            "Test: %s%nInteger value `%d` was expected (actual given: %d)", 
            test, expected, actual
        );
    }
    
    private static <T> void assertOutput (String test, Stream <T> actual, boolean parallel, List <T> expected) {
        assert actual.isParallel () == parallel : String.format (
            "Test: %s%nStream should%s be parallel", 
            test, parallel ? "" : " not"
        );
        assertOutput (test, actual.collect (Collectors.toList ()), expected);
    }
    
    private static <T> void assertOutput (String test, Stream <T> actual, boolean parallel, Set <T> expected) {
        assert actual.isParallel () == parallel : String.format (
            "Test: %s%nStream should%s be parallel", 
            test, parallel ? "" : " not"
        );
        assertOutput (test, actual.collect (Collectors.toList ()), expected);
    }
    
    private static void assertOutput (String test, IntStream actual, boolean parallel, List <Integer> expected) {
        assert actual.isParallel () == parallel : String.format (
            "Test: %s%nStream should%s be parallel", 
            test, parallel ? "" : " not"
        );
        assertOutput (test, actual.boxed ().collect (Collectors.toList ()), expected);
    }
    
    @SuppressWarnings ("unused")
    private static void assertOutput (String test, IntStream actual, boolean parallel, Set <Integer> expected) {
        assert actual.isParallel () == parallel : String.format (
            "Test: %s%nStream should%s be parallel", 
            test, parallel ? "" : " not"
        );
        assertOutput (test, actual.boxed ().collect (Collectors.toList ()), expected);
    }
    
}
