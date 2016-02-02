/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Business.Network.NetworkDirectory;
import Business.Organization.AdminOrganization;
import Business.Organization.Organization;

/**
 *
 * @author Hema
 */
public class Enterprise{
    
    private NetworkDirectory networkDirectory;
    private Organization adminOrganization;
    
    public Enterprise() {
        adminOrganization = new AdminOrganization(Organization.Type.Admin.getValue());
        networkDirectory = new NetworkDirectory();
    }

    public NetworkDirectory getNetworkDirectory() {
        return networkDirectory;
    }

    public void setNetworkDirectory(NetworkDirectory networkDirectory) {
        this.networkDirectory = networkDirectory;
    }

    public Organization getAdminOrganization() {
        return adminOrganization;
    }

    public void setAdminOrganization(Organization adminOrganization) {
        this.adminOrganization = adminOrganization;
    }
    
}
