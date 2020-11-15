package tests.presets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.ToDoubleFunction;
import java.util.stream.IntStream;

import tasks.utils.Box;
import tasks.utils.Item;

public class Boxes implements DataMappingPreset <Box, Item> {

    private final List <Box> boxes = new ArrayList <> ();
    
    @Override
    public void initialize (Random r, DataPreset <Item> items) {
        items.getRandomSequence (200 / 10 + r.nextInt (10), r, false);
        IntStream.range (0, 1000).mapToObj (i -> items.getRandomSequence (i / 10 + r.nextInt (10), r, false).data)
            . map (its -> its.stream ().reduce (new Box (), Box::addItem, (a, b) -> a.addItems (b.getItems ())))
            . forEach (boxes::add);
    }

    @Override
    public List <Box> getData () {
        return Collections.unmodifiableList (boxes);
    }

    @Override
    public boolean doesSupportStatistics () {
        return true;
    }

    @Override
    public ToDoubleFunction <Box> getStatisticsConverter () {
        return Box::getWeight;
    }

    @Override
    public Class <? extends DataPreset <Item>> getSourcePreset () {
        return Items.class;
    }
    
}
