package tasks.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Box extends Item {
    
    protected final List <Item> items = new LinkedList <> ();
    
    public void addItem (Item item) {
        items.add (item);
    }
    
    public List <Item> getItems () {
        return Collections.unmodifiableList (items);
    }
    
    public int getItemsNumber () {
        return items.size ();
    }
    
    @Override
    public void setWeight (double weight) {
        throw new UnsupportedOperationException ();
    }
    
    @Override
    public double getWeight () {
        return items.stream ().mapToDouble (Item::getWeight).sum ();
    }
    
    @Override
    public void setCategory (char category) {
        throw new UnsupportedOperationException ();
    }
    
    @Override
    public char getCategory () {
        return '#';
    }
    
    @Override
    public String toString () {
        return items.stream ().map (Item::toString).collect (Collectors.joining (", ", "Box[", "]"));
    }
    
}
