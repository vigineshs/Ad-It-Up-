/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Advertiser;

import Business.Supplier.Product;

/**
 *
 * @author Hema
 */
public class Ads {
    private String adString;
    private Product product;
    private String seller;

    public Ads() {
        product = new Product();
    }
    
    public String getAdString() {
        return adString;
    }

    public void setAdString(String adString) {
        this.adString = adString;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    @Override
    public String toString() {
        return adString; //To change body of generated methods, choose Tools | Templates.
    }

}
