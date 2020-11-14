package tests.utils;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import tests.presets.DataPreset;
import tests.presets.SupplierMode;


@Target (PARAMETER)
@Retention (RUNTIME)
public @interface TestInputSupplier {
    
    Class <? extends DataPreset <?>> [] presets ();
    
    SupplierMode mode () default SupplierMode.SHUFFLED_SEQUENTIAL;
    
    int [] cycles () default {-1};
    
}
