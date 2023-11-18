/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tool;

import javax.swing.RowFilter;

/**
 *
 * @author khwon
 */
public class TableRowFilter extends RowFilter{
    private String searchkey;
    private int numberCol;
    public TableRowFilter(String key){
        searchkey = key;
//        numberCol = num;
    }
    @Override
    public boolean include(Entry entry){
        
//        System.out.println(entry.getStringValue(0));
        return Integer.parseInt(entry.getValue(2).toString())>0;
    }
}
