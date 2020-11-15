package tests.inputs;

import java.util.Random;

public class ConstantValueProvider {
    
    public final Double value;
    public final int variation;
    
    public final Integer sequenceSrc;
    public final Integer sequenceParameter;
    
    public ConstantValueProvider (double value, int variation) {
        this.sequenceSrc = null; this.sequenceParameter = null;
        this.value = value; this.variation = variation;
    }
    
    public ConstantValueProvider (int sequenceSrc, int sequenceParameter, int variation) {
        this.sequenceParameter = sequenceParameter;
        this.sequenceSrc = sequenceSrc; 
        this.variation = variation;
        this.value = null;
    }
    
    public double getValue (SequenceWithStatistics <?> sequence, Random random) {
        if (value != null) {
            return value.doubleValue () + random.nextDouble () * variation;
        } else if (sequence != null && sequenceParameter != null) {
            final var tmp = sequenceParameter == 0 ? sequence.length
                          : sequenceParameter == 1 ? sequence.min
                          : sequenceParameter == 2 ? sequence.max
                          : sequenceParameter == 3 ? sequence.average
                          : sequenceParameter == 4 ? sequence.median
                          : 0.0;
            return tmp + random.nextDouble () * variation;
        }
        
        return 0.0;
    }
    
}
