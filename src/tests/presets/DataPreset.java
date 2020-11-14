package tests.presets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface DataPreset <T> {
    
    void initialize (Random r);
    
    List <T> getData ();
    
    default int getSize () {
        return getData ().size ();
    }
    
    boolean doesSupportStatistics ();
    
    ToDoubleFunction <T> getStatisticsConverter ();
    
    default SequenceWithStatistics <T> getRandomSequence (int length, Random r, boolean unique) {
        List <T> sequence = List.of ();
        
        if (unique) {
            final var uniques = new ArrayList <> (Set.copyOf (getData ()));
            if (uniques.size () < length) {
                throw new IllegalArgumentException (String.format (
                    "Requested number of unique value (%d) is more than actual number of unique values (%d)",
                    length, uniques.size ()
                ));
            }
            
            Collections.shuffle (uniques, r);
            sequence = uniques.subList (0, length);
        } else {
            final var data = getData ();
            
            sequence = IntStream.range (0, length).map (i -> r.nextInt (data.size ()))
                 . mapToObj (data::get).collect (Collectors.toUnmodifiableList ());
        }
        
        if (doesSupportStatistics () && !sequence.isEmpty ()) {
            final var conv = getStatisticsConverter ();
            
            double med = sequence.stream ().mapToDouble (conv).sorted ().skip (length / 2).findFirst ().orElse (0.0);
            double avg = sequence.stream ().mapToDouble (conv).average ().orElse (0.0);
            double min = sequence.stream ().mapToDouble (conv).min ().orElse (0.0);
            double max = sequence.stream ().mapToDouble (conv).max ().orElse (0.0);
            
            return new SequenceWithStatistics <> (sequence, length, min, max, avg, med);
        } else {
            return new SequenceWithStatistics <> (sequence);
        }
    }
    
}
