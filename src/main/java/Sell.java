public class Sell {
    
    private Garment garment; 
    private int quantity; // Quantidade de itens vendidos
    private double totalPrice; // Preço total da venda
    private String paymentMethod; // Método de pagamento
    private String customerName; // Nome do cliente

    // Construtor padrão
    public Sell() {
        this.garment = null;
        this.quantity = 0;
        this.totalPrice = 0.0;
        this.paymentMethod = "";
        this.customerName = "";
    }

    // Getters
    public Garment getGarment() {
        return garment;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getCustomerName() {
        return customerName;
    }

    // Setters
    public void setGarment(Garment garment) { // Alterado de setClothes para setGarment
        this.garment = garment;
    }

    public void setQuantity(int quantity) throws InvalidValueException {
        if (quantity <= 0) {
            throw new InvalidValueException("A quantidade deve ser um valor positivo.");
        }
        this.quantity = quantity;
    }

    public void setTotalPrice(double totalPrice) throws NegativeValueException {
        if (totalPrice < 0) {
            throw new NegativeValueException("O preço total não pode ser negativo.");
        }
        this.totalPrice = totalPrice;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    } 

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
