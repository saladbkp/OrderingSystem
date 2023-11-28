/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author ray
 */
public class Transactions extends TargetAttrs{
    	public Transactions(String senderid,String targetid,String orderid, String trans) {
            super(senderid,targetid,orderid,trans);
	}

        @Override
	public String toString() {
		String output = String.format("%s", this.getToString());
		return output;
	}
}
