/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Network;

import Business.Organization.OrganizationDirectory;

/**
 *
 * @author Hema
 */
public class Network {
    private String networkName;
    private OrganizationDirectory organizationDirectory;
    private int networkID;
    private static int netCounter = 0;

    public Network() {
        netCounter++;
        networkID = netCounter;
        organizationDirectory = new OrganizationDirectory();
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public int getNetworkID() {
        return networkID;
    }

    public void setNetworkID(int networkID) {
        this.networkID = networkID;
    }

    public static int getNetCounter() {
        return netCounter;
    }

    public static void setNetCounter(int netCounter) {
        Network.netCounter = netCounter;
    }
    
    public OrganizationDirectory getOrganizationDirectory() {
        return organizationDirectory;
    }

    public void setOrganizationDirectory(OrganizationDirectory organizationDirectory) {
        this.organizationDirectory = organizationDirectory;
    }

    @Override
    public String toString() {
        return networkName; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
