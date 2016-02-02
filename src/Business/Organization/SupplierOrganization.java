/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import Business.Role.Role;
import Business.Role.SupplierRole;
import Business.Supplier.SupplierDirectory;
import java.util.ArrayList;

/**
 *
 * @author Hema
 */
public class SupplierOrganization extends Organization{
    private SupplierDirectory supplierDirectory;
    
    public SupplierOrganization() {
        super(Type.Supplier.getValue());
        supplierDirectory = new SupplierDirectory();
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new SupplierRole());
        return roles;
    }

    public SupplierDirectory getSupplierDirectory() {
        return supplierDirectory;
    }

    public void setSupplierDirectory(SupplierDirectory supplierDirectory) {
        this.supplierDirectory = supplierDirectory;
    }
    
}
