/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import Business.Advertiser.AdvertiserDirectory;
import Business.Role.AdvertiserRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Hema
 */
public class AdvertiserOrganization extends Organization{
    private AdvertiserDirectory advertiserDirectory;
    
    public AdvertiserOrganization() {
        super(Type.Advertiser.getValue());
        advertiserDirectory = new AdvertiserDirectory();
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new AdvertiserRole());
        return roles;
    }

    public AdvertiserDirectory getAdvertiserDirectory() {
        return advertiserDirectory;
    }

    public void setAdvertiserDirectory(AdvertiserDirectory advertiserDirectory) {
        this.advertiserDirectory = advertiserDirectory;
    }
    
}
