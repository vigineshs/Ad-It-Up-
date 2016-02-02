/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.WorkQueue;

import Business.Advertiser.Ads;
import Business.BidCategory.BidCategory;
import Business.BidCategory.ProfileCategory;

/**
 *
 * @author Hema
 */
public class BiddingWorkRequest extends WorkRequest{
    private float bidValue;
    private ProfileCategory profileCategory;
    private BidCategory bidCategory;
    private Ads ad;
    private int pageHit;

    public int getPageHit() {
        return pageHit;
    }

    public void setPageHit(int pageHit) {
        this.pageHit = pageHit;
    }
    
    public float getBidValue() {
        return bidValue;
    }

    public void setBidValue(float bidValue) {
        this.bidValue = bidValue;
    }

    public ProfileCategory getProfileCategory() {
        return profileCategory;
    }

    public void setProfileCategory(ProfileCategory profileCategory) {
        this.profileCategory = profileCategory;
    }

    public BidCategory getBidCategory() {
        return bidCategory;
    }

    public void setBidCategory(BidCategory bidCategory) {
        this.bidCategory = bidCategory;
    }

    public Ads getAd() {
        return ad;
    }

    public void setAd(Ads ad) {
        this.ad = ad;
    }

    @Override
    public String toString() {
        return bidCategory.getAdSpace(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
