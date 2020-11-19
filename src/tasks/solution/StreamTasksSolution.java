package tasks.solution;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import tests.StreamTasksTests;

public class StreamTasksSolution extends StreamTasksTests {

    @Override
    public List <Integer> task1 (int a, int b, int c) {
        return List.of (a, b + c - a, c);
    }
    
    @Override
    public Set <Integer> task2 (int a, int b, int c) {
        return Set.copyOf (List.of (a, b, c));
    }
    
    @Override
    public <T> Set <T> task3 (List <T> values) {
        return Set.copyOf (values);
    }
    
    @Override
    public <T> Stream <T> task4 (List <T> values) {
        return values.stream ();
    }
    
    @Override
    public <T> Stream <T> task5 (Set <T> values) {
        return values.stream ();
    }
    
    @Override
    public <T> int task6 (Stream <T> values) {
        return (int) values.count ();
    }
    
    @Override
    public <T> Stream <T> task7 (Stream <T> values, int limit) {
        return values.limit (limit);
    }
    
    @Override
    public <T> Stream <T> task8 (Stream <T> values, int n, int size) {
        return values.skip (size - n).limit (n);
    }
    
    @Override
    public <T> Stream <T> task9 (Stream <T> values1, Stream <T> values2) {
        return Stream.concat (values1, values2);
    }
    
    @Override
    public <T> Stream <T> task10 (Stream <T> values1, Stream <T> values2, Stream <T> values3) {
        return Stream.concat (Stream.concat (values1, values2), values3);
    }
    
    @Override
    public Stream <Integer> task11 (Stream <Integer> numbers, Predicate <Integer> condition) {
        return numbers.filter (condition);
    }
    
    @Override
    public Predicate <Integer> task12 (int from, int to) {
        return i -> i >= from && i <= to;
    }
    
    @Override
    public Predicate <Integer> task13 (Predicate <Integer> positive, Predicate <Integer> negative) {
        return positive.and (negative.negate ());
    }
    
    @Override
    public Stream <Integer> task14 (Stream <Integer> numbers, Function <Integer, Integer> converter) {
        return numbers.map (converter);
    }
    
    @Override
    public Function <Integer, Integer> task15 (int k, int b) {
        return x -> k * x + b;
    }
    
    @Override
    public Function <Integer, Integer> task16 (Function <Integer, Integer> f, Function <Integer, Integer> g) {
        return g.compose (x -> f.apply (x) + 1);
    }
    
    @Override
    public Stream <Integer> task17 (Stream <String> numbers) {
        return numbers.map (Integer::parseInt);
    }
    
    @Override
    public Stream <Integer> task18 (Stream <String> numbers) {
        return numbers.map (num -> num.substring (2)).map (num -> Integer.parseInt (num, 16));
    }
    
    @Override
    public Stream <Integer> task19 (Stream <Integer> numbers) {
        return numbers.distinct ().sorted ();
    }
    
    @Override
    public Stream <Integer> task20 (Stream <Integer> numbers, int limit) {
        return numbers.filter (x -> x * x <= limit).sorted ();
    }
    
    @Override
    public IntStream task21 (int to) {
        return IntStream.range (0, to + 1);
    }
    
    @Override
    public IntStream task22 (List <Integer> numbers) {
        return IntStream.range (0, numbers.size ()).map (i -> numbers.get (i) + i);
    }
    
    @Override
    public IntStream task23 (List <Integer> numbers, int offset) {
        return IntStream.iterate (0, i -> i < numbers.size (), i -> i + offset).map (numbers::get);
    }
    
    @Override
    public Stream <Integer> task24 (List <Integer> numbers) {
        return IntStream.iterate (0, i -> (i + 1) % numbers.size ()).mapToObj (numbers::get);
    }
    
    @Override
    public IntStream task25 (List <Integer> numbers1, List <Integer> numbers2) {
        return IntStream.range (0, Math.min (numbers1.size (), numbers2.size ()))
             . map (i -> numbers1.get (i) + numbers2.get (i));
    }
    
    @Override
    public void testMapInput (Map <String, Integer> name2age) {
        System.out.println (name2age); // SYSOUT
        System.out.println ("Save me Gods!"); // SYSOUT
    }
    
}
