package tests;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import tests.presets.HexStrNumbers;
import tests.presets.IntNumbers;
import tests.presets.IntStrNumbers;
import tests.presets.NamesAges;
import tests.presets.Names;
import tests.utils.Test;
import tests.utils.TestInputCollection;
import tests.utils.TestInputConstant;
import tests.utils.TestInputFunction;
import tests.utils.TestInputPredicate;
import tests.utils.TestResult;

public abstract class StreamTasksTests {
    
    @Test (order = 0)
    @TestResult (repeat = 1)
    public abstract List <Integer> task1 (
        @TestInputConstant (constant = {1, 2, 3, 4, 5, 6}) int a,
        @TestInputConstant (constant = {6, 7, 8}) int b,
        @TestInputConstant (constant = {1, 9, 2, 8, 3, 7, 4}) int c
    ); 
    
    @Test (order = 0)
    @TestResult (repeat = 1)
    public abstract Set <Integer> task2 (
        @TestInputConstant (constant = {6, 2, 8, 4, 0}) int a,
        @TestInputConstant (constant = {1, 3, 5, 7, 9}) int b,
        @TestInputConstant (constant = {1, 2, 5, 4, 3}) int c
    ); 
    
    @Test (order = 0)
    @TestResult (repeat = 1)
    public abstract <T> Set <T> task3 (
        @TestInputCollection (presets = {IntNumbers.class, Names.class}, constant = {5, 10, 20}, variation = 10)
        List <T> values
    ); 
    
    @Test (order = 0)
    @TestResult (repeat = 1, wrap = List.class)
    public abstract <T> Stream <T> task4 (
        @TestInputCollection (presets = {IntNumbers.class, Names.class}, constant = {5, 10, 20}, variation = 10)
        List <T> values
    ); 
    
    @Test (order = 0)
    @TestResult (repeat = 1, wrap = List.class)
    public abstract <T> Stream <T> task5 (
        @TestInputCollection (presets = {IntNumbers.class, Names.class}, constant = {5, 10, 20}, variation = 10)
        Set <T> values
    ); 
    
    @Test (order = 0)
    @TestResult (repeat = 1)
    public abstract <T> int task6 (
        @TestInputCollection (presets = {IntNumbers.class, Names.class}, constant = {5, 10, 20}, variation = 10)
        Stream <T> values
    ); 
    
    @Test (order = 0)
    @TestResult (repeat = 1, wrap = List.class)
    public abstract <T> Stream <T> task7 (
        @TestInputCollection (presets = {IntNumbers.class, Names.class}, percentage = {1.0, 2.0, 3.0}, variation = 10)
        Stream <T> values,
        @TestInputConstant (constant = {20, 30, 40}, variation = 10)
        int limit
    );
    
    @Test (order = 0)
    @TestResult (repeat = 1, wrap = List.class)
    public abstract <T> Stream <T> task8 (
        @TestInputCollection (presets = {IntNumbers.class, Names.class}, percentage = {1.0, 2.0, 3.0}, variation = 10)
        Stream <T> values,
        @TestInputConstant (constant = {5, 10, 15}, variation = 5)
        int n,
        @TestInputConstant (constant = {20, 30, 40}, variation = 10)
        int batch
    );
    
    @Test (order = 0)
    @TestResult (repeat = 1, wrap = List.class)
    public abstract <T> Stream <T> task9 (
        @TestInputCollection (presets = {IntStrNumbers.class, Names.class}, percentage = {0.7, 0.8, 0.9}, variation = 10)
        Stream <T> values1,
        @TestInputCollection (presets = {Names.class}, percentage = {0.1, 0.2, 0.3}, variation = 10)
        Stream <T> values2
    );
    
    @Test (order = 1)
    @TestResult (repeat = 1, wrap = List.class)
    public abstract <T> Stream <T> task10 (
        @TestInputCollection (presets = {IntStrNumbers.class, Names.class}, percentage = {0.7, 0.8, 0.9}, variation = 10)
        Stream <T> values1,
        @TestInputCollection (presets = {Names.class}, percentage = {0.1, 0.2, 0.3}, variation = 10)
        Stream <T> values2,
        @TestInputCollection (presets = {IntStrNumbers.class}, percentage = {0.4, 0.5, 0.5}, variation = 10)
        Stream <T> values3
    );
    
    @Test (order = 1)
    @TestResult (repeat = 1, wrap = List.class)
    public abstract Stream <Integer> task11 (
        @TestInputCollection (presets = {IntNumbers.class}, percentage = {0.7, 0.8, 0.9, 1.7}, variation = 10)
        Stream <Integer> numbers,
        @TestInputPredicate (indices = {1})
        Predicate <Integer> condition
    );
    
