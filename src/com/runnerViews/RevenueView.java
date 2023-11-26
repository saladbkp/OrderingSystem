/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.runnerViews;



import com.runnerdao.AddTaskDao;
import com.runnerdao.ViewRevenueDao;
import com.style.Style;
import com.tool.Tools;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author khwon
 */
public class RevenueView extends JPanel{
        int WIDTH;
	int HEIGHT = 150;
        
        String columns[] = {"Date"," Earnings","Deduction","Total"};
        JTable tasktable = null;
        JScrollPane jscrollpane;
        DefaultTableModel model;
        TableColumnModel columnModel;
        ViewRevenueDao viewrevenue = new ViewRevenueDao(); 
	Style style = new Style();
        
	public RevenueView (int x,int y, int width, int height) {
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
                
                JLabel revenuelabel = new JLabel("Revenue ");
		revenuelabel.setFont(style.title);
                
                // add button sample
                JButton acceptrevenuebutton= new JButton("Search");
		
		
                //add combo box
                JComboBox cmb2 = new JComboBox();
                cmb2.addItem("Daily Revenue");
                cmb2.addItem("Monthly Revenue");
                cmb2.addItem("Annual Revenue");
		
                
                // add content panel
                
                jpanel1.add(revenuelabel);
                
                JPanel jpanel2 = new JPanel();
                jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		jpanel2.setBounds(0,60,WIDTH,50);
                jpanel2.add(cmb2);
                jpanel2.add(acceptrevenuebutton);
                // **** jpanel 1 for selection
                // **** jpanel 2 for table content 
                // if u have better design, just go through ur pattern
                
                this.add(jpanel2);
		this.add(jpanel1);
                table();
                model = Tools.addDataTable(viewrevenue.findTaskData(), model);
             
                this.add(jscrollpane);
        }
        void table() {
		tasktable = TableSetup();
		jscrollpane = new JScrollPane(tasktable);
		jscrollpane.setPreferredSize(new Dimension(WIDTH-20,250));
		tasktable.setPreferredSize(new Dimension(WIDTH-30,1000));
		//jsrcollpane.setVerticalScrollBarPoicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jscrollpane.setBounds(0,120,WIDTH-20,420);
	}
	
	JTable TableSetup() {
		tasktable = new JTable();
		int[] columnWidth = {150,150,150,150,150};
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.setColumnIdentifiers(columns);
		tasktable.setModel(model);
		columnModel = tasktable.getColumnModel();
		tasktable.getTableHeader().setReorderingAllowed(false);
		tasktable.getTableHeader().setResizingAllowed(false);
		int count = columnModel.getColumnCount();
		for(int i=0;i<count;i++) {
			javax.swing.table.TableColumn column = columnModel.getColumn(i);
			column.setPreferredWidth(columnWidth[i]);
		}
		return tasktable;
	}
}
