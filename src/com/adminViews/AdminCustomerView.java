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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class AdminCustomerView extends JPanel {
	int WIDTH = 750;
	int HEIGHT = 150;
	
	public AdminCustomerView(int x,int y, int width, int height) {
		// separate 2 windows ?????? 
		this.setBounds(x,y,width,height);
		Init();
	}
	
	String columns[] = {"CustomerID","CusName","Balance","Age","Gender"};
	JTable tableitem = null;
	JScrollPane jsrcollpane; //scrollbar
	DefaultTableModel model;
	TableColumnModel columnModel;
	Vector rows;
	
	void Init() {
		this.setLayout(null);
		this.setBackground(Color.gray);
		
		JPanel jpanel1 = new JPanel();
		jpanel1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10)); // left alignment
		jpanel1.setBounds(0,0,WIDTH,50);
		jpanel1.setBackground(Color.YELLOW);
		
		JButton addvendorbutton = new JButton("Add customer");;
		JButton removevendorbutton = new JButton("Remove customer");
		JButton readvendorbutton = new JButton("Read customer");
		JButton updatevendorbutton = new JButton("Update customer");
		
		jpanel1.add(addvendorbutton);
		jpanel1.add(removevendorbutton);
		jpanel1.add(readvendorbutton);
		jpanel1.add(updatevendorbutton);
		
		JPanel jpanel2 = new JPanel();
		jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		jpanel2.setBounds(0,60,WIDTH,50);
		jpanel2.setBackground(Color.LIGHT_GRAY);
		
		JLabel jlabel = new JLabel("Customer Name");
		jpanel2.add(jlabel);
		JTextField jtextfield = new JTextField(10);
		jpanel2.add(jtextfield);
		JLabel jlabel1 = new JLabel("Balance");
		jpanel2.add(jlabel1);
		JTextField jtextfield1 = new JTextField(5);
		jpanel2.add(jtextfield1);
		JLabel jlabel2 = new JLabel("Age");
		jpanel2.add(jlabel2);
		JTextField jtextfield2 = new JTextField(5);
		jpanel2.add(jtextfield2);
		JLabel jlabel3 = new JLabel("Gender");
		jpanel2.add(jlabel3);
		JComboBox cmbgender = new JComboBox();
		cmbgender.addItem("--Select Gender--");
		cmbgender.addItem("Male");
		cmbgender.addItem("Female");
		jpanel2.add(cmbgender);
		
		
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
		jsrcollpane.setBounds(0,120,WIDTH-20,420);
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
