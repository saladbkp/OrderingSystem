/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vendorViews;

import com.customerViews.CustomerNotificationView;
import com.customerViews.CustomerOrderView;
import com.style.Style;
import com.tool.Tools;
import com.windows.Login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author khwon
 */
public class ManageVendorView {
        final int WIDTH = 1300;
	final int HEIGHT = 600;
	final int viewWidth = 1100;
	final int viewHeight = 550;
	
	JFrame jframe = new JFrame();
	FlowLayout flowlayout;
        public ManageVendorView(){
                Init();
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.validate();
        }
        // do all adding element code here
        void Init(){
            // title 
		jframe.setLayout(null);
		jframe.setTitle("Vendor View");
		Tools.WindowCenterScreen(WIDTH, HEIGHT, jframe);
                
                JPanel jpanel1 = new JPanel(); // this is for the menu section 
		JLayeredPane jpanel2 = new JLayeredPane(); // this is for the content section
		jpanel1.setBounds(5,5,150,HEIGHT-50);
		jpanel1.setBackground(Color.BLUE); // if u want to change just change
		jpanel1.setLayout(new FlowLayout(flowlayout.CENTER));
                
                // add tab of Vendor View here
		VendorItemView item = new VendorItemView(0,0,viewWidth,viewHeight);
                //OrderNotificationView notification = new OrderNotificationView(0,0,viewWidth,viewHeight);
                CustomerNotificationView notification = new CustomerNotificationView(0,0,viewWidth,viewHeight);

                OrderStatusView status = new OrderStatusView(0,0,viewWidth,viewHeight);
                CustomerOrderView history = new CustomerOrderView(0,0,viewWidth,viewHeight);
                CustomerReviewView review = new CustomerReviewView(0,0,viewWidth,viewHeight);
                RevenueDashboardView revenue = new RevenueDashboardView(0,0,viewWidth,viewHeight);
                
                // default vendor view
		jpanel2.add(item,(Integer)(JLayeredPane.PALETTE_LAYER));
		jpanel2.setBounds(215-50,5,viewWidth,viewHeight);
                
                // add button name
		String buttonName[] = {"Items","Notification","Status","History","Review","Revenue"};
                for(int i=0;i<buttonName.length;i++) {
			JButton btn = new JButton(buttonName[i]);
			btn.setPreferredSize(new Dimension(100,30));
			jpanel1.add(btn);
			btn.setName(buttonName[i]);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jpanel2.removeAll();
					JButton jbl = (JButton)e.getSource();
					if(jbl.getName().equals(buttonName[0])) {
						jpanel2.add(item,(Integer)(JLayeredPane.PALETTE_LAYER));
						jpanel2.moveToFront(item);
                                               
					}
                                        if(jbl.getName().equals(buttonName[1])) {
						jpanel2.add(notification,(Integer)(JLayeredPane.PALETTE_LAYER));
						jpanel2.moveToFront(notification);
                                               
					}
                                        if(jbl.getName().equals(buttonName[2])) {
						jpanel2.add(status,(Integer)(JLayeredPane.PALETTE_LAYER));
						jpanel2.moveToFront(status);
                                               
					}    
                                        if(jbl.getName().equals(buttonName[3])) {
						jpanel2.add(history,(Integer)(JLayeredPane.PALETTE_LAYER));
						jpanel2.moveToFront(history);
                                               
					} 
                                        if(jbl.getName().equals(buttonName[4])) {
						jpanel2.add(review,(Integer)(JLayeredPane.PALETTE_LAYER));
						jpanel2.moveToFront(review);
                                               
					}
                                        if(jbl.getName().equals(buttonName[5])) {
						jpanel2.add(revenue,(Integer)(JLayeredPane.PALETTE_LAYER));
						jpanel2.moveToFront(revenue);
                                               
					}
                                        // add menu button here (copy paste only)
					
				}
			});
		}
		
                Style style = new Style();
                JLabel account = new JLabel("I'm "+ Login.account);
                account.setFont(style.account);
                account.setForeground(Color.WHITE);
                JPanel jpanel3 = new JPanel();
                jpanel3.setBounds(5,HEIGHT-80,120,30);
                jpanel3.setBackground(Color.BLUE); // if u want to change just change
                jpanel3.setLayout(new FlowLayout(flowlayout.CENTER));
                jpanel3.add(account);
                jframe.add(jpanel3);
		jframe.add(jpanel2);
		jframe.add(jpanel1);
                
                jframe.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent  windowEvent) {
                        jframe.dispose();
                        Login login = new Login();
		    }
                });
        }
}
