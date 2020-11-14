package tests;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import tests.presets.Names;
import tests.utils.Test;
import tests.utils.TestInputCollection;
import tests.utils.TestInputConstant;
import tests.utils.TestResult;

public abstract class StreamTasksTests {
    
    @Test (order = 1)
    @TestResult (wrap = List.class)
    public abstract Set <String> task1 (
        @TestInputCollection (presets = {Names.class}, constant = {1, 5}, variation = 5)
        List <String> names
    );
    
    @Test (order = 2)
    @TestResult (wrap = Set.class)
    public abstract List <String> task2 (
        @TestInputCollection (presets = {Names.class}, percentage = {1.01, 1.05, 1.1}, variation = 10)
        List <String> names
    );
    
    @Test (order = 3)
    @TestResult (wrap = Set.class)
    public abstract List <String> task3 (
        @TestInputCollection (presets = {Names.class}, percentage = {1.01, 1.05, 1.1}, variation = 10)
        Set <String> names
    );
    
    @Test (order = 4)
    @TestResult (wrap = Void.class)
    public abstract int task4 (
        @TestInputCollection (presets = {Names.class}, percentage = {1.01, 1.05, 1.1}, variation = 10)
        Set <String> names
    );
    
    @Test (order = 5)
    @TestResult (wrap = Void.class)
    public abstract int task5 (
        @TestInputCollection (presets = {Names.class}, percentage = {1.01, 1.05, 1.1}, variation = 10)
        Set <String> names,
        @TestInputConstant (constant = {3, 6}, sequence = {0}, parameter = 4, variation = 8)
        int length
    );
    
    @Test (order = 6)
    @TestResult (wrap = Void.class)
    public abstract List <String> task6 (
        @TestInputCollection (presets = {Names.class}, percentage = {1.01, 1.05, 2.1})
        Stream <String> names
    );
    
    @Test (order = 6)
    @TestResult (wrap = List.class)
    public abstract Stream <String> task7 (
        @TestInputCollection (presets = {Names.class}, percentage = {1.01, 1.05, 2.1})
        Stream <String> names,
        @TestInputCollection (presets = {Names.class}, percentage = {0.33, 0.45})
        Stream <String> names2
    );
    
}
