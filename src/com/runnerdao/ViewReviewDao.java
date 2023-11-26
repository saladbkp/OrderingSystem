/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.runnerdao;

import com.model_run.History;
import com.model_run.RunnerReview;
import com.tool.TextFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivann
 */
public class ViewReviewDao {
    ArrayList<RunnerReview> reviewarray = new ArrayList<RunnerReview>();
	
	public ViewReviewDao() {
		TextFunction txtfunc = new TextFunction("src/data/runnerreview.txt");
		reviewarray = txtfunc.readfile(RunnerReview.class);
	}
        public List<RunnerReview> findTaskData() {
            
		return this.reviewarray;
	} 
}
