package com.model;

public class Vendors {
	private String vendorID;
	private String vendorPwd;
	private String vendorName;
	private String type;
	private String Location;
	
	public Vendors(String id,String pwd,String name,String type,String location) {
		this.vendorID = id;
		this.vendorPwd = pwd;
		this.vendorName = name;
		this.type = type;
		this.Location = location;
	}
	public String getVendorID() {return this.vendorID;}
	public String getVendorPwd() {return this.vendorPwd;}
	public String getVendorName() {return this.vendorName;}
}
