public class Sell {
    
    private Cloth cloth;
    private int quantity;
    private double totalPrice;
    private String paymentMethod;
    private String customerName;

    // Constructors
    public Sell() {
        this.cloth = null;
        this.quantity = 0;
        this.totalPrice = 0.0;
        this.paymentMethod = "";
        this.customerName = "";
    }

    // Getters and Setters
    public Cloth getCloth() {
        return cloth;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void setClothes(Cloth cloth) {
        this.cloth = cloth;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

     public String getCustomerName() {
        return customerName;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    } 

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
}
