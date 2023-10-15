package com.model;

public class Customers {
	private String customerID;
	private String customerPwd;
	private String customerName;
	private int balance;
	private String gender;
	
	public Customers(String id,String pwd,String name,int balance,String gender) {
		this.customerID = id;
		this.customerPwd = pwd;
		this.customerName = name;
		this.balance = balance;
		this.gender = gender;
	}
	public String getCustomerID() {return this.customerID;}
	public String getCustomerPwd() {return this.customerPwd;}
	public int getCustomerBalance() {return this.balance;}
	public void setCustomerBalance(int balance) 
	{
		this.balance = balance;
	}
	public String getCustomerName() {return this.customerName;}
	public String toString() {
		String output = String.format("%s,%s,%s,%s,%s", this.customerID,this.customerPwd,this.customerName,this.balance,this.gender);
		return output;
	}
}
