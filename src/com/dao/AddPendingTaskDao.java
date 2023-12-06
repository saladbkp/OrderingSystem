/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.model.Orders;
import com.model_run.Tasks;
import com.tool.TextFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ray
 */
public class AddPendingTaskDao {
    ArrayList<Tasks> orderarray = new ArrayList<Tasks>();

    public AddPendingTaskDao() {
            TextFunction txtfunc = new TextFunction("src/data/pendingTasks.txt");
            orderarray = txtfunc.readfile(Tasks.class);
    }
    public ArrayList<Tasks> findDataByRun(String id) {
            List<Tasks> findarray = this.orderarray.stream()
                                               .filter(x -> x.getRunnerID().equals(id))
                                               .toList();            
            return !findarray.isEmpty()?new ArrayList<>(findarray):new ArrayList<Tasks>();
    }
    public ArrayList<Tasks> findDataByOrder(String id) {
            updateArray();
            List<Tasks> findarray = this.orderarray.stream()
                                               .filter(x -> x.getOrderID().equals(id))
                                               .toList();            
            return !findarray.isEmpty()?new ArrayList<>(findarray):new ArrayList<Tasks>();
    }
    public void removeOrders(Tasks order){
        orderarray.remove(order);
    }
    public List<Tasks> findData(){
        return orderarray;
    }
    public void updateArray(){
        TextFunction txtfunc = new TextFunction("src/data/pendingTasks.txt");
        orderarray = txtfunc.readfile(Tasks.class);
    }
}
