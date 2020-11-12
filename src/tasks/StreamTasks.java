package tasks;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTasks {
    
    // @lines [x] - means that this task can be implemented with [x] lines
    
    /**
     * @return Set of given names
     * @lines 1
     */
    public Set <String> task1 (List <String> names) {
        throw new UnsupportedOperationException ("task1");
    }
    
    /**
     * @return List of unique names
     * @lines 1
     */
    public List <String> task2 (List <String> names) {
        throw new UnsupportedOperationException ("task2");
    }
    
    /**
     * @return Number of unique names
     * @lines 1
     */
    public int task3 (List <String> names) {
        throw new UnsupportedOperationException ("task3");
    }
    
    /**
     * @return All integer numbers from segment [0, 7]
     * @lines 1
     */
    public List <Integer> task4 () {
        throw new UnsupportedOperationException ("task4");
    }
    
    /**
     * @return Stream of names
     * @lines 1
     */
    public Stream <String> task5 (List <String> names) {
        throw new UnsupportedOperationException ("task5");
    }
    
    /**
     * @return Parallel stream of names
     * @lines 1
     */
    public Stream <String> task6 (List <String> names) {
        throw new UnsupportedOperationException ("task6");
    }
    
    /**
     * @return Stream of non-null elements (names)
     * @lines 1
     */
    public Stream <String> task7 (List <String> names) {
        throw new UnsupportedOperationException ("task7");
    }
    
    /**
     * @return Stream of names with last name `Smith` ('Nick' -> 'Nick Smith', 'Bob' -> 'Bob Smith')
     * @lines 1
     */
    public Stream <String> task8 (List <String> names) {
        throw new UnsupportedOperationException ("task8");
    }
    
    /**
     * @return Stream of non-null names with last name `Smith`
     * @lines 1
     */
    public Stream <String> task9 (List <String> names) {
        throw new UnsupportedOperationException ("task9");
    }
    
    /**
     * @return Sorted stream of names
     * @lines 1
     */
    public Stream <String> task10 (List <String> names) {
        throw new UnsupportedOperationException ("task10");
    }
    
    /**
     * @return Stream of names with applied function
     * @lines 1
     */
    public Stream <String> task11 (List <String> names, Function <String, String> function) {
        throw new UnsupportedOperationException ("task11");
    }
    
    /**
     * @return Stream of unique names
     * @lines 1
     */
    public Stream <String> task12 (List <String> names) {
        throw new UnsupportedOperationException ("task12");
    }
    
    /**
     * @return Stream of parsed integer numbers
     * @lines 1
     */
    public Stream <Integer> task13 (List <String> numbers) {
        throw new UnsupportedOperationException ("task13");
    }
    
    /**
     * @return Sorted stream of parsed integer numbers
     * @lines 1
     */
    public Stream <Integer> task14 (List <String> numbers) {
        throw new UnsupportedOperationException ("task14");
    }
    
    /**
     * @return Integer stream of parsed integer numbers
     * @lines 1
     */
    public IntStream task15 (List <String> numbers) {
        throw new UnsupportedOperationException ("task15");
    }
    
    /**
     * @return Sum of parsed integer numbers
     * @lines 1
     */
    public int task16 (List <String> numbers) {
        throw new UnsupportedOperationException ("task16");
    }
    
    /**
     * @return Stream of all integer numbers from segment [0, 9932]
     * @lines 1
     */
    public IntStream task17 () {
        throw new UnsupportedOperationException ("task17");
    }
    
    /**
     * @return Stream of names with given length (do not include other names)
     * @hint See the names of method arguments
     * @lines 1
     */
    public Stream <String> task18 (List <String> names, int streamLength) {
        throw new UnsupportedOperationException ("task18");
    }
    
    /**
     * @return Stream of names with given length (do not include other names)
     * @hint See the names of method arguments
     * @lines 1
     */
    public Stream <String> task19 (List <String> names, int nameLength) {
        throw new UnsupportedOperationException ("task19");
    }
    
    /**
     * @return Stream of names sorted by given comparator (be careful, NULLs can be in names)
     * @lines 1
     */
    public Stream <String> task20 (List <String> names, Comparator <String> comparator) {
        throw new UnsupportedOperationException ("task19");
    }
    
    /**
     * @return Stream that contains all elements from 2 given lists
     * @lines 1
     */
    public Stream <String> task21 (List <String> a, List <String> b) {
        throw new UnsupportedOperationException ("task21");
    }
    
    /**
     * @return Stream of numbers that a referred to names in given map
     * @lines 1
     */
    public Stream <Integer> task22 (List <String> names, Map <String, Integer> name2value) {
        throw new UnsupportedOperationException ("task22");
    }
    
    /**
     * @return Sum of numbers that a referred to names in given map
     * @lines 1
     */
    public int task23 (List <String> names, Map <String, Integer> name2value) {
        throw new UnsupportedOperationException ("task23");
    }
    
    /**
     * @return Stream of numbers which positions in given list are in segment [from, to]
     * @lines 1
     */
    public Stream <Integer> task24 (List <Integer> numbers, int from, int to) {
        throw new UnsupportedOperationException ("task24");
    }
    
    /**
     * @return Stream of numbers which positions in given list are in segments: [from1, to1], [from2, to2]
     * @lines 1
     */
    public Stream <Integer> task25 (List <Integer> numbers, int from1, int to1, int from2, int to2) {
        throw new UnsupportedOperationException ("task25");
    }
    
    /**
     * @return Check whether stream of names contains given name
     * @lines 1
     */
    public boolean task26 (Stream <String> names, String name) {
        throw new UnsupportedOperationException ("task26");
    }
    
    /**
     * @return Number of stream elements after first name equals to given
     * @lines 1
     */
    public int task27 (Stream <String> names, String name) {
        throw new UnsupportedOperationException ("task27");
    }
    
    /**
     * @return Stream of names concatenated with corresponding last names with 1 space ([A, B], [C, D, E]) -> [A C, B D]
     * @lines 1
     */
    public Stream <String> task28 (List <String> names, List <String> lastNames) {
        throw new UnsupportedOperationException ("task28");
    }
    
    /**
     * @return List of stream elements (names)
     * @lines 1
     */
    public List <String> task29 (Stream <String> names) {
        throw new UnsupportedOperationException ("task29");
    }
    
    /**
     * @return Set of stream elements (names)
     * @lines 1
     */
    public Set <String> task30 (Stream <String> names) {
        throw new UnsupportedOperationException ("task30");
    }
    
    /**
     * @return List of some stream elements
     * @hint Streams can be endless
     * @lines 1
     */
    public List <Integer> task31 (Stream <Integer> numbers) {
        throw new UnsupportedOperationException ("task31");
    }
    
    /**
     * @return List of names wrapped to single-element lists
     * @lines 1
     */
    public List <List <String>> task32 (Stream <String> names) {
        throw new UnsupportedOperationException ("task32");
    }
    
    /**
     * @return List of names wrapped to single-element lists (twice)
     * @lines 1
     */
    public List <List <List <String>>> task33 (Stream <String> names) {
        throw new UnsupportedOperationException ("task33");
    }
    
    /**
     * @return List of whole names in stream
     * @lines 1
     */
    public List <String> task34 (Stream <List <String>> namesGroups) {
        throw new UnsupportedOperationException ("task34");
    }
    
    /**
     * @return List of all names only from groups which contains given name
     * @hint [[A, B, C], [B, D, F, E], [A, C]], B -> [A, B, C, B, D, F, E]
     * @lines &le; 2
     */
    public List <String> task35 (Stream <List <String>> namesGroups, String name) {
        throw new UnsupportedOperationException ("task35");
    }
    
    /**
     * @return Sum of all numbers in matrix
     * @lines 1
     */
    public int task36 (List <List <Integer>> matrix) {
        throw new UnsupportedOperationException ("task36");
    }
    
    /**
     * @return Trace of square matrix (sum on main diagonal)
     * @lines 1
     */
    public int task37 (List <List <Integer>> matrix) {
        throw new UnsupportedOperationException ("task37");
    }
    
    /**
     * @return Amount of rows with negative sum of numbers in
     * @lines 1
     */
    public int task38 (List <List <Integer>> matrix) {
        throw new UnsupportedOperationException ("task38");
    }
    
    /**
     * @return Stream with reversed order of elements
     * @lines 1
     */
    public Stream <String> task39 (Stream <String> names) {
        throw new UnsupportedOperationException ("task39");
    }
    
    /**
     * @return Split input sequence of numbers to lists of length that is not more than given
     * @hint [A, B, C, D, E, F, G, H, I, J, K], 4 -> [[A, B, C, D], [E, F, G, H], [I, J, K]]
     * @lines &le; 3
     */
    public List <List <Integer>> task40 (List <Integer> numbers, int length) {
        throw new UnsupportedOperationException ("task40");
    }
    
    /**
     * @return Matrix [lines x columns] with reversed order of elements
     * @hint [[A, B, C], [D, E, F]] -> [[F, E, D], [C, B, A]]
     * @lines &le; 3
     */
    public List <List <Integer>> task41 (List <List <Integer>> numbers, int lines, int columns) {
        throw new UnsupportedOperationException ("task41");
    }
    
    /**
     * @return Transposed square matrix [lines x columns]
     * @hint [[A, B], [C, D]] -> [[A, C], [B, D]]
     * @lines &le; 3
     */
    public List <List <Integer>> task42 (List <List <Integer>> numbers, int lines, int columns) {
        throw new UnsupportedOperationException ("task42");
    }
    
    /**
     * @return Stream of elements that accepted by (at least) one of given predicates
     * @lines &le; 2
     */
    public Stream <String> task43 (Stream <String> names, Predicate <String> cond1, Predicate <String> cond2) {
        throw new UnsupportedOperationException ("task43");
    }
    
    /**
     * @return Sum of numbers that are generated by given supplier (amount of numbers is specified)
     * @lines 1
     */
    public int task44 (Supplier <Integer> generator, int number) {
        throw new UnsupportedOperationException ("task44");
    }
    
}
