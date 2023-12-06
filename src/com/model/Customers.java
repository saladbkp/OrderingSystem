package com.model;

import com.tool.Tools;

public class Customers extends CommonAttrs{
	private double balance;
	private String gender;
        private int phoneno;
        private String address;
	
        public Customers(String id,String pwd,String name,String balance,String gender,String pn,String address) {
                super(id,name,pwd);
		this.balance = Double.parseDouble(balance);
		this.gender = gender;
                this.phoneno = Integer.parseInt(pn);
                this.address = address;
	}
        public Customers(String[] txtlist) {
                super(txtlist[0],txtlist[1],txtlist[2]);
		this.balance = Integer.parseInt(txtlist[3]);
		this.gender = txtlist[4];
                this.phoneno = Integer.parseInt(txtlist[5]);
                this.address = txtlist[6];
	}
        
	public double getCustomerBalance() {return this.balance;}
	public void setCustomerBalance(double balance) 
	{
		this.balance = balance;
	}
        public int getCustomerPhone(){return this.phoneno;}
        public String getCustomerAddress(){return this.address;}
        
        @Override
	public String toString() {
		String output = String.format("%s,%s,%s,%s,%s", super.getToString(),Tools.decimformatter.format(this.balance),this.gender,this.phoneno,this.address);
		return output;
	}
}
