/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.model.Customers;
import com.model.Users;
import com.model.Vendors;
import com.tool.TextFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ray
 */
public class AddUserDao {
    AddCommonDao commonfunc = new AddCommonDao();
    ArrayList<Users> userarray = new ArrayList<Users>();
    TextFunction txtfunc = new TextFunction("src/data/users.txt");
    public AddUserDao() {
        userarray = txtfunc.readfile(Users.class);
    }
    public Users findUser(String id){
        List<Users> userlist = commonfunc.findData(userarray, id);
        return !userlist.isEmpty()?userlist.get(0):null;
    }
    public void addUser(Users user){
        userarray.add(user);
        txtfunc.arrayToStr(userarray);
    }
    public void deleteUser(Users user){
        userarray.remove(user);
        txtfunc.arrayToStr(userarray);
    }
}
