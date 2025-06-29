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

import java.util.ArrayList;
import java.util.List;

public class GerSell {

    private final List<Sell> sellList;

    private static GerSell gerSell;
    
    //Singleton
    private GerSell() {
        sellList = new ArrayList<>();
    }

    public static GerSell getGerSell() {
        if (gerSell == null) {
            gerSell = new GerSell();
        }
        return gerSell;
    }

    public List<Sell> getSellList() {
        return sellList;
    }

    public void insertSell(Sell sell) {
        sellList.add(sell);
    }
}
