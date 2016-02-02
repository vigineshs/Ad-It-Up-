/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface.Advertiser;

import Business.Advertiser.AdDirectory;
import Business.Advertiser.Ads;
import Business.Advertiser.Advertiser;
import Business.Enterprise;
import Business.Network.Network;
import Business.Network.NetworkDirectory;
import Business.Organization.AdminOrganization;
import Business.Organization.AdvertiserOrganization;
import Business.Organization.Organization;
import Business.ProductCategory.ProductCategory;
import Business.Supplier.Product;
import Business.UserAccount.UserAccount;
import java.awt.CardLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Hema
 */
public class CreateAdJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CreateAdJPanel
     */
    JPanel container;
    AdDirectory adDirectory;
    UserAccount userAccount;
    NetworkDirectory networkDirectory;
    Enterprise enterprise;
    String link, picture;
    public CreateAdJPanel(JPanel container, UserAccount userAccount, AdDirectory adDirectory, Enterprise enterprise) {
        initComponents();
        this.container = container;
        this.adDirectory = adDirectory;
        this.userAccount = userAccount;
        this.networkDirectory = enterprise.getNetworkDirectory();
        this.enterprise = enterprise;
        populateProductCategoryComboBox();
    }
    
    private void populateProductCategoryComboBox() {
        productCategoryJComboBox.removeAllItems();
        AdminOrganization adminOrganization = (AdminOrganization) enterprise.getAdminOrganization();
        for(ProductCategory productCategory : adminOrganization.getProductCategoryDirectory().getProductList())
            productCategoryJComboBox.addItem(productCategory.getCategory());
    }
    
    private Image scaleImage(Image img, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = scaledImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }
    
    private String picture() {
            JFileChooser chooser = new JFileChooser(".");
            int status = chooser.showOpenDialog(CreateAdJPanel.this);
            if(status == JFileChooser.APPROVE_OPTION){
                File file = chooser.getSelectedFile();
                picture = file.getAbsolutePath();
            }
            
            Image image = new ImageIcon(picture).getImage();
            ImageIcon icon = new ImageIcon(scaleImage(image, imageJLabel.getWidth(), imageJLabel.getHeight()));
            imageJLabel.setIcon(icon);
            return picture;
    }
    
    private void createAd() {
        if(productNameJTextField.getText().equals("") || descriptionJTextField.getText().equals("") || productPriceJTextField.getText().equals("") || productIdJTextField.getText().equals("") || link == null) {
            JOptionPane.showMessageDialog(null, "Fill in all the fields", "Empty Text", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String id = productIdJTextField.getText();
        for(int i = 0; i <id.length();i++){
            char c = id.charAt(i);
            if(c<48 || c>57){
                JOptionPane.showMessageDialog(null, "Product ID field should contain only number", "Mismatch", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        for(Network network : networkDirectory.getNetworkList()) {
            for(Organization organization : network.getOrganizationDirectory().getOrganizationList()) {
                if(organization instanceof AdvertiserOrganization) {
                    AdvertiserOrganization advertiserOrganization = (AdvertiserOrganization) organization;
                    for(Advertiser advertiser : advertiserOrganization.getAdvertiserDirectory().getAdvertiserList()) {
                        for(Ads ads : advertiser.getAdDirectory().getAdList()) {
                            if(id.equals(ads.getProduct().getProductId())) {
                                JOptionPane.showMessageDialog(null, "Product ID field is already available", "Duplicate", JOptionPane.WARNING_MESSAGE);
                                return;
                            }
                        }
                    }
                }
            }
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
        if(productCategoryJComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Product category not available", "Unavailable", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Ads ad = adDirectory.createAndAddAds();
        ad.setAdString(adJTextField.getText());
        ad.setSeller(userAccount.getUsername());
        Product product = ad.getProduct();
        product.setProductName(productNameJTextField.getText());
        product.setProductDescription(descriptionJTextField.getText());
        product.setPrice(Float.parseFloat(productPriceJTextField.getText()));
        product.setProductId(Integer.parseInt(productIdJTextField.getText()));
        product.setProductLink(link);
        product.setProductCategory((String) productCategoryJComboBox.getSelectedItem());
        ad.setProduct(product);
        JOptionPane.showMessageDialog(null, "Product create successfully", "Success", JOptionPane.WARNING_MESSAGE);
        productNameJTextField.setText("");
        descriptionJTextField.setText("");
        productPriceJTextField.setText("");
        productIdJTextField.setText("");
                link ="";
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
        adJTextField = new javax.swing.JTextField();
        backJButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        productPriceJTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        createJButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        imageJLabel = new javax.swing.JLabel();
        addImageJButton = new javax.swing.JButton();
        descriptionJTextField = new javax.swing.JTextField();
        productIdJTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        productNameJTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        productCategoryJComboBox = new javax.swing.JComboBox();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Create Ad");

        jLabel2.setText("Ad");

        backJButton.setBackground(new java.awt.Color(204, 204, 204));
        backJButton.setText("<< Back");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Description");

        jLabel4.setText("Product Name");

        createJButton.setText("Create Ad");
        createJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createJButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Product ID");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        addImageJButton.setText("Add Image");
        addImageJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addImageJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(addImageJButton)
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(addImageJButton)
                .addContainerGap())
        );

        jLabel7.setText("Product Price");

        jLabel6.setText("Product Category");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backJButton)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(adJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(77, 77, 77)
                                        .addComponent(createJButton))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(productIdJTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                                .addComponent(productPriceJTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(productNameJTextField)
                                                .addComponent(descriptionJTextField, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addComponent(productCategoryJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(110, 110, 110)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(232, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backJButton)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(adJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(productNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(productIdJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(productPriceJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(descriptionJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(productCategoryJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(createJButton))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButtonActionPerformed
        // TODO add your handling code here:
        container.remove(this);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
    }//GEN-LAST:event_backJButtonActionPerformed

    private void createJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createJButtonActionPerformed
        // TODO add your handling code here:
        createAd();
    }//GEN-LAST:event_createJButtonActionPerformed

    private void addImageJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addImageJButtonActionPerformed
        // TODO add your handling code here:
        link = picture();
    }//GEN-LAST:event_addImageJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adJTextField;
    private javax.swing.JButton addImageJButton;
    private javax.swing.JButton backJButton;
    private javax.swing.JButton createJButton;
    private javax.swing.JTextField descriptionJTextField;
    private javax.swing.JLabel imageJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox productCategoryJComboBox;
    private javax.swing.JTextField productIdJTextField;
    private javax.swing.JTextField productNameJTextField;
    private javax.swing.JTextField productPriceJTextField;
    // End of variables declaration//GEN-END:variables
}
