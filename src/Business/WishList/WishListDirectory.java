/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.WishList;

import java.util.ArrayList;

/**
 *
 * @author Hema
 */
public class WishListDirectory {
    private ArrayList<WishList> wishLists;

    public WishListDirectory() {
        wishLists = new ArrayList<>();
    }

    public ArrayList<WishList> getWishLists() {
        return wishLists;
    }

    public void setWishLists(ArrayList<WishList> wishLists) {
        this.wishLists = wishLists;
    }
    
    public WishList createAndAddWishList() {
        WishList wishList = new WishList();
        wishLists.add(wishList);
        return wishList;
    }
}
