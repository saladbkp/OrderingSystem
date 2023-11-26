/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.runnerdao;

import com.model_run.Revenue;
import com.model_run.RunnerReview;
import com.tool.TextFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivann
 */
public class ViewRevenueDao {
    ArrayList<Revenue> revenuearray = new ArrayList<Revenue>();
	
	public ViewRevenueDao() {
		TextFunction txtfunc = new TextFunction("src/data/runnerrevenue.txt");
		revenuearray = txtfunc.readfile(Revenue.class);
	}
        public List<Revenue> findTaskData() {
            
		return this.revenuearray;
        }}
