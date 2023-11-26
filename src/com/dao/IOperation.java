/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.model.Notifications;
import java.util.List;

/**
 *
 * @author ray
 */

// FOR 
public interface IOperation {
    public Object findData();
    public Object findDataByID(String id);
    public int seek(String id);
    public void addData(Object obj);
    public void updateData(Object obj);
    public void deleteData(String id);
    
}
