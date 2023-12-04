package com.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Orders {
        private String OrderID; 
	private String CustomerID;
	private String ItemID;
	private String VendorID;
	private String RunnerID;
	private int Quantity;
	private String Type;
	private java.util.Date DateTime;
        private String Status;
	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	
        public Orders(String orderid,String cusid,String itemid,String venid,String runid,String quantity,String type,String dt,String status){
		this.OrderID = orderid;
                this.CustomerID = cusid;
		this.ItemID = itemid;
		this.VendorID = venid;
		this.RunnerID = runid;
		this.Quantity = Integer.parseInt(quantity);
		this.Type = type;
                this.Status = status;   
                try {
                    this.DateTime = formatter.parse(dt);
                } catch (ParseException ex) {
                    Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
                }
	}
        public Orders(Object[] data) {
		this.OrderID = data[0].toString();
                this.CustomerID = data[1].toString();
		this.ItemID = data[2].toString();
		this.VendorID = data[3].toString();
		this.RunnerID = data[4].toString();
		this.Quantity = Integer.parseInt(data[5].toString());
		this.Type = data[6].toString();
                this.Status = data[8].toString();   
                try {
                    this.DateTime = formatter.parse(data[7].toString());
                } catch (ParseException ex) {
                    Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        public String getOrderId(){return this.OrderID;}
	public String getCustomerId() {return this.CustomerID;}
	public String getVendorId() {return this.VendorID;}
	public String getItemId() {return this.ItemID;}
	public String getRunnerId() {return this.RunnerID;}
	public Date getDatetime() {return this.DateTime;}
	public int getQuantity() {return this.Quantity;}
	public String getType() {return this.Type;}
	public String getStatus() {return this.Status;}
        public void setStatus(String status){
            this.Status = status;
        }
	public String toString() {
		String output = String.format("%s,%s,%s,%s,%s,%d,%s,%s,%s", this.OrderID,this.CustomerID,this.ItemID,this.VendorID,this.RunnerID,this.Quantity,this.Type,formatter.format(DateTime),this.Status);
		return output;
	}
	
}
