package com.model;

public class Customers extends CommonAttrs{
	private int balance;
	private String gender;
        private int phoneno;
        private String address;
	
        public Customers(String id,String pwd,String name,int balance,String gender,int pn,String address) {
                super(id,name,pwd);
		this.balance = balance;
		this.gender = gender;
                this.phoneno = pn;
                this.address = address;
	}
        public Customers(String[] txtlist) {
                super(txtlist[0],txtlist[1],txtlist[2]);
		this.balance = Integer.parseInt(txtlist[3]);
		this.gender = txtlist[4];
                this.phoneno = Integer.parseInt(txtlist[5]);
                this.address = txtlist[6];
	}
        
	public int getCustomerBalance() {return this.balance;}
	public void setCustomerBalance(int balance) 
	{
		this.balance = balance;
	}
        public int getCustomerPhone(){return this.phoneno;}
        public String getCustomerAddress(){return this.address;}
        
	public String toString() {
		String output = String.format("%s,%s,%s,%s,%s", super.getToString(),this.balance,this.gender,this.phoneno,this.address);
		return output;
	}
}
