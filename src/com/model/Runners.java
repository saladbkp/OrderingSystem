package com.model;

public class Runners extends CommonAttrs{
	private String location;
	private String vehicle;
	
	public Runners(String id,String pwd,String name,String location,String vehicle) {
		super(id,pwd,name);
		this.location = location;
		this.vehicle = vehicle;
	}
	public String toString() {
		String output = String.format("%s,%s,%s", super.getToString(),this.location,this.vehicle);
		return output;
	}
}
