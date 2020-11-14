package tests.utils;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.function.ToIntFunction;


@Target (METHOD)
@Retention (RUNTIME)
public @interface Test {
    
    public static final ToIntFunction <Method> GET_ORDER =  m -> m.getAnnotation (Test.class).order ();
    
    int order ();
    
    String description () default "";
    
}
