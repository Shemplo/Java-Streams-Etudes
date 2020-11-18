package tests.presets;

import java.util.List;
import java.util.Random;
import java.util.function.ToDoubleFunction;

public class Names implements DataPreset <String> {
    
    private final List <String> names = List.of (
        "Andrey", "Boris", "Clement", "David", "Efim", "Feofan", 
        "Grigory", "Georgy", "Hoang", "Ilon", "Igor", "Jastin",
        "Kevin", "Lewis", "Leander", "Marko", "Natan"
    );

    @Override
    public List <String> getData () {
        return names;
    }

    @Override
    public void initialize (Random r) {}
    
    @Override
    public boolean doesSupportStatistics () {
        return true;
    }
    
    @Override
    public ToDoubleFunction <String> getStatisticsConverter () {
        return String::length;
    }
    
}
