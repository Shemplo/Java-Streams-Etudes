package tasks.utils;


public class Item {
    
    protected double weight;
    
    protected String description, barcode;
    
    protected char category = 'A';
    
    public void setWeight (double weight) {
        this.weight = weight;
    }
    
    public double getWeight () {
        return weight;
    }
    
    public void setDescription (String description) {
        this.description = description;
    }
    
    public String getDescription () {
        return description;
    }
    
    public void setBarcode (String barcode) {
        this.barcode = barcode;
    }
    
    public String getBarcode () {
        return barcode;
    }
    
    public void setCategory (char category) {
        this.category = category;
    }
    
    public char getCategory () {
        return category;
    }
    
    @Override
    public String toString () {
        return String.format ("Item[%c; %s; %.3f; %s]", getCategory (), getBarcode (), getWeight (), getDescription ());
    }
    
}
