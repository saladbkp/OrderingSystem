package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.model.Runners;
import com.tool.TextFunction;

public class AddRunnerDao implements IOperation{
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
        @Override
	public int seek(String str) {
		return commonfunc.seek(runnerarray, str);
	}
	// view
        @Override
	public List<Runners> findDataByID(String id) {
		return commonfunc.findData(runnerarray, id);
	}
        @Override
	public List<Runners> findData() {
		return this.runnerarray;
	}
	// add
	
        @Override
	public void addData(Object obj) {
                Runners r = Runners.class.cast(obj);
		runnerarray.add(r);
                txtfunc.arrayToStr(runnerarray);
	}
	// modify
        @Override
	public void updateData(Object obj) {
                Runners r = Runners.class.cast(obj);
		int index = seek(r.getId());
		runnerarray.set(index,r);
                txtfunc.arrayToStr(runnerarray);

	}
	// delete
        @Override
	public void deleteData(String id) {
		int index = seek(id);
		runnerarray.remove(index);
                txtfunc.arrayToStr(runnerarray);
	}
}
