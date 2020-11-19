package tests.presets;

import java.util.Random;
import java.util.function.ToDoubleFunction;
import java.util.stream.IntStream;

public class IntNumbers extends AbstractListPreset <Integer> {

    @Override
    public void initialize (Random r) {
        IntStream.range (0, 1000).map (__ -> r.nextInt (1000)).forEach (values::add);
    }
    
    @Override
    public ToDoubleFunction <Integer> getStatisticsConverter () {
        return Integer::doubleValue;
    }
    
}
