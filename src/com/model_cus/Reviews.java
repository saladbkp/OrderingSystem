/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model_cus;

import com.model.TargetAttrs;

public class Reviews extends TargetAttrs{
	
	public Reviews(String senderid,String targetid,String orderid, String review) {
            super(senderid,targetid,orderid,review);
	}

        @Override
	public String toString() {
		String output = String.format("%s", this.getToString());
		return output;
	}
}