package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.model.Vendors;
import com.model_cus.Menus;
import com.tool.TextFunction;
import java.util.stream.Collectors;

public class AddVendorDao {
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
	private int seek(String str) {
		return commonfunc.seek(vendorarray, str);
	}
	// view
	public List<Vendors> findVendorData(String id) {
		return commonfunc.findData(vendorarray, id);
	}
	public List<Vendors> findVendorData() {
		return this.vendorarray;
	}
        public String findVendorID(String name) {
		return commonfunc.nameFindID(vendorarray, name);
	}
	// add
	
	public void addVendorData(Vendors v) {
		vendorarray.add(v);
                txtfunc.arrayToStr(vendorarray);
	}
	// modify
	public void updateVendorData(Vendors v) {
		int index = seek(v.getId());
		vendorarray.set(index,v);
                txtfunc.arrayToStr(vendorarray);
	}
	// delete
	public void deleteVendorData(String id) {
		int index = seek(id);
		vendorarray.remove(index);
                txtfunc.arrayToStr(vendorarray);
	}
        // add vendor menu 
        public List<Menus> findVendorMenuData() {
                return vendorarray.stream().map(Menus::new).collect(Collectors.toList());
        }
	
}
