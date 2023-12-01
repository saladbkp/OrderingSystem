/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.model.Customers;
import com.model.Items;
import com.model_cus.VendorFoods;
import com.tool.TextFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author khwon
 */
public class AddItemDao{
        ArrayList<Items> itemarray = new ArrayList<Items>();
        TextFunction txtfunc = new TextFunction("src/data/items.txt");

	AddCommonDao commonfunc = new AddCommonDao();
	public AddItemDao() {
		itemarray = txtfunc.readfile(Items.class);
	}
        public int checkItem(String id) {
		int index = seek(id);
		return index;
	}
        // interface 
        // seek index 
	public int seek(String str) {	
            for (int i=0;i<itemarray.size();i++) {
                    if(itemarray.get(i).getItemId().equals(str)) {return i;}
            }
            return -1;
	}
        public List<Items> findDataByItem(String id) {
		List<Items> findarray = this.itemarray.stream().filter(x->x.getItemId().equals(id)).toList();
		return findarray;
	}
        public List<Items> findDataByVendor(String id) {
		List<Items> findarray = this.itemarray.stream().filter(x->x.getItemVendor().equals(id)).toList();
		return findarray;
	}
        public List<VendorFoods> findFoodByVendor(String id) {
                return itemarray.stream().filter(x->x.getItemVendor().equals(id)).map(VendorFoods::new).collect(Collectors.toList());
        }
	public void addData(Object obj) {
                Items c = Items.class.cast(obj);
		itemarray.add(c);
                txtfunc.arrayToStr(itemarray);
	}
	// modify
	public void updateData(Object obj) {
                Items c = Items.class.cast(obj);
		int index = seek(c.getItemId());
		itemarray.set(index,c);
                txtfunc.arrayToStr(itemarray);
	}
	// delete
	public void deleteData(String id) {
		int index = seek(id);
		itemarray.remove(index);
                txtfunc.arrayToStr(itemarray);
	}
}
