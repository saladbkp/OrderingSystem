/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customerViews;

import com.dao.AddItemDao;
import com.tool.TableRowFilter;
import com.tool.TableWithButtons;
import com.vendorViews.*;
import com.tool.Tools;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ray
 */
public class CustomerCartView extends JPanel{
        int WIDTH;
	int HEIGHT = 150;
	
                         //table
        TableRowSorter tableSorter;
        String columns[] = {"Food","Cost", "Amount","Action"};
        JTable tableitem = null;
	JScrollPane jsrcollpane; //scrollbar
        TableModel tableModel;
	DefaultTableModel model;
	TableColumnModel columnModel;
        AddItemDao itemfunc = new AddItemDao();

	public CustomerCartView(int x,int y, int width, int height, TableModel tableModel) {
		// separate 2 windows ?????? 
		this.setBounds(x,y,width,height);
		this.WIDTH = width;
                this.tableModel = tableModel;
		Init();
	}
        void Init() {
		
		// layout 
		this.setLayout(null);
		this.setBackground(Color.gray);
		
//		JPanel jpanel1 = new JPanel();
//		jpanel1.setLayout(new FlowLayout(FlowLayout.LEFT,50,10)); // left alignment
//		jpanel1.setBounds(0,0,WIDTH,50);
//		jpanel1.setBackground(Color.WHITE);
//                
//                // add button sample
//                JLabel jlabelfood = new JLabel("Food");
//		jpanel1.add(jlabelfood);
//                JLabel jlabelvendor = new JLabel("Vendor");
//		jpanel1.add(jlabelvendor);
//                JLabel jlabelcost = new JLabel("Cost");
//		jpanel1.add(jlabelcost);
//                JLabel jlabelamount = new JLabel("Amount");
//		jpanel1.add(jlabelamount);
//                // add content panel
                JPanel jpanel2 = new JPanel();
                jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		jpanel2.setBounds(0,200,WIDTH,50);
                
                JLabel jlabelfoodservice= new JLabel("Food Service:");
		jpanel2.add(jlabelfoodservice);
                JComboBox cmbfoodservice = new JComboBox();
		cmbfoodservice.addItem("--Select Food Service--");
		cmbfoodservice.addItem("Dine-in");
		cmbfoodservice.addItem("Takeaway");
                                   cmbfoodservice.addItem("Delivery");
		jpanel2.add(cmbfoodservice);
                
                JPanel jpanel3 = new JPanel();
                jpanel3.setLayout(new FlowLayout(FlowLayout.LEFT,70,10));
		jpanel3.setBounds(0,260,WIDTH,50);
                JLabel jlabelsubtotal= new JLabel("Subtotal");
		jpanel3.add(jlabelsubtotal);
                
                JPanel jpanel4 = new JPanel();
                jpanel4.setLayout(new FlowLayout(FlowLayout.LEFT,70,10));
		jpanel4.setBounds(0,300,WIDTH,50);
                JLabel jlabeldeliveryfee= new JLabel("Delivery fee");
		jpanel4.add(jlabeldeliveryfee);
                
                JPanel jpanel5 = new JPanel();
                jpanel5.setLayout(new FlowLayout(FlowLayout.LEFT,70,10));
		jpanel5.setBounds(0,360,WIDTH,50);
                JLabel jlabeltotalcost= new JLabel("Total cost");
		jpanel5.add(jlabeltotalcost);
                
                JPanel jpanel6 = new JPanel();
//                jpanel6.setLayout(new FlowLayout(FlowLayout.LEFT,70,10));
		jpanel6.setBounds(0,420,WIDTH,50);
                jpanel6.setBackground(Color.YELLOW);
                JButton paybutton = new JButton("Pay");
                JButton backbutton = new JButton("Back");
		jpanel6.add(paybutton,new FlowLayout(FlowLayout.LEFT,70,10));
		jpanel6.add(backbutton,new FlowLayout(FlowLayout.RIGHT,70,10));
                
                
                
                // **** jpanel 1 for selection
                // **** jpanel 2 for table content 
                // if u have better design, just go through ur pattern
                //this.add(jpanel1);
                this.add(jpanel2);
                this.add(jpanel3);
                this.add(jpanel4);
                this.add(jpanel5);
                this.add(jpanel6);
                tablefood();
                model = (DefaultTableModel)tableModel;
                tableitem.setModel(model);

                // remove action column
                tableitem.removeColumn(tableitem.getColumnModel().getColumn(3));
                //model = Tools.addDataTableWithButton(itemfunc.findFoodByVendor("v1"),tableitem, model);
                tableSorter = new TableRowSorter(model);
                tableitem.setRowSorter(tableSorter);
                tableSorter.setRowFilter(new TableRowFilter(2,0));
                this.add(jsrcollpane);
        }
          void tablefood() {
		tableitem = TableSetup();
                jsrcollpane = new JScrollPane(tableitem);
		jsrcollpane.setPreferredSize(new Dimension(WIDTH-20,250));
		tableitem.setPreferredSize(new Dimension(WIDTH-30,1000));
		//jsrcollpane.setVerticalScrollBarPoicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsrcollpane.setBounds(0,10,WIDTH-10,180);
                
	}

         //String [] columnname
	JTable TableSetup() {
		tableitem = new JTable();
		int[] columnWidth = {150,150,150,150};
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
                                return column!=3?false:true;
			}
		};
            
		model.setColumnIdentifiers(columns);
                tableitem.setModel(model);
		columnModel = tableitem.getColumnModel();
		tableitem.getTableHeader().setReorderingAllowed(false);
		tableitem.getTableHeader().setResizingAllowed(false);
                tableitem.setRowHeight(30);
                // add button
                TableWithButtons.initializeTableWithButtons(tableitem);
                
		int count = columnModel.getColumnCount();
		for(int i=0;i<count;i++) {
			javax.swing.table.TableColumn column = columnModel.getColumn(i);
			column.setPreferredWidth(columnWidth[i]);
		}
		return tableitem;
	}
}