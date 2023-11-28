/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customerViews;

import com.dao.AddCustomerDao;
import com.dao.AddTransactionDao;
import com.vendorViews.*;
import com.tool.Tools;
import com.windows.Login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author khwon
 */
public class CustomerCreditView extends JPanel{
        int WIDTH;
	int HEIGHT = 150;
	AddTransactionDao tranfunc = new AddTransactionDao();
        AddCustomerDao cusfunc = new AddCustomerDao();

	public CustomerCreditView(int x,int y, int width, int height) {
		// separate 2 windows ?????? 
		this.setBounds(x,y,width,height);
		this.WIDTH = width;
		Init();
	}
        void Init() {
		
		// layout 
		this.setLayout(null);
		this.setBackground(Color.gray);
		
		JPanel jpanel1 = new JPanel();
		jpanel1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10)); // left alignment
		jpanel1.setBounds(0,0,WIDTH,50);
		jpanel1.setBackground(Color.YELLOW);
                
                // add button sample
                JButton topupbutton = new JButton("Request Top Up");
                JTextField jtextfieldtopup = new JTextField(10);
		jpanel1.add(jtextfieldtopup);
		//JButton removeitembutton = new JButton("Remove item");
		//JButton readitembutton = new JButton("Read item");
		//JButton updateitembutton = new JButton("Update item");
                
                jpanel1.add(topupbutton);
		//jpanel1.add(removeitembutton);
		//jpanel1.add(readitembutton);
		//jpanel1.add(updateitembutton);
                
                // add content panel
                JPanel jpanel2 = new JPanel();
                jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		jpanel2.setBounds(0,60,WIDTH,50);
                
                JLabel jlabelbalance = new JLabel("Current Balance:");
		jpanel2.add(jlabelbalance);
                
                String account = Login.account;
                int newbalance = cusfunc.getCustomerBalance(account);
                jlabelbalance.setText(newbalance!=-1?"Current Balance: "+newbalance:"Current Balance: --");
                
                
                JPanel jpanel3 = new JPanel();
                jpanel3.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		jpanel3.setBounds(0,120,WIDTH,50);
                //jpanel3.setBackground(Color.WHITE);
                
                JLabel jlabeltransaction = new JLabel("Transaction:");
		jpanel3.add(jlabeltransaction);
                
                 JPanel jpanel4 = new JPanel();
                jpanel4.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		jpanel4.setBounds(0,170,WIDTH,380);

                // Create an ArrayList of Strings   **********GET DATA FROM TXT FILE THEN INPUT*******************
                List<String> transactionList = tranfunc.updateCombox();
               
                // Create a JList and populate it with the ArrayList
                JList<String> list = new JList<>(transactionList.toArray(new String[0]));
                         
                // Increase the height of each item in the JList
                list.setFixedCellHeight(45); // Set the desired height in pixels

                // Increase the width of the JList
                list.setFixedCellWidth(450); // Set the desired width in pixels
                
                // Create a JScrollPane to enable scrolling if needed
                JScrollPane scrollpanel = new JScrollPane(list);
                 
                // Set the desired height for the JScrollPane (fixed height)
                scrollpanel.setPreferredSize(new Dimension(500, 330)); // Set the desired width and height

                // Add the panel to the jpanel3
                  jpanel4.add(scrollpanel);

                this.add(jpanel4);
                // **** jpanel 1 for selection
                // **** jpanel 2 for table content 
                // if u have better design, just go through ur pattern
                
                this.add(jpanel2);
                this.add(jpanel1);
                this.add(jpanel3);
        }

}