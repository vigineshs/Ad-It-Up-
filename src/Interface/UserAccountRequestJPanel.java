/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import Business.DB4OUtil.DB4OUtil;
import Business.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.UserAccountWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Hema
 */
public class UserAccountRequestJPanel extends javax.swing.JPanel {

    /**
     * Creates new form UserAccountRequestJPanel
     */
    JPanel container;
    Enterprise enterprise;
    private DB4OUtil db;
    public UserAccountRequestJPanel(JPanel container, Enterprise enterprise, DB4OUtil db) {
        initComponents();
        this.container = container;
        this.enterprise = enterprise;
        this.db = db;
        populateGenderComboBox();
        populateNetworkComboBox();
        hideFields();
    }

    private void populateNetworkComboBox() {
        for(Network network : enterprise.getNetworkDirectory().getNetworkList())
            networkJComboBox.addItem(network);
    }
    
    private void populateCategoryComboBox() {
        categoryJComboBox.removeAllItems();
        Network network = (Network) networkJComboBox.getSelectedItem();
        for(Organization organization : network.getOrganizationDirectory().getOrganizationList())
            categoryJComboBox.addItem(organization);
    }
    
    private void populateGenderComboBox() {
        genderJComboBox.removeAllItems();
        genderJComboBox.addItem("Male");
        genderJComboBox.addItem("Female");
    }
    
