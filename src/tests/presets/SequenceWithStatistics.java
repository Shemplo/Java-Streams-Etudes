package tests.presets;

import java.util.List;

public class SequenceWithStatistics <T> {
    
    public final List <T> data;
    
    public final int length;
    public final double min, max, average, median;
    
    public SequenceWithStatistics (
        List <T> data, int length, double min, double max, 
        double average, double median
    ) {
        this.average = average; 
        this.length = length;
        this.median = median;
        this.data = data; 
        this.min = min; 
        this.max = max; 
    }
    
    public SequenceWithStatistics (List <T> data) {
        this (data, 0, 0.0, 0.0, 0.0, 0.0);
    }
    
    @Override
    public String toString () {
        return String.format (
            "|min: %.3f; max: %.3f; avg: %.3f; med: %.3f| (%d) %s",
            min, max, average, median, length, data
        );
    }
    
    private boolean parallel;
    
    public SequenceWithStatistics <?> markParallelStream () {
        parallel = true;
        return this;
    }
    
    public SequenceWithStatistics <?> setParallelStream (boolean parallel) {
        this.parallel = parallel;
        return this;
    }
    
    public boolean isParallelStream () {
        return parallel;
    }
    
}
