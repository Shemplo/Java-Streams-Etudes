package tests.presets;

import java.util.Random;
import java.util.function.ToDoubleFunction;
import java.util.stream.IntStream;

import tasks.utils.Item;

public class Items extends AbstractListPreset <Item> {
    
    @Override
    public void initialize (Random r) {
        IntStream.range (0, 1000).mapToObj (__ -> {
            final var item = new Item ();
            
            final var barcode = r.nextInt (10000);
            
            item.setWeight (500.0 + 3.0 * barcode / 10.0 + (r.nextDouble () - 0.5) * 250);
            item.setBarcode (String.format ("%04d", barcode));
            item.setCategory (randomCategory (r));
            
            return item;
        }).forEach (values::add);
    }
    
    public static char randomCategory (Random r) {
        return (char) ('A' + r.nextInt (26));
    }

    @Override
    public ToDoubleFunction <Item> getStatisticsConverter () {
        return Item::getWeight;
    }
    
}
