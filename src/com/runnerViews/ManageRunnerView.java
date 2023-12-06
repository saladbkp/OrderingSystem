/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.runnerViews;

import com.customerViews.CustomerNotificationView;
import com.customerViews.CustomerOrderView;
import com.dao.AddOrderDao;
import com.dao.AddPendingOrderDao;
import com.dao.AddPendingTaskDao;
import com.model.Notifications;
import com.model.Orders;
import com.model_run.Tasks;
import com.runnerdao.AddTaskDao;
import com.services.ReceiveNotiService;
import com.vendorViews.*;
import com.tool.Tools;
import static com.runnerViews.RunnerTaskView.model;
import static com.runnerViews.RunnerTaskView.tasktable;
import com.windows.Login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author khwon
 */
public class ManageRunnerView {

    final int WIDTH = 1300;
    final int HEIGHT = 600;
    final int viewWidth = 1100;
    final int viewHeight = 550;

    JFrame jframe = new JFrame();
    FlowLayout flowlayout;
    public  ArrayList<String> pendingOrderList = new ArrayList<String>();
    AddOrderDao orderfunc = new AddOrderDao();
    AddPendingTaskDao pentaskfunc = new AddPendingTaskDao();
    
    public ManageRunnerView() {
        Init();
        jframe.setVisible(true);
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jframe.validate();
        // default pop out 
        pendingOrderList = ReceiveNotiService.receiveFromPendingRunner(); 
        updateTable();
    }
    
    public void updateTable(){
                       
            String account = Login.account;
            Calendar cal = Calendar.getInstance();
            String date = Tools.formatter.format(cal.getTime());
            ArrayList<Tasks> taskarray = new ArrayList<Tasks>();
                     
            for (String pendingtxt: pendingOrderList){
                String[] pendinglist = pendingtxt.split(",");
                String customerId = pendinglist[0];
                String orderid = pendinglist[1];
                String status = pendinglist[2];
                String statusStr;
                Orders order = orderfunc.findDataByOrder(orderid);
                if(status.equals("0")){
                    statusStr = "Accepted";
                    List<Orders> allOrders = orderfunc.findOrderData();
                    
                    List<Orders> orderWithID = orderfunc.findDataByOrderMul(orderid);
                    allOrders.removeAll(orderWithID);
                    orderWithID.forEach(item -> item.setStatus("1"));
                    orderWithID.forEach(item -> item.setRunnerID(account));
                    allOrders.addAll(orderWithID);
                    Tools.writeFile("src/data/orders.txt", "");
                    // rewrite order txt
                    for(Orders ord:allOrders){
                        Tools.appendFile("src/data/orders.txt", ord.toString());
                    }
                    // add new task
                    Tasks task = new Tasks(order);
                    taskarray.add(task);
                    //write tasks into task file
                    task.setStatus(statusStr);
                    Tools.appendFile("src/data/tasks.txt", task.toString());
                }
                else{
                    statusStr = "Declined";
                    Tasks task = new Tasks(order);   
                    task.setStatus(statusStr);
                    Tools.appendFile("src/data/tasks.txt", task.toString());
                }

                
                Notifications noti = new Notifications(account, customerId, orderid, "Runner "+statusStr +" your Order", date);
                Tools.appendFile("src/data/notifications.txt", noti.toString());
            }

            for (var task: taskarray){
                task.setStatus("Preparing Food");
                Tools.appendFile("src/data/pendingTasks.txt", task.toString());
            }
            // after all processing then add previous data 
            taskarray.addAll(pentaskfunc.findDataByRun(account)); 
            
            model = Tools.addDataTable(taskarray, model);
            tasktable.setModel(model);
            tasktable.removeColumn(tasktable.getColumnModel().getColumn(2));
            
            
           
        }
    // do all adding element code here
    void Init() {
        // title 
        jframe.setLayout(null);
        jframe.setTitle("Runner View");
        Tools.WindowCenterScreen(WIDTH, HEIGHT, jframe);

        JPanel jpanel1 = new JPanel(); // this is for the menu section 
        JLayeredPane jpanel2 = new JLayeredPane(); // this is for the content section
        jpanel1.setBounds(5, 5, 150, HEIGHT - 50);
        jpanel1.setBackground(Color.BLUE); // if u want to change just change
        jpanel1.setLayout(new FlowLayout(flowlayout.CENTER));

        // add tab of Vendor View here
        RunnerTaskView task = new RunnerTaskView(0, 0, viewWidth, viewHeight); //HIII
        CustomerReviewView review = new CustomerReviewView(0, 0, viewWidth, viewHeight);
        RevenueDashboardView revenue = new RevenueDashboardView(0, 0, viewWidth, viewHeight);
        RunnerTaskHistoryView history = new RunnerTaskHistoryView(0, 0, viewWidth, viewHeight);
        CustomerNotificationView notification = new CustomerNotificationView(0, 0, viewWidth, viewHeight);
        // default vendor view
        jpanel2.add(task, (Integer) (JLayeredPane.PALETTE_LAYER));
        jpanel2.setBounds(215 - 50, 5, viewWidth, viewHeight);

        // add button name
        String buttonName[] = {"Orders", "Review", "Revenue", "History", "Notification"}; //HIIII
        for (int i = 0; i < buttonName.length; i++) {
            JButton btn = new JButton(buttonName[i]);
            btn.setPreferredSize(new Dimension(100, 30));
            jpanel1.add(btn);
            btn.setName(buttonName[i]);
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jpanel2.removeAll();
                    JButton jbl = (JButton) e.getSource();
                    if (jbl.getName().equals(buttonName[0])) {//HII ALL IF STATEMENT
                        jpanel2.add(task, (Integer) (JLayeredPane.PALETTE_LAYER));
                        jpanel2.moveToFront(task);

                    }

                    if (jbl.getName().equals(buttonName[1])) {
                        jpanel2.add(review, (Integer) (JLayeredPane.PALETTE_LAYER));
                        jpanel2.moveToFront(review);

                    }
                    if (jbl.getName().equals(buttonName[2])) {
                        jpanel2.add(revenue, (Integer) (JLayeredPane.PALETTE_LAYER));
                        jpanel2.moveToFront(revenue);

                    }
                    if (jbl.getName().equals(buttonName[3])) {
                        jpanel2.add(history, (Integer) (JLayeredPane.PALETTE_LAYER));
                        jpanel2.moveToFront(history);

                    }
                    if (jbl.getName().equals(buttonName[4])) {
                        jpanel2.add(notification, (Integer) (JLayeredPane.PALETTE_LAYER));
                        jpanel2.moveToFront(notification);

                    }
                    // add menu button here (copy paste only)

                }
            });
        }

        jframe.add(jpanel2);
        jframe.add(jpanel1);

        jframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                jframe.dispose();
                Login login = new Login();
            }
        });
    }
}
