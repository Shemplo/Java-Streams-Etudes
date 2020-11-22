package tests.presets;

import java.util.Random;

public interface DataMappingPreset extends DataPreset {
    
    Class <? extends DataPreset> getSourcePreset ();
    
    DataMappingPreset initialize (Random r, DataPreset preset);
    
    @Override
    default DataMappingPreset initialize (Random r) {
        throw new UnsupportedOperationException ();
    }
    
}
