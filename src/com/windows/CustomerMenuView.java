package com.windows;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CustomerMenuView extends JPanel {
	int WIDTH,HEIGHT;
	
	public CustomerMenuView(int x,int y, int width, int height) {
		WIDTH = width;
		HEIGHT = height+50;
		// separate 2 windows ?????? 
		this.setBounds(x,y,width,height);
		Init();
	}
	
	void Init() {
		this.setLayout(null);
		this.setBackground(Color.gray);
		
		JPanel jpanel1 = new JPanel();
		jpanel1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10)); // left alignment
		jpanel1.setBounds(0,0,WIDTH,HEIGHT);
		jpanel1.setBackground(Color.YELLOW);
		
		JButton addvendorbutton = new JButton("Add vendor");;
		JButton removevendorbutton = new JButton("Remove vendor");
		JButton readvendorbutton = new JButton("Read vendor");
		JButton updatevendorbutton = new JButton("Update vendor");
		
		jpanel1.add(addvendorbutton);
		jpanel1.add(removevendorbutton);
		jpanel1.add(readvendorbutton);
		jpanel1.add(updatevendorbutton);
		
		this.add(jpanel1);
	}
}
