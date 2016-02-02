/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Role;

import Business.Advertiser.Advertiser;
import Business.Advertiser.AdvertiserDirectory;
import Business.Enterprise;
import Business.Organization.Organization;
import Business.Supplier.Supplier;
import Business.Supplier.SupplierDirectory;
import Business.UserAccount.UserAccount;
import Interface.SystemAdmin.SystemAdminWorkArea;
import javax.swing.JPanel;

/**
 *
 * @author Hema
 */
public class SystemAdminRole extends Role{
    @Override
    public JPanel fetchWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, SupplierDirectory supplierDirectory, AdvertiserDirectory advertiserDirectory, Enterprise enterprise) {
        SystemAdminWorkArea systemAdminWorkArea = new SystemAdminWorkArea(userProcessContainer, account, enterprise);
        return systemAdminWorkArea;
    }

}
