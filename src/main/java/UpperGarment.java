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
public class UpperGarment extends Garment {
    private int bustSize;
    private String sleeveLenght;
    private String collarType;
    private boolean hasButton;

    public UpperGarment() { super(); }

    public int getBustSize() { return bustSize; }
    public void setBustSize(int bustSize) throws InvalidValueException {
        if (bustSize <= 0) { throw new InvalidValueException("O tamanho do busto deve ser positivo."); }
        this.bustSize = bustSize;
    }
    public String getSleeveLenght() { return sleeveLenght; }
    public void setSleeveLenght(String sleeveLenght) { this.sleeveLenght = sleeveLenght; }
    public String getCollarType() { return collarType; }
    public void setCollarType(String collarType) { this.collarType = collarType; }
    public boolean hasButton() { return hasButton; }
    public void setButton(boolean hasButton) { this.hasButton = hasButton; }

    //Sobrescrita
    public String getDetailsAsString() {
        return super.getDetailsAsString() +
               "Detalhes específicos da Camiseta:\n" +
               "  Tamanho do Busto: " + this.bustSize + "\n" +
               "  Comprimento da Manga: " + this.sleeveLenght + "\n" +
               "  Tipo de Gola: " + this.collarType + "\n" +
               "  Tem Botões: " + (this.hasButton ? "Sim" : "Não") + "\n";
    }
}
