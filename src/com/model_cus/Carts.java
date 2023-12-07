//CONFIGURED
package com.model_cus;

public class Carts {

    private String customerID;
//                 private String customerName;
    private String vendorID;
    private String foodID;
    private int amount;
    private String foodServiceType;
//                 private String runnerID;
//                 private LocalDateTime DateTime;
    private double subtotal;
    private int deliveryFee;
    private double totalCost;
//                 private String orderStatus;

    public Carts(String cid, String vid, String fid, String amount, String fst, String subtotal, String deliveryfee, String tcost) {
        this.customerID = cid;
        this.vendorID = vid;
        this.foodID = fid;
        this.amount = Integer.parseInt(amount);
        this.foodServiceType = fst;
        this.subtotal = Double.parseDouble(subtotal);
        this.deliveryFee = Integer.parseInt(deliveryfee);
        this.totalCost = Double.parseDouble(tcost);
//		this.runnerID = rid;
//                               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //               		this.DateTime = LocalDateTime.parse(dt, formatter);

//		this.orderStatus = ostatus;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getFoodServiceType() {
        return foodServiceType;
    }

    public void setFoodServiceType(String foodServiceType) {
        this.foodServiceType = foodServiceType;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

//                    public String getOrderStatus() {
//                        return orderStatus;
//                    }
//
//                    public void setOrderStatus(String orderStatus) {
//                        this.orderStatus = orderStatus;
//                    }
    public String toString() {
        String output = String.format("%s,%s,%s,%s,%d,%.2f,%s", this.customerID, this.vendorID, this.foodID, this.amount, this.foodServiceType, this.subtotal, this.deliveryFee, this.totalCost);
        return output;
    }

}
