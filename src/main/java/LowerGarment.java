//André Luiz Gonçalves da Silva Teixeira
//RA: 2564289

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author andre
 */
public class LowerGarment extends Garment {
    private int waistSize;
    private int hipSize;
    private boolean hasPocket;
    
    public LowerGarment() { super(); }

    public int getWaistSize() { return waistSize; }
    public void setWaistSize(int waistSize) throws InvalidValueException {
        if (waistSize <= 0) { throw new InvalidValueException("O tamanho da cintura deve ser positivo."); }
        this.waistSize = waistSize;
    }
    public int getHipSize() { return hipSize; }
    public void setHipSize(int hipSize) throws InvalidValueException {
        if (hipSize <= 0) { throw new InvalidValueException("O tamanho do quadril deve ser positivo."); }
        this.hipSize = hipSize;
    }
    public boolean getHasPocket() { return hasPocket; }
    public void setHasPockets(boolean hasPocket) { this.hasPocket = hasPocket; }
    
    //Sobrescrita
    public String getDetailsAsString() {
        return super.getDetailsAsString() +
               "Detalhes específicos da Calça:\n" +
               "  Tamanho da Cintura: " + this.waistSize + "\n" +
               "  Tamanho do Quadril: " + this.hipSize + "\n" +
               "  Tem Bolsos: " + (this.hasPocket ? "Sim" : "Não") + "\n";
    }
}
