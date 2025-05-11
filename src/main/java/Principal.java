public class Principal {

    public static void main(String[] args) {
        Reader reader = Reader.generateReader();
        Parser parser = Parser.generateParser();
        LowerGarment lowerGarment = new LowerGarment();
        UpperGarment upperGarment = new UpperGarment();
        OnePiece onePiece = new OnePiece();

        String value = "";

        System.out.println("Entre tamanho de camiseta: ");
        value = reader.dataEntry(value);
        boolean isValid = verifySizeLetterBased(value);
        while (!isValid) {
            System.out.println("Tamanho inválido. Tente novamente.");
            value = reader.dataEntry(value);
            isValid = verifySizeLetterBased(value);
        }
        upperGarment.setSize(value);

        System.out.println("Entre tamanho de calça: ");
        value = reader.dataEntry(value);
        int parsedIntegerValue = parser.tryParseInt(value);
        isValid = verifySizeNumberBased(parsedIntegerValue);
        
        while (!isValid) {
            System.out.println("Tamanho inválido. Tente novamente.");
            value = reader.dataEntry(value);
            parsedIntegerValue = parser.tryParseInt(value);
            isValid = verifySizeNumberBased(parsedIntegerValue);
        }
        lowerGarment.setSize(parser.parseIntToString(parsedIntegerValue));

        System.out.println("Tamanho da camiseta: " + upperGarment.getSize());
        System.out.println("Tamanho da calça: " + lowerGarment.getSize());
    }

    public static boolean verifySizeNumberBased(int value) {
        boolean isValid = false;
        if(value >= 30 && value <= 54) {
            if(value % 2 == 0) {
                return isValid = true;
            }
        }
        return isValid;
    }

    public static boolean verifySizeLetterBased(String value){
        boolean isValid = false;
        return !isValid ? value.equalsIgnoreCase("PP") || value.equalsIgnoreCase("P") || value.equalsIgnoreCase("M") || value.equalsIgnoreCase("G") || value.equalsIgnoreCase("GG"): isValid;
    }
        
}
