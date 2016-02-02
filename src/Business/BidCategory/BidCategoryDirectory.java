/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.BidCategory;

import java.util.ArrayList;

/**
 *
 * @author Hema
 */
public class BidCategoryDirectory {
    private ArrayList<BidCategory> bidCategoryList;

    public BidCategoryDirectory() {
        bidCategoryList = new ArrayList<>();
    }

    public ArrayList<BidCategory> getBidCategoryList() {
        return bidCategoryList;
    }

    public void setBidCategoryList(ArrayList<BidCategory> bidCategoryList) {
        this.bidCategoryList = bidCategoryList;
    }
    
    public BidCategory createAndAddBidCategory(String adSpace, String adSpaceDesccription, double basePrice) {
        BidCategory bidCategory = new BidCategory();
        bidCategoryList.add(bidCategory);
        bidCategory.setAdSpace(adSpace);
        bidCategory.setAdSpaceDesccription(adSpaceDesccription);
        bidCategory.setBasePrice(basePrice);
        return bidCategory;
    }
}
