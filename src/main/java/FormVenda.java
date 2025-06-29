//André Luiz Gonçalves da Silva Teixeira
//RA: 2564289

import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author andre
 */
public class FormVenda extends javax.swing.JFrame {

    /**
     * Creates new form FormVenda
     */
    private FormVenda() {
        initComponents();
        setupListeners();
    }
    
    private final Parser parser = Parser.generateParser();
    private double currentDiscount = 0.0; //
    
    //Singleton
    private static FormVenda formVenda;
    public static FormVenda getFormVenda() {
        if (formVenda == null) {
            formVenda = new FormVenda();
        }
        formVenda.loadGarments();
        return formVenda;
    }
    
    private void setupListeners() {
        cbGarments.addItemListener(e -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                updatePrice();
            }
        });
        sldQuantity.addChangeListener(e -> updatePrice());
        btnPerformSale.addActionListener(e -> performSale());
        btnClear.addActionListener(e -> clearForm());
        btnApplyPercentDiscount.addActionListener(e -> applyPercentDiscount());
        btnApplyFixedDiscount.addActionListener(e -> applyFixedDiscount());
    }

    public void loadGarments() {
        Object selectedItem = cbGarments.getSelectedItem(); // Salva o item selecionado
        
        DefaultComboBoxModel<Garment> model = new DefaultComboBoxModel<>();
        for (Garment g : GerGarment.getGerGarment().getGarmentList()) {
            model.addElement(g);
        } //Reflexividade
        cbGarments.setModel(model);

        cbGarments.setRenderer(new DefaultListCellRenderer() {

            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Garment) {
                    Garment g = (Garment) value;
                    setText("ID: " + g.getId() + " - " + g.getBrand() + " (" + g.getColor() + ")"); //Reflexividade
                }
                return this;
            } 
        });
        
        if (selectedItem != null) {
            cbGarments.setSelectedItem(selectedItem);
        }
        
        updatePrice();
    }

    private void updatePrice() {
        currentDiscount = 0;
        Garment selectedGarment = (Garment) cbGarments.getSelectedItem();
        if (selectedGarment == null) {
            lblUnitPrice.setText("R$ 0.00");
            lblTotalPrice.setText("R$ 0.00");
            return;
        }
        lblUnitPrice.setText(String.format("R$ %.2f", selectedGarment.getPrice())); //Reflexividade
        int quantity = sldQuantity.getValue(); //Reflexividade
        double totalPrice = selectedGarment.getPrice() * quantity; //Reflexividade
        lblTotalPrice.setText(String.format("R$ %.2f", totalPrice)); //Reflexividade
    }
    
    private void applyPercentDiscount() {
        String input = JOptionPane.showInputDialog(this, "Digite a porcentagem de desconto (ex: 10 para 10%):", "Aplicar Desconto Percentual", JOptionPane.QUESTION_MESSAGE);
        if (input == null) return;
        
        try {
            double percentage = parser.tryParseDouble(input);
            if (percentage < 0 || percentage > 100) throw new InvalidValueException("A porcentagem deve ser entre 0 e 100.");
            
            Garment g = (Garment) cbGarments.getSelectedItem(); //Reflexividade
            int quantity = sldQuantity.getValue(); //Reflexividade
            double originalTotal = g.getPrice() * quantity; //Reflexividade
            
            currentDiscount = originalTotal * (percentage / 100.0); 
            double finalPrice = originalTotal - currentDiscount;
            
            lblTotalPrice.setText(String.format("R$ %.2f", finalPrice)); //Reflexividade
            JOptionPane.showMessageDialog(this, String.format("Desconto de R$ %.2f aplicado!", currentDiscount), "Desconto Aplicado", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao aplicar desconto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void applyFixedDiscount() {
        String input = JOptionPane.showInputDialog(this, "Digite o valor fixo do desconto (ex: 15 para R$15,00):", "Aplicar Desconto Fixo", JOptionPane.QUESTION_MESSAGE);
        if (input == null) return;

        try {
            double fixedAmount = parser.tryParseDouble(input);
            if (fixedAmount < 0) throw new NegativeValueException("O valor do desconto não pode ser negativo.");

            Garment g = (Garment) cbGarments.getSelectedItem();
            int quantity = sldQuantity.getValue();
            double originalTotal = g.getPrice() * quantity; //Reflexividade
            
            if (fixedAmount > originalTotal) throw new InvalidValueException("O desconto não pode ser maior que o preço total.");

            currentDiscount = fixedAmount;
            double finalPrice = originalTotal - currentDiscount;

            lblTotalPrice.setText(String.format("R$ %.2f", finalPrice));
            JOptionPane.showMessageDialog(this, String.format("Desconto de R$ %.2f aplicado!", currentDiscount), "Desconto Aplicado", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao aplicar desconto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void performSale() {
        try {
            Garment selectedGarment = (Garment) cbGarments.getSelectedItem();
            if (selectedGarment == null) {
                JOptionPane.showMessageDialog(this, "Nenhuma peça selecionada. Venda não pode ser realizada.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String customerName = txtCustomerName.getText(); //Reflexividade
            int quantity = sldQuantity.getValue(); //Reflexividade
            String paymentMethod = (String) cbPaymentMethod.getSelectedItem(); //Reflexividade
            double finalPrice = (selectedGarment.getPrice() * quantity) - currentDiscount; //Reflexividade
            
            if (customerName.trim().isEmpty()) {
                throw new InvalidValueException("O nome do cliente é obrigatório.");
            }
            
            Sell newSell = new Sell();
            newSell.setCustomerName(customerName); //Reflexividade
            newSell.setGarment(selectedGarment); //Reflexividade
            newSell.setQuantity(quantity); //Reflexividade
            newSell.setPaymentMethod(paymentMethod); //Reflexividade
            newSell.setTotalPrice(finalPrice); //Reflexividade

            GerSell.getGerSell().insertSell(newSell);
            
            JOptionPane.showMessageDialog(this, "Venda realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            
        } catch (InvalidValueException | NegativeValueException ex) {
            JOptionPane.showMessageDialog(this, "Erro de Validação: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearForm() {
        txtCustomerName.setText(""); //Reflexividade
        if (cbGarments.getItemCount() > 0) {
            cbGarments.setSelectedIndex(0); //Reflexividade
        }
        if (cbPaymentMethod.getItemCount() > 0) {
            cbPaymentMethod.setSelectedIndex(0); //Reflexividade
        }
        sldQuantity.setValue(1); //Reflexividade
        updatePrice();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbPaymentMethod = new javax.swing.JComboBox<>();
        lblUnitPrice = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        sldQuantity = new javax.swing.JSlider();
        cbGarments = new javax.swing.JComboBox<>();
        lblTotalPrice = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnPerformSale = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnApplyPercentDiscount = new javax.swing.JButton();
        btnApplyFixedDiscount = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Selecione a Peça:");

        cbPaymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dinheiro", "Pix", "Cartão" }));

        lblUnitPrice.setText("R$ 0.00");

        jLabel3.setText("Preço Unitário:");

        jLabel4.setText("Nome do Cliente:");

        jLabel5.setText("Quantidade:");

        jLabel6.setText("Método de Pagamento:");

        sldQuantity.setMaximum(10);
        sldQuantity.setMinimum(1);
        sldQuantity.setMinorTickSpacing(1);
        sldQuantity.setPaintLabels(true);
        sldQuantity.setPaintTicks(true);
        sldQuantity.setValue(1);

        cbGarments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGarmentsActionPerformed(evt);
            }
        });

        lblTotalPrice.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTotalPrice.setText("R$ 0.00");

        jLabel8.setText("Preço Total:");

        btnPerformSale.setText("Realizar Venda");

        btnClear.setText("Limpar");

        btnApplyPercentDiscount.setText("Aplicar Desconto em %");

        btnApplyFixedDiscount.setText("Aplicar Desconto Fixo");
        btnApplyFixedDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyFixedDiscountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbGarments, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblUnitPrice)
                                        .addComponent(cbPaymentMethod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtCustomerName)
                                        .addComponent(sldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblTotalPrice)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnApplyPercentDiscount)
                                            .addComponent(btnApplyFixedDiscount))))
                                .addGap(0, 64, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnClear)
                        .addGap(18, 18, 18)
                        .addComponent(btnPerformSale)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbGarments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lblUnitPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalPrice)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnApplyFixedDiscount)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnApplyPercentDiscount)))))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPerformSale)
                    .addComponent(btnClear))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbGarmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGarmentsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbGarmentsActionPerformed

    private void btnApplyFixedDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyFixedDiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnApplyFixedDiscountActionPerformed

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
            java.util.logging.Logger.getLogger(FormVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApplyFixedDiscount;
    private javax.swing.JButton btnApplyPercentDiscount;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnPerformSale;
    private javax.swing.JComboBox<Garment> cbGarments;
    private javax.swing.JComboBox<String> cbPaymentMethod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblTotalPrice;
    private javax.swing.JLabel lblUnitPrice;
    private javax.swing.JSlider sldQuantity;
    private javax.swing.JTextField txtCustomerName;
    // End of variables declaration//GEN-END:variables
}
