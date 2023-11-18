package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.model.Customers;
import com.tool.TextFunction;

public class AddCustomerDao {
        AddCommonDao commonfunc = new AddCommonDao();
	ArrayList<Customers> cusarray = new ArrayList<Customers>();
        TextFunction txtfunc = new TextFunction("src/data/customers.txt");

	public AddCustomerDao() {
		cusarray = txtfunc.readfile(Customers.class);
	}
	public int checkCustomer(String id) {
		int index = seek(id);
		return index;
	}
	private int seek(String str) {		
		return commonfunc.seek(cusarray, str);
	}
	// view
	public List<Customers> findCustomerData(String id) {
		
		return commonfunc.findData(cusarray, id);
	}
	public List<Customers> findCustomerData() {
		return this.cusarray;
	}
	// add
	
	public void addCustomerData(Customers v) {
		cusarray.add(v);
                txtfunc.arrayToStr(cusarray);
	}
	// modify
	public void updateCustomerData(Customers v) {
		int index = seek(v.getId());
		cusarray.set(index,v);
                txtfunc.arrayToStr(cusarray);
	}
	// delete
	public void deleteCustomerData(String id) {
		int index = seek(id);
		cusarray.remove(index);
                txtfunc.arrayToStr(cusarray);
	}
	// update combobox
	public List<String> updateCombox() {
	    return cusarray.stream()
	                   .map(Customers::getId)
	                   .collect(Collectors.toList());
	}
	public int getCustomerBalance(String id) {
		int index = seek(id);
		return index!=-1?cusarray.get(index).getCustomerBalance():-1;
	}
	public String getCustomerName(String id) {
		int index = seek(id);
		return index!=-1?cusarray.get(index).getUsername():"";
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
