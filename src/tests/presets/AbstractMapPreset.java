package tests.presets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.ToDoubleBiFunction;

import tests.inputs.SequenceWithStatistics;

public abstract class AbstractMapPreset <K, V> implements DataMapPreset <K, V> {
    
    protected final Map <K, V> map = new HashMap <> ();
    
    @Override
    public void initialize (Random r) {
        final var values = initializeValues (r);
        final var keys = initializeKeys (r);
        
        final var length = keys.size ();
        for (int i = 0; i < length; i++) {
            final var value = i >= values.size () ? null : values.get (i);
            map.put (keys.get (i), value);
        }
    }
    
    protected abstract List <V> initializeValues (Random r);
    
    protected abstract List <K> initializeKeys (Random r);
    
    @Override
    public Map <K, V> getData () {
        return Collections.unmodifiableMap (map);
    }
    
    @Override
    public boolean doesSupportStatistics () {
        return true;
    }
    
    @Override
    public SequenceWithStatistics <Map <K, V>> getRandomSequence (int length, Random r, boolean unique, int nulls) {
        if (length > map.size ()) {
            throw new IllegalArgumentException (String.format (
                "Requested number of map (%d) is more than actual size of preset (%d)",
                length, map.size ()
            ));
        }
        
        final var statisticsData = new ArrayList <Double> ();
        final var hasStatistics = doesSupportStatistics ();
        final var keys = new ArrayList <> (map.keySet ());
        Collections.shuffle (keys, r);
        
        final var sequence = new HashMap <K, V> ();
        for (int i = 0; i < length; i++) {
            final var key   = keys.get (i);
            final var value = map.get (key);
            
            sequence.put (key, value);
            
            if (hasStatistics) {
                final var conv = getStatisticsConverter ();
                statisticsData.add (conv.applyAsDouble (key, value));
            }
        }
        
        if (hasStatistics && !sequence.isEmpty ()) {
            final var statistics = statisticsData.stream ().mapToDouble (i -> i).summaryStatistics ();
            double avg = statistics.getAverage (), min = statistics.getMin (), max = statistics.getMax ();
            double med = statisticsData.stream ().mapToDouble (i -> i).sorted ().skip (length / 2).findFirst ().orElse (0.0);
            
            return new SequenceWithStatistics <> (sequence, length, min, max, avg, med);
        } else {
            return new SequenceWithStatistics <> (sequence);
        }
    }
    
    protected abstract ToDoubleBiFunction <K, V> getStatisticsConverter ();
    
}
