package tests.presets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.ToDoubleFunction;
import java.util.stream.IntStream;

import tasks.utils.Item;

public class Items implements DataPreset <Item> {

    private final List <Item> items = new ArrayList <> ();
    
    @Override
    public void initialize (Random r) {
        IntStream.range (0, 1000).mapToObj (__ -> {
            final var item = new Item ();
            
            final var barcode = r.nextInt (10000);
            
            item.setWeight (500.0 + 3.0 * barcode / 10.0 + (r.nextDouble () - 0.5) * 250);
            item.setBarcode (String.format ("%04d", barcode));
            item.setCategory (randomCategory (r));
            
            return item;
        }).forEach (items::add);
    }
    
    public static char randomCategory (Random r) {
        return (char) ('A' + r.nextInt (26));
    }

    @Override
    public List <Item> getData () {
        return Collections.unmodifiableList (items);
    }

    @Override
    public boolean doesSupportStatistics () {
        return true;
    }

    @Override
    public ToDoubleFunction <Item> getStatisticsConverter () {
        return Item::getWeight;
    }
    
}
