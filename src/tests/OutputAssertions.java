package tests;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OutputAssertions {
    
    /*******************************/
    /* DO NOT TOUCH METHODS BELLOW */
    /*******************************/
    
    public static <T> void assertOutput (Set <T> actual, Set <T> expected) {
        final var tmp = new HashSet <> (expected);
        tmp.removeAll (actual);
        
        assert tmp.isEmpty () : String.format (
            "Expected: %s (unordered)%n  Actual:   %s", expected, actual
        );
    }
    
    public static <T> void assertOutput (Set <T> actual, List <T> expected) {
        for (final var item : expected) {
            assert actual.contains (item) : String.format (
                "Expected value `%s` in %s", item, actual
            );
        }
    }
    
    public static <T> void assertOutput (List <T> actual, List <T> expected) {
        assert actual.size () == expected.size () : String.format (
            "Resulted list should contains %d elements (actual size: %d)", 
            expected.size (), actual.size ()
        );
        
        for (int i = 0; i < expected.size (); i++) {
            assert Objects.equals (expected.get (i), actual.get (i)) : String.format (
                "Value `%s` was expected on position %d (actual value: %s)",
                expected.get (i), i, actual.get (i)
            );
        }
    }
    
    public static <T> void assertOutput (List <T> actual, Set <T> expected) {
        assert actual.size () == expected.size () : String.format (
            "Resulted list should contains %d elements (actual size: %d)", 
            expected.size (), actual.size ()
        );
        
        for (int i = 0; i < expected.size (); i++) {
            assert expected.contains (actual.get (i)) : String.format (
                "Value `%s` on position %d should not be in answer",
                actual.get (i), i
            );
        }
    }
    
    public static void assertOutput (Integer actual, Integer expected) {
        assert Objects.equals (actual, expected) : String.format (
            "Integer value `%d` was expected (actual given: %d)", 
            expected, actual
        );
    }
    
    public static <T> void assertOutput (Stream <T> actual, boolean parallel, List <T> expected) {
        assert actual.isParallel () == parallel : String.format (
            "Stream should%s be parallel", parallel ? "" : " not"
        );
        
        assertOutput (actual.collect (Collectors.toList ()), expected);
    }
    
    public static <T> void assertOutput (Stream <T> actual, boolean parallel, Set <T> expected) {
        assert actual.isParallel () == parallel : String.format (
            "Stream should%s be parallel", parallel ? "" : " not"
        );
        
        assertOutput (actual.collect (Collectors.toList ()), expected);
    }
    
    public static void assertOutput (IntStream actual, boolean parallel, List <Integer> expected) {
        assert actual.isParallel () == parallel : String.format (
            "Stream should%s be parallel", parallel ? "" : " not"
        );
        
        assertOutput (actual.boxed ().collect (Collectors.toList ()), expected);
    }
    
    public static void assertOutput (IntStream actual, boolean parallel, Set <Integer> expected) {
        assert actual.isParallel () == parallel : String.format (
            "Stream should%s be parallel", parallel ? "" : " not"
        );
        
        assertOutput (actual.boxed ().collect (Collectors.toList ()), expected);
    }
    
    @SuppressWarnings ("unchecked")
    public static <K, V> void assertOutput (Map <K, V> actual, Map <K, V> expected) {
        assert actual.size () == expected.size () : String.format (
            "Resulted map should contains %d elements (actual size: %d)", 
            expected.size (), actual.size ()
        );
        
        for (final var entry : expected.entrySet ()) {
            assert actual.containsKey (entry.getKey ()) : String.format (
                "Resulted map should contains key `%s`", entry.getKey ()
            );
            
            if (entry.getValue () instanceof Map) {
                final var actualMap = (Map <Object, Object>) actual.get (entry.getKey ());
                final var expectedMap = (Map <Object, Object>) entry.getValue ();
                assertOutput (actualMap, expectedMap);
            } else if (entry.getValue () instanceof List) {
                final var actualList = (List <Object>) actual.get (entry.getKey ());
                final var expectedList = (List <Object>) entry.getValue ();
                assertOutput (actualList, expectedList);
            } else if (entry.getValue () instanceof Set) {
                final var actualSet = (Set <Object>) actual.get (entry.getKey ());
                final var expectedSet = (Set <Object>) entry.getValue ();
                assertOutput (actualSet, expectedSet);
            }
        }
    }
    
}
