/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Order;

import java.util.ArrayList;

/**
 *
 * @author Hema
 */
public class MasterOrderCatalog {
    private ArrayList<Order> masterOrderList;

    public MasterOrderCatalog() {
        masterOrderList =  new ArrayList<>();
    }

    public ArrayList<Order> getMasterOrderList() {
        return masterOrderList;
    }

    public void setMasterOrderList(ArrayList<Order> masterOrderList) {
        this.masterOrderList = masterOrderList;
    }
    
    public Order addOrder(Order order) {
        masterOrderList.add(order);
        return order;
    }
}
