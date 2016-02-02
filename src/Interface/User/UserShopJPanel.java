/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface.User;

import Business.Advertiser.Ads;
import Business.Enterprise;
import Business.FinancialAccount.FinanceAccountDirectory;
import Business.Order.Order;
import Business.Order.OrderItem;
import Business.Organization.AdminOrganization;
import Business.Organization.UserOrganization;
import Business.Supplier.Product;
import Business.Supplier.Supplier;
import Business.Supplier.SupplierDirectory;
import Business.UserAccount.UserAccount;
import Business.WishList.WishList;
import Business.WorkQueue.BiddingWorkRequest;
import java.awt.CardLayout;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hema
 */
public class UserShopJPanel extends javax.swing.JPanel {

    /**
     * Creates new form shopJPanel
     */
    JPanel container;
    FinanceAccountDirectory financeAccountDirectory;
    SupplierDirectory supplierDirectory;
    UserOrganization userOrganization;
    UserAccount userAccount;
    Order order;
    AdminOrganization adminOrganization;
    Enterprise enterprise;
    float currentBid;
    BiddingWorkRequest biddingWorkRequest1, biddingWorkRequest2;
    public UserShopJPanel(JPanel container, UserAccount userAccount, SupplierDirectory supplierDirectory, UserOrganization userOrganization, FinanceAccountDirectory financeAccountDirectory, AdminOrganization adminOrganization, Enterprise enterprise) {
        initComponents();
        this.container = container;
        this.supplierDirectory = supplierDirectory;
        this.userOrganization = userOrganization;
        this.userAccount = userAccount;
        this.financeAccountDirectory = financeAccountDirectory;
        this.adminOrganization = adminOrganization;
        this.enterprise = enterprise;
        this.order = new Order();
        populateSupplierComboBox();
        populateAds();
    }

    private void populateAds() {
        for(BiddingWorkRequest biddingWorkRequest : adminOrganization.getWorkQueue().getBiddingWorkRequests()) {
            if(biddingWorkRequest.getBidCategory().getAdSpace().equals("Shopping Page Top")) {
                if(adLabelTop.getText().equals("")) {
                    biddingWorkRequest1 = biddingWorkRequest;
                    Ads ad = biddingWorkRequest.getAd();
                    adLabelTop.setText("Clickable Ad: " +String.valueOf(ad));
                    currentBid = biddingWorkRequest.getBidValue();
                }
                else if(biddingWorkRequest.getBidValue() > currentBid) {
                    adLabelTop.setText(biddingWorkRequest.getAd().getAdString());
                    biddingWorkRequest1 = biddingWorkRequest;
                }
            }
            if(biddingWorkRequest.getBidCategory().getAdSpace().equals("Shopping Page Side")) {
                if(adLabelSide.getText().equals("")) {
                    biddingWorkRequest2 = biddingWorkRequest;
                    Ads ad = biddingWorkRequest.getAd();
                    adLabelSide.setText("Clickable Ad: " +String.valueOf(ad));
                    currentBid = biddingWorkRequest.getBidValue();
                }
                else if(biddingWorkRequest.getBidValue() > currentBid) {
                    adLabelSide.setText(biddingWorkRequest.getAd().getAdString());
                    biddingWorkRequest2 = biddingWorkRequest;
                }
            }
        }
        
    }
    
    private void populateSupplierComboBox() {
        supplierJComboBox.removeAll();
        for(Supplier supplier : supplierDirectory.getSupplierList())
            supplierJComboBox.addItem(supplier);
        populateTable();
    }
    
    private void populateTable() {
        try{
            Supplier supplier = (Supplier) supplierJComboBox.getSelectedItem();
            DefaultTableModel model = (DefaultTableModel) productsJTable.getModel();
            model.setRowCount(0);
            for(Product product : supplier.getProductDirectory().getProductList()) {
                Object[] row = new Object[2];
                row[0] = product;
                row[1] = product.getPrice();
                model.addRow(row);
            }
        }
        catch(Exception e) {}
    }
    
