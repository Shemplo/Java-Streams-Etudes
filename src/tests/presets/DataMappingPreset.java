package tests.presets;

import java.util.Random;

public interface DataMappingPreset <T, S> extends DataPreset <T> {
    
    Class <? extends DataPreset <S>> getSourcePreset ();
    
    DataMappingPreset <T, S> initialize (Random r, DataPreset <S> preset);
    
    @Override
    default DataMappingPreset <T, S> initialize (Random r) {
        throw new UnsupportedOperationException ();
    }
    
}
