package com.tool;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.model.Customers;
import com.model.Items;
import com.model.Notifications;
import com.model.Orders;
import com.model.Runners;
import com.model.Transactions;
import com.model.Users;
import com.model.Vendors;
import com.model_cus.Reviews;

public class TextFunction {
	String filepath;
	public TextFunction(String path) {
		// "src/data/users.txt"
		filepath = path;
	}
	public <T> ArrayList<T> readfile(Class<T> clazz) {
		ArrayList<T> txtarray = new ArrayList<T>();
		String line = null;
		try {
			FileReader f = new FileReader(filepath);
			BufferedReader br = new BufferedReader(f);
			txtarray.clear();
			while(((line=br.readLine())!=null)) {
				// split ,
				String[] spt = line.split(",");
				if (clazz.equals(Users.class)) {
	                Users user = new Users(spt[0], spt[1], Integer.parseInt(spt[2]));
	                txtarray.add(clazz.cast(user));
	            } 
				else if (clazz.equals(Vendors.class)) {
	                Vendors vendor = new Vendors(spt[0],spt[1], spt[2],spt[3],spt[4],spt[5]);
	                txtarray.add(clazz.cast(vendor));
	            }
				else if (clazz.equals(Customers.class)) {
					Customers customer = new Customers(spt[0],spt[1],spt[2],spt[3],spt[4],spt[5],spt[6]);
	                txtarray.add(clazz.cast(customer));
	            }
				else if (clazz.equals(Runners.class)) {
					Runners runner = new Runners(spt[0],spt[1],spt[2],spt[3],spt[4]);
	                txtarray.add(clazz.cast(runner));
	            }
				else if (clazz.equals(Orders.class)) {
					Orders order = new Orders(spt[0],spt[1],spt[2],spt[3],spt[4],spt[5],spt[6],spt[7],spt[8]);
	                txtarray.add(clazz.cast(order));
	            }
                                else if (clazz.equals(Items.class)) {
					Items item = new Items(spt[0],spt[1],spt[2],spt[3]);
	                txtarray.add(clazz.cast(item));
	            }
                                else if (clazz.equals(Notifications.class)) {
					Notifications noti = new Notifications(spt[0],spt[1],spt[2],spt[3],spt[4]);
	                txtarray.add(clazz.cast(noti));
	            }           else if (clazz.equals(Reviews.class)) {
					Reviews rev = new Reviews(spt[0],spt[1],spt[2],spt[3]);
	                txtarray.add(clazz.cast(rev));
	            }
                                else if (clazz.equals(Transactions.class)) {
					Transactions tran = new Transactions(spt[0],spt[1],spt[2],spt[3],spt[4]);
	                txtarray.add(clazz.cast(tran));
	            }
				//System.out.println(spt[0]);
			}
			System.out.println("Read file "+ clazz.toString() +" success...");
			br.close();
			f.close();
			return txtarray;
			
		}
		catch(IOException e) {
			e.printStackTrace();
			return txtarray;
		}
		
		
	}
        // write array to content
        public <T> void arrayToStr(ArrayList<T> list){
            String listString = "";
            for(T model: list){
                listString += model.toString()+"\n";
            }
            Tools.writeFile(filepath, listString);
        }
        
        
	
}
