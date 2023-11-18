/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

public class Credits {
	private String customerID;
	private String customerName;
	private double balance;
	private String transaction;
        //private double transaction
	
	public Credits(String id, String name,String balance,String transaction) {
		this.customerID = id;
		this.customerName = name;
		this.balance = Double.parseDouble(balance);
		this.transaction = transaction;
	}
	public String getCustomerID() {return this.customerID;}
	public String getCustomerName() {return this.customerName;}
	public double getBalance() {return this.balance;}
	public String getTransaction() {return this.transaction;}
	public String toString() {
		String output = String.format("%s,%s,%s,%s,%s", this.customerID,this.customerName,this.balance,this.transaction);
		return output;
	}
}