    private void showDetails() {
        int selectedRow = productsJTable.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row", "Invalid Option", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Product product = (Product) productsJTable.getValueAt(selectedRow, 0);
        supplierJTextField.setText(product.getSeller());
        productNameJTextField.setText(product.getProductName());
        productIdJTextField.setText(String.valueOf(product.getProductId()));
        productPriceJTextField.setText(String.valueOf(product.getPrice()));
        descriptionJLabel.setText(product.getProductDescription());
        Image image = new ImageIcon(product.getProductLink()).getImage();
        ImageIcon icon = new ImageIcon(product.scaleImage(image, imageJLabel.getWidth(), imageJLabel.getHeight()));
        imageJLabel.setIcon(icon);
    }
    
    private void addToCart() {
        int selectedRow = productsJTable.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row", "Invalid Option", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(((int) quantityJSpinner.getValue()) <= 0 ) {
            JOptionPane.showMessageDialog(null, "Quantity Cannot be negative", "Invalid Option", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Product product = (Product) productsJTable.getValueAt(selectedRow, 0);
        
        OrderItem orderItem = order.createOrderitem();
        orderItem.setProduct(product);
        orderItem.setQuantity((int) quantityJSpinner.getValue());
        orderItem.setAmount(((int)quantityJSpinner.getValue()) * product.getPrice());
        orderItem.setBuyer(userAccount.getUsername());
        orderItem.setStatus("Pending");
        Supplier supplier =  (Supplier) supplierJComboBox.getSelectedItem();
        orderItem.setSeller(supplier.getSupplierName());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        String created = sdf.format(date);
        orderItem.setTimeStamp(created);
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adLabelTop = new javax.swing.JLabel();
        adLabelSide = new javax.swing.JLabel();
        supplierJComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        productsJTable = new javax.swing.JTable();
        addToCartJButton = new javax.swing.JButton();
        viewDetailsJButton = new javax.swing.JButton();
        viewCartJButton = new javax.swing.JButton();
        backJButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        productPriceJTextField = new javax.swing.JTextField();
        productNameJTextField = new javax.swing.JTextField();
        productIdJTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        supplierJTextField = new javax.swing.JTextField();
        quantityJSpinner = new javax.swing.JSpinner();
        imageJLabel = new javax.swing.JLabel();
        descriptionJLabel = new javax.swing.JLabel();
        wishListJButton = new javax.swing.JButton();

        adLabelTop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adLabelTopMouseClicked(evt);
            }
        });

        adLabelSide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adLabelSideMouseClicked(evt);
            }
        });

        supplierJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierJComboBoxActionPerformed(evt);
            }
        });

        productsJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(productsJTable);
        if (productsJTable.getColumnModel().getColumnCount() > 0) {
            productsJTable.getColumnModel().getColumn(0).setResizable(false);
            productsJTable.getColumnModel().getColumn(1).setResizable(false);
        }

        addToCartJButton.setText("Add to Cart");
        addToCartJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartJButtonActionPerformed(evt);
            }
        });

        viewDetailsJButton.setText("View Details");
        viewDetailsJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDetailsJButtonActionPerformed(evt);
            }
        });

        viewCartJButton.setText("View Cart");
        viewCartJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCartJButtonActionPerformed(evt);
            }
        });

        backJButton5.setText("Back");
        backJButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButton5ActionPerformed(evt);
            }
        });

        jLabel3.setText("Product ID");

        jLabel4.setText("Product Price");

        jLabel2.setText("Product Name");

        productPriceJTextField.setEditable(false);

        productNameJTextField.setEditable(false);

        productIdJTextField.setEditable(false);

        jLabel1.setText("Supplier");

        supplierJTextField.setEditable(false);

        descriptionJLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        wishListJButton.setText("Add to Wishlist");
        wishListJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wishListJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(adLabelSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(wishListJButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(quantityJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(viewDetailsJButton)
                            .addGap(18, 18, 18)
                            .addComponent(addToCartJButton))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(supplierJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(192, 192, 192)
                                .addComponent(viewCartJButton))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(backJButton5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(adLabelTop, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(productNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(productIdJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(supplierJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(productPriceJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(imageJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(descriptionJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(adLabelTop, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(backJButton5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(descriptionJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(supplierJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(viewCartJButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(productNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(productIdJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(supplierJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(productPriceJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addToCartJButton)
                            .addComponent(viewDetailsJButton)
                            .addComponent(quantityJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wishListJButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addComponent(adLabelSide, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backJButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButton5ActionPerformed
        // TODO add your handling code here:
        container.remove(this);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
    }//GEN-LAST:event_backJButton5ActionPerformed

    private void supplierJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierJComboBoxActionPerformed
        // TODO add your handling code here:
        populateTable();
    }//GEN-LAST:event_supplierJComboBoxActionPerformed

    private void viewDetailsJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewDetailsJButtonActionPerformed
        // TODO add your handling code here:
        showDetails();
    }//GEN-LAST:event_viewDetailsJButtonActionPerformed

    private void addToCartJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartJButtonActionPerformed
        // TODO add your handling code here:
        addToCart();
    }//GEN-LAST:event_addToCartJButtonActionPerformed

    private void viewCartJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewCartJButtonActionPerformed
        // TODO add your handling code here:
        
        ViewCartJPanel viewCartJPanel = new ViewCartJPanel(container, order, userAccount, userOrganization.getMasterOrderCatalog(), financeAccountDirectory, enterprise);
        CardLayout layout = (CardLayout) container.getLayout();
        container.add("View Cart", viewCartJPanel);
        layout.next(container);
    }//GEN-LAST:event_viewCartJButtonActionPerformed

    private void wishListJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wishListJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = productsJTable.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row", "Invalid Option", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Product product = (Product) productsJTable.getValueAt(selectedRow, 0);
        for(WishList wishList : userOrganization.getWishListDirectory().getWishLists()) {
            if(userAccount.getUsername().equals(wishList.getUsername())) {
                if(product.getProductCategory().equals(wishList.getProductCategory())) {
                    if(product.getProductName().equals(wishList.getProduct())) {
                        if(wishList.getStatus().equals("Pending")) {
                            JOptionPane.showMessageDialog(null, "You already have this pending in your wishlist", "Duplicate Wish", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }
                }
            }
        }
        WishList wishList1 = userOrganization.getWishListDirectory().createAndAddWishList();
        wishList1.setProduct(product.getProductName());
        wishList1.setProductCategory(product.getProductCategory());
        System.out.println(product.getProductCategory());
        wishList1.setStatus("Pending");
        wishList1.setUsername(userAccount.getUsername());
    }//GEN-LAST:event_wishListJButtonActionPerformed

    private void adLabelTopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adLabelTopMouseClicked
        // TODO add your handling code here:
        biddingWorkRequest1.setPageHit(biddingWorkRequest1.getPageHit() + 1);
        AdProductsJPanel adProductsJPanel = new AdProductsJPanel(container, userAccount, biddingWorkRequest1, userOrganization.getMasterOrderCatalog(), financeAccountDirectory, enterprise);
        CardLayout layout = (CardLayout) container.getLayout();
        container.add("Ad Products2", adProductsJPanel);
        layout.next(container);
    }//GEN-LAST:event_adLabelTopMouseClicked

    private void adLabelSideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adLabelSideMouseClicked
        // TODO add your handling code here:
        biddingWorkRequest2.setPageHit(biddingWorkRequest2.getPageHit() + 1);
        AdProductsJPanel adProductsJPanel = new AdProductsJPanel(container, userAccount, biddingWorkRequest2, userOrganization.getMasterOrderCatalog(), financeAccountDirectory, enterprise);
        CardLayout layout = (CardLayout) container.getLayout();
        container.add("Ad Products3", adProductsJPanel);
        layout.next(container);
    }//GEN-LAST:event_adLabelSideMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adLabelSide;
    private javax.swing.JLabel adLabelTop;
    private javax.swing.JButton addToCartJButton;
    private javax.swing.JButton backJButton5;
    private javax.swing.JLabel descriptionJLabel;
    private javax.swing.JLabel imageJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField productIdJTextField;
    private javax.swing.JTextField productNameJTextField;
    private javax.swing.JTextField productPriceJTextField;
    private javax.swing.JTable productsJTable;
    private javax.swing.JSpinner quantityJSpinner;
    private javax.swing.JComboBox supplierJComboBox;
    private javax.swing.JTextField supplierJTextField;
    private javax.swing.JButton viewCartJButton;
    private javax.swing.JButton viewDetailsJButton;
    private javax.swing.JButton wishListJButton;
    // End of variables declaration//GEN-END:variables
}
