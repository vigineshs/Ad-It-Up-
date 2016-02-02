/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.BidCategory;

/**
 *
 * @author Hema
 */
public class ProfileCategory {
    private String categoryName, categoryDescription;
    private int userPotential;
    private double basePrice;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public int getUserPotential() {
        return userPotential;
    }

    public void setUserPotential(int userPotential) {
        this.userPotential = userPotential;
    }
    
    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return categoryName; //To change body of generated methods, choose Tools | Templates.
    }
    
}
