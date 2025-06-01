public class OnePiece extends Garment {

    // Atributos específicos de OnePiece (combinação de Upper e Lower Garment)
    private int waistSize;
    private int hipSize;
    private int bustSize;
    private String sleeveLenght;
    private String collarType;
    private boolean hasButton;
    private boolean hasPocket;

    // Construtor
    public OnePiece() {
        super(); // Chama o construtor da classe pai (Cloth)
        this.bustSize = 0;
        this.hipSize = 0;
        this.waistSize = 0;
        this.sleeveLenght = "";
        this.collarType = "";
        this.hasButton = false;
        this.hasPocket = false;
    }

    // Getters
    public int getWaistSize() {
        return waistSize;
    }

    public int getHipSize() {
        return hipSize;
    }

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

    public boolean hasPocket() {
        return hasPocket;
    }

    // Setters
    public void setWaistSize(int waistSize) throws InvalidValueException {
        if (waistSize <= 0) {
            throw new InvalidValueException("O tamanho da cintura deve ser um valor positivo.");
        }
        this.waistSize = waistSize;
    }

    public void setHipSize(int hipSize) throws InvalidValueException {
        if (hipSize <= 0) {
            throw new InvalidValueException("O tamanho do quadril deve ser um valor positivo.");
        }
        this.hipSize = hipSize;
    }

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

    public void setHasPocket(boolean hasPocket) {
        this.hasPocket = hasPocket;
    }
    
    // Sobrescrita
    public void displayDetails() {
        super.displayDetails(); // Chama o método displayDetails da classe Cloth
        System.out.println("Detalhes específicos da Peça Única:");
        System.out.println("  Tamanho do Busto: " + this.bustSize);
        System.out.println("  Tamanho da Cintura: " + this.waistSize);
        System.out.println("  Tamanho do Quadril: " + this.hipSize);
        System.out.println("  Comprimento da Manga: " + this.sleeveLenght);
        System.out.println("  Tipo de Gola: " + this.collarType);
        System.out.println("  Tem Botões: " + (this.hasButton ? "Sim" : "Não"));
        System.out.println("  Tem Bolsos: " + (this.hasPocket ? "Sim" : "Não"));
    }
}
    

