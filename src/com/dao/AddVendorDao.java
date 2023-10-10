package com.dao;

import java.util.ArrayList;

import javax.swing.JComboBox;

import com.model.Vendors;

public class AddVendorDao {
	ArrayList<Vendors> vendorarray = new ArrayList<Vendors>();
	
	public int checkVendor(String id) {
		TextFunction txtfunc = new TextFunction("src/data/vendors.txt");
		vendorarray = txtfunc.readfile(Vendors.class);
		int index = seek(id);
		return index;
	}
	private int seek(String str) {
		for (int i=0;i<vendorarray.size();i++) {
			//System.out.println(vendorarray.get(i).getVendorID());
			if(vendorarray.get(i).getVendorName().equals(str)) {return i;}
		}
		
		return -1;
	}
}
