//CONFIGURED
package com.model_cus;

// show vendorfoods

import com.model.Items;

public class VendorFoods {
                private String foodID; 
                private String foodName;
                private double foodCost;
                private int amount;

	
	public VendorFoods(Items item) {
                this.foodID = item.getItemId();
		this.foodName = item.getItemName();
		this.foodCost = item.getPrice();
                this.amount = 0;
	}

        public String getFoodName() {return this.foodName;}
        public double getFoodCost(){return this.foodCost;}
                 

	public String toString() {
		String output = String.format("%s,%s,%.2f,%s",this.foodID,this.foodName,this.foodCost,this.amount);
		return output;
	}
}