    private void createAccount() {
        if(nameJTextField.getText().equals("") || ageJTextField.getText().equals("") ||  usernameJTextField.getText().equals("") || passwordField.getText().equals("") || rePasswordField.getText().equals("") || cardNumberJTextField.getText().equals("") || cvvJTextField.getText().equals("") || expirationMonthJTextField.getText().equals("") || expirationYearJTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields", "Empty Text", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(usernameJTextField.getText().equals("systemadmin")) {
            JOptionPane.showMessageDialog(null, "Username unavailable", "Duplicate Username", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(networkJComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Network not available", "Empty Text", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(categoryJComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Organization not available", "Empty Text", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String age = ageJTextField.getText();
        for(int i = 0; i <age.length();i++){
            char c = age.charAt(i);
            if(c<48 || c>57){
                JOptionPane.showMessageDialog(null, "Age field should contain only number", "Mismatch", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        if(Integer.parseInt(age) < 0 || Integer.parseInt(age) > 100) {
            JOptionPane.showMessageDialog(null, "Age should contain only single or 2 digits", "Mismatch", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for(Network network : enterprise.getNetworkDirectory().getNetworkList()) {
            for(Organization organization : network.getOrganizationDirectory().getOrganizationList()) {
                for(UserAccount userAccount : organization.getUserAccountDirectory().getUserAccountList()) {
                    if(usernameJTextField.getText().equals(userAccount.getUsername())) {
                        JOptionPane.showMessageDialog(null, "Username unavailable", "Duplicate Username", JOptionPane.WARNING_MESSAGE);
                        return;                    
                    }
                }
            }
        }
        for(UserAccountWorkRequest userAccountWorkRequest : enterprise.getAdminOrganization().getWorkQueue().getUserAccountWorkRequests()) {
            if(usernameJTextField.getText().equals(userAccountWorkRequest.getUsername())) {
                        JOptionPane.showMessageDialog(null, "Username unavailable", "Duplicate Username", JOptionPane.WARNING_MESSAGE);
                        return;                    
            }
        }
        String cardNo = cardNumberJTextField.getText();
        for(int i = 0; i <cardNo.length();i++){
            char c = cardNo.charAt(i);
            if(c<48 || c>57){
                JOptionPane.showMessageDialog(null, "Card number field should contain only number", "Mismatch", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        String cvv = cvvJTextField.getText();
        for(int i = 0; i <cvv.length();i++){
            char c = cvv.charAt(i);
            if(c<48 || c>57){
                JOptionPane.showMessageDialog(null, "Ccv number field should contain only number", "Mismatch", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        String expM = expirationMonthJTextField.getText();
        for(int i = 0; i <expM.length();i++){
            char c = expM.charAt(i);
            if(c<48 || c>57){
                JOptionPane.showMessageDialog(null, "Expiration month field should contain only number", "Mismatch", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        if(Integer.parseInt(expM) >= 12 || Integer.parseInt(expM) < 0){
             JOptionPane.showMessageDialog(null, "Expiration month field should contain between 1 to 12", "Mismatch", JOptionPane.WARNING_MESSAGE);
             return;
        }
            
        String expY = expirationYearJTextField.getText();
        for(int i = 0; i <expY.length();i++){
            char c = expY.charAt(i);
            if(c<48 || c>57){
                JOptionPane.showMessageDialog(null, "Expiration year field should contain only number", "Mismatch", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        if(expY.length() < 4 || expY.length() < 4) {
            JOptionPane.showMessageDialog(null, "Expiration year should contain only 4 digits", "Mismatch", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(Integer.parseInt(expY) < 2013) {
            JOptionPane.showMessageDialog(null, "Expiration year should be equal or greater than current year", "Mismatch", JOptionPane.WARNING_MESSAGE);
            return;
        }
        char[] passwordCharArray = passwordField.getPassword();
        String password = String.valueOf(passwordCharArray);
        char[] repasswordCharArray = rePasswordField.getPassword();
        String repassword = String.valueOf(repasswordCharArray);
        if(!password.equals(repassword)) {
            JOptionPane.showMessageDialog(null, "Passwords don't match", "Password Mismatch", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int selectedValue = JOptionPane.showConfirmDialog(null,"Are you sure you want to join our "+ categoryJComboBox.getSelectedItem() +"?","Warning!",JOptionPane.YES_NO_OPTION);
        if(selectedValue == JOptionPane.NO_OPTION){
            return;
        }
        UserAccountWorkRequest workRequest = (UserAccountWorkRequest) enterprise.getAdminOrganization().getWorkQueue().createAndAddWorkRequest(WorkRequest.Type.UserAccount);
        workRequest.setSender(nameJTextField.getText());
        workRequest.setAge(Integer.parseInt(ageJTextField.getText()));
        workRequest.setNetwork((Network) networkJComboBox.getSelectedItem());
        workRequest.setCategory((Organization) categoryJComboBox.getSelectedItem());
        workRequest.setGender((String) genderJComboBox.getSelectedItem());
        workRequest.setReceiver("System Admin");
        workRequest.setStatus("Pending");
        workRequest.setUsername(usernameJTextField.getText());
        workRequest.setPassword(password);
        workRequest.setCompanyName(companyNameJTextField.getText());
        workRequest.setCardNumber(cardNumberJTextField.getText());
        workRequest.setCvv(cvvJTextField.getText());
        workRequest.setExpirationMonth(Integer.parseInt(expirationMonthJTextField.getText()));
        workRequest.setExpirationYear(Integer.parseInt(expirationYearJTextField.getText()));
        db.storeSystem(enterprise);
        JOptionPane.showMessageDialog(null, "Please wait for admin to accept", "Creation Successful!", JOptionPane.WARNING_MESSAGE);
        container.removeAll();
    }
    
    private void hideFields() {
            supplierJLabel.setVisible(false);
            supplierJTextField.setVisible(false);
            advertiserJTextField.setVisible(false);
            advertiserJLabel1.setVisible(false);
            jLabel10.setVisible(false);
            companyNameJTextField.setVisible(false);
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
        createJButton = new javax.swing.JButton();
        backJButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nameJTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ageJTextField = new javax.swing.JTextField();
        genderJComboBox = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        networkJComboBox = new javax.swing.JComboBox();
        categoryJComboBox = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        supplierJLabel = new javax.swing.JLabel();
        supplierJTextField = new javax.swing.JTextField();
        advertiserJLabel1 = new javax.swing.JLabel();
        advertiserJTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        companyNameJTextField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        usernameJTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        rePasswordField = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cardNumberJTextField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        expirationMonthJTextField = new javax.swing.JTextField();
        expirationYearJTextField = new javax.swing.JTextField();
        cvvJTextField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Create An Account");

        createJButton.setText("Create");
        createJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createJButtonActionPerformed(evt);
            }
        });

        backJButton5.setText("Back");
        backJButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButton5ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.setName(""); // NOI18N

        jLabel2.setText("Name");

        jLabel3.setText("Gender");

        jLabel4.setText("Age");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel4)
                        .addGap(29, 29, 29)
                        .addComponent(ageJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(genderJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(genderJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ageJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel9.setText("Network");

        networkJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                networkJComboBoxActionPerformed(evt);
            }
        });

        categoryJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryJComboBoxActionPerformed(evt);
            }
        });

        jLabel5.setText("Category");

        supplierJLabel.setText("Supplier Name");

        supplierJTextField.setEditable(false);

        advertiserJLabel1.setText("Advertiser Name");

        advertiserJTextField.setEditable(false);

        jLabel10.setText("Company Name");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(supplierJLabel)
                    .addComponent(jLabel9)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(networkJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoryJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(supplierJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(companyNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(advertiserJLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(advertiserJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(networkJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supplierJLabel)
                    .addComponent(supplierJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(advertiserJLabel1)
                    .addComponent(advertiserJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(companyNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel6.setText("Username");

        jLabel7.setText("Password");

        jLabel8.setText("Re-enter Password");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(usernameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(rePasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(usernameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(rePasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel11.setText("Valid Thru");

        jLabel12.setText("cvv");

        jLabel13.setText("Card No.");

        jLabel14.setText("/");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(cvvJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(expirationMonthJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(expirationYearJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(85, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(cardNumberJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cardNumberJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cvvJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel14)
                    .addComponent(expirationMonthJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expirationYearJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("Basic Information");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setText("UserAccount Information");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setText("Official Information");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel19.setText("Financial Information");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(backJButton5)
                                .addGap(236, 236, 236)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(81, 81, 81)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(367, 367, 367)
                        .addComponent(createJButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel15)
                        .addGap(239, 239, 239)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel17)
                        .addGap(198, 198, 198)
                        .addComponent(jLabel19)))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(backJButton5)
                    .addComponent(jLabel1))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(createJButton)
                .addGap(83, 83, 83))
        );

        jPanel1.getAccessibleContext().setAccessibleName("Basic Info");
    }// </editor-fold>//GEN-END:initComponents

    private void createJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createJButtonActionPerformed
        // TODO add your handling code here:
        createAccount();
        
    }//GEN-LAST:event_createJButtonActionPerformed

    private void backJButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButton5ActionPerformed
        // TODO add your handling code here:
        container.remove(this);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
    }//GEN-LAST:event_backJButton5ActionPerformed

    private void networkJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_networkJComboBoxActionPerformed
        // TODO add your handling code here:
        populateCategoryComboBox();
    }//GEN-LAST:event_networkJComboBoxActionPerformed

    private void categoryJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryJComboBoxActionPerformed
        // TODO add your handling code here:
        try {
            Organization organization = (Organization) categoryJComboBox.getSelectedItem();
            if(organization.getName().equals(Organization.Type.Supplier.getValue())) {
                supplierJLabel.setVisible(true);
                supplierJTextField.setVisible(true);
                supplierJTextField.setText(nameJTextField.getText());
                advertiserJTextField.setVisible(false);
                advertiserJLabel1.setVisible(false);
                jLabel10.setVisible(true);
                companyNameJTextField.setVisible(true);
            }
            else if(organization.getName().equals(Organization.Type.Advertiser.getValue())) {
                supplierJLabel.setVisible(false);
                supplierJTextField.setVisible(false);
                advertiserJTextField.setText(nameJTextField.getText());
                advertiserJTextField.setVisible(true);
                advertiserJLabel1.setVisible(true);
                jLabel10.setVisible(true);
                companyNameJTextField.setVisible(true);
            }
            else {
                hideFields();
            }
        }
        catch(Exception e) {}
            
    }//GEN-LAST:event_categoryJComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel advertiserJLabel1;
    private javax.swing.JTextField advertiserJTextField;
    private javax.swing.JTextField ageJTextField;
    private javax.swing.JButton backJButton5;
    private javax.swing.JTextField cardNumberJTextField;
    private javax.swing.JComboBox categoryJComboBox;
    private javax.swing.JTextField companyNameJTextField;
    private javax.swing.JButton createJButton;
    private javax.swing.JTextField cvvJTextField;
    private javax.swing.JTextField expirationMonthJTextField;
    private javax.swing.JTextField expirationYearJTextField;
    private javax.swing.JComboBox genderJComboBox;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JComboBox networkJComboBox;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPasswordField rePasswordField;
    private javax.swing.JLabel supplierJLabel;
    private javax.swing.JTextField supplierJTextField;
    private javax.swing.JTextField usernameJTextField;
    // End of variables declaration//GEN-END:variables
}
