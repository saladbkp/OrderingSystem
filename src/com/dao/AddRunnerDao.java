package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.model.Runners;
import com.tool.TextFunction;

public class AddRunnerDao {
        AddCommonDao commonfunc = new AddCommonDao();
	ArrayList<Runners> runnerarray = new ArrayList<Runners>();
        TextFunction txtfunc = new TextFunction("src/data/runners.txt");

	public AddRunnerDao() {
		runnerarray = txtfunc.readfile(Runners.class);
	}
	public int checkRunner(String id) {
		int index = seek(id);
		return index;
	}
	private int seek(String str) {
		return commonfunc.seek(runnerarray, str);
	}
	// view
	public List<Runners> findRunnerData(String id) {
		return commonfunc.findData(runnerarray, id);
	}
	public List<Runners> findRunnerData() {
		return this.runnerarray;
	}
	// add
	
	public void addRunnerData(Runners r) {
		runnerarray.add(r);
                txtfunc.arrayToStr(runnerarray);
	}
	// modify
	public void updateRunnerData(Runners r) {
		int index = seek(r.getId());
		runnerarray.set(index,r);
                txtfunc.arrayToStr(runnerarray);

	}
	// delete
	public void deleteRunnerData(String id) {
		int index = seek(id);
		runnerarray.remove(index);
                txtfunc.arrayToStr(runnerarray);
	}
}
