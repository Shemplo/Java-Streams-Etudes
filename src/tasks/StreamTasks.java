package tasks;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import tasks.solution.StreamTasksSolution;

public class StreamTasks extends StreamTasksSolution {
    
    // INTRODUCTION //
    
    /**
     * @return List of given constants [a, b + c - a, c]
     * @see List#of()
     * @lines 1
     */
    public List <Integer> task1 (int a, int b, int c) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Set of given constants [a, b, c]
     * @see Set#of()
     * @lines 1
     */
    public Set <Integer> task2 (int a, int b, int c) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Set of values from given list
     * @see Set#copyOf(Collection)
     * @lines 1
     */
    public <T> Set <T> task3 (List <T> values) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Stream of values from given list
     * @see Collection#stream()
     * @lines 1
     */
    public <T> Stream <T> task4 (List <T> values) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Stream of values from given set
     * @see Collection#stream()
     * @lines 1
     */
    public <T> Stream <T> task5 (Set <T> values) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Number of elements in the stream
     * @see Stream#count()
     * @lines 1
     */
    public <T> int task6 (Stream <T> values) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Given stream that is limited by size
     * @see Stream#limit(long)
     * @lines 1
     */
    public <T> Stream <T> task7 (Stream <T> values, int limit) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Stream that contains last `n` elements of sequence with general `size` size from given stream
     * @example input: [A, B, C, D, E, F, G, H, I, ...], 3, 7 -> output: [E, F, G]
     * @see Stream#skip(long)
     * @lines 1
     */
    public <T> Stream <T> task8 (Stream <T> values, int n, int size) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Stream that contains all values from given streams
     * @see Stream#concat(Stream, Stream)
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
     * @see Stream#filter(Predicate)
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
     * @see Predicate#and(Predicate)
     * @see Predicate#negate()
     * @lines 1
     */
    public Predicate <Integer> task13 (Predicate <Integer> positive, Predicate <Integer> negative) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Stream that contains converted values by `converter`
     * @see Stream#map(Function)
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
     * @see Function#compose(Function)
     * @lines 1
     */
    public Function <Integer, Integer> task16 (Function <Integer, Integer> f, Function <Integer, Integer> g) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Stream of parsed integer numbers from its' string representation
     * @see Integer#parseInt(String)
     * @lines 1
     */
    public Stream <Integer> task17 (Stream <String> numbers) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Stream of parsed hex integer numbers from its' string representation
     * @hint Hex numbers starts from '0x', don't forget to remove this prefix
     * @see Integer#parseInt(String, int)
     * @lines 1
     */
    public Stream <Integer> task18 (Stream <String> numbers) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Sorted stream (ascending) of unique numbers
     * @see Stream#distinct()
     * @see Stream#sorted()
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
    
    // INT STREAMS //
    
    /**
     * @return Stream of integers from segment [0, `to`]
     * @see IntStream#range(int, int)
     * @lines 1
     */
    public IntStream task21 (int to) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Stream of integer numbers from given list with added index: `numbers` [i] + i
     * @see IntStream#map(IntUnaryOperator)
     * @lines 1
     */
    public IntStream task22 (List <Integer> numbers) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Stream of integer numbers on `offset` positions (0 position included)
     * @see IntStream#iterate(int, IntPredicate, IntUnaryOperator)
     * @example [0, 1, 2, 3, 4, 5, 6, 7, 8], 3 -> [0, 3, 6]
     * @lines 1
     */
    public IntStream task23 (List <Integer> numbers, int offset) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Infinite stream of integers (cycled on given `numbers`)
     * @see IntStream#iterate(int, IntUnaryOperator)
     * @see IntStream#mapToObj(IntFunction)
     * @see IntStream#boxed()
     * @example [0, 1, 2, 3, 4] -> [0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 0, 1, 2, 3, ...]
     * @lines 1
     */
    public Stream <Integer> task24 (List <Integer> numbers) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
    /**
     * @return Stream of summed values on the same positions from given lists of numbers 
     * (only positions that there are in both lists)
     * @example [0, 1, 2, 3, 4], [10, 12, 14] -> [10, 13, 16]
     * @lines 1
     */
    public IntStream task25 (List <Integer> numbers1, List <Integer> numbers2) {
        throw new UnsupportedOperationException ("Implement method instead of this line");
    }
    
}
