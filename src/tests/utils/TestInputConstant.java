package tests.utils;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Target (PARAMETER)
@Retention (RUNTIME)
public @interface TestInputConstant {
    
    double [] constant () default {};
    
    int [] sequence () default {};
    
    int parameter () default -1;
    
    int variation () default 0;
    
}
