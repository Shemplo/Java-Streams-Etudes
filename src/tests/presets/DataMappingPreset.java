package tests.presets;

import java.util.Random;

public interface DataMappingPreset <T, S> extends DataPreset <T> {
    
    Class <? extends DataPreset <S>> getSourcePreset ();
    
    void initialize (Random r, DataPreset <S> preset);
    
    @Override
    default void initialize (Random r) {
        throw new UnsupportedOperationException ();
    }
    
}
