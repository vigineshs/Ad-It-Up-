/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface.SystemAdmin;

import Business.Advertiser.Advertiser;
import Business.Enterprise;
import Business.Network.Network;
import Business.Organization.AdvertiserOrganization;
import Business.Organization.Organization;
import Business.Organization.SupplierOrganization;
import Business.Person.Person;
import Business.Role.AdvertiserRole;
import Business.Role.Role;
import Business.Role.SupplierRole;
import Business.Role.UserRole;
import Business.Supplier.Supplier;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.UserAccountWorkRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hema
 */
public class ManageUserAccountsJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageUserAccountsJPanel
     */
    private Enterprise enterprise;
    private JPanel container;
    public ManageUserAccountsJPanel(JPanel container, Enterprise enterprise) {
        initComponents();
        this.container = container;
        this.enterprise = enterprise;
        populateTable();
        hideFields();
    }
    
    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) userAccountsJTable.getModel();
        model.setRowCount(0);
        
        for(UserAccountWorkRequest workRequest :  enterprise.getAdminOrganization().getWorkQueue().getUserAccountWorkRequests()) {
            Object[] row = new Object[4];
            row[0] = workRequest.getSender();
            row[1] = workRequest;
            row[2] = workRequest.getStatus();
            row[3] = workRequest.getCategory();
            
            model.addRow(row);
        }
    }

    private void hideFields() {
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
        networkNameJTextField.setVisible(false);
        nameJTextField.setVisible(false);
        ageJTextField.setVisible(false);
        genderJTextField.setVisible(false);
        categoryJTextField.setVisible(false);
        usernameJTextField.setVisible(false);
        passwordJTextField.setVisible(false);
        companyNameJTextField.setVisible(false);
        acceptJButton.setVisible(false);
        roleJComboBox.setVisible(false);
    }
    
    private void showFields() {
        jLabel1.setVisible(true);
        jLabel2.setVisible(true);
        jLabel3.setVisible(true);
        jLabel4.setVisible(true);
        jLabel5.setVisible(true);
        jLabel7.setVisible(true);
        jLabel8.setVisible(true);
        jLabel6.setVisible(true);
        jLabel9.setVisible(true);
        networkNameJTextField.setVisible(true);
        nameJTextField.setVisible(true);
        ageJTextField.setVisible(true);
        genderJTextField.setVisible(true);
        categoryJTextField.setVisible(true);
        usernameJTextField.setVisible(true);
        passwordJTextField.setVisible(true);
        companyNameJTextField.setVisible(true);
        acceptJButton.setVisible(true);
        roleJComboBox.setVisible(true);
    }
    
    private void displayDetails() {
        int selectedRow = userAccountsJTable.getSelectedRow();
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(null,"please select a row", "Invalid Option", JOptionPane.WARNING_MESSAGE);
            return;        
        }
        UserAccountWorkRequest accountWorkRequest = (UserAccountWorkRequest) userAccountsJTable.getValueAt(selectedRow, 1);
        if(accountWorkRequest.getStatus().equals("Pending")) {
            showFields();
            nameJTextField.setText(accountWorkRequest.getSender());
            genderJTextField.setText(accountWorkRequest.getGender());
            ageJTextField.setText(String.valueOf(accountWorkRequest.getAge()));
            categoryJTextField.setText(String.valueOf(accountWorkRequest.getCategory()));
            usernameJTextField.setText(accountWorkRequest.getUsername());
            passwordJTextField.setText(accountWorkRequest.getPassword());
            networkNameJTextField.setText(String.valueOf(accountWorkRequest.getNetwork()));
            companyNameJTextField.setText(accountWorkRequest.getCompanyName());
            Organization organization = accountWorkRequest.getCategory();
            roleJComboBox.removeAllItems();
            for(Role role : organization.getSupportedRole())
                roleJComboBox.addItem(role);
        }
        else {
            JOptionPane.showMessageDialog(null,"Already processed!", "Invalid Option", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
    }
    
    private void acceptRequest() {
        int selectedRow = userAccountsJTable.getSelectedRow();
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(null,"please select a row", "Invalid Option", JOptionPane.WARNING_MESSAGE);
            return;        
        }
        UserAccountWorkRequest accountWorkRequest = (UserAccountWorkRequest) userAccountsJTable.getValueAt(selectedRow, 1);
        Network network =  accountWorkRequest.getNetwork();
            for(Organization organization : network.getOrganizationDirectory().getOrganizationList()) {
                if(organization.getName().equals(categoryJTextField.getText())) {
                    Person person = organization.getPersonDirectory().createAndAddPerson(nameJTextField.getText(), genderJTextField.getText(), companyNameJTextField.getText(), Integer.parseInt(ageJTextField.getText()));
                    UserAccount userAccount = organization.getUserAccountDirectory().createAndAddUserAccount(usernameJTextField.getText(), passwordJTextField.getText(), person, (Role) roleJComboBox.getSelectedItem());
                    organization.getFinancialAccountDirectory().createAndAddFinanceAccount(accountWorkRequest.getCardNumber(), accountWorkRequest.getCvv(), accountWorkRequest.getExpirationMonth(), accountWorkRequest.getExpirationYear(), accountWorkRequest.getUsername());
                    if(organization.getName().equals(Organization.Type.Supplier.getValue())) {
                        SupplierOrganization supplierOrganization = (SupplierOrganization) organization;
                        Supplier supplier = supplierOrganization.getSupplierDirectory().createAndAddSupplier(usernameJTextField.getText());
                        SupplierRole supplierRole = (SupplierRole) userAccount.getRole();
                        supplierRole.setSupplier(supplier);
                    }
                    else if(organization.getName().equals(Organization.Type.Advertiser.getValue())) {
                        AdvertiserOrganization advertiserOrganization = (AdvertiserOrganization) organization;
                        Advertiser advertiser = advertiserOrganization.getAdvertiserDirectory().createAndAddAdvertiser(usernameJTextField.getText());
                        AdvertiserRole advertiserRole = (AdvertiserRole) userAccount.getRole();
                        advertiserRole.setAdvertiser(advertiser);
                    }
                }
            }
            accountWorkRequest.setStatus("Accepted");
            JOptionPane.showMessageDialog(null,"User Account Created!", "Success", JOptionPane.WARNING_MESSAGE);
            hideFields();
            populateTable();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rejectJButton = new javax.swing.JButton();
        acceptJButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        userAccountsJTable = new javax.swing.JTable();
        ageJTextField = new javax.swing.JTextField();
        usernameJTextField = new javax.swing.JTextField();
        nameJTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        viewDetailsJButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        backJButton5 = new javax.swing.JButton();
        categoryJTextField = new javax.swing.JTextField();
        genderJTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        passwordJTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        networkNameJTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        companyNameJTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        roleJComboBox = new javax.swing.JComboBox();

        rejectJButton.setText("Reject");
        rejectJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectJButtonActionPerformed(evt);
            }
        });

        acceptJButton.setText("Accept");
        acceptJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptJButtonActionPerformed(evt);
            }
        });

        userAccountsJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Person", "Username", "Status", "Category"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(userAccountsJTable);

        ageJTextField.setEditable(false);

        usernameJTextField.setEditable(false);

        nameJTextField.setEditable(false);

        jLabel2.setText("Age");

        jLabel3.setText("Username");

        viewDetailsJButton.setText("View Details");
        viewDetailsJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDetailsJButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Name");

        backJButton5.setText("Back");
        backJButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButton5ActionPerformed(evt);
            }
        });

        categoryJTextField.setEditable(false);

        genderJTextField.setEditable(false);

        jLabel5.setText("Category");

        passwordJTextField.setEditable(false);

        jLabel7.setText("Gender");

        jLabel8.setText("Password");

        jLabel4.setText("Network");

        networkNameJTextField.setEditable(false);

        jLabel6.setText("Company Name");

        companyNameJTextField.setEditable(false);

        jLabel9.setText("Role");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(rejectJButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(backJButton5)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(286, 286, 286)
                                .addComponent(viewDetailsJButton))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameJTextField)
                            .addComponent(ageJTextField)
                            .addComponent(usernameJTextField)
                            .addComponent(companyNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(genderJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoryJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(networkNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(acceptJButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(roleJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(backJButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rejectJButton)
                    .addComponent(viewDetailsJButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(networkNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(companyNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(nameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(ageJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(usernameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(genderJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(categoryJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(passwordJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(roleJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(acceptJButton)
                .addContainerGap(93, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backJButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButton5ActionPerformed
        // TODO add your handling code here:
        container.remove(this);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
    }//GEN-LAST:event_backJButton5ActionPerformed

    private void viewDetailsJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewDetailsJButtonActionPerformed
        // TODO add your handling code here:
        displayDetails();
    }//GEN-LAST:event_viewDetailsJButtonActionPerformed

    private void acceptJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptJButtonActionPerformed
        // TODO add your handling code here:
        acceptRequest();
    }//GEN-LAST:event_acceptJButtonActionPerformed

    private void rejectJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = userAccountsJTable.getSelectedRow();
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(null,"please select a row", "Invalid Option", JOptionPane.WARNING_MESSAGE);
            return;        
        }
        UserAccountWorkRequest accountWorkRequest = (UserAccountWorkRequest) userAccountsJTable.getValueAt(selectedRow, 1);
        accountWorkRequest.setStatus("Rejected");
        hideFields();
        populateTable();
    }//GEN-LAST:event_rejectJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptJButton;
    private javax.swing.JTextField ageJTextField;
    private javax.swing.JButton backJButton5;
    private javax.swing.JTextField categoryJTextField;
    private javax.swing.JTextField companyNameJTextField;
    private javax.swing.JTextField genderJTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JTextField networkNameJTextField;
    private javax.swing.JTextField passwordJTextField;
    private javax.swing.JButton rejectJButton;
    private javax.swing.JComboBox roleJComboBox;
    private javax.swing.JTable userAccountsJTable;
    private javax.swing.JTextField usernameJTextField;
    private javax.swing.JButton viewDetailsJButton;
    // End of variables declaration//GEN-END:variables
}
