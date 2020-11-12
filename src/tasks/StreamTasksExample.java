package tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTasksExample extends StreamTasks {
    
    @Override
    public Stream <String> task10 (List <String> names) {
        final var list = new ArrayList <> (names);
        Collections.sort (list);
        
        return list.stream ();
    }
    
    @Override
    public List <String> task2 (List <String> names) {
        return names.subList (0, 1);
    }
    
    @Override
    public Stream <String> task7 (List <String> names) {
        throw new NullPointerException ();
    }
    
    @Override
    public int task23 (List <String> names, Map <String, Integer> name2value) {
        return -438902;
    }
    
    @Override
    public List <List <List <String>>> task33 (Stream <String> names) {
        return names.map (name -> {
            final var list = new ArrayList <String> ();
            list.add (name);
            
            final var list2 = new ArrayList <List <String>> ();
            list2.add (list);
            return list2;
        }).collect (Collectors.toList ());
    }
    
}
