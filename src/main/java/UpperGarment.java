public class UpperGarment extends Cloth {

    //Attributes
    private int bustSize;
    private String sleeveLenght;
    private String collarType;
    private boolean hasButton;

    //Constructors
    public UpperGarment() {
        super();
    }

    //Getters and Setters
    public String getSleeveLenght() {
        return sleeveLenght;
    }

    public void setSleeveLenght(String sleeveLenght) {
        this.sleeveLenght = sleeveLenght;
    }

    public String getCollarType() {
        return collarType;
    }

    public int getBustSize() {
        return bustSize;
    }

    public void setBustSize(int bustSize) {
        this.bustSize = bustSize;
    }

    public void setCollarType(String collarType) {
        this.collarType = collarType;
    }

    public boolean hasButton() {
        return hasButton;
    }

    public void setButton(boolean hasButton) {
        this.hasButton = hasButton;
    }



}
