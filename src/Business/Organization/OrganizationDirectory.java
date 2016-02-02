/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import java.util.ArrayList;

/**
 *
 * @author Hema
 */
public class OrganizationDirectory {
    private ArrayList<Organization> organizationList;

    public OrganizationDirectory() {
        organizationList = new ArrayList<>();
    }

    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(ArrayList<Organization> organizationList) {
        this.organizationList = organizationList;
    }
    
    public Organization createOrganization(Organization.Type type) {
        if(type.equals(Organization.Type.Advertiser)) {
            //Dynamic binding
            Organization org = new AdvertiserOrganization();
            organizationList.add(org);
            return org;
        }
        if(type.equals(Organization.Type.Supplier)) {
            Organization org = new SupplierOrganization();
            organizationList.add(org);
            return org;
        }
        if(type.equals(Organization.Type.User)) {
            Organization org = new UserOrganization();
            organizationList.add(org);
            return org;
        }
        return null;
    }
}
