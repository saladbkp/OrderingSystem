package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.model.Vendors;
import com.tool.TextFunction;

public class AddVendorDao {
	ArrayList<Vendors> vendorarray = new ArrayList<Vendors>();
	
	public AddVendorDao() {
		TextFunction txtfunc = new TextFunction("src/data/vendors.txt");
		vendorarray = txtfunc.readfile(Vendors.class);
	}
	public int checkVendor(String id) {
		int index = seek(id);
		return index;
	}
	private int seek(String str) {
		for (int i=0;i<vendorarray.size();i++) {
			//System.out.println(vendorarray.get(i).getVendorID());
			if(vendorarray.get(i).getVendorID().equals(str)) {return i;}
		}
		
		return -1;
	}
	// view
	public List<Vendors> findVendorData(String id) {
		List<Vendors> findarrayVendor = this.vendorarray.stream().filter(x->x.getVendorID().equals(id)).toList();
		return findarrayVendor;
	}
	public List<Vendors> findVendorData() {
		return this.vendorarray;
	}
	// add
	
	public void addVendorData(Vendors v) {
		vendorarray.add(v);
	}
	// modify
	public void updateVendorData(Vendors v) {
		int index = seek(v.getVendorID());
		vendorarray.set(index,v);
	}
	// delete
	public void deleteVendorData(String id) {
		int index = seek(id);
		vendorarray.remove(index);
	}
	
}
