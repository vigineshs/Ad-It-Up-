/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import Business.BidCategory.BidCategoryDirectory;
import Business.BidCategory.ProfileCategoryDirectory;
import Business.ProductCategory.ProductCategoryDirectory;
import Business.Role.Role;
import Business.Role.SystemAdminRole;
import java.util.ArrayList;

/**
 *
 * @author Hema
 */
public class AdminOrganization extends Organization{
    private BidCategoryDirectory bidCategoryDirectory;
    private ProfileCategoryDirectory profileCategoryDirectory;
    private ProductCategoryDirectory productCategoryDirectory;
    public AdminOrganization(String name) {
        super(name);
        bidCategoryDirectory = new BidCategoryDirectory();
        profileCategoryDirectory = new ProfileCategoryDirectory();
        productCategoryDirectory = new ProductCategoryDirectory();
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new SystemAdminRole());
        return roles;
    }

    public BidCategoryDirectory getBidCategoryDirectory() {
        return bidCategoryDirectory;
    }

    public void setBidCategoryDirectory(BidCategoryDirectory bidCategoryDirectory) {
        this.bidCategoryDirectory = bidCategoryDirectory;
    }

    public ProfileCategoryDirectory getProfileCategoryDirectory() {
        return profileCategoryDirectory;
    }

    public void setProfileCategoryDirectory(ProfileCategoryDirectory profileCategoryDirectory) {
        this.profileCategoryDirectory = profileCategoryDirectory;
    }

    public ProductCategoryDirectory getProductCategoryDirectory() {
        return productCategoryDirectory;
    }

    public void setProductCategoryDirectory(ProductCategoryDirectory productCategoryDirectory) {
        this.productCategoryDirectory = productCategoryDirectory;
    }
    
}
