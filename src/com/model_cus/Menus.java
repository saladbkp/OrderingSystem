//CONFIGURED

package com.model_cus;

//need extends Vendor?

import com.model.Vendors;


public class Menus{
	private Vendors vendor;
	private int deliveryFee;
	
	public Menus(Vendors vendor) {
                
		this.vendor = vendor;
		this.deliveryFee = 5;
	}
	public String getVendorIcon() {return this.vendor.getVendorIcon();}
        public String getVendorID(){return this.vendor.getId();}
	public String getVendorName() {return this.vendor.getUsername();}
	public String getFoodType() {return this.vendor.getType();}
        public int getDeliveryFee(){return this.deliveryFee;}
        
        public String toString() {
		String output = String.format("%s,%s,%s,%s,%s", getVendorIcon(),getVendorID(),getVendorName(),getFoodType(),this.deliveryFee);
		return output;
	}
        
        
}