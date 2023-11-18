/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author khwon
 */
public class CommonAttrs {
        private String id;
    	private String username;
	private String password;
        
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
        public String getToString(){
            return String.format("%s,%s,%s", this.id,this.password,this.username);
        }
}
