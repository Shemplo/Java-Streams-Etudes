package tests.presets;

import java.util.List;
import java.util.Random;
import java.util.function.ToDoubleFunction;

public class Names extends AbstractListPreset <String> {

    @Override
    public void initialize (Random r) {
        values.addAll (List.of (
            "Andrey", "Boris", "Clement", "David", "Efim", "Feofan", 
            "Grigory", "Georgy", "Hoang", "Ilon", "Igor", "Jastin",
            "Kevin", "Lewis", "Leander", "Marko", "Natan"
        ));
    }
    
    @Override
    public ToDoubleFunction <String> getStatisticsConverter () {
        return String::length;
    }
    
}
