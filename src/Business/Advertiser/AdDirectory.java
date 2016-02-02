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
public class AdDirectory {
    private ArrayList<Ads> adList;

    public AdDirectory() {
        adList = new ArrayList<>();
    }

    public ArrayList<Ads> getAdList() {
        return adList;
    }

    public void setAdList(ArrayList<Ads> adList) {
        this.adList = adList;
    }
    
    public Ads createAndAddAds() {
        Ads ad = new Ads();
        adList.add(ad);
        return ad;
    }
    
    public void deleteAd(Ads ads) {
        adList.remove(ads);
    }
}
