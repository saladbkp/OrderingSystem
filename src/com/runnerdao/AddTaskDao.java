/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.runnerdao;


import com.model.Customers;
import com.model_run.Tasks;
import com.tool.TextFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivann
 */
public class AddTaskDao {            //here 
	ArrayList<Tasks> tasksarray = new ArrayList<Tasks>();
	
	public AddTaskDao() {
		TextFunction txtfunc = new TextFunction("src/data/runnerdata.txt");
		tasksarray = txtfunc.readfile(Tasks.class);
	}
        public List<Tasks> findTaskData() {
            
		return this.tasksarray;
	} 
}
        
//until here
