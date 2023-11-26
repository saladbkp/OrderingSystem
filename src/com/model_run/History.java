/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model_run;

/**
 *
 * @author ivann
 */
public class History {
    private String OrderID;
    private String RunnerID;
    private double TotalCost;
    private String Status;
    
     public History (String orderid, String runnerid, String totalcost, String status){
         this.OrderID = orderid;
         this.RunnerID = runnerid;
         this.TotalCost = Double.parseDouble(totalcost);
         this.Status = status;
     }
     
      public String getOrderID(){
        return OrderID;
    }
      
      public String getRunnerID(){
          return RunnerID;
      }
      
      public double getTotalCost(){
          return TotalCost;
      }
      
      public String toString(){
        return String.format("%s,%s,%s,%s",this.OrderID,this.RunnerID,this.TotalCost, this.Status);
    }
}
