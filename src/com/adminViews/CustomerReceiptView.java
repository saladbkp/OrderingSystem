package com.adminViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class CustomerReceiptView extends JPanel {
		int WIDTH;
		int HEIGHT = 150;
		
		public CustomerReceiptView(int x,int y, int width, int height) {
			// separate 2 windows ?????? 
			this.setBounds(x,y,width,height);
			this.WIDTH = width;
			Init();
		}
		
		String columns[] = {"CustomerID","ItemID","VendorID","Price","Amount"};
		JTable tableitem = null;
		JScrollPane jsrcollpane; //scrollbar
		DefaultTableModel model;
		TableColumnModel columnModel;
		Vector rows;
		
		void Init() {
			this.setLayout(null);
			this.setBackground(Color.gray);
			
			JPanel jpanel1 = new JPanel();
			jpanel1.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10)); // left alignment
			jpanel1.setBounds(0,480,WIDTH,50);
			jpanel1.setBackground(Color.YELLOW);
			
			JButton sendbutton = new JButton("Send Notification");
			sendbutton.setPreferredSize(new Dimension(200,30));
			jpanel1.add(sendbutton);
			
			JPanel jpanel2 = new JPanel();
			jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
			jpanel2.setBounds(0,0,WIDTH,50);
			jpanel2.setBackground(Color.LIGHT_GRAY);
			
			JLabel jlabel = new JLabel("Customer Name");
			jpanel2.add(jlabel);
			JComboBox cmbvendor = new JComboBox();
			cmbvendor.addItem("--Select Customer--");
			jpanel2.add(cmbvendor);
			
			
			this.add(jpanel2);
			this.add(jpanel1);
			table();
			this.add(jsrcollpane);
			
		}
		
		void table() {
			tableitem = TableSetup();
			jsrcollpane = new JScrollPane(tableitem);
			jsrcollpane.setPreferredSize(new Dimension(WIDTH-20,250));
			tableitem.setPreferredSize(new Dimension(WIDTH-30,1000));
			//jsrcollpane.setVerticalScrollBarPoicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			jsrcollpane.setBounds(0,60,WIDTH-20,420);
		}
		
		JTable TableSetup() {
			tableitem = new JTable();
			int[] columnWidth = {150,150,150,150,150};
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
			rows = new Vector(5); //???? not yet import data
			return tableitem;
		}
}
