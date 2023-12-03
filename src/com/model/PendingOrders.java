/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author Asus
 */
public class PendingOrders extends TargetAttrs{
    public PendingOrders(String senderid,String targetid,String orderid ,String content){
        super(senderid,targetid,orderid,content);
    }

    @Override
    public String toString() {
        return this.getToString();
    }
    
    
}
