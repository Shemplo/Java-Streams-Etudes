package tests.presets;

import java.util.List;
import java.util.Random;
import java.util.function.ToDoubleFunction;

public class Names extends AbstractListPreset <String> {

    @Override
    public Names initialize (Random r) {
        values.addAll (List.of (
            "Andrey", "Boris", "Clement", "David", "Efim", "Feofan", 
            "Grigory", "Georgy", "Hoang", "Ilon", "Igor", "Jastin",
            "Kevin", "Lewis", "Leander", "Marko", "Natan", "Olaf",
            "Oleg", "Ostin", "Pavel", "Pedro", "Paul", "Ralf", "Roman",
            "Sebastian", "Sergey", "Thor", "Tirley", "Taras"
        ));
        return this;
    }
    
    @Override
    public ToDoubleFunction <String> getStatisticsConverter () {
        return String::length;
    }
    
}
