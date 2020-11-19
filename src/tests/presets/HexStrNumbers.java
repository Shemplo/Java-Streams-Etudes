package tests.presets;

import java.util.Random;
import java.util.function.ToDoubleFunction;
import java.util.stream.IntStream;

public class HexStrNumbers extends IntStrNumbers {

    @Override
    public HexStrNumbers initialize (Random r) {
        IntStream.range (0, 1000).map (__ -> r.nextInt (1000))
            .mapToObj (n -> String.format ("0x%x", n))
            .forEach (values::add);
        return this;
    }

    @Override
    public ToDoubleFunction <String> getStatisticsConverter () {
        return num -> Integer.parseInt (num.substring (2), 16);
    }
    
}
