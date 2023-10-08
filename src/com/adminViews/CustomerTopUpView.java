package com.adminViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.style.Style;

public class CustomerTopUpView extends JPanel{
	int WIDTH = 730;
	int HEIGHT = 150;
	
	public CustomerTopUpView(int x,int y, int width, int height) {
		// separate 2 windows ?????? 
		this.setBounds(x,y,width,height);
		Init();
	}
	void Init() {
		this.setLayout(null);
		this.setBackground(Color.gray);
		
		JPanel jpanel1 = new JPanel();
		jpanel1.setLayout(new FlowLayout(FlowLayout.RIGHT)); 
		jpanel1.setBackground(Color.YELLOW);
		jpanel1.setPreferredSize(new Dimension(200,420));
		jpanel1.setBounds(150,100,350,150);
		// customer id
		Style style = new Style();
		JLabel jlabel1 = new JLabel("Customer ID:");
		jlabel1.setFont(style.account);
		jpanel1.add(jlabel1);
				
		JTextField jtextfield = new JTextField(15);
		jtextfield.setFont(style.accounttext);
		jpanel1.add(jtextfield);
		
		JLabel jlabel2 = new JLabel("Balance:");
		jlabel2.setFont(style.account);
		jpanel1.add(jlabel2);
				
		JTextField jtextfield2 = new JTextField(15);
		jtextfield2.setFont(style.accounttext);
		jpanel1.add(jtextfield2);
		
		JButton jbutton = new JButton("Add");
		jbutton.setFont(style.ok);
		jbutton.setPreferredSize(new Dimension(210,35));
		jpanel1.add(jbutton);
		
		this.add(jpanel1);
	}
}
