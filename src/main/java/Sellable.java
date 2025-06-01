public interface Sellable {

    public double calculatePrice();
    public void applyDiscount(double percentage) throws InvalidValueException;
    
}
