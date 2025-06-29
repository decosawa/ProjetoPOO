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
public class Parser {
    private static Parser parser;
    private Parser() {}
    
    //Singleton
    public static Parser generateParser() {
        if (parser == null) { parser = new Parser(); }
        return parser;
    }
    
    public int tryParseInt(String value) throws NumberFormatException {
        return Integer.parseInt(value.trim());
    }
    public double tryParseDouble(String value) throws NumberFormatException {
        return Double.parseDouble(value.trim().replace(",", "."));
    }
    
}
