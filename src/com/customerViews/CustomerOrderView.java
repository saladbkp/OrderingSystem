/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customerViews;

import com.dao.AddCustomerDao;
import com.dao.AddItemDao;
import com.dao.AddOrderDao;
import com.dao.AddRunnerDao;
import com.dao.AddVendorDao;
import com.model.Items;
import com.model.Orders;
import com.model.Runners;
import com.model.Vendors;
import com.vendorViews.*;
import com.tool.Tools;
import com.windows.Login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
        
	String columns[] = {"Food","Vendor","Runner","Quantity", "Food service","Time", "Status", "Total cost"};
	JTable tableitem = null;
	JScrollPane jsrcollpane; //scrollbar
	DefaultTableModel model;
	TableColumnModel columnModel;
        AddCustomerDao addcustomerdao = new AddCustomerDao();
        AddVendorDao addvendordao = new AddVendorDao();
        AddRunnerDao addrunnerdao = new AddRunnerDao();
        AddOrderDao addorderdao = new AddOrderDao();
        AddItemDao additemdao = new AddItemDao();
    
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
                assignOrderTable();
                this.add(jsrcollpane);
                this.add(jpanel1);
        }
        void assignOrderTable(){
            List<Orders> orderlist = addorderdao.findDataByCus(Login.account);
            for (int i=0;i<orderlist.size();i++){
                String itemid = orderlist.get(i).getItemId();
                String vendorid = orderlist.get(i).getVendorId();
                String runnerid = orderlist.get(i).getRunnerId();
                Items item = additemdao.findDataByItem(itemid).get(0);
                Vendors vendor = addvendordao.findDataByID(vendorid).get(0);
                Runners runner = addrunnerdao.findDataByID(runnerid).get(0);
                String foodName = item.getItemName();
                Float itemPrice = item.getPrice();
                String vendorName = vendor.getUsername();
                String runnerName = runner.getUsername();
                int Quantity = orderlist.get(i).getQuantity();
                java.util.Date Datetime = orderlist.get(i).getDatetime();
                String serviceType = orderlist.get(i).getType();
                String status = orderlist.get(i).getStatus().equals("1")?"Success":"Failed";
                Float total = itemPrice*Quantity;
                Object[] row = { foodName, vendorName, runnerName, Quantity,serviceType,Datetime, status,total};
                
                model.addRow(row);
            }
            
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
		int[] columnWidth = {150,150,150,100,150,150,100,150};
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
