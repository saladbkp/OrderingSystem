package com.dao;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.model.Users;

public class TextFunction {
	ArrayList<Users> txtarray = new ArrayList<Users>();
	String filepath;
	public TextFunction(String path) {
		// "src/data/users.txt"
		filepath = path;
	}
	public ArrayList<Users> readfile() {
		String line = null;
		try {
			FileReader f = new FileReader(filepath);
			BufferedReader br = new BufferedReader(f);
			txtarray.clear();
			while(((line=br.readLine())!=null)) {
				// split ,
				String[] spt = line.split(",");
				Users user = new Users(spt[0],spt[1],Integer.parseInt(spt[2]));
				txtarray.add(user);
				
				//System.out.println(spt[0]);
			}
			System.out.println("Read file success...");
			br.close();
			f.close();
			return txtarray;
			
		}
		catch(IOException e) {
			e.printStackTrace();
			return new ArrayList<Users>();
		}
		
	}

	// 1 is found, -1 not found
	public int seek(String str) {
		for (int i=0;i<txtarray.size();i++) {
			if(txtarray.get(i).getUsername().equals(str)) {return i;}
		}
		
		return -1;
	}
	
	
}
