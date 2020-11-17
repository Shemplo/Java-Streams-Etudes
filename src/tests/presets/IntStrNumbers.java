package tests.presets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.ToDoubleFunction;
import java.util.stream.IntStream;

public class IntStrNumbers implements DataPreset <String> {

    protected final List <String> numbers = new ArrayList <> ();
    
    @Override
    public void initialize (Random r) {
        IntStream.range (0, 1000).map (__ -> r.nextInt (1000))
            .mapToObj (String::valueOf).forEach (numbers::add);
    }

    @Override
    public List <String> getData () {
        return Collections.unmodifiableList (numbers);
    }
    
    @Override
    public boolean doesSupportStatistics () {
        return true;
    }
    
    @Override
    public ToDoubleFunction <String> getStatisticsConverter () {
        return Integer::parseInt;
    }
    
}
