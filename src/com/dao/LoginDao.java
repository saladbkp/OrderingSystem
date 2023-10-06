package com.dao;

import java.util.ArrayList;
import com.model.Users;

public class LoginDao {
	ArrayList<Users> userarray = new ArrayList<Users>();
	public LoginDao() {
		
	}
	public int checkLogin(String username, String pwd, int role) {
		TextFunction txtfunc = new TextFunction("src/data/users.txt");
		userarray = txtfunc.readfile();
		int index = txtfunc.seek(username);
		
		if (index == -1) {return -1;}
		if(!userarray.isEmpty()) {
		
			if(!userarray.get(index).getPassword().equals(pwd)) {return -1;}
			if(userarray.get(index).getRole() != role) {return -1;}
		}
		return index;
	}
	
}
