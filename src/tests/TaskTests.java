package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class TaskTests {
    
    /*******************************/
    /* DO NOT TOUCH METHODS BELLOW */
    /*******************************/
    
    private final List <BiConsumer <StreamTasksTests, StreamTasksTests>> cases = new ArrayList <> ();
    
    public TaskTests addCase (BiConsumer <StreamTasksTests, StreamTasksTests> caze) {
        cases.add (caze);
        return this;
    }
    
    public int getCasesNumber () {
        return cases.size ();
    }
    
    public void runTests (StreamTasksTests implementation, StreamTasksTests reference) {
        cases.forEach (caze -> caze.accept (implementation, reference));
    }
    
}
