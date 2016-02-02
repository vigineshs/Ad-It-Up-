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
public class Order {
    private ArrayList<OrderItem> orders;
    public static int count =0;
    private int orderId;
    
    public Order() {
        count++;
        orderId = count;
        orders = new ArrayList<>();
    }
    
    

    public ArrayList<OrderItem> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<OrderItem> orders) {
        this.orders = orders;
    }
    
    public OrderItem createOrderitem() {
        OrderItem orderItem = new OrderItem();
        orders.add(orderItem);
        return orderItem;
    }

    @Override
    public String toString() {
        return String.valueOf(orderId);
    }
}
