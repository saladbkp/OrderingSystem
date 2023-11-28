/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author ray
 */
public abstract class TargetAttrs {
    protected String senderid;
    protected String targetid;
    protected String orderid;
    protected String content;
    
    public TargetAttrs(String senderid,String targetid,String orderid ,String content){
        this.senderid = senderid;
        this.targetid = targetid;
        this.orderid = orderid;
        this.content = content;
    }
    public String getSenderId() {
        return senderid;
    }
    public String getTargetId() {
        return targetid;
    }
    public String getOrderId(){
        return orderid;
    }
    public String getContent() {
        return content;
    }
    
    @Override
    public abstract String toString();

    public String getToString(){
        return String.format("%s,%s,%s,%s", this.senderid,this.targetid,this.orderid,this.content);
    }
}
