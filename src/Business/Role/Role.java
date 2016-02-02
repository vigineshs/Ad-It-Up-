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
import javax.swing.JPanel;

/**
 *
 * @author Hema
 */
public abstract class Role {
    public enum RoleType{
        Advertiser("Advertiser"),
        Supplier("Supplier"),
        User("User"),
        SystemAdminRole("System Admin");
        
        private String value;
        private RoleType(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    
    public abstract JPanel fetchWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, SupplierDirectory supplierDirectory, AdvertiserDirectory advertiserDirectory, Enterprise enterprise);

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
