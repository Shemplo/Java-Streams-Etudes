package tests.presets;

import java.util.Collection;
import java.util.Map;
import java.util.Random;

import tests.inputs.LevelsArrange;
import tests.inputs.SequenceWithStatistics;

public interface DataPreset {
    
    DataPreset initialize (Random r);
    
    Object getData ();
    
    default int getSize () {
        final var data = getData ();
        if (data instanceof Map) {
            return ((Map <?, ?>) data).size ();
        } else if (data instanceof Collection) {
            return ((Collection <?>) data).size ();
        }
        
        return 0;
    }
    
    boolean doesSupportStatistics ();
    
    default <T> SequenceWithStatistics <T> getRandomSequence (int levels, int length, Random r, boolean unique) {
        return getRandomSequence (levels, length, r, unique, 0);
    }
    
    default <T> SequenceWithStatistics <T> getRandomSequence (int levels, int length, Random r, boolean unique, double nulls) {
        return getRandomSequence (levels, LevelsArrange.DISHONEST, length, r, unique, (int) Math.round (length * nulls));
    }
    
    <T> SequenceWithStatistics <T> getRandomSequence (int levels, LevelsArrange arrage, int length, Random r, boolean unique, int nulls);
    
}
