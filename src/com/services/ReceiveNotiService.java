/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.services;

import com.dao.AddNotiDao;
import com.model.Notifications;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 *
 * @author ray
 */
public class ReceiveNotiService {

    public static void receiveNotiService(String receiver) {
        // show dialog (noti)
        AddNotiDao noti = new AddNotiDao();
        List<Notifications> notiarray = new ArrayList<Notifications>();
        notiarray = noti.findDataByID(receiver);
        for (Notifications notis : notiarray) {
            ImageIcon img = new ImageIcon("src//img//info.png");
            JOptionPane.showMessageDialog(null, notis.getContent(), "noti", JOptionPane.INFORMATION_MESSAGE, img);
        }
    }

    public static ArrayList<String> receiveFromPendingVendor(String target) {
        // from xxx to yyy
        ArrayList<String> pendingOrderList = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(new File("src/data/pending.txt"));

            while (scanner.hasNextLine()) {
                String pendinglist[] = scanner.nextLine().split(",");
                String targetid = pendinglist[0];
                String orderid = pendinglist[1];
                if(targetid.equals(target)){
                    int decision = JOptionPane.showConfirmDialog(null,"Do you want to accept?", "Receiving Order: "+orderid, YES_NO_OPTION );
                    String decStr = "," + decision;
                    pendingOrderList.add(orderid+decStr);
                }


            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return pendingOrderList;
    }
        public static void receiveFromPendingCustomer(String target) {
        // from xxx to yyy
        try {
            Scanner scanner = new Scanner(new File("src/data/pending.txt"));

            while (scanner.hasNextLine()) {
                String pendinglist[] = scanner.nextLine().split(",");
                String targetid = pendinglist[0];
                String orderid = pendinglist[1];
                String status = pendinglist[2];
                if(targetid.equals(target)){
                    JOptionPane.showMessageDialog(null, String.format("%s -- %s",orderid,status));
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
        
        public static ArrayList<String> receiveFromPendingRunner() {
        // from xxx to yyy
        ArrayList<String> pendingTaskList = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(new File("src/data/pending.txt"));

            while (scanner.hasNextLine()) {
                String pendinglist[] = scanner.nextLine().split(",");
                String targetid = pendinglist[0];
                String orderid = pendinglist[1];
                String status = pendinglist[2];
                if(status.equals("Accepted")){
                    int decision = JOptionPane.showConfirmDialog(null,"Do you want to accept?", "Receiving Task: "+orderid, YES_NO_OPTION );
                    String taskStr = targetid+","+orderid+"," + decision;
                    pendingTaskList.add(taskStr);
                    System.out.println(taskStr);
                }

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return pendingTaskList;
    }
}
