/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import com.tool.Tools;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ray
 */
public class Transactions extends TargetAttrs{
        private java.util.Date datetime; 
    	public Transactions(String senderid,String targetid,String orderid, String trans,String datetime) {
            super(senderid,targetid,orderid,trans);
            try {
                this.datetime = Tools.formatter.parse(datetime);
            } catch (ParseException ex) {
                Logger.getLogger(Notifications.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
        public Date getDatetime() {
            return datetime;
        }

        @Override
	public String toString() {
		String output = String.format("%s", this.getToString(),Tools.formatter.format(datetime));
		return output;
	}
}
