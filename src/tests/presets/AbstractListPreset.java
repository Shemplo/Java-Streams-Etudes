package tests.presets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import tests.inputs.SequenceWithStatistics;

public abstract class AbstractListPreset <T> implements DataPreset <List <T>> {
    
    protected final List <T> values = new ArrayList <> ();
    
    @Override
    public List <T> getData () {
        return Collections.unmodifiableList (values);
    }
    
    public SequenceWithStatistics <List <T>> getRandomSequence (int length, Random r, boolean unique, int nulls) {
        List <T> sequence = List.of ();
        
        if (unique) {
            final var uniques = new ArrayList <> (Set.copyOf (getData ()));
            if (uniques.size () < length) {
                throw new IllegalArgumentException (String.format (
                    "Requested number of unique value (%d) is more than actual number of unique values (%d)",
                    length, uniques.size ()
                ));
            }
            
            for (int i = 0; i < nulls; i++) {
                uniques.add (null);
            }
            
            Collections.shuffle (uniques, r);
            sequence = Collections.unmodifiableList (uniques.subList (0, length));
        } else {
            final var data = getData ();
            
            final var list = IntStream.range (0, length).map (i -> r.nextInt (data.size ()))
                . mapToObj (data::get).collect (Collectors.toList ());
            for (int i = 0; i < nulls; i++) { list.add (null); }
            
            Collections.shuffle (list, r);
            sequence = Collections.unmodifiableList (list);
        }
        
        if (doesSupportStatistics () && !sequence.isEmpty ()) {
            final var conv = getStatisticsConverter ();
            
            final var statistics = sequence.stream ().mapToDouble (conv).summaryStatistics ();
            double avg = statistics.getAverage (), min = statistics.getMin (), max = statistics.getMax ();
            double med = sequence.stream ().mapToDouble (conv).sorted ().skip (length / 2).findFirst ().orElse (0.0);
            
            return new SequenceWithStatistics <> (sequence, length, min, max, avg, med);
        } else {
            return new SequenceWithStatistics <> (sequence);
        }
    }
    
    @Override
    public boolean doesSupportStatistics () {
        return true;
    }
    
    protected abstract ToDoubleFunction <T> getStatisticsConverter ();
    
}
