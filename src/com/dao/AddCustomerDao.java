package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.model.Customers;
import com.model.Users;
import com.tool.TextFunction;
import com.tool.Tools;

public class AddCustomerDao implements IOperation{
        AddCommonDao commonfunc = new AddCommonDao();
        AddUserDao userfunc = new AddUserDao();
	ArrayList<Customers> cusarray = new ArrayList<Customers>();
        TextFunction txtfunc = new TextFunction("src/data/customers.txt");

	public AddCustomerDao() {
		cusarray = txtfunc.readfile(Customers.class);
	}
	public int checkCustomer(String id) {
		int index = seek(id);
		return index;
	}
        public void updateArray(){
            ArrayList<Customers> cusarray = new ArrayList<Customers>();
            TextFunction txtfunc = new TextFunction("src/data/customers.txt");
        }
        // interface 
        // seek index 
        @Override
	public int seek(String str) {		
		return commonfunc.seek(cusarray, str);
	}
	// view
        @Override
	public List<Customers> findDataByID(String id) {
		
		return commonfunc.findData(cusarray, id);
	}
        @Override
	public List<Customers> findData() {
		return this.cusarray;
	}
	// add
	
        @Override
	public void addData(Object obj) {
                Customers c = Customers.class.cast(obj);
		cusarray.add(c);
                txtfunc.arrayToStr(cusarray);
                Users user = new Users(c.getId(),c.getPassword(),3);
                userfunc.addUser(user);
	}
	// modify
	public void updateData(Object obj) {
                Customers c = Customers.class.cast(obj);
		int index = seek(c.getId());
		cusarray.set(index,c);
                txtfunc.arrayToStr(cusarray);
	}
	// delete
	public void deleteData(String id) {
		int index = seek(id);
		cusarray.remove(index);
                txtfunc.arrayToStr(cusarray);
                Users user = userfunc.findUser(id);
                userfunc.deleteUser(user);
	}
        //////////////////////////////////////////
        
	// update combobox
	public List<String> updateCombox() {
	    return cusarray.stream()
	                   .map(Customers::getId)
	                   .collect(Collectors.toList());
	}
	public double getCustomerBalance(String id) {
                updateArray();
		int index = seek(id);
		return index!=-1?cusarray.get(index).getCustomerBalance():-1;
	}
	public String getCustomerName(String id) {
		int index = seek(id);
		return index!=-1?cusarray.get(index).getUsername():"";
	}
	public void customerTopUp(String id,double balance) {
		int index = seek(id);
		double currentbalance = cusarray.get(index).getCustomerBalance();
//		System.out.println(currentbalance+" "+balance);
		Customers cus =  cusarray.get(index);
		cus.setCustomerBalance(currentbalance+balance);
		updateData(cus);
	}


}
