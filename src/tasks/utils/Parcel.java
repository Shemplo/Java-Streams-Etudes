package tasks.utils;

import java.util.stream.Collectors;

public class Parcel extends Box {
    
    private String from, to;
    
    public void setFrom (String from) {
        this.from = from;
    }
    
    public String getFrom () {
        return from;
    }
    
    public void setTo (String to) {
        this.to = to;
    }
    
    public String getTo () {
        return to;
    }
    
    @Override
    public String toString () {
        final var prefix = String.format ("Parcel(%s -> %s)[", getFrom (), getTo ());
        return items.stream ().map (Item::toString).collect (Collectors.joining (", ", prefix, "]"));
    }
    
}
