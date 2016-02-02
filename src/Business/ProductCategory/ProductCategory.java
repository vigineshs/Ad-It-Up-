/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.ProductCategory;

/**
 *
 * @author Hema
 */
public class ProductCategory {
    private String category;
    private int productCategoryID;
    private static int pcCounter;

    public ProductCategory() {
        ++pcCounter;
        productCategoryID = pcCounter;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getProductCategoryID() {
        return productCategoryID;
    }

    public void setProductCategoryID(int productCategoryID) {
        this.productCategoryID = productCategoryID;
    }

    public static int getPcCounter() {
        return pcCounter;
    }

    public static void setPcCounter(int pcCounter) {
        ProductCategory.pcCounter = pcCounter;
    }

    @Override
    public String toString() {
        return category; //To change body of generated methods, choose Tools | Templates.
    }
    
}
