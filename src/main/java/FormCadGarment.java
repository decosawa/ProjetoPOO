//André Luiz Gonçalves da Silva Teixeira
//RA: 2564289

import java.awt.CardLayout;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author andre
 */
public class FormCadGarment extends javax.swing.JFrame {

    /**
     * Creates new form FormCadGarment
     */
    private CardLayout cardLayoutForSpecificFields;
    private FormCadGarment() {
        initComponents();
        cardLayoutForSpecificFields = (CardLayout) specificFieldsPanel.getLayout();
        setupListeners();
        updateDynamicPanels(); 
    }
    
    // Parser para conversão de tipos
    private final Parser parser = Parser.generateParser();

    //Singleton
    private static FormCadGarment formCadGarment;
    public static FormCadGarment getFormCadGarment() {
        if (formCadGarment == null) {
            formCadGarment = new FormCadGarment();
        }
        return formCadGarment;
    }
    
   private void setupListeners() {
        cbGarmentType.addActionListener(e -> updateDynamicPanels());
        btnRegister.addActionListener(e -> registerGarment());
        btnConsult.addActionListener(e -> consultGarment());
        btnUpdate.addActionListener(e -> updateGarment());
        btnDelete.addActionListener(e -> deleteGarment());
        btnClear.addActionListener(e -> clearForm());
    }

    private void updateDynamicPanels() {
        String selected = (String) cbGarmentType.getSelectedItem();
        if (selected != null) {
            switch (selected) {
                case "Camiseta":
                    cardLayoutForSpecificFields.show(specificFieldsPanel, "UPPER");
                    break;
                case "Calça":
                    cardLayoutForSpecificFields.show(specificFieldsPanel, "LOWER");
                    break;
                case "Peça Única":
                    cardLayoutForSpecificFields.show(specificFieldsPanel, "ONE_PIECE");
                    break;
            }
        }
    }

