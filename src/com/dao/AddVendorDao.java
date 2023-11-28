package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.model.Vendors;
import com.model_cus.Menus;
import com.tool.TextFunction;
import java.util.stream.Collectors;

public class AddVendorDao implements IOperation{
        AddCommonDao commonfunc = new AddCommonDao();
	ArrayList<Vendors> vendorarray = new ArrayList<Vendors>();
        TextFunction txtfunc = new TextFunction("src/data/vendors.txt");
	
	public AddVendorDao() {
		vendorarray = txtfunc.readfile(Vendors.class);
	}
	public int checkVendor(String id) {
		int index = seek(id);
		return index;
	}
        @Override
	public int seek(String str) {
		return commonfunc.seek(vendorarray, str);
	}
	// view
        @Override
	public List<Vendors> findDataByID(String id) {
		return commonfunc.findData(vendorarray, id);
	}
        @Override
	public List<Vendors> findData() {
		return this.vendorarray;
	}
        public String findVendorID(String name) {
		return commonfunc.nameFindID(vendorarray, name);
	}
	// add
	
        @Override
	public void addData(Object obj) {
                Vendors v = Vendors.class.cast(obj);
		vendorarray.add(v);
                txtfunc.arrayToStr(vendorarray);
	}
	// modify
	public void updateData(Object obj) {
                Vendors v = Vendors.class.cast(obj);
		int index = seek(v.getId());
		vendorarray.set(index,v);
                txtfunc.arrayToStr(vendorarray);
	}
	// delete
	public void deleteData(String id) {
		int index = seek(id);
		vendorarray.remove(index);
                txtfunc.arrayToStr(vendorarray);
	}
        // add vendor menu 
        public List<Menus> findVendorMenuData() {
                return vendorarray.stream().map(Menus::new).collect(Collectors.toList());
        }
	
}
