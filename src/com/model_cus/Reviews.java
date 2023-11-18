/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

public class Reviews {
	private String vendorID;
	private String vendorName;
                 private String customerID;
	private String review;
	
	public Reviews(String vid,String vname,String cid, String review) {
		this.vendorID = vid;
		this.vendorName = vname;
                		this.customerID = cid;
		this.review = review;
		
	}
        	public String getVendorID() {return this.vendorID;}
                	public String getVendorName() {return this.vendorName;}
	public String getCustomerID() {return this.customerID;}
        	public String getReview() {return this.review;}
        

	public String toString() {
		String output = String.format("%s,%s,%s,%s,%s", this.vendorID,this.vendorName,this.customerID,this.review);
		return output;
	}
}