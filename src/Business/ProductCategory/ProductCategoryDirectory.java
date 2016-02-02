/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.ProductCategory;

import java.util.ArrayList;

/**
 *
 * @author Hema
 */
public class ProductCategoryDirectory {
    private ArrayList<ProductCategory> productList;

    public ProductCategoryDirectory() {
        productList = new ArrayList<>();
    }

    public ArrayList<ProductCategory> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<ProductCategory> productList) {
        this.productList = productList;
    }
    
    public ProductCategory createAndAddProductCategory(String name) {
        ProductCategory productCategory = new ProductCategory();
        productList.add(productCategory);
        productCategory.setCategory(name);
        return productCategory;
    }
}
