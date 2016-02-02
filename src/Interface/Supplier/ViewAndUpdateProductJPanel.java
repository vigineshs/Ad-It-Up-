/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Supplier;

import Business.Network.Network;
import Business.Organization.Organization;
import Business.Organization.SupplierOrganization;
import Business.Supplier.Product;
import Business.Supplier.Supplier;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Hema
 */
public class ViewAndUpdateProductJPanel extends javax.swing.JPanel {

    
    /**
     * Creates new form ViewAndUpdateProductJPanel
     */
    private JPanel container;
    private Product product;
    public ViewAndUpdateProductJPanel(JPanel container, Product product) {
        initComponents();
        this.container = container;
        this.product = product;
        populateTextField();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        productNameJTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        backJButton = new javax.swing.JButton();
        productPriceJTextField = new javax.swing.JTextField();
        updateJButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        backJButton5 = new javax.swing.JButton();

        jLabel4.setText("Product Price");

        backJButton.setText("<< Back");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });

        updateJButton.setText("Update Product");
        updateJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateJButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Update Product");

        jLabel2.setText("Product Name");

        backJButton5.setText("Back");
        backJButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backJButton5)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(backJButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(updateJButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(69, 69, 69)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(productNameJTextField)
                                    .addComponent(productPriceJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jLabel1)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(backJButton5)
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(productPriceJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateJButton)
                            .addComponent(backJButton)))
                    .addComponent(productNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(97, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void populateTextField() {
        productNameJTextField.setText(product.getProductName());
        productPriceJTextField.setText(String.valueOf(product.getPrice()));
    }
    
    private void backJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButtonActionPerformed
        // TODO add your handling code here:
        container.remove(this);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
    }//GEN-LAST:event_backJButtonActionPerformed

    private void updateJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateJButtonActionPerformed
        // TODO add your handling code here:
        if(productNameJTextField.getText().equals("") || productPriceJTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Fill in all the fields", "Empty Text", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String price = productPriceJTextField.getText();
            for(int i = 0; i <price.length();i++){
                char c = price.charAt(i);
                if(c<48 || c>57){
                     if(c !=46) {
                    JOptionPane.showMessageDialog(null, "Base Price field should contain only number", "Mismatch", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                }
            }
        product.setProductName(productNameJTextField.getText());
        product.setPrice(Float.parseFloat(productPriceJTextField.getText()));
        JOptionPane.showMessageDialog(null, "Update successful! ", "Success", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_updateJButtonActionPerformed

    private void backJButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButton5ActionPerformed
        // TODO add your handling code here:
        container.remove(this);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
    }//GEN-LAST:event_backJButton5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backJButton;
    private javax.swing.JButton backJButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField productNameJTextField;
    private javax.swing.JTextField productPriceJTextField;
    private javax.swing.JButton updateJButton;
    // End of variables declaration//GEN-END:variables
}