    @Test (order = 1)
    @TestResult (repeat = 1, checkBy = 10, wrap = List.class) // TODO: fix wrapping in single invokers
    public abstract Predicate <Integer> task12 (
        @TestInputConstant (constant = {100, 200, 300}, variation = 50)
        int from, 
        @TestInputConstant (constant = {500}, variation = 100)
        int to
    );
    
    @Test (order = 1)
    @TestResult (repeat = 1, checkBy = 10, wrap = List.class)
    public abstract Predicate <Integer> task13 (
        @TestInputPredicate (indices = {2})
        Predicate <Integer> positive,
        @TestInputPredicate (indices = {3})
        Predicate <Integer> negative
    );
    
    @Test (order = 1)
    @TestResult (repeat = 1, wrap = List.class)
    public abstract Stream <Integer> task14 (
        @TestInputCollection (presets = {IntNumbers.class}, percentage = {0.7, 0.8, 0.9, 1.7}, variation = 10)
        Stream <Integer> numbers,
        @TestInputFunction (indices = {2})
        Function <Integer, Integer> converter
    );
    
    @Test (order = 1)
    @TestResult (repeat = 1, checkBy = 13, wrap = List.class)
    public abstract Function <Integer, Integer> task15 (
        @TestInputConstant (constant = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, variation = 20)
        int k, 
        @TestInputConstant (constant = {-250}, variation = 500)
        int b
    );
    
    @Test (order = 1)
    @TestResult (repeat = 1, checkBy = 13, wrap = List.class)
    public abstract Function <Integer, Integer> task16 (
        @TestInputFunction (indices = {2, 4, 3})
        Function <Integer, Integer> f,
        @TestInputFunction (indices = {3})
        Function <Integer, Integer> g
    );
    
    @Test (order = 1)
    @TestResult (repeat = 1, wrap = List.class)
    public abstract Stream <Integer> task17 (
        @TestInputCollection (presets = {IntStrNumbers.class}, percentage = {0.7, 0.8, 0.9, 1.7}, variation = 20)
        Stream <String> numbers
    );
    
    @Test (order = 1)
    @TestResult (repeat = 1, wrap = List.class)
    public abstract Stream <Integer> task18 (
        @TestInputCollection (presets = {HexStrNumbers.class}, percentage = {0.8, 0.9, 1.3, 1.7}, variation = 20)
        Stream <String> numbers
    );
    
    @Test (order = 1)
    @TestResult (repeat = 1, wrap = List.class)
    public abstract Stream <Integer> task19 (
        @TestInputCollection (presets = {IntNumbers.class}, percentage = {0.9, 1.2, 1.8, 1.9}, variation = 20)
        Stream <Integer> numbers
    );
    
    @Test (order = 2)
    @TestResult (repeat = 1, wrap = List.class)
    public abstract Stream <Integer> task20 (
        @TestInputCollection (presets = {IntNumbers.class}, percentage = {0.9, 1.2, 1.8, 1.9}, variation = 10)
        Stream <Integer> numbers,
        @TestInputConstant (sequence = {0}, parameter = 2)
        int limit
    );
    
    @Test (order = 2)
    @TestResult (repeat = 1, wrap = List.class)
    public abstract IntStream task21 (
        @TestInputConstant (constant = {325, 5490, 3921, 53890}, variation = 500)
        int to
    );
    
    @Test (order = 2)
    @TestResult (repeat = 1, wrap = List.class)
    public abstract IntStream task22 (
        @TestInputCollection (presets = {IntNumbers.class}, percentage = {0.8, 0.9, 1.0}, variation = 10)
        List <Integer> numbers
    );
    
    @Test (order = 2)
    @TestResult (repeat = 1, wrap = List.class)
    public abstract IntStream task23 (
        @TestInputCollection (presets = {IntNumbers.class}, percentage = {0.4, 0.5, 1.2, 1.7, 2.0}, variation = 10)
        List <Integer> numbers,
        @TestInputConstant (constant = {2, 3, 4})
        int offset
    );
    
    @Test (order = 2)
    @TestResult (repeat = 1, checkBy = 6, wrap = List.class)
    public abstract Stream <Integer> task24 (
        @TestInputCollection (presets = {IntNumbers.class}, constant = {5, 10, 15}, variation = 10)
        List <Integer> numbers
    );
    
    @Test (order = 2)
    @TestResult (repeat = 1, wrap = List.class)
    public abstract IntStream task25 (
        @TestInputCollection (presets = {IntNumbers.class}, constant = {5, 10, 15}, 
            percentage = {1.3, 1.4}, variation = 10)
        List <Integer> numbers1,
        @TestInputCollection (presets = {IntNumbers.class}, constant = {5, 10, 15}, 
            percentage = {1.3, 1.4}, variation = 10)
        List <Integer> numbers2
    );
    
    @Test (order = 100)
    public abstract void testMapInput (
        @TestInputCollection (presets = {NamesAges.class}, constant = {10, 12}, variation = 5, nulls = {5, 1})
        Map <String, Integer> name2age
    );
    
}
