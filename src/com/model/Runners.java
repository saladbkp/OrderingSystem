package com.model;

public class Runners {
	private String runnerID;
	private String runnerPwd;
	private String runnerName;
	private String location;
	private String vehicle;
	
	public Runners(String id,String pwd,String name,String location,String vehicle) {
		this.runnerID = id;
		this.runnerPwd = pwd;
		this.runnerName = name;
		this.location = location;
		this.vehicle = vehicle;
	}
	public String getRunnerID() {return this.runnerID;}
	public String getRunnerPwd() {return this.runnerPwd;}
	public String toString() {
		String output = String.format("%s,%s,%s,%s,%s", this.runnerID,this.runnerPwd,this.runnerName,this.location,this.vehicle);
		return output;
	}
}
