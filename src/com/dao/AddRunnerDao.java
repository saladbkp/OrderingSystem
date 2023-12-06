package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.model.Runners;
import com.model.Users;
import com.tool.TextFunction;

public class AddRunnerDao implements IOperation{
        AddCommonDao commonfunc = new AddCommonDao();
        AddUserDao userfunc = new AddUserDao();
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
                Users user = new Users(r.getId(),r.getPassword(),4);
                userfunc.addUser(user);
	}
	// modify
	public void updateData(Object obj) {
                Runners r = Runners.class.cast(obj);
		int index = seek(r.getId());
		runnerarray.set(index,r);
                txtfunc.arrayToStr(runnerarray);

	}
	// delete
	public void deleteData(String id) {
		int index = seek(id);
		runnerarray.remove(index);
                txtfunc.arrayToStr(runnerarray);
                Users user = userfunc.findUser(id);
                userfunc.deleteUser(user);
	}
}
