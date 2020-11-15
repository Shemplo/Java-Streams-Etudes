package tasks.solution;
import java.util.List;
import java.util.Set;

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
    
}
