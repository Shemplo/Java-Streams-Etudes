package tests.presets;

import java.util.Collection;
import java.util.Map;
import java.util.Random;

import tests.inputs.SequenceWithStatistics;

public interface DataPreset <T> {
    
    void initialize (Random r);
    
    T getData ();
    
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
    
    default SequenceWithStatistics <T> getRandomSequence (int length, Random r, boolean unique) {
        return getRandomSequence (length, r, unique, 0);
    }
    
    default SequenceWithStatistics <T> getRandomSequence (int length, Random r, boolean unique, double nulls) {
        return getRandomSequence (length, r, unique, (int) Math.round (length * nulls));
    }
    
    SequenceWithStatistics <T> getRandomSequence (int length, Random r, boolean unique, int nulls);
    
}
