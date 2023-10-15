package com.adminViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.dao.AddRunnerDao;
import com.model.Customers;
import com.model.Runners;
import com.tool.Tools;

public class AdminRunnerView extends JPanel {
	int WIDTH;
	int HEIGHT = 150;
	
	public AdminRunnerView(int x,int y, int width, int height) {
		// separate 2 windows ?????? 
		this.setBounds(x,y,width,height);
		this.WIDTH = width;
		Init();
	}
	
	String columns[] = {"RunnerID","RunnerPwd","RunnerName","Location","Vehicle"};
	JTable tableitem = null;
	JScrollPane jsrcollpane; //scrollbar
	DefaultTableModel model;
	TableColumnModel columnModel;
	AddRunnerDao runnerfunc = new AddRunnerDao();
	
	void Init() {
		this.setLayout(null);
		this.setBackground(Color.gray);
		
		JPanel jpanel1 = new JPanel();
		jpanel1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10)); // left alignment
		jpanel1.setBounds(0,0,WIDTH,50);
		jpanel1.setBackground(Color.YELLOW);
		
		JButton addrunnerbutton = new JButton("Add runner");;
		JButton removerunnerbutton = new JButton("Remove runner");
		JButton readrunnerbutton = new JButton("Read runner");
		JButton updaterunnerbutton = new JButton("Update runner");
		
		jpanel1.add(addrunnerbutton);
		jpanel1.add(removerunnerbutton);
		jpanel1.add(readrunnerbutton);
		jpanel1.add(updaterunnerbutton);
		
		JPanel jpanel2 = new JPanel();
		jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		jpanel2.setBounds(0,60,WIDTH,50);
		jpanel2.setBackground(Color.LIGHT_GRAY);
		
		JLabel jlabelid = new JLabel("Runner Id");
		jpanel2.add(jlabelid);
		JTextField jtextfieldid = new JTextField(3);
		jpanel2.add(jtextfieldid);
		JLabel jlabel = new JLabel("Runner Name");
		jpanel2.add(jlabel);
		JTextField jtextfield = new JTextField(10);
		jpanel2.add(jtextfield);
		JLabel jlabelpwd = new JLabel("Password");
		jpanel2.add(jlabelpwd);
		JPasswordField jtextfieldpwd = new JPasswordField(5);
		jpanel2.add(jtextfieldpwd);
		JLabel jlabel2 = new JLabel("Location");
		jpanel2.add(jlabel2);
		JComboBox cmblocation = new JComboBox();
		cmblocation.addItem("--Select Location--");
		cmblocation.addItem("KL");
		cmblocation.addItem("Pahang");
		cmblocation.addItem("Johor");
		jpanel2.add(cmblocation);
		JLabel jlabel3 = new JLabel("Vehicle");
		jpanel2.add(jlabel3);
		JComboBox cmbvehicle = new JComboBox();
		cmbvehicle.addItem("--Select Vehicle--");
		cmbvehicle.addItem("Motor");
		cmbvehicle.addItem("Car");
		cmbvehicle.addItem("Bike");
		jpanel2.add(cmbvehicle);
		
//		
		
		
		this.add(jpanel2);
		this.add(jpanel1);
		table();
		model = Tools.addDataTable(runnerfunc.findRunnerData(), model);
		this.add(jsrcollpane);
		
		// button function 
		
				addrunnerbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String Id = jtextfieldid.getText();
						String Name = jtextfield.getText();
						String Pwd = String.valueOf(jtextfieldpwd.getPassword());
						String Location = cmblocation.getSelectedItem().toString();
						String Vehicle = cmbvehicle.getSelectedItem().toString();
						if((Id+Name+Pwd).equals("")||(cmblocation.getSelectedIndex()+cmbvehicle.getSelectedIndex())==0) {
							JOptionPane.showMessageDialog(null,"Fill Up Runner Info","Invalid Operation",JOptionPane.WARNING_MESSAGE);
						}
						else {
							int existID = runnerfunc.checkRunner(Id);
							if(existID==-1) {
								Runners r = new Runners(Id,Pwd,Name,Location,Vehicle);
								runnerfunc.addRunnerData(r);
								model = Tools.addDataTable(runnerfunc.findRunnerData(), model);
													    
								JOptionPane.showMessageDialog(null,"Add successfully " + Id,"Runner",JOptionPane.WARNING_MESSAGE);
								}
							else {JOptionPane.showMessageDialog(null, Id + " Existed","Runner",JOptionPane.WARNING_MESSAGE);}
							
						}
					}
				});
				readrunnerbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(jtextfieldid.getText().equals("")) {
							
							model = Tools.addDataTable(runnerfunc.findRunnerData(), model);
						}
						else {
							String id = jtextfieldid.getText();
							int existID = runnerfunc.checkRunner(id);
							if(existID==-1) {JOptionPane.showMessageDialog(null,"Invalid: " + id,"Runner",JOptionPane.WARNING_MESSAGE);}
							else {model = Tools.addDataTable(runnerfunc.findRunnerData(id), model);}
						}
					}
				});
				removerunnerbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String id = jtextfieldid.getText();
						if(id.equals("")) {
							JOptionPane.showMessageDialog(null,"Fill Up Runner Id","Invalid Operation",JOptionPane.WARNING_MESSAGE);
						}
						else {
							int existID = runnerfunc.checkRunner(id);
							if(existID==-1) {JOptionPane.showMessageDialog(null,"Invalid: " + id,"Customer",JOptionPane.WARNING_MESSAGE);}
							else {
								runnerfunc.deleteRunnerData(id);
								model = Tools.addDataTable(runnerfunc.findRunnerData(), model);
								JOptionPane.showMessageDialog(null,"Delete successfully " + id,"Customer",JOptionPane.WARNING_MESSAGE);
								}
						}
					}
				});
				updaterunnerbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String Id = jtextfieldid.getText();
						String Name = jtextfield.getText();
						String Pwd = String.valueOf(jtextfieldpwd.getPassword());
						String Location = cmblocation.getSelectedItem().toString();
						String Vehicle = cmbvehicle.getSelectedItem().toString();
						if((Id+Name+Pwd).equals("")||(cmblocation.getSelectedIndex()+cmbvehicle.getSelectedIndex())==0) {
							JOptionPane.showMessageDialog(null,"Fill Up Runner Info","Invalid Operation",JOptionPane.WARNING_MESSAGE);
							
						}
						else {
							int existID = runnerfunc.checkRunner(Id);
							if(existID!=-1) {
								Runners c = new Runners(Id,Pwd,Name,Location,Vehicle);
								runnerfunc.updateRunnerData(c);
								model = Tools.addDataTable(runnerfunc.findRunnerData(), model);
								JOptionPane.showMessageDialog(null,"Update successfully " + Id,"Runner",JOptionPane.WARNING_MESSAGE);
								}
							else {JOptionPane.showMessageDialog(null, Id + " Not Exist","Runner",JOptionPane.WARNING_MESSAGE);}
							
						}
					}
				});
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
		return tableitem;
	}

}
