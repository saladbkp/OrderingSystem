/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import java.util.Random;

/**
 *
 * @author khwon
 */
public class Notifications {
    private String NotiId;
    private String TargetId;
    private String Content;
    public Notifications(String notiid,String targetid,String content){
        NotiId = notiid;
        TargetId = targetid;
        Content = content;
    }
    public String getNotiId(){return NotiId;}
    public String getTargetId(){return TargetId;}
    public String getContent(){return Content;}
    public String toString(){
        return String.format("%s,%s,%s",NotiId,TargetId,Content);
    }
}
