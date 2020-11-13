package tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import tasks.StreamTasks;
import tasks.utils.Box;
import tasks.utils.Item;
import tests.utils.F3;
import tests.utils.F5;

public class TestsPool {
    
    /*******************************/
    /* DO NOT TOUCH METHODS BELLOW */
    /*******************************/
    
    private static final Random R = new Random (1L);
    
    private static final List <String> NAMES = List.of (
        "Andrey", "Boris", "Clement", "David", "Efim", "Feofan", 
        "Grigory", "Georgy", "Hoang", "Ilon", "Igor", "Jastin",
        "Kevin", "Lewis", "Leander", "Marko", "Natan"
    );
    
    private static final List <String> LAST_NAMES = List.of (
        "Argol", "Barlow", "Cliff", "Dvorkin", "Eps", "Foker"
    );
    
    private static final List <String> STR_NUMBERS = listSequence (
        0, 100, __ -> String.valueOf (R.nextInt (1000))
    );
    
    private static final List <Integer> INT_NUMBERS = listSequence (
        0, 100, __ -> R.nextInt (1000)
    );
    
    private final List <TaskTests <?, ?>> pool;
    
    public TestsPool (StreamTasks reference) {
        pool = List.of (
            prepareTask1  (reference),
            prepareTask2  (reference),
            prepareTask3  (reference),
            prepareTask4  (reference),
            prepareTasks5n6 (reference, false),
            prepareTasks5n6 (reference, true),
            prepareTask7  (reference),
            prepareTask8  (reference),
            prepareTask9  (reference),
            prepareTask10 (reference),
            prepareTask11 (reference),
            prepareTask12 (reference),
            prepareTask13 (reference),
            prepareTask14 (reference),
            prepareTask15 (reference),
            prepareTask16 (reference),
            prepareTask17 (reference),
            prepareTask18 (reference),
            prepareTask19 (reference),
            prepareTask20 (reference),
            prepareTask21 (reference),
            prepareTask22 (reference),
            prepareTask23 (reference),
            prepareTask24 (reference),
            prepareTask25 (reference),
            prepareTask26 (reference),
            prepareTask27 (reference),
            prepareTask28 (reference),
            prepareTask29 (reference),
            prepareTask30 (reference),
            prepareTask31 (reference),
            prepareTask32 (reference),
            prepareTask33 (reference),
            prepareTask34 (reference),
            prepareTask35 (reference),
            prepareTask36 (reference),
            prepareTask37 (reference),
            prepareTask38 (reference),
            prepareTask39 (reference),
            prepareTask40 (reference),
            prepareTask41 (reference),
            prepareTask42 (reference),
            prepareTask43 (reference),
            prepareTask44 (reference),
            prepareTask45 (reference),
            prepareTask46 (reference),
            prepareTask47 (reference),
            prepareTask48 (reference),
            prepareTask49 (reference),
            prepareTask50 (reference),
            prepareTask51 (reference),
            
            prepareTask52 (reference),
            prepareTask53 (reference),
            prepareTask54 (reference),
            prepareTask55 (reference),
            prepareTask56n57 (reference, false),
            prepareTask56n57 (reference, true),
            prepareTask58 (reference),
            prepareTask59 (reference),
            prepareTask60 (reference)
        );
    }
    
    public int getTestsNumber () {
        return pool.size ();
    }
    
    public TaskTests <?, ?> getTaskTests (int index) {
        return pool.get (index);
    }
    
    // TASKS //
    
    private static interface TF0 extends Supplier <BiConsumer <StreamTasks, BiConsumer <Object, Object>>> {}
    private static interface TF1 extends Function <Integer, BiConsumer <StreamTasks, BiConsumer <Object, Object>>> {}
    private static interface TF2 extends BiFunction <Integer, Integer, BiConsumer <StreamTasks, BiConsumer <Object, Object>>> {}
    private static interface TF3 extends F3 <Integer, Integer, Integer, BiConsumer <StreamTasks, BiConsumer <Object, Object>>> {}
    private static interface TF5 extends F5 <
        Integer, Integer, Integer, Integer, Integer, 
        BiConsumer <StreamTasks, BiConsumer <Object, Object>>
    > {}
    
