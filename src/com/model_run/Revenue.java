/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model_run;

/**
 *
 * @author ivann
 */
public class Revenue {
     private String Date;
    private double Earning;
    private double Deduction;
    private double Total;
    
     public Revenue (String date, String earning, String deduction, String total){
         this.Date = date;
         this.Earning = Double.parseDouble(earning);
         this.Deduction = Double.parseDouble(deduction);
         this.Total = Double.parseDouble(total);
     }
     
      public String getDate(){
        return Date;
    }
      
      public double getEarning(){
          return Earning;
      }
      
      public double getDeduction(){
          return Deduction;
      }
       public double getTotal(){
          return Total;
      }
      
      public String toString(){
        return String.format("%s,%s,%s,%s",this.Date,this.Earning,this.Deduction, this.Total);
    }
}

