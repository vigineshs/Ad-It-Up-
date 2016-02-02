/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.FinancialAccount;

import java.util.ArrayList;

/**
 *
 * @author Hema
 */
public class FinanceAccountDirectory {
    private ArrayList<FinanceAccount> financeAccountList;

    public FinanceAccountDirectory() {
        financeAccountList = new ArrayList<>();
    }

    public ArrayList<FinanceAccount> getFinanceAccountList() {
        return financeAccountList;
    }

    public void setFinanceAccountList(ArrayList<FinanceAccount> financeAccountList) {
        this.financeAccountList = financeAccountList;
    }
    
    public void createAndAddFinanceAccount(String cardNo, String cvv, int expMonth, int expYear, String username) {
        FinanceAccount financeAccount = new FinanceAccount();
        financeAccountList.add(financeAccount);
        financeAccount.setCardNo(cardNo);
        financeAccount.setCvv(cvv);
        financeAccount.setExpirationMonth(expMonth);
        financeAccount.setExpirationYear(expYear);
        financeAccount.setUsername(username);
        financeAccount.setBalance(1000);
    }
}
