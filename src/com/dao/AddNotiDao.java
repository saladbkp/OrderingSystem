/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.model.Notifications;
import com.tool.TextFunction;
import com.windows.Login;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author khwon
 */
public class AddNotiDao implements IOperation{
        ArrayList<Notifications> notiarray = new ArrayList<Notifications>();
        TextFunction txtfunc = new TextFunction("src/data/notifications.txt");
	AddCommonDao commonfunc = new AddCommonDao();
	public AddNotiDao() {
		notiarray = txtfunc.readfile(Notifications.class);
	}

        // interface 
        // seek index 
        @Override
	public int seek(String str) {		
            return commonfunc.seekTarget(notiarray, str);
    	}
	// view
        @Override
	public List<Notifications> findDataByID(String id) {
		
		return commonfunc.findTargetData(notiarray, id);
	}
        @Override
	public List<Notifications> findData() {
		return this.notiarray;
	}
	// add
	
        @Override
	public void addData(Object obj) {
                Notifications c = Notifications.class.cast(obj);
		notiarray.add(c);
                txtfunc.arrayToStr(notiarray);
	}
        
        public List<String> updateCombox() {
            String account = Login.account;
            Date cal = Calendar.getInstance().getTime();
            //String datetimenow = Tools.formatter.format(cal.getTime());
            List<String> output = new ArrayList<String>();
            for(int i=0;i<notiarray.size();i++){
                String order = notiarray.get(i).getOrderId();
                String sender = notiarray.get(i).getSenderId();
                String rcpt = notiarray.get(i).getTargetId();
                String noti = notiarray.get(i).getContent();
                long datediff = cal.getTime() - notiarray.get(i).getDatetime().getTime();
                long diffHours = datediff / (60 * 60 * 1000);
                long diffMins = datediff / (60 * 1000) % 60;
                String dur = diffHours>0?String.format("(%s hours ago)",diffHours ):String.format("(%s mins ago)",diffMins );
                int sendStatus = sender.equals(account)?1:0;
                int rcptStatus = rcpt.equals(account)?1:0;
                if(sendStatus+rcptStatus>0){
                    
                    String outputStr = String.format("Noti from %s: %s %s",order,noti,dur);
                    output.add(outputStr);
                }
            }
	    return output;
	}
}
