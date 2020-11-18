package tests.presets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.ToDoubleFunction;
import java.util.stream.IntStream;

public class IntNumbers implements DataPreset <Integer> {

    private final List <Integer> numbers = new ArrayList <> ();
    
    @Override
    public void initialize (Random r) {
        IntStream.range (0, 1000).map (__ -> r.nextInt (1000)).forEach (numbers::add);
    }

    @Override
    public List <Integer> getData () {
        return Collections.unmodifiableList (numbers);
    }
    
    @Override
    public boolean doesSupportStatistics () {
        return true;
    }
    
    @Override
    public ToDoubleFunction <Integer> getStatisticsConverter () {
        return Integer::doubleValue;
    }
    
}
