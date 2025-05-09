public class OnePiece extends Cloth {

    // Attributes
    private int waistSize;
    private int hipSize;
    private int bustSize;
    private String sleeveLenght;
    private String collarType;
    private boolean hasButton;
    private boolean hasPockets;

    // Constructors
    public OnePiece() {
        super();
    }

    // Getters and Setters
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

    public boolean hasPockets() {
        return hasPockets;
    }

    public void setWaistSize(int waistSize) {
        this.waistSize = waistSize;
    }

    public void setHipSize(int hipSize) {
        this.hipSize = hipSize;
    }

    public void setBustSize(int bustSize) {
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
    
}
