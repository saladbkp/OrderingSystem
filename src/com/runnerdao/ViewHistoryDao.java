/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.runnerdao;

import com.model_run.History;
import com.model_run.Tasks;
import com.tool.TextFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivann
 */
public class ViewHistoryDao {
    ArrayList<History> historyarray = new ArrayList<History>();
	
	public ViewHistoryDao() {
		TextFunction txtfunc = new TextFunction("src/data/runnerhistory.txt");
		historyarray = txtfunc.readfile(History.class);
	}
        public List<History> findTaskData() {
            
		return this.historyarray;
	} 
}

