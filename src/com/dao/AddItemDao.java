/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

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
public class AddItemDao {
        ArrayList<Items> itemarray = new ArrayList<Items>();
	AddCommonDao commonfunc = new AddCommonDao();
	public AddItemDao() {
		TextFunction txtfunc = new TextFunction("src/data/items.txt");
		itemarray = txtfunc.readfile(Items.class);
	}
        public List<Items> findDataByItem(String id) {
		List<Items> findarray = this.itemarray.stream().filter(x->x.getItemId().equals(id)).toList();
		return findarray;
	}
        public List<VendorFoods> findFoodByVendor(String id) {
                return itemarray.stream().filter(x->x.getItemVendor().equals(id)).map(VendorFoods::new).collect(Collectors.toList());
        }
}
