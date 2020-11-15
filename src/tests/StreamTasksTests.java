package tests;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import tests.presets.IntNumbers;
import tests.presets.Names;
import tests.presets.StrNumbers;
import tests.utils.Test;
import tests.utils.TestInputCollection;
import tests.utils.TestInputConstant;
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
        @TestInputCollection (presets = {StrNumbers.class, Names.class}, percentage = {0.7, 0.8, 0.9}, variation = 10)
        Stream <T> values1,
        @TestInputCollection (presets = {Names.class}, percentage = {0.1, 0.2, 0.3}, variation = 10)
        Stream <T> values2
    );
    
    @Test (order = 1)
    @TestResult (repeat = 1, wrap = List.class)
    public abstract <T> Stream <T> task10 (
        @TestInputCollection (presets = {StrNumbers.class, Names.class}, percentage = {0.7, 0.8, 0.9}, variation = 10)
        Stream <T> values1,
        @TestInputCollection (presets = {Names.class}, percentage = {0.1, 0.2, 0.3}, variation = 10)
        Stream <T> values2,
        @TestInputCollection (presets = {StrNumbers.class}, percentage = {0.4, 0.5, 0.5}, variation = 10)
        Stream <T> values3
    );
    
}
