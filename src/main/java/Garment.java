//André Luiz Gonçalves da Silva Teixeira
//RA: 2564289

public abstract class Garment implements Sellable {

    private int id;
    private String color;
    private String brand;
    private String audience;
    private String fabric;
    private String season;
    private String pattern;
    private String size;
    private double price;
    private int length;

    public Garment() {
        this.color = ""; this.brand = ""; this.audience = ""; this.fabric = "";
        this.season = ""; this.pattern = ""; this.size = ""; this.price = 0.0; this.length = 0;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getAudience() { return audience; }
    public void setAudience(String audience) { this.audience = audience; }
    public String getFabric() { return fabric; }
    public void setFabric(String fabric) { this.fabric = fabric; }
    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }
    public String getPattern() { return pattern; }
    public void setPattern(String pattern) { this.pattern = pattern; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public double getPrice() { return price; }
    public void setPrice(double price) throws NegativeValueException {
        if (price < 0) { throw new NegativeValueException("O preço не pode ser negativo."); }
        this.price = price;
    }
    public int getLength() { return length; }
    public void setLength(int length) throws NegativeValueException {
        if (length < 0) { throw new NegativeValueException("O comprimento не pode ser negativo."); }
        this.length = length;
    }

    public String getDetailsAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Detalhes gerais da roupa:\n");
        sb.append("  ID: ").append(this.id).append("\n");
        sb.append("  Cor: ").append(this.color).append("\n");
        sb.append("  Marca: ").append(this.brand).append("\n");
        sb.append("  Público: ").append(this.audience).append("\n");
        sb.append("  Tecido: ").append(this.fabric).append("\n");
        sb.append("  Estação: ").append(this.season).append("\n");
        sb.append("  Padrão: ").append(this.pattern).append("\n");
        sb.append("  Tamanho: ").append(this.size).append("\n");
        sb.append("  Comprimento: ").append(this.length).append(" cm\n");
        sb.append("  Preço: R$").append(String.format("%.2f", this.price)).append("\n");
        return sb.toString();
    }
    
    public String getDetailsAsString(boolean simple) {
        if (simple) {
            return getBrand() + " - " + getColor() + " (ID: " + getId() + ")";
        } else {
            return getDetailsAsString();
        }
    }

    public double calculatePrice() { return this.price; }

    public void applyDiscount(double percentage) throws InvalidValueException {
        if (percentage < 0 || percentage > 100) { throw new InvalidValueException("O desconto deve estar entre 0 e 100."); }
        this.price = this.price * (1 - (percentage / 100.0));
    }
    
    //Sobrecarga
    public void applyDiscount(int fixedAmount) throws NegativeValueException {
        double newPrice = this.price - fixedAmount;
        if (newPrice < 0) {
            throw new NegativeValueException("O desconto de valor fixo não pode deixar o preço negativo.");
        }
        setPrice(newPrice);
    }

}