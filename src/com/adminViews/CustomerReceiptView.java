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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.dao.AddOrderDao;
import com.model.Notifications;
import com.tool.Tools;
import com.model.Receipts;
import java.util.Calendar;
import java.util.Random;

public class CustomerReceiptView extends JPanel {
		int WIDTH;
		int HEIGHT = 150;
		
		public CustomerReceiptView(int x,int y, int width, int height) {
			// separate 2 windows ?????? 
			this.setBounds(x,y,width,height);
			this.WIDTH = width;
			Init();
		}
		
		String columns[] = {"OrderID","CustomerID","ItemID","VendorID","RunnerID","Total","Type","DateTime","Status"};
		JTable tableitem = null;
		JScrollPane jsrcollpane; //scrollbar
		DefaultTableModel model;
		TableColumnModel columnModel;
		AddOrderDao orderfunc = new AddOrderDao();
		
		void Init() {
			this.setLayout(null);
			this.setBackground(Color.gray);
			
			JPanel jpanel1 = new JPanel();
			jpanel1.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10)); // left alignment
			jpanel1.setBounds(0,480,WIDTH,60);
			jpanel1.setBackground(Color.YELLOW);
			
			JButton sendbutton = new JButton("Send Notification");
			sendbutton.setPreferredSize(new Dimension(200,45));
			jpanel1.add(sendbutton);
			
			JPanel jpanel2 = new JPanel();
			jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
			jpanel2.setBounds(0,0,WIDTH,50);
			jpanel2.setBackground(Color.LIGHT_GRAY);
			
                        JLabel jlabelord = new JLabel("Order ID");
			jpanel2.add(jlabelord);
			JComboBox cmbord = new JComboBox(orderfunc.updateComboxOrd().toArray());
			cmbord.insertItemAt("--Select Order--", 0);
			cmbord.setSelectedIndex(0);
			jpanel2.add(cmbord);
			JLabel jlabelcus = new JLabel("Customer ID");
			jpanel2.add(jlabelcus);
			JComboBox cmbcus = new JComboBox(orderfunc.updateComboxCus().toArray());
			cmbcus.insertItemAt("--Select Customer--", 0);
			cmbcus.setSelectedIndex(0);
			jpanel2.add(cmbcus);
			JLabel jlabelvendor = new JLabel("Vendor ID");
			jpanel2.add(jlabelvendor);
			JComboBox cmbvendor = new JComboBox(orderfunc.updateComboxVen().toArray());
			cmbvendor.insertItemAt("--Select Vendor--", 0);
			cmbvendor.setSelectedIndex(0);
			jpanel2.add(cmbvendor);
			JLabel jlabelrunner = new JLabel("Runner ID");
			jpanel2.add(jlabelrunner);
			JComboBox cmbrunner = new JComboBox(orderfunc.updateComboxRun().toArray());
			cmbrunner.insertItemAt("--Select Runner--", 0);
			cmbrunner.setSelectedIndex(0);
			jpanel2.add(cmbrunner);
			
			
			this.add(jpanel2);
			this.add(jpanel1);
			table();
			model = Tools.addDataTable(orderfunc.findOrderData(), model);
			this.add(jsrcollpane);
			cmbord.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			    	if(cmbord.getSelectedIndex()==0) {return;}
                                cmbcus.setSelectedIndex(0);
			    	cmbvendor.setSelectedIndex(0);
			    	cmbrunner.setSelectedIndex(0);
			    	String id = cmbord.getSelectedItem().toString();
			    	model = Tools.addDataTable(orderfunc.findDataByOrder(id), model);
			    }
			});
			cmbcus.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			    	if(cmbcus.getSelectedIndex()==0) {return;}
			    	cmbvendor.setSelectedIndex(0);
			    	cmbrunner.setSelectedIndex(0);
                                cmbord.setSelectedIndex(0);
			    	String id = cmbcus.getSelectedItem().toString();
			    	model = Tools.addDataTable(orderfunc.findDataByCus(id), model);
			    }
			});
			cmbvendor.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			    	if(cmbvendor.getSelectedIndex()==0) {return;}
			    	cmbcus.setSelectedIndex(0);
			    	cmbrunner.setSelectedIndex(0);
			    	String id = cmbvendor.getSelectedItem().toString();
			    	model = Tools.addDataTable(orderfunc.findDataByVen(id), model);
			    }
			});
			cmbrunner.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			    	if(cmbrunner.getSelectedIndex()==0) {return;}
			    	cmbvendor.setSelectedIndex(0);
			    	cmbcus.setSelectedIndex(0);
                                cmbord.setSelectedIndex(0);
			    	String id = cmbrunner.getSelectedItem().toString();
			    	model = Tools.addDataTable(orderfunc.findDataByRun(id), model);
			    }
			});
			sendbutton.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			    	String choice = "Nothing";
			    	if(cmbcus.getSelectedIndex()!=0) {choice="Customer";}
			    	if(cmbvendor.getSelectedIndex()!=0) {choice="Vendor";}
			    	if(cmbrunner.getSelectedIndex()!=0) {choice="Runner";}
                                if(cmbord.getSelectedIndex()!=0) 
                                {
                                    choice="Order";
                                    // generate receipt
                                    Receipts rc = new Receipts(cmbord.getSelectedItem().toString());
                                    // generate notification
                                    Calendar cal = Calendar.getInstance();
                                    String datetime = Tools.formatter.format(cal.getTime());
                                    String cusid = model.getValueAt(0, 1).toString();
                                    String venid = model.getValueAt(0, 3).toString();
                                    String orderid = model.getValueAt(0, 0).toString(); 
                                    Notifications noti = new Notifications(venid,cusid,orderid,"NEW RECEIPT ORDER",datetime);
                                    Tools.writeFile("src/data/receipt.txt", rc.toString()); 
                                    Tools.appendFile("src/data/notifications.txt", noti.toString()); 
                                }
                                JOptionPane.showMessageDialog(null,"Sending "+choice,"Notification",JOptionPane.WARNING_MESSAGE);
                                // show dialog 
                                //ManageAdminView.client.sendMessasge("noti");

			    }
			});
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
			int[] columnWidth = {150,150,150,150,150,150,150,150,150};
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
