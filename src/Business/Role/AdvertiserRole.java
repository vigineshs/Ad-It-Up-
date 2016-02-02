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
import Business.Supplier.SupplierDirectory;
import Business.UserAccount.UserAccount;
import Interface.Advertiser.AdvertiserWorkAreaJPanel;
import javax.swing.JPanel;

/**
 *
 * @author Hema
 */
public class AdvertiserRole extends Role {
    private Advertiser Advertiser;

    public Advertiser getAdvertiser() {
        return Advertiser;
    }

    public void setAdvertiser(Advertiser Advertiser) {
        this.Advertiser = Advertiser;
    }

    @Override
    public JPanel fetchWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, SupplierDirectory supplierDirectory, AdvertiserDirectory advertiserDirectory, Enterprise enterprise) {
        AdvertiserWorkAreaJPanel advertiserWorkAreaJPanel = new AdvertiserWorkAreaJPanel(userProcessContainer, account, enterprise, organization);
        return advertiserWorkAreaJPanel;
    }
    
}
