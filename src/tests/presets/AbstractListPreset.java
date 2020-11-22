package tests.presets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import tests.inputs.LevelsArrange;
import tests.inputs.SequenceWithStatistics;

public abstract class AbstractListPreset <T> implements DataPreset {
    
    protected final List <T> values = new ArrayList <> ();
    
    @Override
    public List <T> getData () {
        return Collections.unmodifiableList (values);
    }
    
    @Override
    public <ST> SequenceWithStatistics <ST> getRandomSequence (
        int levels, LevelsArrange arrange, int length, Random r, 
        boolean unique, int nulls
    ) {
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
        
        @SuppressWarnings ("unchecked")
        final var ss = (ST) arrangeSequence (sequence, levels, arrange, r);
        
        if (doesSupportStatistics () && !sequence.isEmpty ()) {
            final var conv = getStatisticsConverter ();
            
            final var statistics = sequence.stream ().mapToDouble (conv).summaryStatistics ();
            double avg = statistics.getAverage (), min = statistics.getMin (), max = statistics.getMax ();
            double med = sequence.stream ().mapToDouble (conv).sorted ().skip (length / 2).findFirst ().orElse (0.0);
            
            return new SequenceWithStatistics <> (ss, length, min, max, avg, med);
        } else {
            return new SequenceWithStatistics <> (ss);
        }
    }
    
    private List <?> arrangeSequence (List <?> sequence, int levels, LevelsArrange arrange, Random r) {
        List <Object> current = new ArrayList <> (sequence);
        
        if (arrange == LevelsArrange.CUBE) {
            final var side = (int) Math.round (Math.pow (current.size (), 1.0 / levels));
            for (int i = 1; i < levels; i++) {
                final var iterations = (int) Math.round (current.size () * 1.0 / side);
                final var tmp = new ArrayList <Object> ();
                
                int p = 0;
                for (int j = 0; j < iterations; j++) {
                    final var right = Math.min (p + side, current.size ());
                    tmp.add (current.subList (p, right));
                    p += right - p;
                }
                
                current = tmp;
                
            }
            
            return Collections.unmodifiableList (current);
        } else if (arrange == LevelsArrange.HONEST || arrange == LevelsArrange.DISHONEST) {
            for (int i = 1; i < levels; i++) {
                final var tmp = new ArrayList <Object> ();
                
                final int parts = 2 << (levels - i - 1), size = current.size ();
                final var split = arrange == LevelsArrange.HONEST ? doHonestSplit (size, parts) 
                                : doDishonestSplit (size, parts, r);
                
                int p = 0;
                for (int j = 0; j < split.length; j++) {
                    tmp.add (current.subList (p, p + split [j]));
                    p += split [j];
                }
                
                current = tmp;
            }
            
            return Collections.unmodifiableList (current);
        }
        
        throw new IllegalArgumentException ("Unknown levels arrengement: " + arrange);
    }
     
    public static int [] doHonestSplit (int length, int parts) {
        int [] split = new int [parts];
        Arrays.fill (split, length / parts);
        
        final var rest = length % parts;
        for (int i = 0; i < rest; i++) {
            split [i] += 1; 
        }
        
        return split;
    }
    
    public static int [] doDishonestSplit (int length, int parts, Random r) {
        final var borders = IntStream.range (0, parts - 1)
            . mapToObj (__ -> r.nextInt (length)).sorted ()
            . collect (Collectors.toList ());
        
        int [] split = new int [parts];
        int prevBorder = 0, sum = 0;
        
        for (int i = 0; i < borders.size (); i++) {
            split [i] = borders.get (i) - prevBorder;
            prevBorder = borders.get (i);
            sum += split [i];
        }
        
        split [parts - 1] = length - sum;
        return split;
    }
    
    @Override
    public boolean doesSupportStatistics () {
        return true;
    }
    
    protected abstract ToDoubleFunction <T> getStatisticsConverter ();
    
}
