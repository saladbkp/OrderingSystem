package com.dao;

import java.util.ArrayList;
import com.model.Users;
import com.tool.TextFunction;

public class LoginDao {
	ArrayList<Users> userarray = new ArrayList<Users>();
	public LoginDao() {
		
	}
	public int checkLogin(String username, String pwd, int role) {
		TextFunction txtfunc = new TextFunction("src/data/users.txt");
		userarray = txtfunc.readfile(Users.class);
		int index = seek(username);
		
		if (index == -1) {return -1;}
		if(!userarray.isEmpty()) {
		
			if(!userarray.get(index).getPassword().equals(pwd)) {return -1;}
			if(userarray.get(index).getRole() != role) {return -1;}
		}
		return index;
	}
	// 1 is found, -1 not found
	private int seek(String str) {
		for (int i=0;i<userarray.size();i++) {
			if(userarray.get(i).getId().equals(str)) {return i;}
		}
		
		return -1;
	}
	public ArrayList<Users> currentUser(){
		return this.userarray;
	}
}
