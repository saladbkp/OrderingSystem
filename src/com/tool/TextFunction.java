package com.tool;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.model.Users;
import com.model.Vendors;

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
	                Vendors vendor = new Vendors(spt[0],spt[1], spt[2],spt[3],spt[4]);
	                txtarray.add(clazz.cast(vendor));
	            }
				
				//System.out.println(spt[0]);
			}
			System.out.println("Read file success...");
			br.close();
			f.close();
			return txtarray;
			
		}
		catch(IOException e) {
			e.printStackTrace();
			return txtarray;
		}
		
		
	}


	
	
}
