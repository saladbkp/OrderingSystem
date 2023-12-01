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
import com.style.Style;
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
        Style style = new Style();
        
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
                JLabel orderhistory = new JLabel("Order History");
		orderhistory.setFont(style.title);
                jpanel1.add(orderhistory);
                    // add button sample
                    


                table();
                assignOrderTable();
                this.add(jsrcollpane);
                this.add(jpanel1);
                
                int accountRole = Login.role;
                if(accountRole == 3){
                    JButton reorderbutton = new JButton("Reorder");
                    jpanel1.add(reorderbutton);
                }
                else{
                    // remove vendor and runner info
                    tableitem.removeColumn(tableitem.getColumnModel().getColumn(1));
                    tableitem.removeColumn(tableitem.getColumnModel().getColumn(1));
                }
                
        }
        void assignOrderTable(){
            
            List<Orders> orderlist = addorderdao.findDataByCus(Login.account);;
            switch(Login.role){
                case 2: 
                    orderlist = addorderdao.findDataByVen(Login.account);
                    break;
                case 3: 
                    orderlist = addorderdao.findDataByCus(Login.account);
                    break;
                case 4: 
                    orderlist = addorderdao.findDataByRun(Login.account);
                    break;
                
            }
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
                String Datetime = Tools.formatter.format(orderlist.get(i).getDatetime());
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
