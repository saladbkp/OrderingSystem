package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.model.Runners;
import com.tool.TextFunction;

public class AddRunnerDao {
	ArrayList<Runners> runnerarray = new ArrayList<Runners>();
	
	public AddRunnerDao() {
		TextFunction txtfunc = new TextFunction("src/data/runners.txt");
		runnerarray = txtfunc.readfile(Runners.class);
	}
	public int checkRunner(String id) {
		int index = seek(id);
		return index;
	}
	private int seek(String str) {
		for (int i=0;i<runnerarray.size();i++) {
			if(runnerarray.get(i).getRunnerID().equals(str)) {return i;}
		}
		
		return -1;
	}
	// view
	public List<Runners> findRunnerData(String id) {
		List<Runners> findarrayCus = this.runnerarray.stream().filter(x->x.getRunnerID().equals(id)).toList();
		return findarrayCus;
	}
	public List<Runners> findRunnerData() {
		return this.runnerarray;
	}
	// add
	
	public void addRunnerData(Runners r) {
		runnerarray.add(r);
	}
	// modify
	public void updateRunnerData(Runners r) {
		int index = seek(r.getRunnerID());
		runnerarray.set(index,r);
	}
	// delete
	public void deleteRunnerData(String id) {
		int index = seek(id);
		runnerarray.remove(index);
	}
}
