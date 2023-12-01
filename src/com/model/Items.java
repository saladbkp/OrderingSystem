/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author khwon
 */
public class Items{
    private String ItemId;
    private String ItemName;
    private Float Price;
    private String VendorId;
    public Items(String id,String name,String price,String vendorid){
        this.VendorId = vendorid;
        this.ItemId = id;
        this.ItemName = name;
        this.Price = Float.parseFloat(price);
    }
    public String getItemId(){return this.ItemId;}
    public String getItemName(){return this.ItemName;}
    public Float getPrice(){return this.Price;}
    public String getItemVendor(){return this.VendorId;}
    
    public String toString(){
        return String.format("%s,%s,%s,%s", this.ItemId,ItemName,Price,VendorId);
    }
}
