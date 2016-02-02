/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface.SystemAdmin;

import Business.BidCategory.BidCategory;
import Business.BidCategory.ProfileCategory;
import Business.Enterprise;
import Business.Organization.AdminOrganization;
import Business.ProductCategory.ProductCategory;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hema
 */
public class ManageCategoriesJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageProfileCategoryJPanel
     */
    JPanel container;
    AdminOrganization adminOrganization;
    Enterprise enterprise;
    public ManageCategoriesJPanel(JPanel container, Enterprise enterprise) {
        initComponents();
        this.container = container;
        this.enterprise = enterprise;
        this.adminOrganization = (AdminOrganization) enterprise.getAdminOrganization();
        System.out.println(adminOrganization);
        populateCategories();
        populateProductCategoryTable();
        hideFields();
    }

    private void populateCategories() {
        categoryJComboBox.removeAllItems();
        categoryJComboBox.addItem("Bid Cateories");
        categoryJComboBox.addItem("Profile Categories");
    }
    
    private void populateBidCategories() {
        DefaultTableModel model = (DefaultTableModel) categoriesJTable.getModel();
        model.setRowCount(0);
        try{
            for(BidCategory bidCategory : adminOrganization.getBidCategoryDirectory().getBidCategoryList()) {
                Object[] row = new Object[2];
                row[0] = bidCategory;
                row[1] = bidCategory.getBasePrice();
                model.addRow(row);
            }
        }
        catch(Exception e) {}
    }
    
    private void populateProfileCategories() {
        DefaultTableModel model = (DefaultTableModel) categoriesJTable.getModel();
        model.setRowCount(0);
        try {
            for(ProfileCategory profileCategory : adminOrganization.getProfileCategoryDirectory().getProfileCategoryList()) {
                Object[] row = new Object[2];
                row[0] = profileCategory;
                row[1] = profileCategory.getBasePrice();
                model.addRow(row);
            }
        }
        catch(Exception e) {}
    }
    
    private void populateProductCategoryTable() {
        DefaultTableModel tableModel = (DefaultTableModel) productCategoryJTable.getModel();
        tableModel.setRowCount(0);
        for(ProductCategory productCategory : adminOrganization.getProductCategoryDirectory().getProductList()) {
            Object[] row = new Object[2];
            row[0] = productCategory.getProductCategoryID();
            row[1] = productCategory;
            tableModel.addRow(row);
        }
    }
    
    private void hideFields() {
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        categoryJTextField.setVisible(false);
        descriptionJTextField.setVisible(false);
        basePriceJTextField.setVisible(false);
        userPotentialJTextField.setVisible(false);
        createJButton.setVisible(false);
    }
    
    private void showFields() {
        jLabel3.setVisible(true);
        jLabel4.setVisible(true);
        jLabel5.setVisible(true);
        categoryJTextField.setVisible(true);
        descriptionJTextField.setVisible(true);
        basePriceJTextField.setVisible(true);
        createJButton.setVisible(true);
    }
    
    private void createBidCategory() {
        if(categoryJTextField.getText().equals("") || descriptionJTextField.getText().equals("") || basePriceJTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill all fields", "Empty Text", JOptionPane.WARNING_MESSAGE);
                    return;
        }
        for(BidCategory bidCategory : adminOrganization.getBidCategoryDirectory().getBidCategoryList()) {
            if(bidCategory.getAdSpace().equals(categoryJTextField.getText()) || bidCategory.getAdSpaceDesccription().equals(descriptionJTextField.getText())) {
                JOptionPane.showMessageDialog(null, "Entry already available", "Duplicate", JOptionPane.WARNING_MESSAGE);
                    return;
            }
        }
        String basePrice = basePriceJTextField.getText();
        for(int i = 0; i <basePrice.length();i++){
            char c = basePrice.charAt(i);
            if(c<48 || c>57){
                if(c !=46) {
                    JOptionPane.showMessageDialog(null, "Base Price field should contain only number", "Mismatch", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        }
        adminOrganization.getBidCategoryDirectory().createAndAddBidCategory(categoryJTextField.getText(), descriptionJTextField.getText(), Double.parseDouble(basePriceJTextField.getText()));
    }
    
    private void createProfileCategory() {
        if(categoryJTextField.getText().equals("") || descriptionJTextField.getText().equals("") || basePriceJTextField.getText().equals("") || userPotentialJTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill all fields", "Empty Text", JOptionPane.WARNING_MESSAGE);
                    return;
        }
        String basePrice = basePriceJTextField.getText();
        for(int i = 0; i <basePrice.length();i++){
            char c = basePrice.charAt(i);
            if(c<48 || c>57){
                if(c !=46) {
                    JOptionPane.showMessageDialog(null, "Base Price field should contain only number", "Mismatch", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        }
        String up = userPotentialJTextField.getText();
        for(int i = 0; i <up.length();i++){
            char c = up.charAt(i);
            if(c<48 || c>57){
                JOptionPane.showMessageDialog(null, "User Potential field should contain only number", "Mismatch", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        if(Integer.parseInt(up) <= 0 || Integer.parseInt(up) > 100) {
            JOptionPane.showMessageDialog(null, "User Potential field should between 0 to 100", "Mismatch", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for(ProfileCategory profileCategory : adminOrganization.getProfileCategoryDirectory().getProfileCategoryList()) {
            if(profileCategory.getCategoryName().equals(categoryJTextField.getText()) || profileCategory.getCategoryDescription().equals(descriptionJTextField.getText())) {
                JOptionPane.showMessageDialog(null, "Entry already made0", "Duplicate", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        adminOrganization.getProfileCategoryDirectory().createAndAddProfileCategory(categoryJTextField.getText(), descriptionJTextField.getText(), Integer.parseInt(userPotentialJTextField.getText()), Double.parseDouble(basePriceJTextField.getText()));
    }
    
    private void validateAndAdd() {
        if(productCategoryJTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill product Category", "Empty Text", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for(ProductCategory productCategory : adminOrganization.getProductCategoryDirectory().getProductList()) {
            if(productCategoryJTextField.getText().equals(productCategory.getCategory())) {
                JOptionPane.showMessageDialog(null, "Please fill a product category", "Duplicate Category", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        for(ProductCategory productCategory : adminOrganization.getProductCategoryDirectory().getProductList()) {
            if(productCategory.getCategory().equals(categoryJTextField.getText())) {
                JOptionPane.showMessageDialog(null, "Entry already made", "Duplicate", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        adminOrganization.getProductCategoryDirectory().createAndAddProductCategory(productCategoryJTextField.getText());
        populateProductCategoryTable();
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
        jLabel2 = new javax.swing.JLabel();
        categoryJComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        descriptionJTextField = new javax.swing.JTextField();
        categoryJTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        basePriceJTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        userPotentialJTextField = new javax.swing.JTextField();
        createJButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        categoriesJTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        productCategoryJTable = new javax.swing.JTable();
        productCategoryJTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        addJButton = new javax.swing.JButton();
        backJButton5 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Manage Category");

        jLabel2.setText("Choose Category to be managed");

        categoryJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryJComboBoxActionPerformed(evt);
            }
        });

        jLabel3.setText("Category Name");

        jLabel4.setText("Description");

        jLabel5.setText("Base Price");

        jLabel6.setText("UserPotential");

        createJButton.setText("Create");
        createJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createJButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("%");

        categoriesJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Category Name", "Base Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(categoriesJTable);
        if (categoriesJTable.getColumnModel().getColumnCount() > 0) {
            categoriesJTable.getColumnModel().getColumn(0).setResizable(false);
            categoriesJTable.getColumnModel().getColumn(1).setResizable(false);
        }

        productCategoryJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Product Category"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(productCategoryJTable);

        jLabel8.setText("Product Category");

        addJButton.setText("Add");
        addJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJButtonActionPerformed(evt);
            }
        });

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
                        .addGap(46, 46, 46)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addGap(33, 33, 33)
                                                .addComponent(productCategoryJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(addJButton)
                                                .addGap(71, 71, 71))))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(categoryJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(userPotentialJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7))
                                    .addComponent(categoryJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                    .addComponent(descriptionJTextField)
                                    .addComponent(basePriceJTextField)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(createJButton)))))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backJButton5)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(categoryJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(categoryJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(descriptionJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(productCategoryJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(addJButton)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(basePriceJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(userPotentialJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(createJButton)
                .addContainerGap(117, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void categoryJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryJComboBoxActionPerformed
        // TODO add your handling code here:
        String category = (String) categoryJComboBox.getSelectedItem();
        showFields();
        if(category.equals("Bid Cateories")) {
            userPotentialJTextField.setVisible(false);
            jLabel7.setVisible(false);
            jLabel6.setVisible(false);
            populateBidCategories();
        }
        else {
            userPotentialJTextField.setVisible(true);
            jLabel7.setVisible(true);
            jLabel6.setVisible(true);
            populateProfileCategories();
        }
    }//GEN-LAST:event_categoryJComboBoxActionPerformed

    private void createJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createJButtonActionPerformed
        // TODO add your handling code here:
        String category = (String) categoryJComboBox.getSelectedItem();
        if(category.equals("Bid Cateories")) {
            createBidCategory();
        }
        else {
            createProfileCategory();
        }
    }//GEN-LAST:event_createJButtonActionPerformed

    private void addJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addJButtonActionPerformed
        // TODO add your handling code here:
        validateAndAdd();
    }//GEN-LAST:event_addJButtonActionPerformed

    private void backJButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButton5ActionPerformed
        // TODO add your handling code here:
        container.remove(this);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
    }//GEN-LAST:event_backJButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addJButton;
    private javax.swing.JButton backJButton5;
    private javax.swing.JTextField basePriceJTextField;
    private javax.swing.JTable categoriesJTable;
    private javax.swing.JComboBox categoryJComboBox;
    private javax.swing.JTextField categoryJTextField;
    private javax.swing.JButton createJButton;
    private javax.swing.JTextField descriptionJTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable productCategoryJTable;
    private javax.swing.JTextField productCategoryJTextField;
    private javax.swing.JTextField userPotentialJTextField;
    // End of variables declaration//GEN-END:variables
}
