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
public interface Sellable {
    double calculatePrice();
    void applyDiscount(double percentage) throws InvalidValueException;
}
