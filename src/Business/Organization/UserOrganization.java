/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import Business.Order.MasterOrderCatalog;
import Business.Role.Role;
import Business.Role.UserRole;
import Business.WishList.WishListDirectory;
import java.util.ArrayList;

/**
 *
 * @author Hema
 */
public class UserOrganization extends Organization{
    private MasterOrderCatalog masterOrderCatalog;
    private WishListDirectory wishListDirectory;
    
    public UserOrganization() {
        super(Type.User.getValue());
        masterOrderCatalog = new MasterOrderCatalog();
        wishListDirectory = new WishListDirectory();
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new UserRole());
        return roles;
    }

    public MasterOrderCatalog getMasterOrderCatalog() {
        return masterOrderCatalog;
    }

    public void setMasterOrderCatalog(MasterOrderCatalog masterOrderCatalog) {
        this.masterOrderCatalog = masterOrderCatalog;
    }

    public WishListDirectory getWishListDirectory() {
        return wishListDirectory;
    }

    public void setWishListDirectory(WishListDirectory wishListDirectory) {
        this.wishListDirectory = wishListDirectory;
    }
    
}
