package tests;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import tests.presets.IntNumbers;
import tests.presets.Names;
import tests.presets.StrNumbers;
import tests.utils.Test;
import tests.utils.TestInputCollection;
import tests.utils.TestInputConstant;
import tests.utils.TestInputFunction;
import tests.utils.TestInputPredicate;
import tests.utils.TestInputSupplier;
import tests.utils.TestResult;

public abstract class StreamTasksTests {
    
    @Test (order = 0)
    @TestResult (wrap = List.class)
    public abstract Set <String> task1 (
        @TestInputCollection (presets = {Names.class}, constant = {1, 5}, variation = 5)
        List <String> names
    );
    
    @Test (order = 0)
    @TestResult (wrap = Set.class)
    public abstract List <String> task2 (
        @TestInputCollection (presets = {Names.class}, percentage = {1.01, 1.05, 1.1}, variation = 10)
        List <String> names
    );
    
    @Test (order = 0)
    @TestResult (wrap = Set.class)
    public abstract List <String> task3 (
        @TestInputCollection (presets = {Names.class}, percentage = {1.01, 1.05, 1.1}, variation = 10)
        Set <String> names
    );
    
    @Test (order = 0)
    @TestResult (wrap = Void.class)
    public abstract int task4 (
        @TestInputCollection (presets = {Names.class}, percentage = {1.01, 1.05, 1.1}, variation = 10)
        Set <String> names
    );
    
    @Test (order = 0)
    @TestResult (wrap = Void.class)
    public abstract int task5 (
        @TestInputCollection (presets = {Names.class}, percentage = {1.01, 1.05, 1.1}, variation = 10)
        Set <String> names,
        @TestInputConstant (constant = {3, 6}, sequence = {0}, parameter = 4, variation = 8)
        int length
    );
    
    @Test (order = 0)
    @TestResult (wrap = Void.class)
    public abstract List <String> task6 (
        @TestInputCollection (presets = {Names.class}, percentage = {1.01, 1.05, 2.1})
        Stream <String> names
    );
    
    @Test (order = 0)
    @TestResult (wrap = List.class)
    public abstract Stream <String> task7 (
        @TestInputCollection (presets = {Names.class}, percentage = {1.01, 1.05, 2.1})
        Stream <String> names,
        @TestInputCollection (presets = {Names.class}, percentage = {0.33, 0.45})
        Stream <String> names2
    );
    
    @Test (order = 0)
    @TestResult (wrap = List.class)
    public abstract Stream <String> task8 (
        @TestInputCollection (presets = {Names.class}, percentage = {1.01, 1.05, 2.1})
        Stream <String> names,
        @TestInputCollection (presets = {Names.class}, percentage = {0.33, 0.45})
        Stream <String> names2,
        @TestInputConstant (sequence = {0}, parameter = 0, variation = 10)
        int limit
    );
    
    @Test (order = 0)
    @TestResult (repeat = 1)
    public abstract <T> List <T> task9 (
        @TestInputCollection (presets = {Names.class, IntNumbers.class}, percentage = {0.91, 1.05, 1.51})
        Stream <T> values
    );
    
    @Test (order = 1)
    @TestResult (wrap = Void.class)
    public abstract <T> List <T> task10 (
        @TestInputSupplier (presets = {Names.class, IntNumbers.class})
        Supplier <T> generator, 
        @TestInputConstant (constant = {100}, variation = 10)
        int limit
    );
    
    @Test (order = 1)
    @TestResult (wrap = Void.class)
    public abstract List <Integer> task11 ();
    
    @Test (order = 1)
    @TestResult (wrap = Void.class)
    public abstract List <String> task12 (
        @TestInputCollection (presets = {Names.class}, constant = {6, 7}, 
            percentage = {0.91, 1.05, 1.51}, variation = 3) 
        Stream <String> names, 
        @TestInputPredicate (indices = {0})
        Predicate <String> condition
    );
    
    @Test (order = 1)
    @TestResult (wrap = Void.class, checkBy = 11, repeat = 2)
    public abstract Predicate <String> task13 ();
    
    
    
    @Test (order = 1)
    @TestResult (wrap = Void.class)
    public abstract int task14 (
        @TestInputConstant (constant = {43})
        int a
    );
    
    @Test (order = 1)
    @TestResult (wrap = Void.class, checkBy = 13, repeat = 7)
    public abstract int task15 (
        @TestInputConstant (constant = {2, 5, 6})
        int a, 
        @TestInputConstant (constant = {1, 3})
        int b
    );
    
    @Test (order = 1)
    @TestResult (wrap = Void.class, checkBy = 14, repeat = 2)
    public abstract int task16 ();
    
    @Test (order = 1)
    @TestResult (wrap = Void.class)
    public abstract List <Integer> task17 (
        @TestInputCollection (presets = {StrNumbers.class}, percentage = {1.04, 1.06})
        List <String> values, 
        @TestInputFunction (indices = {1})
        Function <String, Integer> converter
    );
    
}
