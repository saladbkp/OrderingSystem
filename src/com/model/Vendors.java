package com.model;

public class Vendors {
	private String vendorID;
	private String vendorName;
	private String type;
	private String Location;
	
	public Vendors(String id,String name,String type,String location) {
		this.vendorID = id;
		this.vendorName = name;
		this.type = type;
		this.Location = location;
	}
	public String getVendorID() {return this.vendorID;}
	public String getVendorName() {return this.vendorName;}
}
