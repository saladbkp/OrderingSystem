/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.services;

import com.dao.AddNotiDao;
import com.model.Notifications;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 *
 * @author ray
 */
public class ReceiveNotiService {
    public static void receiveNotiService(String receiver){
        // show dialog (noti)
        AddNotiDao noti = new AddNotiDao();
        List<Notifications> notiarray = new ArrayList<Notifications>();
        notiarray = noti.findDataByID(receiver);
        for (Notifications notis : notiarray){
            ImageIcon img = new ImageIcon("src//img//info.png");
            JOptionPane.showMessageDialog(null, notis.getContent(),"noti",JOptionPane.INFORMATION_MESSAGE,img);
        }
    }
    public static int receiveFromPending(){
        JFrame f; 
        f=new JFrame();   
        f.setSize(300, 300);  
        f.setLocationRelativeTo(null);
        f.setLayout(null);  
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  
        f.setVisible(true);  
        // from xxx to yyy
        
        int decision = JOptionPane.showConfirmDialog(f,"Do you want to accept?", "Receiving Order", YES_NO_OPTION );
        System.out.println("d: "+decision);
        return decision;
    }
            
}
