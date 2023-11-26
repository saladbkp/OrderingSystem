/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model_run;

/**
 *
 * @author ivann
 */
public class RunnerReview {
    private String OrderID;
    private String Date;
    private String Review;
    private String Method;
    
    
    public RunnerReview (String orderid, String date, String review, String method){
        this.OrderID = orderid;
        this.Date = date;
        this.Review = review;
        this.Method = method;
        
    }
    public String getOrderID(){
    
        return OrderID;
    }
    
    public String getDate(){
        return Date;
        
    }
    
    public String getReview(){
        return Review;
    }
    
    public String getMethod(){
        return Method;
    }
    public String toString(){
        return String.format("%s,%s,%s,%s",this.OrderID,this.Date,this.Review, this.Method);
    }     
}
