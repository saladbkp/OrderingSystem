/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author khwon
 */
public abstract class CommonAttrs {
//        private String id;
//    	private String username;
//	private String password;
  
        protected String id;
    	protected String username;
	protected String password;
    
        public CommonAttrs(String id,String pwd,String usn){
            this.id = id;
            this.username = usn;
            this.password = pwd;
        }
        public String getId(){
            return this.id;
        }
        public String getUsername(){
            return this.username;
        }
        public String getPassword(){
            return this.password;
        }
        @Override
        public abstract String toString();
        
        public String getToString(){
            return String.format("%s,%s,%s", this.id,this.password,this.username);
        }
}
