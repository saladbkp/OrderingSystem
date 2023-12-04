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
    private String filterkey;
    private int numberCol;
    public TableRowFilter(int num,String key){
        filterkey = key;
        numberCol = num;
    }
    // filter integer only ~~~
    @Override
    public boolean include(Entry entry){
        
//        System.out.println(entry.getStringValue(0));
        boolean isNumeric = filterkey.chars().allMatch( Character::isDigit );
        if(isNumeric){
            return Integer.parseInt(entry.getValue(this.numberCol).toString())>Integer.parseInt(this.filterkey);
        }
        else{
            return entry.getValue(this.numberCol).toString().equals(this.filterkey);
        }
    }
}
