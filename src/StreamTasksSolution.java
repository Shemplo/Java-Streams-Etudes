import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTasksSolution extends StreamTasks {
    
    @Override
    public Set <String> task1 (List <String> names) {
        return Set.copyOf (names);
    }
    
    @Override
    public List <String> task2 (List <String> names) {
        return List.copyOf (Set.copyOf (names));
    }
    
    @Override
    public int task3 (List <String> names) {
        return task2 (names).size ();
    }
    
    @Override
    public List <Integer> task4 () {
        return List.of (0, 1, 2, 3, 4, 5, 6, 7);
    }
    
    @Override
    public Stream <String> task5 (List <String> names) {
        return names.stream ();
    }
    
    @Override
    public Stream <String> task6 (List <String> names) {
        return names.parallelStream ();
    }
    
    @Override
    public Stream <String> task7 (List <String> names) {
        return names.stream ().filter (Objects::nonNull);
    }
    
    @Override
    public Stream <String> task8 (List <String> names) {
        return names.stream ().map (n -> n + " Smith");
    }
    
    @Override
    public Stream <String> task9 (List <String> names) {
        return names.stream ().filter (Objects::nonNull).map (n -> n + " Smith");
    }
    
    @Override
    public Stream <String> task10 (List <String> names) {
        return names.stream ().sorted ();
    }
    
    @Override
    public Stream <String> task11 (List <String> names, Function <String, String> function) {
        return names.stream ().map (function);
    }
    
    @Override
    public Stream <String> task12 (List <String> names) {
        return names.stream ().distinct ();
    }
    
    @Override
    public Stream <Integer> task13 (List <String> numbers) {
        return numbers.stream ().map (Integer::parseInt);
    }
    
    @Override
    public Stream <Integer> task14 (List <String> numbers) {
        return numbers.stream ().map (Integer::parseInt).sorted ();
    }
    
    @Override
    public IntStream task15 (List <String> numbers) {
        return numbers.stream ().mapToInt (Integer::parseInt);
    }
    
    @Override
    public int task16 (List <String> numbers) {
        return task15 (numbers).sum ();
    }
    
    @Override
    public IntStream task17 () {
        return IntStream.range (0, 9933);
    }
    
    @Override
    public Stream <String> task18 (List <String> names, int streamLength) {
        return names.stream ().limit (streamLength);
    }
    
    @Override
    public Stream <String> task19 (List <String> names, int nameLength) {
        return names.stream ().filter (name -> name.length () == nameLength);
    }
    
    @Override
    public Stream <String> task20 (List <String> names, Comparator <String> comparator) {
        return names.stream ().filter (Objects::nonNull).sorted (comparator);
    }
    
    @Override
    public Stream <String> task21 (List <String> a, List <String> b) {
        return Stream.concat (a.stream (), b.stream ());
    }
    
    @Override
    public Stream <Integer> task22 (List <String> names, Map <String, Integer> name2value) {
        return names.stream ().map (name2value::get);
    }
    
    @Override
    public int task23 (List <String> names, Map <String, Integer> name2value) {
        return names.stream ().filter (name2value::containsKey).mapToInt (name2value::get).sum ();
    }
    
    @Override
    public Stream <Integer> task24 (List <Integer> numbers, int from, int to) {
        return IntStream.range (from, to + 1).mapToObj (numbers::get);
    }
    
    @Override
    public Stream <Integer> task25 (List <Integer> numbers, int from1, int to1, int from2, int to2) {
        return IntStream.range (0, numbers.size ()).filter (i -> (i >= from1 && i <= to1) || (i >= from2 && i <= to2)).mapToObj (numbers::get);
    }
    
    @Override
    public boolean task26 (Stream <String> names, String name) {
        return names.filter (name::equals).findFirst ().isPresent ();
    }
    
    @Override
    public int task27 (Stream <String> names, String name) {
        return (int) Math.max (names.dropWhile (n -> !name.equals (n)).count () - 1, 0);
    }
    
    @Override
    public Stream <String> task28 (List <String> names, List <String> lastNames) {
        return IntStream.range (0, Math.min (names.size (), lastNames.size ())).mapToObj (i -> String.format ("%s %s", names.get (i), lastNames.get (i)));
    }
    
    @Override
    public List <String> task29 (Stream <String> names) {
        return names.collect (Collectors.toList ());
    }
    
}
