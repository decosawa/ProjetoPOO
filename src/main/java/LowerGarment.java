public class LowerGarment extends Garment {

    // Atributos específicos de LowerGarment
    private int waistSize;
    private int hipSize;
    private boolean hasPocket;
    
    // Construtor
    public LowerGarment() {
        super(); // Chama o construtor da classe pai (Garment)
        this.waistSize = 0;
        this.hipSize = 0;
        this.hasPocket = false;
    }

    // Getters
    public int getWaistSize() {
        return waistSize;
    }

    public int getHipSize() {
        return hipSize;
    }

    public boolean getHasPocket() {
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

    public void setHasPockets(boolean hasPocket) {
        this.hasPocket = hasPocket;
    }
    
    //Sobrescrita
    public void displayDetails() {
        super.displayDetails(); // Chama o método displayDetails da classe Garment
        System.out.println("Detalhes específicos da Calça:");
        System.out.println("  Tamanho da Cintura: " + this.waistSize);
        System.out.println("  Tamanho do Quadril: " + this.hipSize);
        System.out.println("  Tem Bolsos: " + (this.hasPocket ? "Sim" : "Não"));
    }
}
