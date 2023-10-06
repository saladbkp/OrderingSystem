package com.windows;

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

public class ManageView {
	final int WIDTH = 900;
	final int HEIGHT = 600;
	JFrame jframe = new JFrame();
	FlowLayout flowlayout;
	
	ManageView(){
		Init();
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.validate();
	}
	void Init() {
		// title 
		jframe.setLayout(null);
		jframe.setTitle("Customer View");
		WindowSetup.WindowCenterScreen(WIDTH, HEIGHT, jframe);
		
		JPanel jpanel1 = new JPanel();
		JLayeredPane jpanel2 = new JLayeredPane();
		jpanel1.setBounds(5,5,150,HEIGHT-50);
		jpanel1.setBackground(Color.BLUE);
		jpanel1.setLayout(new FlowLayout(flowlayout.CENTER));
		
		JButton menuButton = new JButton("Restaurant");
		JButton hisButton = new JButton("Personal");
		menuButton.setPreferredSize(new Dimension(100,30));
		hisButton.setPreferredSize(new Dimension(100,30));
		jpanel1.add(menuButton);
		jpanel1.add(hisButton);
		
		// add tab of Customer View
		CustomerMenuView customer = new CustomerMenuView(0,0,665+50,HEIGHT-50);
		jpanel2.add(customer,(Integer)(JLayeredPane.PALETTE_LAYER));
		CustomerHistoryView customerhis = new CustomerHistoryView(0,0,665+50,HEIGHT-50);
		jpanel2.add(customerhis,(Integer)(JLayeredPane.PALETTE_LAYER));
		
		jpanel2.setBounds(215-50,5,680+50,HEIGHT-50);

		
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpanel2.moveToFront(customer);
			}
		});
		
		
		jframe.add(jpanel2);
		jframe.add(jpanel1);
		
		
	}
	
	
}
