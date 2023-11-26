/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customerViews;

import static com.customerViews.ManageCustomerView.jpanel2;
import com.dao.AddVendorDao;
import com.tool.ImageRender;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author khwon
 */
public class CustomerMenuView extends JPanel{
        int WIDTH;
	int HEIGHT = 150;
        final int viewWidth = 1100;
	final int viewHeight = 550;
        
                 //table
        String columns[] = {"Vendor","ID","Name","Food Type","Delivery fee"};
	JTable tableitem = null;
	JScrollPane jsrcollpane; //scrollbar
	DefaultTableModel model;
	TableColumnModel columnModel;
        AddVendorDao vendorfunc = new AddVendorDao();
        
	public CustomerMenuView(int x,int y, int width, int height) {
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
                JButton proceedbutton = new JButton("Proceed");
		//JButton removeitembutton = new JButton("Remove item");
		//JButton readitembutton = new JButton("Read item");
		//JButton updateitembutton = new JButton("Update item");
                
                // button function 
                
                proceedbutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int selectedRow = tableitem.getSelectedRow();
                        if(selectedRow != -1){
                            String vendorid = tableitem.getModel().getValueAt(selectedRow, 1).toString();
                            //String vendorid = vendorfunc.findVendorID(vendorname);
                            CustomerVendorFoodView vendorfood = new CustomerVendorFoodView(0,0,viewWidth,viewHeight,vendorid);
                            ManageCustomerView.jpanel2.removeAll();
                            ManageCustomerView.jpanel2.add(vendorfood,(Integer)(JLayeredPane.PALETTE_LAYER));
                            ManageCustomerView.jpanel2.moveToFront(vendorfood); 
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Select a vendor","Invalid Operation",JOptionPane.WARNING_MESSAGE);
                        }

                        // stop here 2023/11/14
                    }
                });
                
                jpanel1.add(proceedbutton);
		//jpanel1.add(removeitembutton);
		//jpanel1.add(readitembutton);
		//jpanel1.add(updateitembutton);
                
                // add content panel

                
                //this.add(jpanel2);
                this.add(jpanel1);
                table();
                model = Tools.addDataTable(vendorfunc.findVendorMenuData(), model);
                tableitem.removeColumn(tableitem.getColumnModel().getColumn(1));
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
		tableitem.setRowHeight(50);
		// column 5 is logo 
		tableitem.getColumnModel().getColumn(0).setCellRenderer(new ImageRender());
		int count = columnModel.getColumnCount();

		for(int i=0;i<count;i++) {
			javax.swing.table.TableColumn column = columnModel.getColumn(i);
			column.setPreferredWidth(columnWidth[i]);
		}
		return tableitem;
	}

}
