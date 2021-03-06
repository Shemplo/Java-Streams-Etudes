package tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InvocationResult {
    
    public final Object result;
    private long runtime;
    
    private final List <Object> consumers = new ArrayList <> ();
    
    public InvocationResult (Object result, long runtime, List <Object> consumerResults) {
        this.result = result; this.runtime = runtime;
        consumers.addAll (consumerResults);
    }
    
    public InvocationResult (Object result, long runtime) {
        this (result, runtime, List.of ());
    }
    
    public InvocationResult addConsumerValue (Object value) {
        consumers.add (value);
        return this;
    }
    
    public InvocationResult addConsumerValues (List <Object> values) {
        consumers.addAll (values);
        return this;
    }
    
    public InvocationResult addRuntime (long delta) {
        runtime += delta;
        return this;
    }
    
    public InvocationResult addAnotherResult (InvocationResult result) {
        addConsumerValues (result.getConsumers ());
        addRuntime (result.getRuntime ());
        return this;
    }
    
    public long getRuntime () {
        return runtime;
    }
    
    
    public List <Object> getConsumers () {
        return Collections.unmodifiableList (consumers);
    }
    
}
