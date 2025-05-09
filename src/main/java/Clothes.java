public abstract class Clothes {

    private String color;
    private String brand;
    private String audience;
    private String fabric;
    private String season;
    private String pattern;
    private String size;
    private double price;
    private int length;

    
    // Constructors
    public Clothes() {
        this.color = "";
        this.brand = "";
        this.audience = "";
        this.fabric = "";
        this.season = "";
        this.pattern = "";
        this.size = "";
        this.price = 0.0;
        this.length = 0;
    }

    // Getters and Setters
    public String getColor() {
        return color;
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

    public String getSeason() {
        return season;
    }

    public String getPattern() {
        return pattern;
    }

    public String getSize() {
        return size;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
    public void setSize(String size) {
        this.size = size.toUpperCase();
    }

    public void setSeason(String season) {
        this.season = season;
    }

        public void setColor(String color) {
        this.color = color;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAudience() {
        return audience;
    }
    public void setAudience(String audience) {
        this.audience = audience;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }
    
}
