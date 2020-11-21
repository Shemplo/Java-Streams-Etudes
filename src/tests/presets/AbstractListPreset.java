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
    
    public SequenceWithStatistics <List <T>> getRandomSequence (int levels, int length, Random r, boolean unique, int nulls) {
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
    
    public static List <Integer> doHonestSplit (int levels, int length) {
        final var split = new ArrayList <Integer> ();
        final var parts = 2 << (levels - 1);
        
        if (parts >= length) {
            for (int i = 0; i < parts; i++) {
                split.add (i < length ? 1 : 0);
            }
        } else {
            final var partSize = (int) Math.round (length * 1.0 / parts);
            int sum = 0;
            
            for (int i = 0; i < parts; i++) {
                final var value = Math.min (partSize, length - sum);
                split.add (value);
                sum += value;
            }
        }
        
        return split;
    }
    
    public static List <Integer> doDishonestSplit (int levels, int length, Random r) {
        final var split = new ArrayList <Integer> ();
        final var parts = 2 << (levels - 1);
        
        if (parts >= length) {
            for (int i = 0; i < parts; i++) {
                split.add (i < length ? 1 : 0);
            }
            
            Collections.shuffle (split, r);
        } else {
            final var borders = IntStream.range (0, parts - 1)
                . mapToObj (__ -> r.nextInt (length)).sorted ()
                . collect (Collectors.toList ());
            
            int previousBorder = 0, sum = 0;
            for (int border : borders) {
                final var value = border - previousBorder;
                previousBorder = border;
                split.add (value);
                sum += value;
            }
            
            split.add (length - sum);
        }
        
        return split;
    }
    
    @Override
    public boolean doesSupportStatistics () {
        return true;
    }
    
    protected abstract ToDoubleFunction <T> getStatisticsConverter ();
    
}
