/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import Business.FinancialAccount.FinanceAccountDirectory;
import Business.Person.PersonDirectory;
import Business.Role.Role;
import Business.UserAccount.UserAccountDirectory;
import Business.WorkQueue.WorkQueue;
import java.util.ArrayList;

/**
 *
 * @author Hema
 */
public abstract class Organization {
    
    private String name;
    private WorkQueue workQueue;
    private UserAccountDirectory userAccountDirectory;
    private PersonDirectory personDirectory;
    private FinanceAccountDirectory financialAccountDirectory;
    private int organizationID;
    private static int orgCounter;
    
    public enum Type{
        Admin("Admin Organization"), Advertiser("Advertiser Organization"), Supplier("Supplier Organization"), User("User Organization");
        private String value;
        private Type(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
    }

    //public Organization() {
      //  financialAccountDirectory = new FinanceAccountDirectory();
    //}
    
    public Organization(String name) {
        this.name = name;
        workQueue = new WorkQueue();
        userAccountDirectory = new UserAccountDirectory();
        personDirectory = new PersonDirectory();
        financialAccountDirectory = new FinanceAccountDirectory();
        organizationID = orgCounter;
        ++orgCounter;
    }

    public abstract ArrayList<Role> getSupportedRole();
    
    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    public PersonDirectory getPersonDirectory() {
        return personDirectory;
    }

    public void setPersonDirectory(PersonDirectory personDirectory) {
        this.personDirectory = personDirectory;
    }

    public FinanceAccountDirectory getFinancialAccountDirectory() {
        return financialAccountDirectory;
    }

    public void setFinancialAccountDirectory(FinanceAccountDirectory financialAccountDirectory) {
        this.financialAccountDirectory = financialAccountDirectory;
    }
    
    public static int getOrgCounter() {
        return orgCounter;
    }

    public static void setOrgCounter(int orgCounter) {
        Organization.orgCounter = orgCounter;
    }
    
    public int getOrganizationID() {
        return organizationID;
    }
    
    public String getName() {
        return name;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkQueue(WorkQueue workQueue) {
        this.workQueue = workQueue;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
