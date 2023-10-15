package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.model.Customers;
import com.tool.TextFunction;

public class AddCustomerDao {
	ArrayList<Customers> cusarray = new ArrayList<Customers>();
	
	public AddCustomerDao() {
		TextFunction txtfunc = new TextFunction("src/data/customers.txt");
		cusarray = txtfunc.readfile(Customers.class);
	}
	public int checkCustomer(String id) {
		int index = seek(id);
		return index;
	}
	private int seek(String str) {
		for (int i=0;i<cusarray.size();i++) {
			if(cusarray.get(i).getCustomerID().equals(str)) {return i;}
		}
		
		return -1;
	}
	// view
	public List<Customers> findCustomerData(String id) {
		List<Customers> findarrayCus = this.cusarray.stream().filter(x->x.getCustomerID().equals(id)).toList();
		return findarrayCus;
	}
	public List<Customers> findCustomerData() {
		return this.cusarray;
	}
	// add
	
	public void addCustomerData(Customers v) {
		cusarray.add(v);
	}
	// modify
	public void updateCustomerData(Customers v) {
		int index = seek(v.getCustomerID());
		cusarray.set(index,v);
	}
	// delete
	public void deleteCustomerData(String id) {
		int index = seek(id);
		cusarray.remove(index);
	}
	// update combobox
	public List<String> updateCombox() {
	    return cusarray.stream()
	                   .map(Customers::getCustomerID)
	                   .collect(Collectors.toList());
	}
	public int getCustomerBalance(String id) {
		int index = seek(id);
		return index!=-1?cusarray.get(index).getCustomerBalance():-1;
	}
	public String getCustomerName(String id) {
		int index = seek(id);
		return index!=-1?cusarray.get(index).getCustomerName():"";
	}
	public void customerTopUp(String id,int balance) {
		int index = seek(id);
		int currentbalance = cusarray.get(index).getCustomerBalance();
//		System.out.println(currentbalance+" "+balance);
		Customers cus =  cusarray.get(index);
		cus.setCustomerBalance(currentbalance+balance);
		updateCustomerData(cus);
	}
}
