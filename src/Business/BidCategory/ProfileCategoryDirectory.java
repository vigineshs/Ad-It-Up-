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
public class ProfileCategoryDirectory {
    private ArrayList<ProfileCategory> profileCategoryList;

    public ProfileCategoryDirectory() {
        profileCategoryList = new ArrayList<>();
    }

    public ArrayList<ProfileCategory> getProfileCategoryList() {
        return profileCategoryList;
    }

    public void setProfileCategoryList(ArrayList<ProfileCategory> profileCategoryList) {
        this.profileCategoryList = profileCategoryList;
    }
    
    public ProfileCategory createAndAddProfileCategory(String categoryName, String categoryDescription, int userPotential, double basePrice) {
        ProfileCategory profileCategory = new ProfileCategory();
        profileCategoryList.add(profileCategory);
        profileCategory.setCategoryName(categoryName);
        profileCategory.setCategoryDescription(categoryDescription);
        profileCategory.setUserPotential(userPotential);
        profileCategory.setBasePrice(basePrice);
        return profileCategory;
    }
}