    private TaskTests <?, ?> prepareTask1 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            checker.accept (impl.task1 (names), ref.task1 (names));
        };
            
        return single (f.apply (1)).add (f.apply (5));
    }
    
    private TaskTests <?, ?> prepareTask2 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            checker.accept (impl.task2 (names), ref.task2 (names));
        };
        
        return single (f.apply (1)).add (f.apply (NAMES.size () + 1));
    }
    
    private TaskTests <?, ?> prepareTask3 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            checker.accept (impl.task3 (names), ref.task3 (names));
        };
        
        return single (f.apply (NAMES.size () + 3));
    }
    
    private TaskTests <?, ?> prepareTask4 (StreamTasks ref) {
        return single ((impl, checker) -> checker.accept (impl.task4 (), ref.task4 ()));
    }
    
    private TaskTests <?, ?> prepareTasks5n6 (StreamTasks ref, boolean parallel) {
        final TF1 f = n -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            checker.accept (
                parallel ? impl.task6 (names) : impl.task5 (names), 
                parallel ? list (ref.task6 (names)) : list (ref.task5 (names))
            );
        };
        
        return single (f.apply (1), parallel).add (f.apply (NAMES.size () + 1), parallel);
    }
    
    private TaskTests <?, ?> prepareTask7 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var names = listWithNulls (randomSubsequence (NAMES, n, R), m, R);
            checker.accept (impl.task7 (names), list (ref.task7 (names)));
        };
        
        return single (f.apply (1, 2)).add (f.apply (5, 3)).add (f.apply (3, 5));
    }
    
    private TaskTests <?, ?> prepareTask8 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            checker.accept (impl.task8 (names), list (ref.task8 (names)));
        };
        
        return single (f.apply (1)).add (f.apply (3)).add (f.apply (10));
    }
    
    private TaskTests <?, ?> prepareTask9 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var names = listWithNulls (randomSubsequence (NAMES, n, R), m, R);
            checker.accept (impl.task9 (names), list (ref.task9 (names)));
        };
        
        return single (f.apply (1, 2)).add (f.apply (5, 3)).add (f.apply (10, 15));
    }
    
    private TaskTests <?, ?> prepareTask10 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            checker.accept (impl.task10 (names), list (ref.task10 (names)));
        };
        
        return single (f.apply (2)).add (f.apply (9)).add (f.apply (NAMES.size () + 5));
    }
    
    private TaskTests <?, ?> prepareTask11 (StreamTasks ref) {
        final Function <String, String> mapper = s -> s.concat ("Solk");
        final TF1 f = n -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            checker.accept (impl.task11 (names, mapper), list (ref.task11 (names, mapper)));
        };
        
        return single (f.apply (2)).add (f.apply (6)).add (f.apply (NAMES.size () + 15));
    }
    
    private TaskTests <?, ?> prepareTask12 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            checker.accept (impl.task12 (names), set (ref.task12 (names)));
        };
        
        return single (f.apply (2)).add (f.apply (17)).add (f.apply (NAMES.size () + 10));
    }
    
    private TaskTests <?, ?> prepareTask13 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var numbers = randomSubsequence (STR_NUMBERS, n, R);
            checker.accept (impl.task13 (numbers), list (ref.task13 (numbers)));
        };
        
        return single (f.apply (1)).add (f.apply (5)).add (f.apply (NAMES.size () + 1));
    }
    
    private TaskTests <?, ?> prepareTask14 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var numbers = randomSubsequence (STR_NUMBERS, n, R);
            checker.accept (impl.task14 (numbers), list (ref.task14 (numbers)));
        };
        
        return single (f.apply (1)).add (f.apply (7)).add (f.apply (NAMES.size () + 3));
    }
    
    private TaskTests <?, ?> prepareTask15 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var numbers = randomSubsequence (STR_NUMBERS, n, R);
            checker.accept (impl.task15 (numbers), list (ref.task15 (numbers)));
        };
        
        return single (f.apply (1)).add (f.apply (5)).add (f.apply (NAMES.size () + 3));
    }
    
    private TaskTests <?, ?> prepareTask16 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var numbers = randomSubsequence (STR_NUMBERS, n, R);
            checker.accept (impl.task16 (numbers), ref.task16 (numbers));
        };
        
        return single (f.apply (1)).add (f.apply (5)).add (f.apply (10));
    }
    
    private TaskTests <?, ?> prepareTask17 (StreamTasks ref) {
        final TF0 f = () -> (impl, checker) -> {
            checker.accept (impl.task17 (), list (ref.task17 ()));
        };
        
        return single (f.get ());
    }
    
    private TaskTests <?, ?> prepareTask18 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            checker.accept (impl.task18 (names, m), list (ref.task18 (names, m)));
        };
        
        return single (f.apply (10, 7)).add (f.apply (15, 20)).add (f.apply (NAMES.size () + 7, 20));
    }
    
    private TaskTests <?, ?> prepareTask19 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            checker.accept (impl.task19 (names, m), list (ref.task19 (names, m)));
        };
        
        return single (f.apply (10, 5)).add (f.apply (15, 4)).add (f.apply (NAMES.size () + 1, 6));
    }
    
    private TaskTests <?, ?> prepareTask20 (StreamTasks ref) {
        final Comparator <String> comparator = (a, b) -> Integer.compare (a.length (), b.length ());
        final TF1 f = n -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            checker.accept (impl.task20 (names, comparator), list (ref.task20 (names, comparator)));
        };
        
        return single (f.apply (10)).add (f.apply (15)).add (f.apply (NAMES.size () + 3));
    }
    
    private TaskTests <?, ?> prepareTask21 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var names1 = randomSubsequence (NAMES, n, R);
            final var names2 = randomSubsequence (NAMES, m, R);
            checker.accept (impl.task21 (names1, names2), list (ref.task21 (names1, names2)));
        };
        
        return single (f.apply (0, 10)).add (f.apply (10, 10))
             . add (f.apply (10, 0)).add (f.apply (10, 4));
    }
    
    private TaskTests <?, ?> prepareTask22 (StreamTasks ref) {
        return single ((impl, checker) -> {
            final var names = NAMES.subList (0, 3);
            
            final var name2value = Map.of (names.get (0), 43, names.get (1), -32, names.get (2), 150);
            checker.accept (impl.task22 (names, name2value), list (ref.task22 (names, name2value)));
        }).add ((impl, checker) -> {
            final var names = NAMES.subList (0, 10);
            
            final var numbers = listSequence (0, 10, i -> Integer.parseInt (STR_NUMBERS.get (i)));
            final var name2value = Map.of (
                names.get (0), numbers.get (0), names.get (1), numbers.get (1), 
                names.get (2), numbers.get (2), names.get (3), numbers.get (3), 
                names.get (4), numbers.get (4), names.get (5), numbers.get (5), 
                names.get (6), numbers.get (6), names.get (7), numbers.get (7),
                names.get (8), numbers.get (8), names.get (9), numbers.get (9)
            );
            
            checker.accept (impl.task22 (names, name2value), list (ref.task22 (names, name2value)));
        });
    }
    
    private TaskTests <?, ?> prepareTask23 (StreamTasks ref) {
        return single ((impl, checker) -> {
            final var names = listOfUniques (randomSubsequence (NAMES, 10, R));
            final var name2value = Map.of (names.get (0), 43, names.get (1), -32, names.get (3), 150);
            checker.accept (impl.task23 (names, name2value), ref.task23 (names, name2value));
        }).add ((impl, checker) -> {
            final var numbers = listSequence (0, 10, i -> Integer.parseInt (STR_NUMBERS.get (i)));
            final var names = listOfUniques (randomSubsequence (NAMES, 15, R));
            final var name2value = Map.of (
                names.get (0), numbers.get (0), names.get (1), numbers.get (1), 
                names.get (3), numbers.get (3), names.get (5), numbers.get (5)
            );
            
            checker.accept (impl.task23 (names, name2value), ref.task23 (names, name2value));
        });
    }
    
    private TaskTests <?, ?> prepareTask24 (StreamTasks ref) {
        final TF3 f = (n, m, k) -> (impl, checker) -> {
            final var numbers = randomSubsequence (INT_NUMBERS, n, R);
            checker.accept (impl.task24 (numbers, m, k), list (ref.task24 (numbers, m, k)));
        };
        
        return single (f.apply (10, 2, 4)).add (f.apply (20, 4, 10))
             . add (f.apply (30, 8, 8)).add (f.apply (30, 21, 28));
    }
    
    private TaskTests <?, ?> prepareTask25 (StreamTasks ref) {
        final TF5 f = (n, m, k, a, b) -> (impl, checker) -> {
            final var numbers = randomSubsequence (INT_NUMBERS, n, R);
            checker.accept (impl.task25 (numbers, m, k, a, b), list (ref.task25 (numbers, m, k, a, b)));
        };
        
        return single (f.apply (10, 2, 4, 5, 8)).add (f.apply (20, 4, 10, 12, 12))
             . add (f.apply (30, 8, 8, 12, 15)).add (f.apply (30, 21, 28, 2, 10))
             . add (f.apply (30, 21, 28, 2, 40));
    }
    
    private TaskTests <?, ?> prepareTask26 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            final var name = names.get (m);
            checker.accept (impl.task26 (names.stream (), name) ? 1 : 0, ref.task26 (names.stream (), name) ? 1 : 0);
        };
        
        return single (f.apply (1, 0)).add (f.apply (20, 14)).add ((impl, checker) -> {
            final var names = randomSubsequence (NAMES, NAMES.size () + 1, R);
            final var name = NAMES.get (R.nextInt (NAMES.size ()));
            
            checker.accept (impl.task26 (names.stream (), name) ? 1 : 0, ref.task26 (names.stream (), name) ? 1 : 0);
        });
    }
    
    private TaskTests <?, ?> prepareTask27 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            final var name = names.get (m);
            checker.accept (impl.task27 (names.stream (), name), ref.task27 (names.stream (), name));
        };
        
        return single (f.apply (1, 0)).add (f.apply (25, 18)).add ((impl, checker) -> {
            final var names = randomSubsequence (NAMES, NAMES.size () + 1, R);
            final var name = NAMES.get (R.nextInt (NAMES.size ()));
            
            checker.accept (impl.task27 (names.stream (), name), ref.task27 (names.stream (), name));
        });
    }
    
    private TaskTests <?, ?> prepareTask28 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var lastnames = randomSubsequence (LAST_NAMES, n, R);
            final var names = randomSubsequence (NAMES, m, R);
            checker.accept (impl.task28 (names, lastnames), list (ref.task28 (names, lastnames)));
        };
        
        return single (f.apply (1, 1)).add (f.apply (10, 10)).add (f.apply (30, 20));
    }
    
    private TaskTests <?, ?> prepareTask29 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            checker.accept (impl.task29 (names.stream ()), ref.task29 (names.stream ()));
        };
        
        return single (f.apply (1)).add (f.apply (20)).add (f.apply (NAMES.size () + 4));
    }
    
    private TaskTests <?, ?> prepareTask30 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            checker.accept (impl.task30 (names.stream ()), ref.task30 (names.stream ()));
        };
        
        return single (f.apply (1)).add (f.apply (30)).add (f.apply (NAMES.size () + 1));
    }
    
    private TaskTests <?, ?> prepareTask31 (StreamTasks ref) {
        final TF0 f = () -> (impl, checker) -> {
            final var stream = Stream.iterate (0, i -> i + 1);
            final var answer = impl.task31 (stream);
            checker.accept (answer, answer);
        };
        
        return single (f.get ());
    }
    
    private TaskTests <?, ?> prepareTask32 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            checker.accept (impl.task32 (names.stream ()), ref.task32 (names.stream ()));
        };
        
        return single (f.apply (1)).add (f.apply (20)).add (f.apply (NAMES.size () + 4));
    }
    
    private TaskTests <?, ?> prepareTask33 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            checker.accept (impl.task33 (names.stream ()), ref.task33 (names.stream ()));
        };
        
        return single (f.apply (1)).add (f.apply (20)).add (f.apply (NAMES.size () + 4));
    }
    
    private TaskTests <?, ?> prepareTask34 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var names = IntStream.range (0, n)
                . mapToObj (i -> randomSubsequence (NAMES, m, R))
                . collect (Collectors.toList ());
            
            checker.accept (impl.task34 (names.stream ()), ref.task34 (names.stream ()));
        };
        
        return single (f.apply (1, 0)).add (f.apply (10, 0)).add (f.apply (7, 3))
             . add (f.apply (15, 5)).add (f.apply (20, NAMES.size () + 1));
    }
    
    private TaskTests <?, ?> prepareTask35 (StreamTasks ref) {
        final TF3 f = (n, m, k) -> (impl, checker) -> {
            final var names = IntStream.range (0, n)
                . mapToObj (i -> randomSubsequence (NAMES, m + i, R))
                . collect (Collectors.toList ());
            final var name = NAMES.get (k);
            
            checker.accept (impl.task35 (names.stream (), name), ref.task35 (names.stream (), name));
        };
        
        int lim = NAMES.size ();
        return single (f.apply (1, 0, 0)).add (f.apply (10, 5, 0)).add (f.apply (17, 10, 0))
             . add (f.apply (15, 15, R.nextInt (lim)))
             . add (f.apply (20, NAMES.size () + 1, R.nextInt (lim)));
    }
    
    private TaskTests <?, ?> prepareTask36 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var matrix = IntStream.range (0, n)
                . mapToObj (i -> List.copyOf (randomSubsequence (INT_NUMBERS, m, R)))
                . collect (Collectors.toUnmodifiableList ());
            
            checker.accept (impl.task36 (matrix), ref.task36 (matrix));
        };
        
        return single (f.apply (1, 1)).add (f.apply (2, 2)).add (f.apply (5, 5))
             . add (f.apply (10, 15)).add (f.apply (20, INT_NUMBERS.size () + 1));
    }
    
    private TaskTests <?, ?> prepareTask37 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var matrix = IntStream.range (0, n)
                . mapToObj (i -> List.copyOf (randomSubsequence (INT_NUMBERS, m, R)))
                . collect (Collectors.toUnmodifiableList ());
            
            checker.accept (impl.task37 (matrix), ref.task37 (matrix));
        };
        
        return single (f.apply (1, 1)).add (f.apply (2, 2)).add (f.apply (5, 5)).add (f.apply (10, 10))
             . add (f.apply (INT_NUMBERS.size () + 3, INT_NUMBERS.size () + 3));
    }
    
    private TaskTests <?, ?> prepareTask38 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var matrix = IntStream.range (0, n)
                . mapToObj (i -> List.copyOf (randomSubsequence (INT_NUMBERS, m, R)))
                . collect (Collectors.toUnmodifiableList ());
            
            checker.accept (impl.task38 (matrix), ref.task38 (matrix));
        };
        
        return single (f.apply (1, 1)).add (f.apply (2, 5)).add (f.apply (5, 10)).add (f.apply (10, 5))
             . add (f.apply (100, INT_NUMBERS.size () + 3));
    }
    
    private TaskTests <?, ?> prepareTask39 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            checker.accept (impl.task39 (names.stream ()), list (ref.task39 (names.stream ())));
        };
        
        return single (f.apply (1)).add (f.apply (20)).add (f.apply (NAMES.size () + 4));
    }
    
    private TaskTests <?, ?> prepareTask40 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var numbers = randomSubsequence (INT_NUMBERS, n, R);
            checker.accept (impl.task40 (numbers, m), ref.task40 (numbers, m));
        };
        
        return single (f.apply (1, 1)).add (f.apply (1, 2)).add (f.apply (2, 2))
             . add (f.apply (3, 2)).add (f.apply (10, 2)).add (f.apply (10, 3))
             . add (f.apply (INT_NUMBERS.size () + 1, 7));
    }
    
    private TaskTests <?, ?> prepareTask41 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var matrix = IntStream.range (0, n)
                . mapToObj (i -> List.copyOf (randomSubsequence (INT_NUMBERS, m, R)))
                . collect (Collectors.toUnmodifiableList ());
            checker.accept (impl.task41 (matrix, n, m), ref.task41 (matrix, n, m));
        };
        
        return single (f.apply (1, 1)).add (f.apply (1, 2)).add (f.apply (2, 2))
             . add (f.apply (3, 2)).add (f.apply (8, 4)).add (f.apply (7, 23))
             . add (f.apply (INT_NUMBERS.size () + 1, INT_NUMBERS.size () / 2));
    }
    
    private TaskTests <?, ?> prepareTask42 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var matrix = IntStream.range (0, n)
                . mapToObj (i -> List.copyOf (randomSubsequence (INT_NUMBERS, m, R)))
                . collect (Collectors.toUnmodifiableList ());
            checker.accept (impl.task42 (matrix, n, m), ref.task42 (matrix, n, m));
        };
        
        return single (f.apply (1, 1)).add (f.apply (2, 2)).add (f.apply (5, 5))
             . add (f.apply (3, 3)).add (f.apply (10, 10)).add (f.apply (13, 13))
             . add (f.apply (INT_NUMBERS.size () + 2, INT_NUMBERS.size () + 2));
    }
    
    private TaskTests <?, ?> prepareTask43 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var names = listWithNulls (randomSubsequence (NAMES, n, R), m, R);
            final Predicate <String> test1 = str -> str.toLowerCase ().contains ("b");
            final Predicate <String> test2 = str -> str.length () >= m;
            
            checker.accept (
                impl.task43 (names.stream (), test1, test2), 
                list (ref.task43 (names.stream (), test1, test2))
            );
        };
        
        return single (f.apply (1, 3)).add (f.apply (5, 5)).add (f.apply (15, 5))
             . add (f.apply (NAMES.size () + 4, 4));
    }
    
    private TaskTests <?, ?> prepareTask44 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var sum = new AtomicInteger (0);
            final Supplier <Integer> supplier = () -> {
                final var num = R.nextInt (1000);
                sum.addAndGet (num);
                return num;
            };
            
            checker.accept (impl.task44 (supplier, n), sum.get ());
        };
        
        return single (f.apply (1)).add (f.apply (5)).add (f.apply (12)).add (f.apply (86));
    }
    
    private TaskTests <?, ?> prepareTask45 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var accumulator = new ArrayList <Integer> ();
            final Consumer <Integer> handler = accumulator::add;
            
            final var numbers = randomSubsequence (INT_NUMBERS, n, R);
            final var answer = new ArrayList <> (Set.copyOf (numbers));
            Collections.sort (answer);
            
            impl.task45 (numbers.stream ().map (Integer::toHexString), handler);
            checker.accept (accumulator, answer);
        };
        
        return single (f.apply (1)).add (f.apply (10)).add (f.apply (24)).add (f.apply (97))
             . add (f.apply (INT_NUMBERS.size () + 6));
    }
    
    private TaskTests <?, ?> prepareTask46 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var ps = IntStream.range (0, n).<Predicate <String>> mapToObj (
                i -> str -> str.toLowerCase ().contains (String.format ("%c", 'a' + i))
            ).collect (Collectors.toList ());
            
            final Predicate <String> pImpl = impl.task46 (ps), pRef = ref.task46 (ps);
            final var names = randomSubsequence (NAMES, m, R);
            
            checker.accept (
                impl.task43 (names.stream (), pImpl, __ -> false), 
                list (ref.task43 (names.stream (), pRef, __ -> false))
            );
        };
        
        return single (f.apply (1, 1)).add (f.apply (10, 10)).add (f.apply (24, 10))
             . add (f.apply (10, 30)).add (f.apply (25, NAMES.size () + 6));
    }
    
    private TaskTests <?, ?> prepareTask47 (StreamTasks ref) {
        final var presets = List.of (
            "(((())", "(((())))", "()(()))", "()(())", "((()()(()))())", "((()()(()))(())", 
            ")()()))()(()())", "((()()()()))", "(((((()()((())))((()))(((((()))))))))))((()))",
            "((((()))((()))))(((((())(()()()()())))))", "((((((())((()))))(()(()()()())))))",
            "(((((()))(()))((((())((()()()()()))(((()))))))))((())()))(((())())()())((())(()))"
        );
        
        final TF1 f = n -> (impl, checker) -> {
            final var sequence = presets.get (n);
            checker.accept (
                impl.task47 (sequence.chars ()) ? 1 : 0, 
                ref.task47 (sequence.chars ()) ? 1 : 0
            );
        };
        
        final var tests = single (f.apply (1));
        IntStream.range (1, presets.size ()).forEach (i -> tests.add (f.apply (i)));
        return tests;
    }
    
    private TaskTests <?, ?> prepareTask48 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var answer = new ArrayList <Integer> ();
            answer.add (0);
            
            final Supplier <Integer> delta = () -> {
                final var d = R.nextInt (1000);
                
                answer.add (answer.get (answer.size () - 1) + d);
                return d;
            };
            
            final var provided = list (impl.task48 (n, delta));
            assert answer.size () == n && provided.size () == n : String.format (
                "Stream should countain %d numbers (actual size: %d)", 
                n, provided.size ()
            );
            
            checker.accept (provided, answer);
        };
        
        return single (f.apply (1)).add (f.apply (20)).add (f.apply (112));
    }
    
    private TaskTests <?, ?> prepareTask49 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var number = R.nextInt (n);
            
            final var counter = new AtomicInteger ();
            final ToIntFunction <Integer> comparator = i -> {
                counter.incrementAndGet (); // how much time comparator was called
                return (int) Math.signum (i - number);
            };
            
            checker.accept (impl.task49 (comparator, n), number);
            assert counter.get () <= Math.ceil (Math.log (n) / Math.log (2)) + 1 
                 : "You used too many comparing operations";
        };
        
        return single (f.apply (100)).add (f.apply (1)).add (f.apply (1000))
             . add (f.apply (100000)).add (f.apply (1000000));
    }
    
    private TaskTests <?, ?> prepareTask50 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var names = randomSubsequence (NAMES, n, R);
            checker.accept (impl.task50 (names).limit (m), list (ref.task50 (names).limit (m)));
        };
        
        return single (f.apply (1, 3)).add (f.apply (5, 7)).add (f.apply (3, 8)).add (f.apply (5, 16))
             . add (f.apply (NAMES.size () + 4, 4)).add (f.apply (3, NAMES.size ()));
    }
    
    private TaskTests <?, ?> prepareTask51 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var numbers = randomSubsequence (INT_NUMBERS, n, R);
            checker.accept (impl.task51 (numbers), list (ref.task51 (numbers)));
        };
        
        return single (f.apply (1)).add (f.apply (5)).add (f.apply (8))
             . add (f.apply (11)).add (f.apply (84)).add (f.apply (389));
    }
    
    private static final List <Item> ITEMS = listSequence (0, 1000, i -> {
        final var item = new Item ();
        item.setBarcode (String.format ("%04d", R.nextInt (10000)));
        item.setWeight (R.nextDouble () * 3000);
        item.setCategory (randomCategory ());
        
        return item;
    });
    
    private static char randomCategory () {
        return (char) ('A' + R.nextInt (26));
    }
    
    private TaskTests <?, ?> prepareTask52 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var items = randomSubsequence (ITEMS, n, R);
            checker.accept (impl.task52 (items), ref.task52 (items));
        };
        
        return single (f.apply (1)).add (f.apply (3)).add (f.apply (5))
             . add (f.apply (8)).add (f.apply (23)).add (f.apply (88))
             . add (f.apply (10 + R.nextInt (100)));
    }
    
    private TaskTests <?, ?> prepareTask53 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var items = randomSubsequence (ITEMS, n, R);
            final var cat = randomCategory ();
            
            checker.accept (impl.task53 (items, cat), ref.task53 (items, cat));
        };
        
        return single (f.apply (1)).add (f.apply (3)).add (f.apply (7))
             . add (f.apply (6)).add (f.apply (29)).add (f.apply (82))
             . add (f.apply (10 + R.nextInt (100)));
    }
    
    private TaskTests <?, ?> prepareTask54 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var items = randomSubsequence (ITEMS, n, R);
            checker.accept (impl.task54 (items), ref.task54 (items));
        };
        
        return single (f.apply (1)).add (f.apply (12)).add (f.apply (19))
             . add (f.apply (60)).add (f.apply (94)).add (f.apply (182))
             . add (f.apply (100 + R.nextInt (1000)))
             . add (f.apply (ITEMS.size () + 1));
    }
    
    private TaskTests <?, ?> prepareTask55 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var items = randomSubsequence (ITEMS, n, R);
            checker.accept (impl.task55 (items), ref.task55 (items));
        };
        
        return single (f.apply (1)).add (f.apply (37)).add (f.apply (79))
             . add (f.apply (90)).add (f.apply (174)).add (f.apply (253))
             . add (f.apply (500 + R.nextInt (1000)))
             . add (f.apply (ITEMS.size () + 1));
    }
    
    private TaskTests <?, ?> prepareTask56n57 (StreamTasks ref, boolean parallel) {
        final BiFunction <List <Item>, StreamTasks, Box> call = (list, impl) -> {
            final var stream = parallel ? list.parallelStream () : list.stream ();
            return parallel ? impl.task57 (stream) : impl.task56 (stream);
        };
        
        final TF1 f = n -> (impl, checker) -> {
            final var items = randomSubsequence (ITEMS, n, R);
            
            final var answer = Optional.ofNullable (call.apply (items, impl)).orElse (new Box ());
            checker.accept (answer.getItems (), call.apply (items, ref).getItems ());
        };
        
        return single (f.apply (1)).add (f.apply (22)).add (f.apply (31))
             . add (f.apply (46)).add (f.apply (100 + R.nextInt (1000)))
             . add (f.apply (ITEMS.size () + 2));
    }
    
    private TaskTests <?, ?> prepareTask58 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var boxes = Collections.unmodifiableList (generateBoxes (n, m, 100, ref));
            checker.accept (impl.task58 (boxes), list (ref.task58 (boxes)));
        };
        
        return single (f.apply (1, 2)).add (f.apply (37, 2)).add (f.apply (79, 2))
             . add (f.apply (90, 6)).add (f.apply (174, 6)).add (f.apply (253, 6))
             . add (f.apply (500 + R.nextInt (1000), 18))
             . add (f.apply (ITEMS.size () + 1, 30));
    }
    
    private TaskTests <?, ?> prepareTask59 (StreamTasks ref) {
        final TF2 f = (n, m) -> (impl, checker) -> {
            final var boxes = Collections.unmodifiableList (generateBoxes (n, m, m * 2, ref));
            final var weight = 2000.0 + R.nextDouble () * 500;
            
            checker.accept (impl.task59 (boxes, weight), list (ref.task59 (boxes, weight)));
        };
        
        return single (f.apply (1, 2)).add (f.apply (17, 2)).add (f.apply (29, 2))
             . add (f.apply (90, 6)).add (f.apply (174, 6)).add (f.apply (253, 6))
             . add (f.apply (500 + R.nextInt (1000), 11))
             . add (f.apply (ITEMS.size () + 1, 15));
    }
    
    private TaskTests <?, ?> prepareTask60 (StreamTasks ref) {
        final TF1 f = n -> (impl, checker) -> {
            final var items = new ArrayList <Item> ();
            final var stream = Stream.generate (() -> {
                final var item = ITEMS.get (R.nextInt (ITEMS.size ()));
                items.add (item);
                return item;
            });
            
            final var limit = n * 1000 * R.nextDouble ();
            final var box = new Box ();
            
            impl.task60 (stream, limit, box);
            
            final var answer = new ArrayList <> ();
            for (int i = 0, w = 0; i < items.size () && w + items.get (i).getWeight () <= limit; i++) {
                w += items.get (i).getWeight ();
                answer.add (items.get (i));
            }
            
            checker.accept (box.getItems (), answer);
        };
        
        return single (f.apply (3)).add (f.apply (4)).add (f.apply (5)).add (f.apply (6)).add (f.apply (50));
    }
    
    // USEFUL METHODS //
    
    private static <R, O> TaskTests <R, O> empty () {
        return new TaskTests <R, O> ();
    }
    
    private static <R, O> TaskTests <R, O> single (BiConsumer <StreamTasks, BiConsumer <R, O>> testCase, boolean parallel) {
        return TestsPool.<R, O> empty ().add (testCase, parallel);
    }
    
    private static <R, O> TaskTests <R, O> single (BiConsumer <StreamTasks, BiConsumer <R, O>> testCase) {
        return single (testCase, false);
    }
    
    private static <T> List <T> randomSubsequence (Collection <T> values, int size, Random r) {
        return randomSubsequence (values, Function.identity (), size, r);
    }
    
    private static <T, R> List <R> randomSubsequence (
        Collection <T> values, Function <T, R> mapper, int size, Random r
    ) {
        final var input = List.copyOf (values);
        
        return IntStream.range (0, size).map (i -> r.nextInt (input.size ()))
             . mapToObj (input::get).map (mapper)
             . collect (Collectors.toList ());
    }
    
    private static <T> List <T> list (Stream <T> stream) {
        return stream.collect (Collectors.toList ());
    }
    
    private static List <Integer> list (IntStream stream) {
        return list (stream.mapToObj (i -> i));
    }
    
    private static <T> Set <T> set (Stream <T> stream) {
        return stream.collect (Collectors.toSet ());
    }
    
    private static <T> List <T> listOfUniques (List <T> values) {
        return List.copyOf (Set.copyOf (values));
    }
    
    private static <T> List <T> listSequence (int from, int length, IntFunction <T> converter) {
        return IntStream.range (from, from + length).mapToObj (converter).collect (Collectors.toList ());
    }
    
    private static <T> List <T> listWithNulls (Collection <T> values, int nulls, Random r) {
        final var list = new ArrayList <> (values);
        while (nulls-- > 0) { list.add (null); }
        
        Collections.shuffle (list, r);
        return list;
    }
    
    private static List <Box> generateBoxes (int number, int sizeFrom, int sizeDeviation, StreamTasks impl) {
        return IntStream.range (0, number).mapToObj (__ -> {
            return randomSubsequence (ITEMS, sizeFrom + R.nextInt (sizeDeviation), R);
        }).map (its -> impl.task57 (its.stream ())).collect (Collectors.toList ());
    }
    
}
