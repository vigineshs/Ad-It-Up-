/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Business.Person.Person;
import Business.Role.Role;
import Business.Role.SystemAdminRole;

/**
 *
 * @author Hema
 */
public class ConfigureAnEnterprise {
    public static Enterprise configureBusiness() {
        Enterprise enterprise = new Enterprise();
        Person p = enterprise.getAdminOrganization().getPersonDirectory().createAndAddPerson("AdItUp Admin", "Male", "AdItUp", 21);
        Role role = new SystemAdminRole();
        enterprise.getAdminOrganization().getUserAccountDirectory().createAndAddUserAccount("systemadmin", "systemadmin", p, role);
        enterprise.getAdminOrganization().getFinancialAccountDirectory().createAndAddFinanceAccount("98765432", "158", 07, 2017, "systemadmin");
        return enterprise;
    }
}
