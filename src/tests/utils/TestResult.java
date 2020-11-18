package tests.utils;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Target (METHOD)
@Retention (RUNTIME)
public @interface TestResult {
    
    Class <?> wrap () default Void.class;
    
    boolean parallel () default false;
    
    int checkBy () default -1;
    
    /**
     * Works clearly only for methods without parameters.
     * In case of method with parameters it will have effect only
     * if this value is more then number of parameters combinations.
     * @return
     */
    int repeat () default 1;
    
}
