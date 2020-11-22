package tests.presets;

import java.util.Random;
import java.util.function.ToDoubleFunction;
import java.util.stream.IntStream;

public class IntStrNumbers extends AbstractListPreset <String> {

    @Override
    public IntStrNumbers initialize (Random r) {
        IntStream.range (0, 1000).map (__ -> r.nextInt (1000))
            .mapToObj (String::valueOf).forEach (values::add);
        return this;
    }
    
    @Override
    public ToDoubleFunction <String> getStatisticsConverter () {
        return Integer::parseInt;
    }
    
}
