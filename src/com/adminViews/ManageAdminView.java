package com.adminViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.tool.WindowSetup;

public class ManageAdminView {
	final int WIDTH = 930;
	final int HEIGHT = 600;
	JFrame jframe = new JFrame();
	FlowLayout flowlayout;
	
	public ManageAdminView(){
		Init();
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.validate();
	}
	void Init() {
		// title 
		jframe.setLayout(null);
		jframe.setTitle("Administrator View");
		WindowSetup.WindowCenterScreen(WIDTH, HEIGHT, jframe);
		
		JPanel jpanel1 = new JPanel();
		JLayeredPane jpanel2 = new JLayeredPane();
		jpanel1.setBounds(5,5,150,HEIGHT-50);
		jpanel1.setBackground(Color.BLUE);
		jpanel1.setLayout(new FlowLayout(flowlayout.CENTER));
		
//		JButton vendorButton = new JButton("Vendor");
//		JButton customerButton = new JButton("Customer");
//		vendorButton.setPreferredSize(new Dimension(100,30));
//		customerButton.setPreferredSize(new Dimension(100,30));
//		jpanel1.add(vendorButton);
//		jpanel1.add(customerButton);
		
		// add tab of Customer View
		AdminVendorView vendor = new AdminVendorView(0,0,700+50,HEIGHT-50);
		jpanel2.add(vendor,(Integer)(JLayeredPane.PALETTE_LAYER));
		AdminCustomerView customer = new AdminCustomerView(0,0,700+50,HEIGHT-50);
		jpanel2.add(customer,(Integer)(JLayeredPane.PALETTE_LAYER));
		CustomerTopUpView customertopup = new CustomerTopUpView(0,0,700+50,HEIGHT-50);
		jpanel2.add(customertopup,(Integer)(JLayeredPane.PALETTE_LAYER));
		
		jpanel2.setBounds(215-50,5,700+50,HEIGHT-50);
		
		// dynamic set button
		String buttonName[] = {"Vendor","Customer","Top Up"};
		
		for(int i=0;i<buttonName.length;i++) {
			JButton btn = new JButton(buttonName[i]);
			btn.setPreferredSize(new Dimension(100,30));
			jpanel1.add(btn);
			btn.setName(buttonName[i]);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton jbl = (JButton)e.getSource();
					if(jbl.getName().equals(buttonName[0])) {
						jpanel2.moveToFront(vendor);
					}
					if(jbl.getName().equals(buttonName[1])) {
						jpanel2.moveToFront(customer);
					}
					if(jbl.getName().equals(buttonName[2])) {
						jpanel2.moveToFront(customertopup);
					}
					
				}
			});
		}
		
		
		jframe.add(jpanel2);
		jframe.add(jpanel1);
		
		
	}
	
	
}
