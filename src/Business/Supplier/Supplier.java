/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Supplier;

/**
 *
 * @author Hema
 */
public class Supplier {
    private String supplierName;
    private int supplierIdentifier;
    private ProductDirectory productDirectory;

    public Supplier() {
        productDirectory = new ProductDirectory();
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    
    public int getSupplierIdentifier() {
        return supplierIdentifier;
    }

    public void setSupplierIdentifier(int supplierIdentifier) {
        this.supplierIdentifier = supplierIdentifier;
    }

    public ProductDirectory getProductDirectory() {
        return productDirectory;
    }

    public void setProductDirectory(ProductDirectory productDirectory) {
        this.productDirectory = productDirectory;
    }

    @Override
    public String toString() {
        return supplierName; //To change body of generated methods, choose Tools | Templates.
    }
    
}
