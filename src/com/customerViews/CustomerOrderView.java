/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customerViews;

import com.vendorViews.*;
import com.tool.Tools;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author khwon
 */
public class CustomerOrderView extends JPanel{
        int WIDTH;
	int HEIGHT = 150;
        
	String columns[] = {"Amount","Food","Vendor", "Time", "Status", "Food service", "Runner", "Total cost"};
	JTable tableitem = null;
	JScrollPane jsrcollpane; //scrollbar
	DefaultTableModel model;
	TableColumnModel columnModel;
        
	public CustomerOrderView(int x,int y, int width, int height) {
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
//                
                                    // add button sample
                                JButton reorderbutton = new JButton("Reorder");
                                                    jpanel1.add(reorderbutton);
//                JLabel jlabelfood = new JLabel("Food");
//		jpanel1.add(jlabelfood);
//                JLabel jlabelvendor = new JLabel("Vendor");
//		jpanel1.add(jlabelvendor);
//                JLabel jlabeltime = new JLabel("Time");
//		jpanel1.add(jlabeltime);
//                JLabel jlabelstatus = new JLabel("Status");
//		jpanel1.add(jlabelstatus);
//                JLabel jlabelfoodservice= new JLabel("Food Service");
//		jpanel1.add(jlabelfoodservice);
//                JLabel jlabeltotalcost = new JLabel("Total Cost");
//		jpanel1.add(jlabeltotalcost);
//                

                // add content panel
                //JPanel jpanel2 = new JPanel();
                //jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		//jpanel2.setBounds(0,60,WIDTH,50);
                
                // **** jpanel 1 for selection
                // **** jpanel 2 for table content 
                // if u have better design, just go through ur pattern
                
                //this.add(jpanel2);
                table();
                this.add(jsrcollpane);
                this.add(jpanel1);
        }
        
        
        void table() {
		tableitem = TableSetup();
		jsrcollpane = new JScrollPane(tableitem);
		jsrcollpane.setPreferredSize(new Dimension(WIDTH-20,250));
		tableitem.setPreferredSize(new Dimension(WIDTH-30,1000));
		//jsrcollpane.setVerticalScrollBarPoicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsrcollpane.setBounds(0,60,WIDTH-0,420);
	}
	
	JTable TableSetup() {
		tableitem = new JTable();
		int[] columnWidth = {30,150,150,150,150,150,150,50};
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.setColumnIdentifiers(columns);
		tableitem.setModel(model);
		columnModel = tableitem.getColumnModel();
		tableitem.getTableHeader().setReorderingAllowed(false);
		tableitem.getTableHeader().setResizingAllowed(false);
		int count = columnModel.getColumnCount();
		for(int i=0;i<count;i++) {
			javax.swing.table.TableColumn column = columnModel.getColumn(i);
			column.setPreferredWidth(columnWidth[i]);
		}
		return tableitem;

}
}
