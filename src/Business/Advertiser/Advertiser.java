/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Advertiser;

import Business.Supplier.ProductDirectory;

/**
 *
 * @author Hema
 */
public class Advertiser {
    private String advertiserName;
    private AdDirectory adDirectory;

    public Advertiser() {
        adDirectory = new AdDirectory();
    }

    public String getAdvertiserName() {
        return advertiserName;
    }

    public void setAdvertiserName(String advertiserName) {
        this.advertiserName = advertiserName;
    }

    public AdDirectory getAdDirectory() {
        return adDirectory;
    }

    public void setAdDirectory(AdDirectory adDirectory) {
        this.adDirectory = adDirectory;
    }

    @Override
    public String toString() {
        return advertiserName; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
