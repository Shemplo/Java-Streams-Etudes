package tasks;
import java.io.PrintStream;
import java.util.Locale;

import tasks.solution.StreamTasksSolution;
import tests.StreamTasksTests;
import tests.TestsRunner;

public class StreamTasksMain {
    
    /*******************************/
    /* DO NOT TOUCH METHODS BELLOW */
    /*******************************/
    
    public static PrintStream ORIGINAL_OUT = System.out;
    
    public static void main (String ... args) {
        Locale.setDefault (Locale.ENGLISH);
        
        try {
            assert false;
            System.out.println ("JVM flag `-ea` is not enabled");
            System.exit (1);
        } catch (AssertionError ae) {
            // This is necessary to check whether -ea flag is set
        }
        
        StreamTasksTests implementation = null;
        if (args.length > 0 && "validate".equals (args [0])) {            
            implementation = new StreamTasksSolution ();
        } else if (args.length > 0 && "example".equals (args [0])) {
            implementation = new StreamTasksExample ();
        } else {                
            implementation = new StreamTasks ();
        }
        
        int exitCode = new TestsRunner (implementation, new StreamTasksSolution ()).test ();
        System.exit (exitCode);
    }
    
}
