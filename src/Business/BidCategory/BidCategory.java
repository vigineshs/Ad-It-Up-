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
public class BidCategory {
    private String adSpace, adSpaceDesccription;
    private double basePrice;

    public String getAdSpace() {
        return adSpace;
    }

    public void setAdSpace(String adSpace) {
        this.adSpace = adSpace;
    }

    public String getAdSpaceDesccription() {
        return adSpaceDesccription;
    }

    public void setAdSpaceDesccription(String adSpaceDesccription) {
        this.adSpaceDesccription = adSpaceDesccription;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return adSpace; //To change body of generated methods, choose Tools | Templates.
    }
    
}
