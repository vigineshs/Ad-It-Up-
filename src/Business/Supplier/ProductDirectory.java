/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Supplier;

import Business.UserAccount.UserAccount;
import java.util.ArrayList;

/**
 *
 * @author Hema
 */
public class ProductDirectory {
    private ArrayList<Product> productList;

    public ProductDirectory() {
        productList = new ArrayList<>();
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }
    
    public Product createAndAddProduct(String name, int id, Float price, String description, String supplier, String link, String productCategory) {
        Product product = new Product();
        productList.add(product);
        product.setProductName(name);
        product.setProductId(id);
        product.setPrice(price);
        product.setProductDescription(description);
        product.setSeller(supplier);
        product.setProductLink(link);
        product.setProductCategory(productCategory);
        return product;
    }
    
    public void deleteProduct(Product product) {
        productList.remove(product);
    }
}
