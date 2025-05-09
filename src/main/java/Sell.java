public class Sell {
    
    private Cloth clothes;
    private int quantity;
    private double totalPrice;
    private String paymentMethod;
    private String customerName;

    // Constructors
    public Sell() {
        this.quantity = 0;
        this.totalPrice = 0.0;
        this.paymentMethod = "";
        this.customerName = "";
    }

    // Getters and Setters
    public Cloth getClothes() {
        return clothes;
    }
    
    public void setClothes(Cloth clothes) {
        this.clothes = clothes;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
}
