/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.runnerViews;

import com.runnerdao.AddTaskDao;
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
import com.style.Style;
import javax.swing.JDialog;
/**
 *
 * @author khwon
 */
public class RunnerTaskView extends JPanel{
        int WIDTH;
	int HEIGHT = 150;
        final int viewWidth = 1100;
	final int viewHeight = 550;
	
        String columns[] = {"Order ID","VendorID","RunnerID","VendorName","FoodID","FoodName","Amount","Service Type","TotalCost","Time","Status"};
        JTable tasktable = null;
        JScrollPane jscrollpane;
        DefaultTableModel model;
        TableColumnModel columnModel;
        AddTaskDao runnerfunc = new AddTaskDao(); 
        Style style = new Style();
	public RunnerTaskView(int x,int y, int width, int height) {
		// separate 2 windows ?????? 
		this.setBounds(x,y,width,height);
		this.WIDTH = width;
		Init();
	}
        public static JLayeredPane jpanel1;
        public static JLayeredPane jpanel2;
        void Init() {
		
		// layout 
		this.setLayout(null);
		this.setBackground(Color.gray);
		
		JPanel jpanel1 = new JPanel();
		jpanel1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10)); // left alignment
		jpanel1.setBounds(0,0,WIDTH,50);
		jpanel1.setBackground(Color.YELLOW);
                
                JPanel jpanel2 = new JPanel();
                
                // add button sample
                JButton removeitembutton = new JButton("Decline");
                JButton updateitembutton = new JButton("Update");
                JButton additembutton = new JButton("Accept");
                
		
                
                JLabel topuplabel = new JLabel("Task");
		topuplabel.setFont(style.title);
		
                //add combo box
                JComboBox cmb = new JComboBox();
                cmb.addItem("Preparing Food");
                cmb.addItem("Delivering Food");
                cmb.addItem("Settled");
                
                
		
		jpanel1.add(topuplabel);
		
		
                
                // add content panel
                
                jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		jpanel2.setBounds(0,60,WIDTH,50);
                jpanel2.add(cmb);
                jpanel2.add(updateitembutton);
                jpanel2.add(additembutton);
		jpanel2.add(removeitembutton);
                // **** jpanel 1 for selection
                // **** jpanel 2 for table content 
                // if u have better design, just go through ur pattern
                
                
                this.add(jpanel2);
		this.add(jpanel1);
                table();
                model = Tools.addDataTable(runnerfunc.findTaskData(), model);
                
                this.add(jscrollpane);
               additembutton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Create an instance of AcceptTaskView
        AcceptTaskView accepttask = new AcceptTaskView(0, 0, viewWidth, viewHeight);

        // Create a JDialog to display the AcceptTaskView
        JDialog dialog = new JDialog();
        dialog.setSize(viewWidth, viewHeight);
        dialog.setContentPane(accepttask);
        dialog.setLocationRelativeTo(null);  // Center the dialog on the screen
        dialog.setModal(true);  // Make the dialog modal (blocks user input to other windows)

        // Display the dialog
        dialog.setVisible(true);
    }
});
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
		int[] columnWidth = {150,150,150,150,150,150,150,150,150,150,150};
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
