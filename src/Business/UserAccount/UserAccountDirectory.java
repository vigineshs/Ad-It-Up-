/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.UserAccount;

import Business.Person.Person;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Hema
 */
public class UserAccountDirectory {
    private ArrayList<UserAccount> userAccountList;

    public UserAccountDirectory() {
        userAccountList = new ArrayList<>();
    }

    public ArrayList<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(ArrayList<UserAccount> userAccountList) {
        this.userAccountList = userAccountList;
    }
    
    public UserAccount createAndAddUserAccount(String username, String password, Person person, Role role) {
        UserAccount userAccount = new UserAccount();
        userAccountList.add(userAccount);
        userAccount.setUsername(username);
        userAccount.setPassword(password);
        userAccount.setRole(role);
        userAccount.setPerson(person);
        return userAccount;
    }
    
    public UserAccount authenticateUser(String username, String password) {
        for(UserAccount userAccount : userAccountList) {
            if(username.equals(userAccount.getUsername()) && password.equals(userAccount.getPassword())) {
                    return userAccount;
            }
        }
        return null;
    }
}