    private void registerGarment() {
        try {
            String selectedType = (String) cbGarmentType.getSelectedItem(); //Reflexividade
            Garment garment;

            if (selectedType.startsWith("Camiseta")) {
                UpperGarment ug = new UpperGarment();
                fillCommonFields(ug);
                ug.setBustSize(parser.tryParseInt(txtUpperBustSize.getText())); //Reflexividade
                ug.setSleeveLenght(txtUpperSleeveLength.getText()); //Reflexividade
                ug.setCollarType(txtUpperCollarType.getText()); //Reflexividade
                ug.setButton(chkUpperHasButton.isSelected());
                garment = ug;
            } else if (selectedType.startsWith("Calça")) {
                LowerGarment lg = new LowerGarment();
                fillCommonFields(lg);
                lg.setWaistSize(parser.tryParseInt(txtLowerWaistSize.getText())); //Reflexividade
                lg.setHipSize(parser.tryParseInt(txtLowerHipSize.getText())); //Reflexividade
                lg.setHasPockets(chkLowerHasPocket.isSelected());
                garment = lg;
            } else { // Peça Única
                OnePiece op = new OnePiece();
                fillCommonFields(op);
                op.setBustSize(parser.tryParseInt(txtOnePieceBustSize.getText())); //Reflexividade
                op.setSleeveLenght(txtOnePieceSleeveLength.getText()); //Reflexividade
                op.setCollarType(txtOnePieceCollarType.getText()); //Reflexividade
                op.setButton(chkOnePieceHasButton.isSelected()); //Reflexividade
                op.setWaistSize(parser.tryParseInt(txtOnePieceWaistSize.getText())); //Reflexividade
                op.setHipSize(parser.tryParseInt(txtOnePieceHipSize.getText())); //Reflexividade
                op.setHasPocket(chkOnePieceHasPocket.isSelected()); //Reflexividade
                garment = op;
            }

            GerGarment.getGerGarment().insertGarment(garment);
            JOptionPane.showMessageDialog(this, "Peça cadastrada com sucesso! ID: " + garment.getId(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            clearForm();

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Erro de formato: Verifique os campos numéricos (preço, comprimento, tamanhos).", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultGarment() {
        String idStr = JOptionPane.showInputDialog(this, "Digite o ID da peça para consultar:", "Consultar Peça", JOptionPane.QUESTION_MESSAGE);
        if (idStr == null || idStr.trim().isEmpty()) {
            return;
        }
        try {
            int id = parser.tryParseInt(idStr);
            Garment g = GerGarment.getGerGarment().consultGarmentById(id); //Reflexividade 
            if (g != null) {
                clearForm();
                populateForm(g);
                btnUpdate.setEnabled(true);
                btnDelete.setEnabled(true);
                btnRegister.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Peça com ID " + id + " não encontrada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                clearForm();
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "ID inválido. Por favor, insira um número inteiro.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateGarment() {
        try {
            int id = parser.tryParseInt(txtId.getText());
            Garment existingGarment = GerGarment.getGerGarment().consultGarmentById(id);
            if (existingGarment == null) {
                JOptionPane.showMessageDialog(this, "Erro: Peça não encontrada para alteração.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Garment updatedGarment;

            if (existingGarment instanceof UpperGarment) {
                UpperGarment ug = new UpperGarment();
                fillCommonFields(ug);
                ug.setBustSize(parser.tryParseInt(txtUpperBustSize.getText())); //Reflexividade
                ug.setSleeveLenght(txtUpperSleeveLength.getText()); //Reflexividade
                ug.setCollarType(txtUpperCollarType.getText()); //Reflexividade
                ug.setButton(chkUpperHasButton.isSelected()); //Reflexividade
                updatedGarment = ug;
            } else if (existingGarment instanceof LowerGarment) {
                LowerGarment lg = new LowerGarment();
                fillCommonFields(lg);
                lg.setWaistSize(parser.tryParseInt(txtLowerWaistSize.getText())); //Reflexividade
                lg.setHipSize(parser.tryParseInt(txtLowerHipSize.getText())); //Reflexividade
                lg.setHasPockets(chkLowerHasPocket.isSelected()); //Reflexividade
                updatedGarment = lg;
            } else { // OnePiece
                OnePiece op = new OnePiece();
                fillCommonFields(op);
                op.setBustSize(parser.tryParseInt(txtOnePieceBustSize.getText())); //Reflexividade
                op.setSleeveLenght(txtOnePieceSleeveLength.getText()); //Reflexividade
                op.setCollarType(txtOnePieceCollarType.getText()); //Reflexividade
                op.setButton(chkOnePieceHasButton.isSelected()); //Reflexividade
                op.setWaistSize(parser.tryParseInt(txtOnePieceWaistSize.getText())); //Reflexividade
                op.setHipSize(parser.tryParseInt(txtOnePieceHipSize.getText())); //Reflexividade
                op.setHasPocket(chkOnePieceHasPocket.isSelected()); //Reflexividade
                updatedGarment = op;
            }

            updatedGarment.setId(id); //Reflexividade

            GerGarment.getGerGarment().updateGarment(updatedGarment);
            JOptionPane.showMessageDialog(this, "Peça alterada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            clearForm();

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Erro de formato nos campos numéricos ao tentar alterar.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao alterar a peça: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteGarment() {
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir esta peça?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int id = parser.tryParseInt(txtId.getText());
                if (GerGarment.getGerGarment().deleteGarment(id)) {
                    JOptionPane.showMessageDialog(this, "Peça excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro: Peça não encontrada para exclusão.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir a peça: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void fillCommonFields(Garment garment) throws NegativeValueException, InvalidValueException {
        garment.setColor(txtColor.getText()); //Reflexividade
        garment.setBrand(txtBrand.getText()); //Reflexividade
        garment.setAudience(txtAudience.getText()); //Reflexividade
        garment.setFabric(txtFabric.getText()); //Reflexividade
        garment.setSeason(txtSeason.getText()); //Reflexividade
        garment.setPattern(txtPattern.getText()); //Reflexividade
        garment.setSize(txtSize.getText()); //Reflexividade
        garment.setLength(parser.tryParseInt(txtLength.getText())); //Reflexividade
        garment.setPrice(parser.tryParseDouble(txtPrice.getText())); //Reflexividade
    }

    private void populateForm(Garment g) {
        txtId.setText(String.valueOf(g.getId())); //Reflexividade
        txtColor.setText(g.getColor()); //Reflexividade
        txtBrand.setText(g.getBrand()); //Reflexividade
        txtAudience.setText(g.getAudience()); //Reflexividade
        txtFabric.setText(g.getFabric()); //Reflexividade
        txtSeason.setText(g.getSeason()); //Reflexividade
        txtPattern.setText(g.getPattern()); //Reflexividade
        txtSize.setText(g.getSize()); //Reflexividade
        txtLength.setText(String.valueOf(g.getLength())); //Reflexividade
        txtPrice.setText(String.format("%.2f", g.getPrice()).replace(",", ".")); //Reflexividade

        if (g instanceof UpperGarment) {
            UpperGarment ug = (UpperGarment) g;
            cbGarmentType.setSelectedItem("Camiseta (Upper)"); //Reflexividade
            txtUpperBustSize.setText(String.valueOf(ug.getBustSize())); //Reflexividade
            txtUpperSleeveLength.setText(ug.getSleeveLenght()); //Reflexividade
            txtUpperCollarType.setText(ug.getCollarType()); //Reflexividade
            chkUpperHasButton.setSelected(ug.hasButton()); //Reflexividade
        } else if (g instanceof LowerGarment) {
            LowerGarment lg = (LowerGarment) g;
            cbGarmentType.setSelectedItem("Calça (Lower)"); //Reflexividade
            txtLowerWaistSize.setText(String.valueOf(lg.getWaistSize())); //Reflexividade
            txtLowerHipSize.setText(String.valueOf(lg.getHipSize())); //Reflexividade
            chkLowerHasPocket.setSelected(lg.getHasPocket()); //Reflexividade
        } else if (g instanceof OnePiece) {
            OnePiece op = (OnePiece) g;
            cbGarmentType.setSelectedItem("Peça Única (OnePiece)");
            txtOnePieceBustSize.setText(String.valueOf(op.getBustSize())); //Reflexividade
            txtOnePieceSleeveLength.setText(op.getSleeveLenght()); //Reflexividade
            txtOnePieceCollarType.setText(op.getCollarType()); //Reflexividade
            chkOnePieceHasButton.setSelected(op.hasButton()); //Reflexividade
            txtOnePieceWaistSize.setText(String.valueOf(op.getWaistSize())); //Reflexividade
            txtOnePieceHipSize.setText(String.valueOf(op.getHipSize())); //Reflexividade
            chkOnePieceHasPocket.setSelected(op.hasPocket()); //Reflexividade
        }
        updateDynamicPanels();
    }

    private void clearForm() {
        // Limpa campos comuns
        txtId.setText(""); txtColor.setText(""); txtBrand.setText(""); txtAudience.setText(""); //Reflexividade
        txtFabric.setText(""); txtSeason.setText(""); txtPattern.setText(""); txtSize.setText(""); //Reflexividade
        txtLength.setText(""); txtPrice.setText(""); //Reflexividade
        
        // Limpa campos específicos
        txtUpperBustSize.setText(""); txtUpperSleeveLength.setText(""); txtUpperCollarType.setText(""); //Reflexividade
        chkUpperHasButton.setSelected(false); //Reflexividade
        txtLowerWaistSize.setText(""); txtLowerHipSize.setText(""); chkLowerHasPocket.setSelected(false); //Reflexividade
        txtOnePieceBustSize.setText(""); txtOnePieceSleeveLength.setText(""); txtOnePieceCollarType.setText(""); //Reflexividade
        txtOnePieceWaistSize.setText(""); txtOnePieceHipSize.setText(""); //Reflexividade
        chkOnePieceHasButton.setSelected(false); chkOnePieceHasPocket.setSelected(false); //Reflexividade

        cbGarmentType.setSelectedIndex(0); //Reflexividade

        btnRegister.setEnabled(true); //Reflexividade
        btnUpdate.setEnabled(false); //Reflexividade
        btnDelete.setEnabled(false); //Reflexividade
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbGarmentType = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtBrand = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtAudience = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtFabric = new javax.swing.JTextField();
        txtSize = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtLength = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        specificFieldsPanel = new javax.swing.JPanel();
        upperGarmentPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        chkUpperHasButton = new javax.swing.JCheckBox();
        txtUpperSleeveLength = new javax.swing.JTextField();
        txtUpperCollarType = new javax.swing.JTextField();
        txtUpperBustSize = new javax.swing.JTextField();
        lowerGarmentPanel = new javax.swing.JPanel();
        chkLowerHasPocket = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
        txtLowerHipSize = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtLowerWaistSize = new javax.swing.JTextField();
        onePiecePanel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        chkOnePieceHasButton = new javax.swing.JCheckBox();
        txtOnePieceCollarType = new javax.swing.JTextField();
        txtOnePieceSleeveLength = new javax.swing.JTextField();
        txtOnePieceBustSize = new javax.swing.JTextField();
        txtOnePieceWaistSize = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtOnePieceHipSize = new javax.swing.JTextField();
        chkOnePieceHasPocket = new javax.swing.JCheckBox();
        jLabel23 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnConsult = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txtPattern = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtSeason = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Gerais"));

        jLabel1.setText("Tipo de Peça:");

        cbGarmentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Camiseta", "Calça", "Peça Única" }));
        cbGarmentType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGarmentTypeActionPerformed(evt);
            }
        });

        jLabel2.setText("ID (Gerado Automaticamente):");

        txtId.setEditable(false);

        jLabel3.setText("Cor:");

        jLabel4.setText("Marca:");

        jLabel5.setText("Público:");

        jLabel6.setText("Tecido:");

        jLabel7.setText("Tamanho:");

        jLabel8.setText("Comprimento (em cm):");

        jLabel9.setText("Preço (em R$):");

        txtPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceActionPerformed(evt);
            }
        });

        specificFieldsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Específicos"));
        specificFieldsPanel.setLayout(new java.awt.CardLayout());

        jLabel10.setText("Tamanho Busto:");

        jLabel11.setText("Comprimento Manga:");

        jLabel12.setText("Tipo de Gola:");

        jLabel13.setText("Tem Botões?");

        chkUpperHasButton.setText("Sim");
        chkUpperHasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkUpperHasButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout upperGarmentPanelLayout = new javax.swing.GroupLayout(upperGarmentPanel);
        upperGarmentPanel.setLayout(upperGarmentPanelLayout);
        upperGarmentPanelLayout.setHorizontalGroup(
            upperGarmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperGarmentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperGarmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(upperGarmentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(64, 64, 64)
                        .addComponent(txtUpperBustSize))
                    .addGroup(upperGarmentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(33, 33, 33)
                        .addComponent(txtUpperSleeveLength, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                    .addGroup(upperGarmentPanelLayout.createSequentialGroup()
                        .addGroup(upperGarmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(81, 81, 81)
                        .addGroup(upperGarmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(upperGarmentPanelLayout.createSequentialGroup()
                                .addComponent(chkUpperHasButton)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtUpperCollarType, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))))
                .addGap(137, 137, 137))
        );
        upperGarmentPanelLayout.setVerticalGroup(
            upperGarmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperGarmentPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(upperGarmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtUpperBustSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(upperGarmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtUpperSleeveLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(upperGarmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtUpperCollarType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(upperGarmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(chkUpperHasButton))
                .addContainerGap(169, Short.MAX_VALUE))
        );

        specificFieldsPanel.add(upperGarmentPanel, "UPPER");

        chkLowerHasPocket.setText("Sim");
        chkLowerHasPocket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLowerHasPocketActionPerformed(evt);
            }
        });

        jLabel14.setText("Tem Bolsos?");

        jLabel16.setText("Tamanho Quadril:");

        jLabel17.setText("Tamanho Cintura:");

        javax.swing.GroupLayout lowerGarmentPanelLayout = new javax.swing.GroupLayout(lowerGarmentPanel);
        lowerGarmentPanel.setLayout(lowerGarmentPanelLayout);
        lowerGarmentPanelLayout.setHorizontalGroup(
            lowerGarmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowerGarmentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lowerGarmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lowerGarmentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(64, 64, 64)
                        .addComponent(txtLowerWaistSize, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
                    .addGroup(lowerGarmentPanelLayout.createSequentialGroup()
                        .addGroup(lowerGarmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addGap(64, 64, 64)
                        .addGroup(lowerGarmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLowerHipSize)
                            .addGroup(lowerGarmentPanelLayout.createSequentialGroup()
                                .addComponent(chkLowerHasPocket)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(117, 117, 117))
        );
        lowerGarmentPanelLayout.setVerticalGroup(
            lowerGarmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowerGarmentPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(lowerGarmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtLowerWaistSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(lowerGarmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtLowerHipSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(lowerGarmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(chkLowerHasPocket))
                .addContainerGap(225, Short.MAX_VALUE))
        );

        specificFieldsPanel.add(lowerGarmentPanel, "LOWER");

        jLabel15.setText("Tamanho Busto:");

        jLabel18.setText("Comprimento Manga:");

        jLabel19.setText("Tipo de Gola:");

        jLabel20.setText("Tem Botões?");

        chkOnePieceHasButton.setText("Sim");
        chkOnePieceHasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkOnePieceHasButtonActionPerformed(evt);
            }
        });

        jLabel21.setText("Tamanho Quadril:");

        jLabel22.setText("Tamanho Cintura:");

        chkOnePieceHasPocket.setText("Sim");
        chkOnePieceHasPocket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkOnePieceHasPocketActionPerformed(evt);
            }
        });

        jLabel23.setText("Tem Bolsos?");

        javax.swing.GroupLayout onePiecePanelLayout = new javax.swing.GroupLayout(onePiecePanel);
        onePiecePanel.setLayout(onePiecePanelLayout);
        onePiecePanelLayout.setHorizontalGroup(
            onePiecePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(onePiecePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(onePiecePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(onePiecePanelLayout.createSequentialGroup()
                        .addGroup(onePiecePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(onePiecePanelLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(64, 64, 64)
                                .addComponent(txtOnePieceBustSize, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                            .addGroup(onePiecePanelLayout.createSequentialGroup()
                                .addGroup(onePiecePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel21))
                                .addGap(33, 33, 33)
                                .addGroup(onePiecePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtOnePieceCollarType)
                                    .addComponent(txtOnePieceSleeveLength)
                                    .addComponent(txtOnePieceWaistSize)
                                    .addComponent(txtOnePieceHipSize))))
                        .addGap(137, 137, 137))
                    .addGroup(onePiecePanelLayout.createSequentialGroup()
                        .addGroup(onePiecePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(onePiecePanelLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(62, 62, 62)
                                .addComponent(chkOnePieceHasButton))
                            .addGroup(onePiecePanelLayout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(64, 64, 64)
                                .addComponent(chkOnePieceHasPocket)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        onePiecePanelLayout.setVerticalGroup(
            onePiecePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(onePiecePanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(onePiecePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtOnePieceBustSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(onePiecePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtOnePieceSleeveLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(onePiecePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtOnePieceCollarType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(onePiecePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtOnePieceWaistSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(onePiecePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtOnePieceHipSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(onePiecePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(chkOnePieceHasPocket))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(onePiecePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(chkOnePieceHasButton))
                .addGap(42, 42, 42))
        );

        specificFieldsPanel.add(onePiecePanel, "ONE_PIECE");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ações"));

        btnConsult.setText("Consultar");
        jPanel2.add(btnConsult);

        btnRegister.setText("Cadastrar");
        jPanel2.add(btnRegister);

        btnDelete.setText("Excluir");
        jPanel2.add(btnDelete);

        btnUpdate.setText("Alterar");
        jPanel2.add(btnUpdate);

        btnClear.setText("Limpar");
        jPanel2.add(btnClear);

        jLabel24.setText("Padrão:");

        txtPattern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPatternActionPerformed(evt);
            }
        });

        jLabel25.setText("Estação:");
        jLabel25.setToolTipText("");

        txtSeason.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSeasonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(specificFieldsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtSeason, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtPrice)
                        .addComponent(txtBrand)
                        .addComponent(txtSize, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                        .addComponent(txtAudience, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                        .addComponent(txtFabric, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                        .addComponent(cbGarmentType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtColor)
                        .addComponent(txtId)
                        .addComponent(txtLength, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPattern)))
                .addGap(76, 76, 76))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbGarmentType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAudience, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtFabric, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtPattern, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtSeason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(specificFieldsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel24.getAccessibleContext().setAccessibleName("Padrão:");

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkUpperHasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkUpperHasButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkUpperHasButtonActionPerformed

    private void chkLowerHasPocketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLowerHasPocketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkLowerHasPocketActionPerformed

    private void chkOnePieceHasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkOnePieceHasButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkOnePieceHasButtonActionPerformed

    private void chkOnePieceHasPocketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkOnePieceHasPocketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkOnePieceHasPocketActionPerformed

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceActionPerformed

    private void txtPatternActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPatternActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPatternActionPerformed

    private void txtSeasonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSeasonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSeasonActionPerformed

    private void cbGarmentTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGarmentTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbGarmentTypeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormCadGarment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCadGarment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCadGarment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCadGarment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCadGarment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnConsult;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbGarmentType;
    private javax.swing.JCheckBox chkLowerHasPocket;
    private javax.swing.JCheckBox chkOnePieceHasButton;
    private javax.swing.JCheckBox chkOnePieceHasPocket;
    private javax.swing.JCheckBox chkUpperHasButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel lowerGarmentPanel;
    private javax.swing.JPanel onePiecePanel;
    private javax.swing.JPanel specificFieldsPanel;
    private javax.swing.JTextField txtAudience;
    private javax.swing.JTextField txtBrand;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtFabric;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLength;
    private javax.swing.JTextField txtLowerHipSize;
    private javax.swing.JTextField txtLowerWaistSize;
    private javax.swing.JTextField txtOnePieceBustSize;
    private javax.swing.JTextField txtOnePieceCollarType;
    private javax.swing.JTextField txtOnePieceHipSize;
    private javax.swing.JTextField txtOnePieceSleeveLength;
    private javax.swing.JTextField txtOnePieceWaistSize;
    private javax.swing.JTextField txtPattern;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSeason;
    private javax.swing.JTextField txtSize;
    private javax.swing.JTextField txtUpperBustSize;
    private javax.swing.JTextField txtUpperCollarType;
    private javax.swing.JTextField txtUpperSleeveLength;
    private javax.swing.JPanel upperGarmentPanel;
    // End of variables declaration//GEN-END:variables
}
