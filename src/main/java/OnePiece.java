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
public class OnePiece extends Garment {
    private int waistSize, hipSize, bustSize;
    private String sleeveLenght, collarType;
    private boolean hasButton, hasPocket;

    public OnePiece() { super(); }

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
    public int getBustSize() { return bustSize; }
    public void setBustSize(int bustSize) throws InvalidValueException {
        if (bustSize <= 0) { throw new InvalidValueException("O tamanho do busto deve ser positivo."); }
        this.bustSize = bustSize;
    }
    public String getSleeveLenght() { return sleeveLenght; }
    public void setSleeveLenght(String s) { this.sleeveLenght = s; }
    public String getCollarType() { return collarType; }
    public void setCollarType(String s) { this.collarType = s; }
    public boolean hasButton() { return hasButton; }
    public void setButton(boolean b) { this.hasButton = b; }
    public boolean hasPocket() { return hasPocket; }
    public void setHasPocket(boolean b) { this.hasPocket = b; }
    
    //Sobrescrita
    public String getDetailsAsString() {
        return super.getDetailsAsString() +
               "Detalhes específicos da Peça Única:\n" +
               "  Tamanho do Busto: " + this.bustSize + "\n" +
               "  Tamanho da Cintura: " + this.waistSize + "\n" +
               "  Tamanho do Quadril: " + this.hipSize + "\n" +
               "  Comprimento da Manga: " + this.sleeveLenght + "\n" +
               "  Tipo de Gola: " + this.collarType + "\n" +
               "  Tem Botões: " + (this.hasButton ? "Sim" : "Não") + "\n" +
               "  Tem Bolsos: " + (this.hasPocket ? "Sim" : "Não") + "\n";
    }
}
