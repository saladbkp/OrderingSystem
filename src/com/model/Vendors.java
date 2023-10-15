package com.model;

public class Vendors {
	private String vendorID;
	private String vendorPwd;
	private String vendorName;
	private String type;
	private String Location;
	private String Icon;
	
	public Vendors(String id,String pwd,String name,String type,String location,String icon) {
		this.vendorID = id;
		this.vendorPwd = pwd;
		this.vendorName = name;
		this.type = type;
		this.Location = location;
		this.Icon = icon;
	}
	public String getVendorID() {return this.vendorID;}
	public String getVendorPwd() {return this.vendorPwd;}
	public String getVendorName() {return this.vendorName;}
	public String getVendorIcon() {return this.Icon;}
	public String toString() {
		String output = String.format("%s,%s,%s,%s,%s,%s", this.vendorID,this.vendorPwd,this.vendorName,this.type,this.Location,this.Icon);
		return output;
	}
}
