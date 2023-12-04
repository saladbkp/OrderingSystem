/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model_run;

import com.model.Orders;
import com.tool.Tools;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ivann
 */
public class Tasks {
    private String OrderID;
    private String VendorID;
    private String RunnerID;
    private String CustomerID;
    private double TotalCost;
    private Date Time;
    private String Status;

    public Tasks(String OrderID, String VendorID, String RunnerID, String CustomerID, String TotalCost, String Time, String Status) {
        this.OrderID = OrderID;
        this.VendorID = VendorID;
        this.RunnerID = RunnerID;
        this.CustomerID = CustomerID;
        this.TotalCost = Double.parseDouble(TotalCost);
        try {
            this.Time = Tools.formatter.parse(Time);
        } catch (ParseException ex) {
            Logger.getLogger(Tasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.Status = Status;
    }
    
    public Tasks(Orders order){
        this.OrderID = order.getOrderId();
        this.VendorID = order.getVendorId();
        this.RunnerID = order.getRunnerId();
        this.CustomerID = order.getCustomerId();
        this.TotalCost = 5.00;
        this.Time = order.getDatetime();
        this.Status = "Preparing Food";
        
    
}

    public String getOrderID() {
        return OrderID;
    }

    public String getVendorID() {
        return VendorID;
    }

    public String getRunnerID() {
        return RunnerID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public double getTotalCost() {
        return TotalCost;
    }

    public Date getTime() {
        return Time;
    }
    
    public String getStatus(){
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    
    public String toString(){
        return String.format("%s,%s,%s,%s,%s,%s,%s",
                this.OrderID,this.VendorID,this.RunnerID,this.CustomerID,this.TotalCost, 
                Tools.formatter.format( this.Time), this.Status);
    }
}
