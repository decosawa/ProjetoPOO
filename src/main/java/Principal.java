public class Principal {

    public static void main(String[] args) {
        Reader reader = Reader.generateReader();
        Parser parser = Parser.generateParser();
        LowerGarment lowerGarment = new LowerGarment();
        UpperGarment upperGarment = new UpperGarment();

        String value = "";
        boolean isValid = false;

        System.out.println("Entre tamanho de camiseta: ");
        value = reader.dataEntry(value);
        upperGarment.setSize(value.toUpperCase());
        isValid = verifyRealSizeUpperGarment(upperGarment.getSize());
        do {
            if (isValid) {
                System.out.println("Tamanho válido: " + upperGarment.getSize());
            } else {
                System.out.println("Tamanho inválido. Tente novamente.");
                value = reader.dataEntry(value);
                upperGarment.setSize(value);
                isValid = verifyRealSizeUpperGarment(upperGarment.getSize());
            }
        } while (!isValid);

        System.out.println("Entre tamanho de calça: ");
        value = reader.dataEntry(value);
        lowerGarment.setSize(parser.tryParseInt(value));
        isValid = verifyRealSizeLowerGarment(lowerGarment.getSize());
        do {
            if (isValid) {
                System.out.println("Tamanho válido: " + lowerGarment.getSize());
            } else {
                System.out.println("Tamanho inválido. Tente novamente.");
                value = reader.dataEntry(value);
                lowerGarment.setSize(parser.tryParseInt(value));
                isValid = verifyRealSizeLowerGarment(lowerGarment.getSize());
            }
        } while (!isValid);

        
        System.out.println("==========================");
        System.out.println("Tamanho de calça: " + lowerGarment.getSize());
        System.out.println("Tamanho de camiseta: " + upperGarment.getSize());
    }

    public static Boolean verifyRealSizeUpperGarment(String size){
        boolean isValid=false;
        return !isValid ? size.equals("PP") || size.equals("P") || size.equals("M") || size.equals("G") || size.equals("GG") : false;
    }

    public static Boolean verifyRealSizeLowerGarment(int size){
        boolean isValid=false;
        if(size >= 30 && size <= 54){
            if(size % 2 == 0){
                isValid = true;
            }else{
                isValid = false;
            }
        }else{
            isValid = false;
        }

        return isValid;
    }
       
}
