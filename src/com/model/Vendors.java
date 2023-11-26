package com.model;

public class Vendors extends CommonAttrs{
	private String type;
	private String Location;
	private String Icon;
	
	public Vendors(String id,String pwd,String name,String type,String location,String icon) {
		super(id,pwd,name);
		this.type = type;
		this.Location = location;
		this.Icon = icon;
	}
        public Vendors(String[] txtlist) {
		super(txtlist[0],txtlist[1],txtlist[2]);
		this.type = txtlist[3];
		this.Location = txtlist[4];
		this.Icon = txtlist[5];
	}
        public String getType(){return this.type;}
	public String getVendorIcon() {return this.Icon;}
        @Override
	public String toString() {
		String output = String.format("%s,%s,%s,%s", super.getToString(),this.type,this.Location,this.Icon);
		return output;
	}
}
