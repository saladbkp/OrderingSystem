/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customerViews;

import com.dao.AddNotiDao;
import com.style.Style;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author khwon
 */
public class CustomerNotificationView extends JPanel{
        int WIDTH;
	int HEIGHT = 150;
	AddNotiDao notifunc = new AddNotiDao();
        
	public CustomerNotificationView(int x,int y, int width, int height) {
		// separate 2 windows ?????? 
		this.setBounds(x,y,width,height);
		this.WIDTH = width;
		Init();
	}
        void Init() {
		
		// layout 
		this.setLayout(null);
		this.setBackground(Color.gray);
		Style style = new Style();
		JPanel jpanel1 = new JPanel();
		jpanel1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10)); // left alignment
		jpanel1.setBounds(0,0,WIDTH,50);
		jpanel1.setBackground(Color.YELLOW);
                JLabel notificationlabel = new JLabel("Notification");
		notificationlabel.setFont(style.title);
		jpanel1.add(notificationlabel);
                
                // add content panel
                JPanel jpanel2 = new JPanel();
                jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		jpanel2.setBounds(0,60,WIDTH,500);
                // Create an ArrayList of Strings   **********GET DATA FROM TXT FILE THEN INPUT*******************

                // Create a JList and populate it with the ArrayList
                JList<String> list = new JList<>(notifunc.updateCombox().toArray(new String[0]));
                       // Set the desired width and height for the JList
                //list.setPreferredSize(new Dimension(150, 70));
                
                // Increase the height(gap) of each item in the JList
                list.setFixedCellHeight(30); // Set the desired height in pixels

                // Increase the width of the JList
                list.setFixedCellWidth(450); // Set the desired width in pixels
                
                // Create a JScrollPane to enable scrolling if needed
                JScrollPane scrollpanel = new JScrollPane(list);
                scrollpanel.setPreferredSize(new Dimension(500, 450));
                //scrollpanel.setViewportView(list);
                //jpanel3.add(scrollpanel);

                // Add the panel to the jpanel3
                  jpanel2.add(scrollpanel);
               
                // **** jpanel 1 for selection
                // **** jpanel 2 for table content 
                // if u have better design, just go through ur pattern
                
                this.add(jpanel2);
		this.add(jpanel1);
        }

}