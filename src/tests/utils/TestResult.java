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
    
}
