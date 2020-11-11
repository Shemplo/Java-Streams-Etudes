package tasks;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTasks {
    
    // All methods bellow can be solved in a single line
    
    /**
     * @return Set of given names
     */
    public Set <String> task1 (List <String> names) {
        throw new UnsupportedOperationException ("task1");
    }
    
    /**
     * @return List of unique names
     */
    public List <String> task2 (List <String> names) {
        throw new UnsupportedOperationException ("task2");
    }
    
    /**
     * @return Number of unique names
     */
    public int task3 (List <String> names) {
        throw new UnsupportedOperationException ("task3");
    }
    
    /**
     * @return All integer numbers from segment [0, 7]
     */
    public List <Integer> task4 () {
        throw new UnsupportedOperationException ("task4");
    }
    
    /**
     * @return Stream of names
     */
    public Stream <String> task5 (List <String> names) {
        throw new UnsupportedOperationException ("task5");
    }
    
    /**
     * @return Parallel stream of names
     */
    public Stream <String> task6 (List <String> names) {
        throw new UnsupportedOperationException ("task6");
    }
    
    /**
     * @return Stream of non-null elements (names)
     */
    public Stream <String> task7 (List <String> names) {
        throw new UnsupportedOperationException ("task7");
    }
    
    /**
     * @return Stream of names with last name `Smith` ('Nick' -> 'Nick Smith', 'Bob' -> 'Bob Smith')
     */
    public Stream <String> task8 (List <String> names) {
        throw new UnsupportedOperationException ("task8");
    }
    
    /**
     * @return Stream of non-null names with last name `Smith`
     */
    public Stream <String> task9 (List <String> names) {
        throw new UnsupportedOperationException ("task9");
    }
    
    /**
     * @return Sorted stream of names
     */
    public Stream <String> task10 (List <String> names) {
        throw new UnsupportedOperationException ("task10");
    }
    
    /**
     * @return Stream of names with applied function
     */
    public Stream <String> task11 (List <String> names, Function <String, String> function) {
        throw new UnsupportedOperationException ("task11");
    }
    
    /**
     * @return Stream of unique names
     */
    public Stream <String> task12 (List <String> names) {
        throw new UnsupportedOperationException ("task12");
    }
    
    /**
     * @return Stream of parsed integer numbers
     */
    public Stream <Integer> task13 (List <String> numbers) {
        throw new UnsupportedOperationException ("task13");
    }
    
    /**
     * @return Sorted stream of parsed integer numbers
     */
    public Stream <Integer> task14 (List <String> numbers) {
        throw new UnsupportedOperationException ("task14");
    }
    
    /**
     * @return Integer stream of parsed integer numbers
     */
    public IntStream task15 (List <String> numbers) {
        throw new UnsupportedOperationException ("task15");
    }
    
    /**
     * @return Sum of parsed integer numbers
     */
    public int task16 (List <String> numbers) {
        throw new UnsupportedOperationException ("task16");
    }
    
    /**
     * @return Stream of all integer numbers from segment [0, 9932]
     */
    public IntStream task17 () {
        throw new UnsupportedOperationException ("task17");
    }
    
    /**
     * @return Stream of names with given length (do not include other names)
     * @hint See the names of method arguments
     */
    public Stream <String> task18 (List <String> names, int streamLength) {
        throw new UnsupportedOperationException ("task18");
    }
    
    /**
     * @return Stream of names with given length (do not include other names)
     * @hint See the names of method arguments
     */
    public Stream <String> task19 (List <String> names, int nameLength) {
        throw new UnsupportedOperationException ("task19");
    }
    
    /**
     * @return Stream of names sorted by given comparator (be careful, NULLs can be in names)
     */
    public Stream <String> task20 (List <String> names, Comparator <String> comparator) {
        throw new UnsupportedOperationException ("task19");
    }
    
    /**
     * @return Stream that contains all elements from 2 given lists
     */
    public Stream <String> task21 (List <String> a, List <String> b) {
        throw new UnsupportedOperationException ("task21");
    }
    
    /**
     * @return Stream of numbers that a referred to names in given map
     */
    public Stream <Integer> task22 (List <String> names, Map <String, Integer> name2value) {
        throw new UnsupportedOperationException ("task22");
    }
    
    /**
     * @return Sum of numbers that a referred to names in given map
     */
    public int task23 (List <String> names, Map <String, Integer> name2value) {
        throw new UnsupportedOperationException ("task23");
    }
    
    /**
     * @return Stream of numbers which positions in given list are in segment [from, to]
     */
    public Stream <Integer> task24 (List <Integer> numbers, int from, int to) {
        throw new UnsupportedOperationException ("task24");
    }
    
    /**
     * @return Stream of numbers which positions in given list are in segments: [from1, to1], [from2, to2]
     */
    public Stream <Integer> task25 (List <Integer> numbers, int from1, int to1, int from2, int to2) {
        throw new UnsupportedOperationException ("task25");
    }
    
    /**
     * @return Check whether stream of names contains given name
     */
    public boolean task26 (Stream <String> names, String name) {
        throw new UnsupportedOperationException ("task26");
    }
    
    /**
     * @return Number of stream elements after first name equals to given
     */
    public int task27 (Stream <String> names, String name) {
        throw new UnsupportedOperationException ("task27");
    }
    
    /**
     * @return Stream of names concatenated with corresponding last names with 1 space ([A, B], [C, D, E]) -> [A C, B D]
     */
    public Stream <String> task28 (List <String> names, List <String> lastNames) {
        throw new UnsupportedOperationException ("task28");
    }
    
    /**
     * @return List of stream elements
     */
    public List <String> task29 (Stream <String> names) {
        throw new UnsupportedOperationException ("task29");
    }
    
}
