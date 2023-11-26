/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customerViews;

import static com.adminViews.ManageAdminView.client;
import com.services.Client;
import com.vendorViews.*;
import com.tool.Tools;
import com.windows.Login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author khwon
 */
public class ManageCustomerView {
        final int WIDTH = 1300;
	final int HEIGHT = 600;
	final int viewWidth = 1100;
	final int viewHeight = 550;
	
	JFrame jframe = new JFrame();
	FlowLayout flowlayout;
        private Socket socket;
        public static Client client;
        public ManageCustomerView() throws IOException{
                Init();
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.validate();
//                startClient(account);
                
        }
        void startClient(String username) throws IOException{
            socket = new Socket("localhost",1234);
            client = new Client(socket,username);
            client.listenForMessage();
            client.sendMessasge("init");
        }

        // do all adding element code here
        public static JLayeredPane jpanel2;
                void Init(){
            // title 
			jframe.setLayout(null);
			jframe.setTitle("Customer View");
			Tools.WindowCenterScreen(WIDTH, HEIGHT, jframe);
					
			JPanel jpanel1 = new JPanel(); // this is for the menu section 
			jpanel2 = new JLayeredPane(); // this is for the content section
			jpanel1.setBounds(5,5,150,HEIGHT-50);
			jpanel1.setBackground(Color.BLUE); // if u want to change just change
			jpanel1.setLayout(new FlowLayout(flowlayout.CENTER));
					
                        // add tab of Vendor View here
			CustomerMenuView menu = new CustomerMenuView(0,0,viewWidth,viewHeight);
//                        CustomerCartView cart = new CustomerCartView(0,0,viewWidth,viewHeight);
                        CustomerOrderView order = new CustomerOrderView(0,0,viewWidth,viewHeight);
//                        CustomerVendorFoodView vendorfood = new CustomerVendorFoodView(0,0,viewWidth,viewHeight,"");
                        CustomerReview review = new CustomerReview(0,0,viewWidth,viewHeight);
                        CustomerCreditView credit = new CustomerCreditView(0,0,viewWidth,viewHeight);
                        
                        CustomerNotificationView notification = new CustomerNotificationView(0,0,viewWidth,viewHeight);
					// default vendor view
			jpanel2.add(menu,(Integer)(JLayeredPane.PALETTE_LAYER));
			jpanel2.setBounds(215-50,5,viewWidth,viewHeight);
					
					// add button name
			String buttonName[] = {"Menu",  "Order", "Review", "Credit", "Notification" };
//			String buttonName[] = {"Menu", "Review", "Credit", "Notification" };

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
							jpanel2.add(menu,(Integer)(JLayeredPane.PALETTE_LAYER));
							jpanel2.moveToFront(menu);}
											
                                                        if(jbl.getName().equals(buttonName[1])) {
							jpanel2.add(order,(Integer)(JLayeredPane.PALETTE_LAYER));
							jpanel2.moveToFront(order); }
												
																						
                                                        if(jbl.getName().equals(buttonName[2])) {
							jpanel2.add(review,(Integer)(JLayeredPane.PALETTE_LAYER));
							jpanel2.moveToFront(review); }
																						
                                                        if(jbl.getName().equals(buttonName[3])) {
							jpanel2.add(credit,(Integer)(JLayeredPane.PALETTE_LAYER));
							jpanel2.moveToFront(credit); }
																						
                                                        if(jbl.getName().equals(buttonName[4])) {
							jpanel2.add(notification,(Integer)(JLayeredPane.PALETTE_LAYER));
							jpanel2.moveToFront(notification); }
													
													
                                                        // add menu button here (copy paste only)
						
					}
				});
			}
			
			
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
