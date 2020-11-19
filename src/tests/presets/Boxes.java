package tests.presets;

import java.util.List;
import java.util.Random;
import java.util.function.ToDoubleFunction;
import java.util.stream.IntStream;

import tasks.utils.Box;
import tasks.utils.Item;

public class Boxes extends AbstractMappingListPreset <Box, Item> {
    
    @Override
    public Boxes initialize (Random r, DataPreset <List <Item>> items) {
        IntStream.range (0, 1000).mapToObj (i -> items.getRandomSequence (i / 10 + r.nextInt (10), r, false).data)
            . map (its -> its.stream ().reduce (new Box (), Box::addItem, (a, b) -> a.addItems (b.getItems ())))
            . forEach (values::add);
        return this;
    }

    @Override
    public ToDoubleFunction <Box> getStatisticsConverter () {
        return Box::getWeight;
    }

    @Override
    public Class <? extends DataPreset <List <Item>>> getSourcePreset () {
        return Items.class;
    }
    
}
