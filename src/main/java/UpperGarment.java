public class UpperGarment extends Garment {

    // Atributos específicos de UpperGarment
    private int bustSize;
    private String sleeveLenght;
    private String collarType;
    private boolean hasButton;

    // Construtor
    public UpperGarment() {
        super();
        this.bustSize = 0;
        this.sleeveLenght = "";
        this.collarType = "";
        this.hasButton = false;
    }

    // Getters
    public int getBustSize() {
        return bustSize;
    }

    public String getSleeveLenght() {
        return sleeveLenght;
    }

    public String getCollarType() {
        return collarType;
    }

    public boolean hasButton() {
        return hasButton;
    }

    // Setters
    public void setBustSize(int bustSize) throws InvalidValueException {
        if (bustSize <= 0) {
            throw new InvalidValueException("O tamanho do busto deve ser um valor positivo.");
        }
        this.bustSize = bustSize;
    }

    public void setSleeveLenght(String sleeveLenght) {
        this.sleeveLenght = sleeveLenght;
    }

    public void setCollarType(String collarType) {
        this.collarType = collarType;
    }

    public void setButton(boolean hasButton) {
        this.hasButton = hasButton;
    }

    //Sobrescrita
    public void displayDetails() {
        super.displayDetails(); // Chama o método displayDetails da classe Cloth
        System.out.println("Detalhes específicos da Camiseta:");
        System.out.println("  Tamanho do Busto: " + this.bustSize);
        System.out.println("  Comprimento da Manga: " + this.sleeveLenght);
        System.out.println("  Tipo de Gola: " + this.collarType);
        System.out.println("  Tem Botões: " + (this.hasButton ? "Sim" : "Não"));
    }
}
