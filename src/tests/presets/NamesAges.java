package tests.presets;

import java.util.List;
import java.util.Random;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NamesAges extends AbstractMapPreset <String, Integer> {

    @Override
    protected List <Integer> initializeValues (Random r) {
        return IntStream.range (0, 100).mapToObj (__ -> 5 + r.nextInt (40)).collect (Collectors.toList ());
    }

    @Override
    protected List <String> initializeKeys (Random r) {
        return new Names ().initialize (r).getRandomSequence (1, 30, r, true).data;
    }

    @Override
    protected ToDoubleBiFunction <String, Integer> getStatisticsConverter () {
        return (k, v) -> v;
    }
    
}
