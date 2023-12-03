/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.model.PendingOrders;
import com.tool.TextFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class PendingOrdersDao {
    ArrayList<PendingOrders> pendingarray = new ArrayList<PendingOrders>();
    TextFunction txtfunc = new TextFunction("src/data/notifications.txt");
    AddCommonDao commonfunc = new AddCommonDao();
    public PendingOrdersDao() {
            pendingarray = txtfunc.readfile(PendingOrders.class);
    }
    public List<PendingOrders> findData() {
            return this.pendingarray;
    } 
}
