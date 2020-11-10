import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTasksMain {
    
    public static void main (String ... args) {
        try { 
            runTasks (new StreamTasks ()); 
            System.out.println ("All tests completed!"); // SYSOUT
        } catch (UnsupportedOperationException uoe) {
            System.out.printf ("Method `%s` is not implemened%n", uoe.getMessage ()); // SYSOUT
        } catch (AssertionError ae) {
            System.out.println ("Wrong answer given"); // SYSOUT
            System.out.println (ae.getMessage ()); // SYSOUT
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
            assert expected.get (i).equals (actual.get (i)) : String.format (
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
