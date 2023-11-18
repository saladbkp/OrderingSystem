package com.model;

public class Users extends CommonAttrs{
	private int role;
	
	public Users(String id,String pwd,int role) {
		super(id,pwd,id);
		this.role = role;
	}
        public Users(String[] txtlist) {
		super(txtlist[0],txtlist[1],txtlist[2]);
		this.role =  Integer.parseInt(txtlist[3]);
	}
	public int getRole() {return this.role;}
	
}
