/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import com.dao.AddCustomerDao;
import com.dao.AddItemDao;
import com.dao.AddOrderDao;
import com.dao.AddRunnerDao;
import com.dao.AddVendorDao;
import com.tool.Tools;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author khwon
 */
public class Receipts {

    //private int receiptNo;
    private Orders order;
    private Customers customer;
    private Vendors vendor;
    private Runners runner;
    private java.util.Date DateTime;
    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    AddCustomerDao addcustomerdao = new AddCustomerDao();
    AddVendorDao addvendordao = new AddVendorDao();
    AddRunnerDao addrunnerdao = new AddRunnerDao();
    AddOrderDao addorderdao = new AddOrderDao();
    AddItemDao additemdao = new AddItemDao();

    public Receipts(String orderid) {
        Calendar cal = Calendar.getInstance();
        this.DateTime = cal.getTime();
        this.order = addorderdao.findDataByOrder(orderid);
        this.customer = addcustomerdao.findDataByID(order.getCustomerId()).get(0);
        this.vendor = addvendordao.findDataByID(order.getVendorId()).get(0);
        var runnerList = addrunnerdao.findDataByID(order.getRunnerId());
        this.runner = !runnerList.isEmpty()? runnerList.get(0): null;
    }

    private String customerInfo() {
        String infotxt = "";
        infotxt += "Username: " + this.customer.getUsername() + "\n";
        infotxt += "Phone: " + this.customer.getCustomerPhone() + "\n";
        infotxt += "Address: " + this.customer.getCustomerAddress() + "\n";
        return infotxt;
    }

    private String OrderInfo() {
        String ordertxt = "";
        float amount = 0;

        Items fooditem = additemdao.findDataByItem(order.getItemId()).get(0);
        float price = fooditem.getPrice();
        int quantity = order.getQuantity();
        float total = price * quantity;
        amount += total;
        String linetxt = String.format("%-10s %10.2f %10d %15.2f\n", fooditem.getItemName(), price, quantity, total);
        ordertxt += linetxt;

        String subtotal = "\nSubtotal: RM" + Tools.decimformatter.format(amount);
        ordertxt += subtotal;
        
        if(runner!=null){
            ordertxt += "\nDelivery fee: RM5";
        }
        ordertxt += "\n--------------------------";
        ordertxt += "\nTotal: "+Tools.decimformatter.format(amount+5);
        
        return ordertxt;
    }


    public String toString() {
        return "Date:" + formatter.format(DateTime) + "\nOrder No:" + order.getOrderId()
                + "\n\nCustomer Details\n" + customerInfo()
                + String.format("\n%-10s %10s %10s %15s\n", "OrderName", "Price", "Quantity", "Total") + OrderInfo();

    }

}
