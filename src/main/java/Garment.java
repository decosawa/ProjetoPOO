public abstract class Garment implements Sellable {

    private String color;
    private String brand;
    private String audience;
    private String fabric;
    private String season;
    private String pattern;
    private String size;
    private double price;
    private int length;

    // Construtor padrão
    public Garment() {
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

    // Getters
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

    public String getAudience() {
        return audience;
    }

    // Setters
    public void setLength(int length) throws NegativeValueException {
        if (length < 0) {
            throw new NegativeValueException("O comprimento não pode ser negativo.");
        }
        this.length = length;
    }

    public void setSize(String size) throws InvalidValueException {
        // Validação de tamanho genérica que pode ser refinada nas subclasses
        String upperCaseSize = size.toUpperCase();
        // Verifica se o tamanho é um dos tamanhos de letra ou se é um número
        if (!upperCaseSize.matches("PP|P|M|G|GG") && !upperCaseSize.matches("\\d+")) {
            throw new InvalidValueException("Formato de tamanho inválido. Use PP, P, M, G, GG ou um número.");
        }
        this.size = upperCaseSize;
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

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public void setPrice(double price) throws NegativeValueException {
        if (price < 0) {
            // Lança uma exceção personalizada se o preço for negativo
            throw new NegativeValueException("O preço não pode ser negativo.");
        }
        this.price = price;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    // Sobrescrita
    // Método para exibir detalhes gerais da peça de roupa
    public void displayDetails() {
        System.out.println("Detalhes gerais da roupa:");
        System.out.println("  Cor: " + this.color);
        System.out.println("  Marca: " + this.brand);
        System.out.println("  Público: " + this.audience);
        System.out.println("  Tecido: " + this.fabric);
        System.out.println("  Estação: " + this.season);
        System.out.println("  Padrão: " + this.pattern);
        System.out.println("  Tamanho: " + this.size);
        System.out.println("  Comprimento: " + this.length + " cm");
        System.out.println("  Preço: R$" + String.format("%.2f", this.price));
    }

    // Sobrecarga
    // Método sobrecarregado para exibir detalhes com uma mensagem personalizada
    public void displayDetails(String customMessage) {
        System.out.println(customMessage);
        displayDetails(); // Chama o método displayDetails sem parâmetros
    }

    // Implementação dos métodos da interface Sellable
    public double calculatePrice() {
        // Implementação básica: retorna o preço atual
        return this.price;
    }

    public void applyDiscount(double percentage) throws InvalidValueException {
        if (percentage < 0 || percentage > 100) {
            throw new InvalidValueException("A porcentagem de desconto deve estar entre 0 e 100.");
        }
        this.price = this.price * (1 - (percentage / 100.0));
    }
}
