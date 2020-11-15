package tasks.solution;
import java.util.List;
import java.util.Set;
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
    
}
