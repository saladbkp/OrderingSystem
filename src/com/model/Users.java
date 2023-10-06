package com.model;

public class Users {
	private String username;
	private String password;
	private int role;
	
	public Users(String usn,String pwd,int role) {
		this.username = usn;
		this.password = pwd;
		this.role = role;
	}
	public String getUsername() {return this.username;}
	public String getPassword() {return this.password;}
	public int getRole() {return this.role;}
	
}
