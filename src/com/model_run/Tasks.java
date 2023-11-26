/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model_run;

/**
 *
 * @author ivann
 */
public class Tasks {
    private String OrderID;
    private String VendorID;
    private String RunnerID;
    private String VendorName;
    private String FoodID;
    private String FoodName;
    private String Amount;
    private String FoodServiceType;
    private double TotalCost;
    private String Time;
    private String Status;
    
    public Tasks(String orderid, String vendorid, String runnerid, String vendorname, String foodid, String foodname, String amount, String foodservicetype, String totalcost, String time, String status){
        this.OrderID = orderid;
        this.VendorID = vendorid;
        this.RunnerID = runnerid;
        this.VendorName = vendorname;
        this.FoodID = foodid;
        this.FoodName = foodname;
        this.Amount = amount;
        this.FoodServiceType = foodservicetype;
        this.TotalCost = Double.parseDouble(totalcost);
        this.Time = time;
        this.Status = status;
        
    
}
    public String getOrderID(){
        return OrderID;
    }
    
    public String getVendorID(){
        return VendorID;
    }
    
    public String getRunnerID(){
        return RunnerID;
    }
    
    public String getVendorName(){
        return VendorName;
    }
    
    public String getFoodID(){
        return FoodID;
    }
    
    public String getFoodName(){
        return FoodName;
    }
    
    public String getAmount(){
        return Amount;
    }
    
    public String getFoodServiceType(){
        return FoodServiceType;
    }
    
    public double getTotalCost(){
        return TotalCost;
    }
    
    public String getTime(){
        return Time;
    }
    
    public String getStatus(){
        return Status;
    }
    public String toString(){
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",this.OrderID,this.VendorID,this.RunnerID,this.VendorName,this.FoodID, this.FoodName,this.Amount,this.FoodServiceType,this.TotalCost, this.Time, this.Status);
    }
}
