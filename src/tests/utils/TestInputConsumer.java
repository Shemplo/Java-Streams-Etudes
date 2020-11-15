package tests.utils;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import tests.inputs.ConsumerMode;


@Target (PARAMETER)
@Retention (RUNTIME)
public @interface TestInputConsumer {
    
    ConsumerMode mode () default ConsumerMode.LIST_COLLECTOR;
    
}
