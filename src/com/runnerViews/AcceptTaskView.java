/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.runnerViews;

import com.runnerdao.AddTaskDao;
import com.style.Style;
import com.tool.Tools;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ivann
 */
public class AcceptTaskView extends JPanel{
        int WIDTH;
	int HEIGHT = 150;
	
        String columns[] = {"Order ID","VendorID","RunnerID","VendorName","FoodID","FoodName","Amount","Service Type","TotalCost","Time","Status"};
        JTable tasktable = null;
        JScrollPane jscrollpane;
        DefaultTableModel model;
        TableColumnModel columnModel;
        AddTaskDao runnerfunc = new AddTaskDao(); 
        Style style = new Style();
	public AcceptTaskView(int x,int y, int width, int height) {
		// separate 2 windows ?????? 
		this.setBounds(x,y,width,height);
		this.WIDTH = width;
		Init();
	}
        public static JLayeredPane jpanel2;
        void Init() {
		
		// layout 
		this.setLayout(null);
		this.setBackground(Color.gray);
		
		JPanel jpanel1 = new JPanel();
		jpanel1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10)); // left alignment
		jpanel1.setBounds(0,0,WIDTH,50);
		jpanel1.setBackground(Color.YELLOW);
                
                // add button sample
                JButton additembutton = new JButton("Accept");
		JButton removeitembutton = new JButton("Decline");
                JButton updateitembutton = new JButton("Update");
                
                JLabel topuplabel = new JLabel("Accepted Task");
		topuplabel.setFont(style.title);
		
                //add combo box
                JComboBox cmb = new JComboBox();
                cmb.addItem("Preparing Food");
                cmb.addItem("Delivering Food");
                cmb.addItem("Settled");
                
                
		
		jpanel1.add(topuplabel);
		
		
                
                // add content panel
                JPanel jpanel2 = new JPanel();
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
                // Action listener for the "Update" button
            updateitembutton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            // Get the selected value from the combo box
            String selectedStatus = cmb.getSelectedItem().toString();

            // Get the selected row
            int selectedRow = tasktable.getSelectedRow();

            // Check if a row is selected
            if (selectedRow != -1) {
                // Assuming the "Status" column is the 10th column (index 9)
                model.setValueAt(selectedStatus, selectedRow, 10);
            } else {
                // Show a message if no row is selected
                JOptionPane.showMessageDialog(null, "Please select a row to update.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
            }
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

