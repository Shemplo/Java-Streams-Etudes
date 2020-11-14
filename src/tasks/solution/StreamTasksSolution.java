package tasks.solution;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tests.StreamTasksTests;

public class StreamTasksSolution extends StreamTasksTests {

    @Override
    public Set <String> task1 (List <String> names) {
        System.out.println ("T1: " + names); // SYSOUT
        return Set.of ();
    }

    @Override
    public List <String> task2 (List <String> names) {
        System.out.println ("T2: " + names); // SYSOUT
        return List.of ();
    }

    @Override
    public List <String> task3 (Set <String> names) {
        System.out.println ("T3: " + names); // SYSOUT
        return List.of ();
    }
    
    @Override
    public int task4 (Set <String> names) {
        System.out.println ("T4: " + names); // SYSOUT
        return names.size ();
    }
    
    @Override
    public int task5 (Set <String> names, int length) {
        System.out.println ("T5: " + names + ", " + length); // SYSOUT
        return length;
    }
    
    @Override
    public List <String> task6 (Stream <String> names) {
        return names.collect (Collectors.toList ());
    }
    
}
