package tests.utils;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import tests.inputs.LevelsArrange;
import tests.presets.DataPreset;


@Target (PARAMETER)
@Retention (RUNTIME)
public @interface TestInputCollection {
    
    Class <? extends DataPreset> [] presets () default {};
    
    int [] constant () default {};
    
    double [] percentage () default {};
    
    int variation () default 0;
    
    int [] nulls () default {0};
    
    int levels () default 1;
    
    /**
     * Now works only for non-Map inputs
     * @return
     */
    LevelsArrange arrengement () default LevelsArrange.DISHONEST;
    
    boolean allUnique () default false;
    
    boolean parallel () default false;
    
}
