import java.util.ArrayList; 
import java.util.List;    

public class Principal {

    public static void main(String[] args) {
        //Singleton
        Reader reader = Reader.generateReader();
        Parser parser = Parser.generateParser();
        
        // Instanciação dos objetos de vestuário
        List<UpperGarment> upperGarments = new ArrayList<>(); 
        List<LowerGarment> lowerGarments = new ArrayList<>(); 
        List<OnePiece> onePieces = new ArrayList<>(); 
        List<Sell> sales = new ArrayList<>(); // <-- NOVA LISTA PARA ARMAZENAR VÁRIAS VENDAS

        int option;
        String input;

        // Loop principal do menu
        do {
            System.out.println("\n--- Menu Principal de Gestão de Roupas ---");
            System.out.println("1. Registrar Camiseta");
            System.out.println("2. Registrar Calça");
            System.out.println("3. Registrar Peça Única");
            System.out.println("4. Mostrar Detalhes das Camisetas"); 
            System.out.println("5. Mostrar Detalhes das Calças");   
            System.out.println("6. Mostrar Detalhes das Peças Únicas"); 
            System.out.println("7. Realizar Venda"); 
            System.out.println("8. Mostrar Detalhes das Vendas"); // <-- NOVA OPÇÃO PARA MOSTRAR VENDAS
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            input = reader.dataEntry("");
            option = parser.tryParseInt(input); 

            switch (option) {
                case 1:
                    registerUpperGarment(reader, parser, upperGarments); 
                    break;
                case 2:
                    registerLowerGarment(reader, parser, lowerGarments); 
                    break;
                case 3:
                    registerOnePiece(reader, parser, onePieces); 
                    break;
                case 4:
                    displayUpperGarments(upperGarments); 
                    break;
                case 5:
                    displayLowerGarments(lowerGarments); 
                    break;
                case 6:
                    displayOnePieces(onePieces); 
                    break;
                case 7:
                    performSale(reader, parser, upperGarments, lowerGarments, onePieces, sales); 
                    break;
                case 8: 
                    displaySales(sales);
                    break;
                case 0:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (option != 0);
    }

    // MÉTODOS DE REGISTO (sem alterações em relação à versão anterior)
    public static void registerUpperGarment(Reader reader, Parser parser, List<UpperGarment> upperGarments) { 
        System.out.println("\n--- Registro de Camiseta ---");
        try {
            UpperGarment newUpperGarment = new UpperGarment(); 

            System.out.print("Cor: ");
            newUpperGarment.setColor(reader.dataEntry("")); //Reflexividade
            System.out.print("Marca: ");
            newUpperGarment.setBrand(reader.dataEntry("")); //Reflexividade
            System.out.print("Público (Masculino/Feminino/Infantil): ");
            newUpperGarment.setAudience(reader.dataEntry("")); //Reflexividade
            System.out.print("Tecido: ");
            newUpperGarment.setFabric(reader.dataEntry("")); //Reflexividade
            System.out.print("Estação (Verão/Inverno/Outono/Primavera): ");
            newUpperGarment.setSeason(reader.dataEntry("")); //Reflexividade
            System.out.print("Padrão (Liso/Estampado): ");
            newUpperGarment.setPattern(reader.dataEntry("")); //Reflexividade

            boolean validLength = false;
            while (!validLength) {
                try {
                    System.out.print("Comprimento em cm: ");
                    newUpperGarment.setLength(parser.tryParseInt(reader.dataEntry(""))); //Reflexividade
                    validLength = true;
                } catch (NegativeValueException e) {
                    System.out.println("Erro: " + e.getMessage() + " Por favor, insira um valor válido.");
                }
            }

            boolean validPrice = false;
            while (!validPrice) {
                try {
                    System.out.print("Preço: R$");
                    newUpperGarment.setPrice(parser.tryParseDouble(reader.dataEntry(""))); //Reflexividade
                    validPrice = true;
                } catch (NegativeValueException e) {
                    System.out.println("Erro: " + e.getMessage() + " Por favor, insira um valor válido.");
                }
            }

            boolean validSize = false;
            while (!validSize) {
                try {
                    System.out.print("Tamanho (PP, P, M, G, GG): ");
                    String sizeInput = reader.dataEntry("");
                    if (!verifySizeLetterBased(sizeInput)) { 
                        throw new InvalidValueException("Tamanho inválido. Use PP, P, M, G ou GG.");
                    }
                    newUpperGarment.setSize(sizeInput);  //Reflexividade
                    validSize = true;
                } catch (InvalidValueException e) {
                    System.out.println("Erro: " + e.getMessage() + " Tente novamente.");
                }
            }
            
            boolean validBustSize = false;
            while (!validBustSize) {
                try {
                    System.out.print("Tamanho do Busto: ");
                    newUpperGarment.setBustSize(parser.tryParseInt(reader.dataEntry(""))); //Reflexividade
                    validBustSize = true;
                } catch (InvalidValueException e) {
                    System.out.println("Erro: " + e.getMessage() + " Por favor, insira um valor válido.");
                }
            }

            System.out.print("Comprimento da Manga (Curta/Longa/3/4): ");
            newUpperGarment.setSleeveLenght(reader.dataEntry("")); //Reflexividade
            System.out.print("Tipo de Gola (Redonda/V/Polo): ");
            newUpperGarment.setCollarType(reader.dataEntry("")); //Reflexividade
            System.out.print("Tem Botões (sim/nao): ");
            newUpperGarment.setButton(reader.dataEntry("").equalsIgnoreCase("sim")); //Reflexividade

            upperGarments.add(newUpperGarment); 
            System.out.println("Camiseta registrada com sucesso!");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado ao registrar a camiseta: " + e.getMessage());
        }
    }

    public static void registerLowerGarment(Reader reader, Parser parser, List<LowerGarment> lowerGarments) { 
        System.out.println("\n--- Registro de Calça ---");
        try {
            LowerGarment newLowerGarment = new LowerGarment(); 

            System.out.print("Cor: ");
            newLowerGarment.setColor(reader.dataEntry("")); //Reflexividade
            System.out.print("Marca: ");
            newLowerGarment.setBrand(reader.dataEntry("")); //Reflexividade
            System.out.print("Público (Masculino/Feminino/Infantil): ");
            newLowerGarment.setAudience(reader.dataEntry("")); //Reflexividade
            System.out.print("Tecido: ");
            newLowerGarment.setFabric(reader.dataEntry("")); //Reflexividade
            System.out.print("Estação (Verão/Inverno/Outono/Primavera): ");
            newLowerGarment.setSeason(reader.dataEntry("")); //Reflexividade
            System.out.print("Padrão (Liso/Estampado): ");
            newLowerGarment.setPattern(reader.dataEntry("")); //Reflexividade

            boolean validLength = false;
            while (!validLength) {
                try {
                    System.out.print("Comprimento em cm: ");
                    newLowerGarment.setLength(parser.tryParseInt(reader.dataEntry(""))); //Reflexividade
                    validLength = true;
                } catch (NegativeValueException e) {
                    System.out.println("Erro: " + e.getMessage() + " Por favor, insira um valor válido.");
                }
            }

            boolean validPrice = false;
            while (!validPrice) {
                try {
                    System.out.print("Preço: R$");
                    newLowerGarment.setPrice(parser.tryParseDouble(reader.dataEntry(""))); //Reflexividade
                    validPrice = true;
                } catch (NegativeValueException e) {
                    System.out.println("Erro: " + e.getMessage() + " Por favor, insira um valor válido.");
                }
            }

            boolean validSize = false;
            while (!validSize) {
                try {
                    System.out.print("Tamanho (30-54, apenas números pares): ");
                    int sizeValue = parser.tryParseInt(reader.dataEntry(""));
                    if (!verifySizeNumberBased(sizeValue)) { 
                        throw new InvalidValueException("Tamanho inválido. Use números pares entre 30 e 54.");
                    }
                    newLowerGarment.setSize(parser.parseIntToString(sizeValue)); //Reflexividade
                    validSize = true;
                } catch (InvalidValueException e) {
                    System.out.println("Erro: " + e.getMessage() + " Tente novamente.");
                }
            }

            boolean validWaistSize = false;
            while (!validWaistSize) {
                try {
                    System.out.print("Tamanho da Cintura: ");
                    newLowerGarment.setWaistSize(parser.tryParseInt(reader.dataEntry(""))); //Reflexividade
                    validWaistSize = true;
                } catch (InvalidValueException e) {
                    System.out.println("Erro: " + e.getMessage() + " Por favor, insira um valor válido.");
                }
            }

            boolean validHipSize = false;
            while (!validHipSize) {
                try {
                    System.out.print("Tamanho do Quadril: ");
                    newLowerGarment.setHipSize(parser.tryParseInt(reader.dataEntry(""))); //Reflexividade
                    validHipSize = true;
                } catch (InvalidValueException e) {
                    System.out.println("Erro: " + e.getMessage() + " Por favor, insira um valor válido.");
                }
            }

            System.out.print("Tem Bolsos (sim/nao): ");
            newLowerGarment.setHasPockets(reader.dataEntry("").equalsIgnoreCase("sim")); //Reflexividade

            lowerGarments.add(newLowerGarment); 
            System.out.println("Calça registrada com sucesso!");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado ao registrar a calça: " + e.getMessage());
        }
    }

    public static void registerOnePiece(Reader reader, Parser parser, List<OnePiece> onePieces) { 
        System.out.println("\n--- Registro de Peça Única ---");
        try {
            OnePiece newOnePiece = new OnePiece(); 

            System.out.print("Cor: ");
            newOnePiece.setColor(reader.dataEntry("")); //Reflexividade
            System.out.print("Marca: ");
            newOnePiece.setBrand(reader.dataEntry("")); //Reflexividade
            System.out.print("Público (Masculino/Feminino/Infantil): ");
            newOnePiece.setAudience(reader.dataEntry("")); //Reflexividade
            System.out.print("Tecido: ");
            newOnePiece.setFabric(reader.dataEntry("")); //Reflexividade
            System.out.print("Estação (Verão/Inverno/Outono/Primavera): ");
            newOnePiece.setSeason(reader.dataEntry("")); //Reflexividade
            System.out.print("Padrão (Liso/Estampado): ");
            newOnePiece.setPattern(reader.dataEntry("")); //Reflexividade

            boolean validLength = false;
            while (!validLength) {
                try {
                    System.out.print("Comprimento em cm: ");
                    newOnePiece.setLength(parser.tryParseInt(reader.dataEntry(""))); //Reflexividade
                    validLength = true;
                } catch (NegativeValueException e) {
                    System.out.println("Erro: " + e.getMessage() + " Por favor, insira um valor válido.");
                }
            }

            boolean validPrice = false;
            while (!validPrice) {
                try {
                    System.out.print("Preço: R$");
                    newOnePiece.setPrice(parser.tryParseDouble(reader.dataEntry(""))); //Reflexividade
                    validPrice = true;
                } catch (NegativeValueException e) {
                    System.out.println("Erro: " + e.getMessage() + " Por favor, insira um valor válido.");
                }
            }

            boolean validSize = false;
            while (!validSize) {
                try {
                    System.out.print("Tamanho (PP, P, M, G, GG ou 30-54, pares): ");
                    String sizeInput = reader.dataEntry("");
                    boolean isLetterSize = verifySizeLetterBased(sizeInput);
                    boolean isNumberSize = false;
                    try {
                        int numSize = Integer.parseInt(sizeInput);
                        isNumberSize = verifySizeNumberBased(numSize);
                    } catch (NumberFormatException ignored) {
                        // Não é um número, então isNumberSize permanece false
                    }

                    if (!isLetterSize && !isNumberSize) {
                        throw new InvalidValueException("Tamanho inválido. Use PP, P, M, G, GG ou números pares entre 30 e 54.");
                    }
                    newOnePiece.setSize(sizeInput); //Reflexividade
                    validSize = true;
                } catch (InvalidValueException e) {
                    System.out.println("Erro: " + e.getMessage() + " Tente novamente.");
                }
            }

            boolean validBustSize = false;
            while (!validBustSize) {
                try {
                    System.out.print("Tamanho do Busto: ");
                    newOnePiece.setBustSize(parser.tryParseInt(reader.dataEntry(""))); //Reflexividade
                    validBustSize = true;
                } catch (InvalidValueException e) {
                    System.out.println("Erro: " + e.getMessage() + " Por favor, insira um valor válido.");
                }
            }

            boolean validWaistSize = false;
            while (!validWaistSize) {
                try {
                    System.out.print("Tamanho da Cintura: ");
                    newOnePiece.setWaistSize(parser.tryParseInt(reader.dataEntry(""))); //Reflexividade
                    validWaistSize = true;
                } catch (InvalidValueException e) {
                    System.out.println("Erro: " + e.getMessage() + " Por favor, insira um valor válido.");
                }
            }

            boolean validHipSize = false;
            while (!validHipSize) {
                try {
                    System.out.print("Tamanho do Quadril: ");
                    newOnePiece.setHipSize(parser.tryParseInt(reader.dataEntry(""))); //Reflexividade
                    validHipSize = true;
                } catch (InvalidValueException e) {
                    System.out.println("Erro: " + e.getMessage() + " Por favor, insira um valor válido.");
                }
            }

            System.out.print("Comprimento da Manga (Curta/Longa/3/4): ");
            newOnePiece.setSleeveLenght(reader.dataEntry("")); //Reflexividade
            System.out.print("Tipo de Gola (Redonda/V/Polo): ");
            newOnePiece.setCollarType(reader.dataEntry("")); //Reflexividade
            System.out.print("Tem Botões (sim/nao): ");
            newOnePiece.setButton(reader.dataEntry("").equalsIgnoreCase("sim")); //Reflexividade
            System.out.print("Tem Bolsos (sim/nao): ");
            newOnePiece.setHasPocket(reader.dataEntry("").equalsIgnoreCase("sim")); //Reflexividade

            onePieces.add(newOnePiece); 
            System.out.println("Peça Única registrada com sucesso!");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado ao registrar a peça única: " + e.getMessage());
        }
    }

    public static void displayUpperGarments(List<UpperGarment> upperGarments) { 
        System.out.println("\n--- Detalhes das Camisetas Registradas ---");
        if (upperGarments.isEmpty()) { 
            System.out.println("Nenhuma camiseta registrada ainda."); 
            return; 
        }
        for (int i = 0; i < upperGarments.size(); i++) { 
            System.out.println("\n--- Camiseta #" + (i + 1) + " ---"); 
            upperGarments.get(i).displayDetails(""); 
        }
    }

    public static void displayLowerGarments(List<LowerGarment> lowerGarments) { 
        System.out.println("\n--- Detalhes das Calças Registradas ---");
        if (lowerGarments.isEmpty()) { 
            System.out.println("Nenhuma calça registrada ainda."); 
            return; 
        }
        for (int i = 0; i < lowerGarments.size(); i++) { 
            System.out.println("\n--- Calça #" + (i + 1) + " ---"); 
            lowerGarments.get(i).displayDetails(""); 
        }
    }

    public static void displayOnePieces(List<OnePiece> onePieces) { 
        System.out.println("\n--- Detalhes das Peças Únicas Registradas ---");
        if (onePieces.isEmpty()) { 
            System.out.println("Nenhuma peça única registrada ainda."); 
            return; 
        }
        for (int i = 0; i < onePieces.size(); i++) { 
            System.out.println("\n--- Peça Única #" + (i + 1) + " ---"); 
            onePieces.get(i).displayDetails(""); 
        }
    }

    // MÉTODO PARA REALIZAR VENDAS
    public static void performSale(Reader reader, Parser parser, 
                                List<UpperGarment> upperGarments, 
                                List<LowerGarment> lowerGarments, 
                                List<OnePiece> onePieces,
                                List<Sell> sales) {
        System.out.println("\n--- Realizar Venda ---");
        try {
            Sell newSell = new Sell(); // <-- CRIA UMA NOVA INSTÂNCIA DE VENDA

            System.out.print("Nome do Cliente: ");
            newSell.setCustomerName(reader.dataEntry("")); //Reflexividade

            Garment selectedGarment = null; 

            System.out.println("\nSelecione o tipo de peça para venda:"); 
            System.out.println("1. Camiseta"); 
            System.out.println("2. Calça"); 
            System.out.println("3. Peça Única"); 
            System.out.print("Opção: "); 
            int garmentTypeOption = parser.tryParseInt(reader.dataEntry(""));  

            List<? extends Garment> currentGarmentList = null; 

            switch (garmentTypeOption) { 
                case 1:
                    currentGarmentList = upperGarments; 
                    System.out.println("\n--- Camisetas Disponíveis ---"); 
                    for (int i = 0; i < upperGarments.size(); i++) { 
                        System.out.println((i + 1) + ". " + upperGarments.get(i).getBrand() + " - " + upperGarments.get(i).getColor()); //Reflexividade 
                    }
                    break;
                case 2:
                    currentGarmentList = lowerGarments; 
                    System.out.println("\n--- Calças Disponíveis ---"); 
                    for (int i = 0; i < lowerGarments.size(); i++) { 
                        System.out.println((i + 1) + ". " + lowerGarments.get(i).getBrand() + " - " + lowerGarments.get(i).getColor()); //Reflexividade
                    }
                    break;
                case 3:
                    currentGarmentList = onePieces; 
                    System.out.println("\n--- Peças Únicas Disponíveis ---"); 
                    for (int i = 0; i < onePieces.size(); i++) { 
                        System.out.println((i + 1) + ". " + onePieces.get(i).getBrand() + " - " + onePieces.get(i).getColor()); //Reflexividade 
                    }
                    break;
                default:
                    System.out.println("Opção de tipo de peça inválida."); 
                    return; 
            }

            if (currentGarmentList == null || currentGarmentList.isEmpty()) { 
                System.out.println("Não há itens deste tipo para vender."); 
                return; 
            }

            System.out.print("Selecione o número do item para venda: "); 
            int itemIndex = parser.tryParseInt(reader.dataEntry("")) - 1; 

            if (itemIndex >= 0 && itemIndex < currentGarmentList.size()) { 
                selectedGarment = currentGarmentList.get(itemIndex); 
            } else { 
                System.out.println("Seleção de item inválida."); 
                return; 
            }

            newSell.setGarment(selectedGarment); 

            boolean validQuantity = false;
            while (!validQuantity) {
                try {
                    System.out.print("Quantidade: ");
                    newSell.setQuantity(parser.tryParseInt(reader.dataEntry(""))); //Reflexividade
                    validQuantity = true;
                } catch (InvalidValueException e) {
                    System.out.println("Erro: " + e.getMessage() + " Por favor, insira um valor válido.");
                }
            }

            double itemPrice = selectedGarment.calculatePrice(); 
            newSell.setTotalPrice(itemPrice * newSell.getQuantity()); //Reflexividade

            System.out.print("Método de Pagamento: ");
            newSell.setPaymentMethod(reader.dataEntry("")); //Reflexividade

            System.out.println("\n--- Detalhes da Venda ---");
            System.out.println("Cliente: " + newSell.getCustomerName()); //Reflexividade
            System.out.println("Item: " + newSell.getGarment().getBrand() + " (" + newSell.getGarment().getClass().getSimpleName() + ")"); //Reflexividade
            System.out.println("Quantidade: " + newSell.getQuantity()); //Reflexividade
            System.out.println("Preço Unitário: R$" + String.format("%.2f", itemPrice)); 
            System.out.println("Preço Total: R$" + String.format("%.2f", newSell.getTotalPrice())); //Reflexividade
            System.out.println("Método de Pagamento: " + newSell.getPaymentMethod()); //Reflexividade

            System.out.print("Deseja aplicar um desconto? (sim/nao): ");
            if (reader.dataEntry("").equalsIgnoreCase("sim")) {
                boolean validDiscount = false;
                while (!validDiscount) {
                    try {
                        System.out.print("Porcentagem de Desconto (0-100): ");
                        double discount = parser.tryParseDouble(reader.dataEntry(""));
                        selectedGarment.applyDiscount(discount); 
                        newSell.setTotalPrice(selectedGarment.calculatePrice() * newSell.getQuantity());  //Reflexividade
                        System.out.println("Desconto aplicado! Novo preço total: R$" + String.format("%.2f", newSell.getTotalPrice())); //Reflexividade
                        validDiscount = true;
                    } catch (InvalidValueException e) {
                        System.out.println("Erro: " + e.getMessage() + " Por favor, insira um valor válido.");
                    }
                }
            }
            sales.add(newSell); // <-- ADICIONA A NOVA VENDA À LISTA DE VENDAS
            System.out.println("Venda concluída e registrada!");

        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado durante a venda: " + e.getMessage());
        }
    }

    // NOVO MÉTODO PARA EXIBIR DETALHES DE VÁRIAS VENDAS
    public static void displaySales(List<Sell> sales) {
        System.out.println("\n--- Detalhes das Vendas Registradas ---");
        if (sales.isEmpty()) {
            System.out.println("Nenhuma venda registrada ainda.");
            return;
        }
        for (int i = 0; i < sales.size(); i++) {
            Sell currentSell = sales.get(i);
            System.out.println("\n--- Venda #" + (i + 1) + " ---");
            System.out.println("Cliente: " + currentSell.getCustomerName());
            // Verifica se o item da venda é nulo antes de tentar acessar seus métodos
            if (currentSell.getGarment() != null) {
                System.out.println("Item Vendido: " + currentSell.getGarment().getBrand() + " (" + currentSell.getGarment().getClass().getSimpleName() + ")"); //Reflexividade
            } else {
                System.out.println("Item Vendido: [Item não especificado]");
            }
            System.out.println("Quantidade: " + currentSell.getQuantity()); //Reflexividade
            // Recalcula o preço unitário para exibição, se o garment não for nulo
            if (currentSell.getGarment() != null) {
                System.out.println("Preço Unitário: R$" + String.format("%.2f", currentSell.getGarment().calculatePrice())); //Reflexividade
            }
            System.out.println("Preço Total da Venda: R$" + String.format("%.2f", currentSell.getTotalPrice())); //Reflexividade
            System.out.println("Método de Pagamento: " + currentSell.getPaymentMethod()); //Reflexividade
        }
    }

    public static boolean verifySizeNumberBased(int value) {
        return value >= 30 && value <= 54 && value % 2 == 0;
    }

    public static boolean verifySizeLetterBased(String value){
        String upperCaseValue = value.toUpperCase();
        return upperCaseValue.equals("PP") || upperCaseValue.equals("P") || upperCaseValue.equals("M") || upperCaseValue.equals("G") || upperCaseValue.equals("GG");
    }
}