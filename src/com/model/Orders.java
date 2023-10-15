package com.model;

public class Orders {
	private String CustomerID;
	private String ItemID;
	private String VendorID;
	private String RunnerID;
	private int Total;
	private float Amount;
	private String DateTime;
	
	public Orders(String cusid,String itemid,String venid,String runid,int total,float amount,String dt) {
		this.CustomerID = cusid;
		this.ItemID = itemid;
		this.VendorID = venid;
		this.RunnerID = runid;
		this.Total = total;
		this.Amount = amount;
		this.DateTime = dt;
	}
	public String getCustomerId() {return this.CustomerID;}
	public String getVendorId() {return this.VendorID;}
	public String getItemId() {return this.ItemID;}
	public String getRunnerId() {return this.RunnerID;}
	public String getDatetime() {return this.DateTime;}
	public int getTotal() {return this.Total;}
	public float getAmount() {return this.Amount;}
	
	public String toString() {
		String output = String.format("%s,%s,%s,%s,%d,%.2f,%s", this.CustomerID,this.ItemID,this.VendorID,this.RunnerID,this.Total,this.Amount,this.DateTime);
		return output;
	}
	
}
