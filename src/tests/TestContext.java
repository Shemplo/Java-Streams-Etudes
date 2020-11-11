package tests;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestContext <T, O> {
    
    /*******************************/
    /* DO NOT TOUCH METHODS BELLOW */
    /*******************************/
    
    private final boolean parallelStream;
    private final List <T> input;
    private final O output;
    
    public TestContext (List <T> input, O output, boolean parallelStream) {
        this.parallelStream = parallelStream;
        this.output = output;
        this.input = input;
    }
    
    public TestContext (List <T> input, O output) {
        this (input, output, false);
    }
    
    public TestContext (Set <T> input, O output) {
        this (List.copyOf (input), output);
    }
    
    public TestContext (Stream <T> input, O output) {
        this (input.collect (Collectors.toList ()), output);
    }
    
    public List <T> getListInput () {
        return input;
    }
    
    public Set <T> getSetInput () {
        return Set.copyOf (input);
    }
    
    public Stream <T> getStreamInput () {
        return input.stream ();
    }
    
    public O getOutput () {
        return output;
    }
    
    public boolean isParallelStream () {
        return parallelStream;
    }
    
}
