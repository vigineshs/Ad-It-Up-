/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Advertiser;

import java.util.ArrayList;

/**
 *
 * @author Hema
 */
public class AdvertiserDirectory {
    private ArrayList<Advertiser> advertiserList;

    public AdvertiserDirectory() {
        advertiserList = new ArrayList<>();
    }

    public ArrayList<Advertiser> getAdvertiserList() {
        return advertiserList;
    }

    public void setAdvertiserList(ArrayList<Advertiser> advertiserList) {
        this.advertiserList = advertiserList;
    }
    
    public Advertiser createAndAddAdvertiser(String name) {
        Advertiser advertiser = new Advertiser();
        advertiserList.add(advertiser);
        advertiser.setAdvertiserName(name);
        return advertiser;
    }
}
