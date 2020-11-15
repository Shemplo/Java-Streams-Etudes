package tests.inputs;

import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

import tests.utils.Pair;

public class ConsumerGenerator <T> implements Supplier <Pair <Consumer <T>, Object>> {
    
    private final ConsumerMode mode;
    
    public ConsumerGenerator (ConsumerMode mode) {
        if (mode == null) {
            throw new IllegalArgumentException ("Consumer mode is not defined");
        }
        
        this.mode = mode;
    }
    
    public Pair <Consumer <T>, Object> get () {
        if (mode == ConsumerMode.LIST_COLLECTOR) {
            final var collector = new ArrayList <Object> ();
            return new Pair <> (collector::add, collector);
        } else if (mode == ConsumerMode.SUMMATOR) {
            final var summator = new AtomicInteger ();
            return new Pair <> (v -> {
                if (v instanceof Integer) {
                    summator.addAndGet ((Integer) v);
                }
            }, summator);
        } else if (mode == ConsumerMode.JOINER) {
            final var sj = new StringJoiner (", ");
            return new Pair <> (v -> sj.add (String.valueOf (v)), sj);
        }
        
        throw new IllegalStateException ("Consumer mode is not defined");
    }
    
}
