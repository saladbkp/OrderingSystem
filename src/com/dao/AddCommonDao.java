/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.model.CommonAttrs;
import com.model.Customers;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author khwon
 */
public class AddCommonDao {
    public <T extends CommonAttrs> List<T> findData(List<T> dataArray, String id) {
            return dataArray.stream()
                    .filter(x -> x.getId().equals(id))
                    .collect(Collectors.toList());
        }
    public <T extends CommonAttrs> int seek(List<T> dataArray,String str ){
            for (int i=0;i<dataArray.size();i++) {
                    if(dataArray.get(i).getId().equals(str)) {return i;}
            }
            return -1;
    }
        public <T extends CommonAttrs> String nameFindID(List<T> dataArray,String str ){
            for (int i=0;i<dataArray.size();i++) {
                    if(dataArray.get(i).getUsername().equals(str)) {return dataArray.get(i).getId();}
            }
            return "";
    }
}
