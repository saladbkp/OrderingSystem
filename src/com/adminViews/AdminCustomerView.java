package com.adminViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

import com.dao.AddCustomerDao;
import com.model.Customers;
import com.tool.Tools;

public class AdminCustomerView extends JPanel {
	int WIDTH;
	int HEIGHT = 150;
	
	public AdminCustomerView(int x,int y, int width, int height) {
		// separate 2 windows ?????? 
		this.setBounds(x,y,width,height);
		this.WIDTH = width;
		Init();
	}
	
	String columns[] = {"CustomerID","CustomerPwd","CustomerName","Balance","Gender","Phone No","Address"};
	JTable tableitem = null;
	JScrollPane jsrcollpane; //scrollbar
	DefaultTableModel model;
	TableColumnModel columnModel;
	AddCustomerDao customerfunc = new AddCustomerDao();
	
	void Init() {
		this.setLayout(null);
		this.setBackground(Color.gray);
		
		JPanel jpanel1 = new JPanel();
		jpanel1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10)); // left alignment
		jpanel1.setBounds(0,0,WIDTH,50);
		jpanel1.setBackground(Color.YELLOW);
		
		JButton addcusbutton = new JButton("Add customer");;
		JButton removecusbutton = new JButton("Remove customer");
		JButton readcusbutton = new JButton("Read customer");
		JButton updatecusbutton = new JButton("Update customer");
		
		jpanel1.add(addcusbutton);
		jpanel1.add(removecusbutton);
		jpanel1.add(readcusbutton);
		jpanel1.add(updatecusbutton);
		
		JPanel jpanel2 = new JPanel();
		jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		jpanel2.setBounds(0,60,WIDTH,50);
		jpanel2.setBackground(Color.LIGHT_GRAY);
		
		JLabel jlabelid = new JLabel("Customer Id");
		jpanel2.add(jlabelid);
		JTextField jtextfieldid = new JTextField(3);
		jpanel2.add(jtextfieldid);
		JLabel jlabel = new JLabel("Customer Name");
		jpanel2.add(jlabel);
		JTextField jtextfield = new JTextField(10);
		jpanel2.add(jtextfield);
		JLabel jlabelpwd = new JLabel("Password");
		jpanel2.add(jlabelpwd);
		JPasswordField jtextfieldpwd = new JPasswordField(5);
		jpanel2.add(jtextfieldpwd);
		JLabel jlabel3 = new JLabel("Gender");
		jpanel2.add(jlabel3);
		JComboBox cmbgender = new JComboBox();
		cmbgender.addItem("--Select Gender--");
		cmbgender.addItem("Male");
		cmbgender.addItem("Female");
		jpanel2.add(cmbgender);
		JLabel jlabelpn = new JLabel("Phone No");
		jpanel2.add(jlabelpn);
		JTextField jtextfieldpn = new JTextField(10);
		jpanel2.add(jtextfieldpn);
                JLabel jlabeladdress = new JLabel("Address");
		jpanel2.add(jlabeladdress);
                JComboBox cmblocation = new JComboBox();
		cmblocation.addItem("--Select Address--");
		cmblocation.addItem("kepong");
		cmblocation.addItem("pj");
		jpanel2.add(cmblocation);
                
		this.add(jpanel2);
		this.add(jpanel1);
		table();

		model = Tools.addDataTable(customerfunc.findCustomerData(), model);
		this.add(jsrcollpane);
		
		// button function 
		
		addcusbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Id = jtextfieldid.getText();
				String Name = jtextfield.getText();
				String Pwd = String.valueOf(jtextfieldpwd.getPassword());
				String Gender = cmbgender.getSelectedItem().toString();
                                String Phoneno =jtextfieldpn.getText();
                                String Address = cmblocation.getSelectedItem().toString();
				if((Id+Name+Pwd+Phoneno).equals("")||cmbgender.getSelectedIndex()+cmblocation.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null,"Fill Up Custoemr Info","Invalid Operation",JOptionPane.WARNING_MESSAGE);
				}
				else {
					int existID = customerfunc.checkCustomer(Id);
					if(existID==-1) {
						Customers c = new Customers(Id,Pwd,Name,0,Gender,Integer.parseInt(Phoneno),Address);
						customerfunc.addCustomerData(c);
						model = Tools.addDataTable(customerfunc.findCustomerData(), model);
											    
						JOptionPane.showMessageDialog(null,"Add successfully " + Id,"Customer",JOptionPane.WARNING_MESSAGE);
						}
					else {JOptionPane.showMessageDialog(null, Id + " Existed","Customer",JOptionPane.WARNING_MESSAGE);}
					
				}
			}
		});
		readcusbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jtextfieldid.getText().equals("")) {
					
					model = Tools.addDataTable(customerfunc.findCustomerData(), model);
				}
				else {
					String id = jtextfieldid.getText();
					int existID = customerfunc.checkCustomer(id);
					if(existID==-1) {JOptionPane.showMessageDialog(null,"Invalid: " + id,"Customer",JOptionPane.WARNING_MESSAGE);}
					else {model = Tools.addDataTable(customerfunc.findCustomerData(id), model);}
				}
			}
		});
		removecusbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = jtextfieldid.getText();
				if(id.equals("")) {
					JOptionPane.showMessageDialog(null,"Fill Up Customer Id","Invalid Operation",JOptionPane.WARNING_MESSAGE);
				}
				else {
					int existID = customerfunc.checkCustomer(id);
					if(existID==-1) {JOptionPane.showMessageDialog(null,"Invalid: " + id,"Customer",JOptionPane.WARNING_MESSAGE);}
					else {
						customerfunc.deleteCustomerData(id);
						model = Tools.addDataTable(customerfunc.findCustomerData(), model);
						JOptionPane.showMessageDialog(null,"Delete successfully " + id,"Customer",JOptionPane.WARNING_MESSAGE);
						}
				}
			}
		});
		updatecusbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Id = jtextfieldid.getText();
				String Name = jtextfield.getText();
				String Pwd = String.valueOf(jtextfieldpwd.getPassword());
				String Gender = cmbgender.getSelectedItem().toString();
                                String Phoneno = jtextfieldpn.getText();
                                String Address = cmblocation.getSelectedItem().toString();
				if((Id+Name+Pwd+Phoneno).equals("")||cmbgender.getSelectedIndex()+cmblocation.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null,"Fill Up Customer Info","Invalid Operation",JOptionPane.WARNING_MESSAGE);
					
				}
				else {
					int existID = customerfunc.checkCustomer(Id);
					if(existID!=-1) {
						Customers c = new Customers(Id,Pwd,Name,0,Gender,Integer.parseInt(Phoneno),Address);
						customerfunc.updateCustomerData(c);
						model = Tools.addDataTable(customerfunc.findCustomerData(), model);
						JOptionPane.showMessageDialog(null,"Update successfully " + Id,"Customer",JOptionPane.WARNING_MESSAGE);
						}
					else {JOptionPane.showMessageDialog(null, Id + " Not Exist","Customer",JOptionPane.WARNING_MESSAGE);}
					
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
		int[] columnWidth = {150,150,150,150,150,150,150};
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
	
	void refreshTable() {
		List<Customers> updatearray = CustomerTopUpView.updatearray;
		if(updatearray!=null) {
			for(int i=0;i<updatearray.size();i++) {
				customerfunc.updateCustomerData(updatearray.get(i));
			}
		}
		model = Tools.addDataTable(customerfunc.findCustomerData(), model);
		
	}
}
