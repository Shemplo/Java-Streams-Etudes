package tasks;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import tests.StreamTasksTests;

public class StreamTasks extends StreamTasksTests {
    
    // INTRODUCTION //
    
    /**
     * @return List of given constants [a, b + c - a, c]
     * @lines 1
     */
    public List <Integer> task1 (int a, int b, int c) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Set of given constants [a, b, c]
     * @lines 1
     */
    public Set <Integer> task2 (int a, int b, int c) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Set of values from given list
     * @lines 1
     */
    public <T> Set <T> task3 (List <T> values) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Stream of values from given list
     * @lines 1
     */
    public <T> Stream <T> task4 (List <T> values) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Stream of values from given set
     * @lines 1
     */
    public <T> Stream <T> task5 (Set <T> values) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Number of elements in the stream
     * @lines 1
     */
    public <T> int task6 (Stream <T> values) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Given stream that is limited by size
     * @lines 1
     */
    public <T> Stream <T> task7 (Stream <T> values, int limit) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Stream that contains last `n` elements of sequence with general `size` size from given stream
     * @example input: [A, B, C, D, E, F, G, H, I, ...], 3, 7 -> output: [E, F, G]
     * @lines 1
     */
    public <T> Stream <T> task8 (Stream <T> values, int n, int size) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Stream that contains all values from given streams
     * @lines 1
     */
    public <T> Stream <T> task9 (Stream <T> values1, Stream <T> values2) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Stream that contains all values from given streams
     * @lines 1
     */
    public <T> Stream <T> task10 (Stream <T> values1, Stream <T> values2, Stream <T> values3) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    // FIRST FLOWS //
    
    /**
     * @return Stream that contains only values accepted by predicate (condition)
     * @lines 1
     */
    public Stream <Integer> task11 (Stream <Integer> numbers, Predicate <Integer> condition) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Predicate that will accept numbers in segment [from, to]
     * @lines 1
     */
    public Predicate <Integer> task12 (int from, int to) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Predicate that combine two: `positive` as well and negation of `negative`
     * @lines 1
     */
    public Predicate <Integer> task13 (Predicate <Integer> positive, Predicate <Integer> negative) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Stream that contains converted values by `converter`
     * @lines 1
     */
    public Stream <Integer> task14 (Stream <Integer> numbers, Function <Integer, Integer> converter) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Function that convert each element (x) to expression: k * x + b
     * @lines 1
     */
    public Function <Integer, Integer> task15 (int k, int b) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Function that convert each element (x) to expression: g (f (x) + 1)
     * @lines 1
     */
    public Function <Integer, Integer> task16 (Function <Integer, Integer> f, Function <Integer, Integer> g) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Stream of parsed integer numbers from its' string representation
     * @lines 1
     */
    public Stream <Integer> task17 (Stream <String> numbers) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Stream of parsed hex integer numbers from its' string representation
     * @hint Hex numbers starts from '0x', don't forget to remove this prefix
     * @lines 1
     */
    public Stream <Integer> task18 (Stream <String> numbers) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Sorted stream (ascending) of unique numbers
     * @lines 1
     */
    public Stream <Integer> task19 (Stream <Integer> numbers) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Sorted stream (ascending) of numbers whose square doesn't exceed `limit` (less or equal)
     * @lines 1
     */
    public Stream <Integer> task20 (Stream <Integer> numbers, int limit) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
}
