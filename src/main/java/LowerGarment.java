public class LowerGarment extends Cloth {

    //Attributes
    private int waistSize;
    private int hipSize;
    private boolean hasPocket;
    
    //Constructors
    public LowerGarment() {
        super();
    }

    //Getters and Setters
    public int getWaistSize() {
        return waistSize;
    }

    private int getHipSize() {
        return hipSize;
    }

    private boolean getHasPocket() {
        return hasPocket;
    }

    public void setHasPockets(boolean hasPocket) {
        this.hasPocket = hasPocket;
    }

    public void setHipSize(int hipSize) {
        this.hipSize = hipSize;
    }

    public void setWaistSize(int waistSize) {
        this.waistSize = waistSize;
    }
    
}
