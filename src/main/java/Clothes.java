public abstract class Clothes {

    private String color;
    private String brand;
    private String type;
    private String fabric;
    private double price;
    
    // Constructors
    public Clothes() {
        this.color = "";
        this.brand = "";
        this.type = "";
        this.fabric = "";
        this.price = 0.0;
    }

    // Getters and Setters
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public String getFabric() {
        return fabric;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }
    
}
