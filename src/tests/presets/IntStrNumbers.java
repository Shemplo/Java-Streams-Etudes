package tests.presets;

import java.util.Random;
import java.util.function.ToDoubleFunction;
import java.util.stream.IntStream;

public class IntStrNumbers extends AbstractListPreset <String> {

    @Override
    public void initialize (Random r) {
        IntStream.range (0, 1000).map (__ -> r.nextInt (1000))
            .mapToObj (String::valueOf).forEach (values::add);
    }
    
    @Override
    public ToDoubleFunction <String> getStatisticsConverter () {
        return Integer::parseInt;
    }
    
}
