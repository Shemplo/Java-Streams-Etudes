package tasks;
import tasks.solution.StreamTasksSolution;
import tests.TestsRunner;

public class StreamTasksMain {
    
    /*******************************/
    /* DO NOT TOUCH METHODS BELLOW */
    /*******************************/
    
    public static void main (String ... args) {
        StreamTasks implementation = null;
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
