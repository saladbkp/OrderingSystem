/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.runnerViews;

import com.vendorViews.*;
import com.tool.Tools;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        public ManageRunnerView(){
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
		jframe.setTitle("Runner View");
		Tools.WindowCenterScreen(WIDTH, HEIGHT, jframe);
                
                JPanel jpanel1 = new JPanel(); // this is for the menu section 
		JLayeredPane jpanel2 = new JLayeredPane(); // this is for the content section
		jpanel1.setBounds(5,5,150,HEIGHT-50);
		jpanel1.setBackground(Color.BLUE); // if u want to change just change
		jpanel1.setLayout(new FlowLayout(flowlayout.CENTER));
                
                // add tab of Vendor View here
		RunnerTaskView task = new RunnerTaskView(0,0,viewWidth,viewHeight); //HIII
                ReviewView review = new ReviewView(0,0,viewWidth,viewHeight);
                RevenueView revenue = new RevenueView(0,0,viewWidth,viewHeight);
                HistoryView history = new HistoryView(0,0,viewWidth,viewHeight);
                // default vendor view
		jpanel2.add(task,(Integer)(JLayeredPane.PALETTE_LAYER));
		jpanel2.setBounds(215-50,5,viewWidth,viewHeight);
                
                // add button name
		String buttonName[] = {"Orders","Review","Revenue","History "}; //HIIII
                for(int i=0;i<buttonName.length;i++) {
			JButton btn = new JButton(buttonName[i]);
			btn.setPreferredSize(new Dimension(100,30));
			jpanel1.add(btn);
			btn.setName(buttonName[i]);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jpanel2.removeAll();
					JButton jbl = (JButton)e.getSource();
					if(jbl.getName().equals(buttonName[0])) {//HII ALL IF STATEMENT
						jpanel2.add(task,(Integer)(JLayeredPane.PALETTE_LAYER));
						jpanel2.moveToFront(task);
                                               
					}
                                       
                                        if(jbl.getName().equals(buttonName[1])) {
						jpanel2.add(review,(Integer)(JLayeredPane.PALETTE_LAYER));
						jpanel2.moveToFront(review);
                                               
					}
                                        if(jbl.getName().equals(buttonName[2])) {
						jpanel2.add(revenue,(Integer)(JLayeredPane.PALETTE_LAYER));
						jpanel2.moveToFront(revenue);
                                               
					}
                                        if(jbl.getName().equals(buttonName[3])) {
						jpanel2.add(history,(Integer)(JLayeredPane.PALETTE_LAYER));
						jpanel2.moveToFront(history);
                                               
					}
                                        // add menu button here (copy paste only)
					
				}
			});
		}
		
		
		jframe.add(jpanel2);
		jframe.add(jpanel1);
        }
}
