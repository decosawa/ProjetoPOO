public class Parser {
    
    private static Parser parser;
    public static int count=0;

    private Parser(){}

    public static Parser generateParser() {
        if(parser == null) {
            parser = new Parser();
            count++;
        }
        return parser;
    }

    public int tryParseInt(String value){
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            System.out.println("Por favor, insira um número inteiro.");
            value = Reader.generateReader().dataEntry(value);
            return tryParseInt(value);
        }
    }

    public double tryParseDouble(String value){
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            System.out.println("Por favor, insira um número decimal.");
            value = Reader.generateReader().dataEntry(value);
            return tryParseDouble(value);
        }
    }


}
