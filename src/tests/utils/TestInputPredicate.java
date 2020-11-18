package tests.utils;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Target (PARAMETER)
@Retention (RUNTIME)
public @interface TestInputPredicate {
    
    int [] indices ();
    
}
