package tests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

public class TaskTests {
    
    /*******************************/
    /* DO NOT TOUCH METHODS BELLOW */
    /*******************************/
    
    private final List <?> inputs; 
    private final Method method;
    
    public TaskTests (Method method, List <?> inputs) {
        this.method = method; this.inputs = inputs;
    }
    
    private final List <BiFunction <StreamTasksTests, StreamTasksTests, InvocationResult>> cases = new ArrayList <> ();
    
    public TaskTests addCase (BiFunction <StreamTasksTests, StreamTasksTests, InvocationResult> caze) {
        cases.add (caze);
        return this;
    }
    
    public int getCasesNumber () {
        return cases.size ();
    }
    
    public Method getMethod () {
        return method;
    }
    
    public List <?> getInputs () {
        return Collections.unmodifiableList (inputs);
    }
    
    public InvocationResult runTests (StreamTasksTests implementation, StreamTasksTests reference) {
        final var stub = new InvocationResult (null, 0);
        return cases.stream ().map (caze -> caze.apply (implementation, reference))
             . reduce (stub, (a, b) -> a == stub && b != null ? b : a);
    }
    
}
