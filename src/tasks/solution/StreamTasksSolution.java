package tasks.solution;
import java.util.List;
import java.util.Set;
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
    
}
