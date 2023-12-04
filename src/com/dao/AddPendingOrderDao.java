/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.model.Orders;
import com.tool.TextFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ray
 */
public class AddPendingOrderDao {
    ArrayList<Orders> orderarray = new ArrayList<Orders>();

    public AddPendingOrderDao() {
            TextFunction txtfunc = new TextFunction("src/data/pendingOrders.txt");
            orderarray = txtfunc.readfile(Orders.class);
    }
    public ArrayList<Orders> findDataByVen(String id) {
            List<Orders> findarray = this.orderarray.stream()
                                               .filter(x -> x.getVendorId().equals(id))
                                               .toList();            
            return !findarray.isEmpty()?new ArrayList<>(findarray):new ArrayList<Orders>();
    }
    public Orders findDataByOrder(String id) {
            Orders findarray = this.orderarray.stream().filter(x->x.getOrderId().equals(id)).toList().get(0);
            return findarray;
    }
    public void removeOrders(Orders order){
        orderarray.remove(order);
    }
    public List<Orders> findData(){
        return orderarray;
    }
}
