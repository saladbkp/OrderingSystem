/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.model.Transactions;
import com.tool.TextFunction;
import com.windows.Login;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ray
 */
public class AddTransactionDao implements IOperation{
    AddCommonDao commonfunc = new AddCommonDao();
	ArrayList<Transactions> tranarray = new ArrayList<Transactions>();
        TextFunction txtfunc = new TextFunction("src/data/transactions.txt");

	public AddTransactionDao() {
		tranarray = txtfunc.readfile(Transactions.class);
	}

        // interface 
        // seek index 
        @Override
	public int seek(String str) {		
            return commonfunc.seekTarget(tranarray, str);
    	}
	// view
        public List<Transactions> findDataByPeriod(List<Transactions> tranarray,int period) {
            if(period==0) return tranarray;
            List<Transactions> output = new ArrayList<Transactions>();
            for(int i=0;i<tranarray.size();i++){
                Calendar cal = Calendar.getInstance();
                cal.setTime(tranarray.get(i).getDatetime());
                int month = cal.get(Calendar.MONTH)+1;
                if(month==period) output.add(tranarray.get(i));

            }
            return output;

	}
        @Override
	public List<Transactions> findDataByID(String id) {
		return commonfunc.findTargetData(tranarray, id);
	}
        @Override
	public List<Transactions> findData() {
		return this.tranarray;
	}
	// add
	
        @Override
	public void addData(Object obj) {
                Transactions c = Transactions.class.cast(obj);
		tranarray.add(c);
                txtfunc.arrayToStr(tranarray);
	}
        public List<String> updateCombox() {
            String account = Login.account;
            List<String> output = new ArrayList<String>();
            for(int i=0;i<tranarray.size();i++){
                String order = tranarray.get(i).getOrderId();
                String sender = tranarray.get(i).getSenderId();
                String rcpt = tranarray.get(i).getTargetId();
                String credit = tranarray.get(i).getContent();
                int sendStatus = sender.equals(account)?1:0;
                int rcptStatus = rcpt.equals(account)?1:0;
                if(sendStatus+rcptStatus>0){
                    String creditStatus = sendStatus==1?"-":"+";
                    String statement =  sendStatus==1?"Order from": "Reload Balance";
                    String outputStr = String.format("%s RM%s : %s %s",creditStatus,credit,statement,order);
                    output.add(outputStr);
                }
            }
	    return output;
	}
        
}
