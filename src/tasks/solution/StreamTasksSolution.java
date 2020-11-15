package tasks.solution;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tests.StreamTasksTests;

public class StreamTasksSolution extends StreamTasksTests {

    @Override
    public Set <String> task1 (List <String> names) {
        return Set.of ();
    }

    @Override
    public List <String> task2 (List <String> names) {
        return List.of ();
    }

    @Override
    public List <String> task3 (Set <String> names) {
        return List.of ();
    }
    
    @Override
    public int task4 (Set <String> names) {
        return names.size ();
    }
    
    @Override
    public int task5 (Set <String> names, int length) {
        return length;
    }
    
    @Override
    public List <String> task6 (Stream <String> names) {
        return names.collect (Collectors.toList ());
    }
    
    @Override
    public Stream <String> task7 (Stream <String> names, Stream <String> names2) {
        return Stream.concat (names, names2);
    }
    
    @Override
    public Stream <String> task8 (Stream <String> names, Stream <String> names2, int limit) {
        return task7 (names, names2).limit (limit);
    }
    
    @Override
    public <T> List <T> task9 (Stream <T> values) {
        return values.collect (Collectors.toList ());
    }
    
    @Override
    public <T> List <T> task10 (Supplier <T> generator, int limit) {
        return Stream.generate (generator).limit (limit).collect (Collectors.toList ());
    }
    
    @Override
    public List <Integer> task11 () {
        return List.of (0, 1, 2, 3, 4, 6);
    }
    
    @Override
    public List <String> task12 (Stream <String> names, Predicate <String> condition) {
        return names.filter (condition).collect (Collectors.toList ());
    }
    
    @Override
    public Predicate <String> task13 () {
        return s -> s.length () > 4;
    }
    
    @Override
    public int task14 (int a) {
        return a;
    }
    
    @Override
    public int task15 (int a, int b) {
        //System.out.println ("T15: " + a + " + " + b + " = " + (a + b)); // SYSOUT
        return a + b + 8;
    }
    
    @Override
    public int task16 () {
        return 76;
    }
    
    @Override
    public List <Integer> task17 (List <String> values, Function <String, Integer> converter) {
        return values.stream ().map (converter).collect (Collectors.toList ());
    }
    
    @Override
    public void task18 (List <String> values, Consumer <String> collector) {
        //System.out.println ("T18: " + values); // SYSOUT
        //System.out.println (collector); // SYSOUT
        values.forEach (collector);
    }
    
    @Override
    public <T> List <T> task19 (Stream <T> values, Function <T, T> converter) {
        return values.map (converter).collect (Collectors.toList ());
    }
    
}
