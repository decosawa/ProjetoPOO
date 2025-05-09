public class LowerGarment extends Clothes {

    //Attributes
    private int waistSize;
    private int hipSize;
    private boolean hasPockets;
    
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

    private boolean getHasPockets() {
        return hasPockets;
    }

    public void setHasPockets(boolean hasPockets) {
        this.hasPockets = hasPockets;
    }

    public void setHipSize(int hipSize) {
        this.hipSize = hipSize;
    }

    public void setWaistSize(int waistSize) {
        this.waistSize = waistSize;
    }

    
    
}
