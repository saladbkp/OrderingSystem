/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customerdao;

import com.dao.AddCommonDao;
import com.dao.IOperation;
import com.model.Customers;
import com.model_cus.Reviews;
import com.tool.TextFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ray
 */
public class AddReviewDao implements IOperation{
        AddCommonDao commonfunc = new AddCommonDao();
	ArrayList<Reviews> reviewarray = new ArrayList<Reviews>();
        TextFunction txtfunc = new TextFunction("src/data/reviews.txt");

	public AddReviewDao() {
		reviewarray = txtfunc.readfile(Reviews.class);
	}
        // interface 
        // seek index 
        @Override
	public int seek(String str) {		
            return commonfunc.seekTarget(reviewarray, str);
    	}
	// view
        @Override
	public List<Reviews> findDataByID(String id) {
		
		return commonfunc.findTargetData(reviewarray, id);
	}
        @Override
	public List<Reviews> findData() {
		return this.reviewarray;
	}
	// add
	
        @Override
	public void addData(Object obj) {
                Reviews c = Reviews.class.cast(obj);
		reviewarray.add(c);
                txtfunc.arrayToStr(reviewarray);
	}
//	// modify
//        @Override
//	public void updateData(Object obj) {
//                return;
//	}
//	// delete
//        @Override
//	public void deleteData(String id) {
//		return;
//	}
        
        public List<String> updateCombox() {
	    return reviewarray.stream()
	                   .map(Reviews::getOrderId)
	                   .collect(Collectors.toList());
	}
}
