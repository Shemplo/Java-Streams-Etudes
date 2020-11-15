package tests;

import java.util.List;
import java.util.Set;

import tests.utils.Test;
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
    
}
