//André Luiz Gonçalves da Silva Teixeira
//RA: 2564289

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author andre
 */
public class Sell {
    private Garment garment; 
    private int quantity;
    private double totalPrice;
    private String paymentMethod;
    private String customerName;

    public Sell() {}

    public Garment getGarment() { return garment; }
    public void setGarment(Garment garment) { this.garment = garment; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) throws InvalidValueException {
        if (quantity <= 0) { throw new InvalidValueException("A quantidade deve ser positiva."); }
        this.quantity = quantity;
    }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) throws NegativeValueException {
        if (totalPrice < 0) { throw new NegativeValueException("O preço total não pode ser negativo."); }
        this.totalPrice = totalPrice;
    }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
}
