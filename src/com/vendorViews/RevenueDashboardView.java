package com.vendorViews;

import com.style.Style;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author khwon
 */
public class RevenueDashboardView extends JPanel{
        int WIDTH;
	int HEIGHT = 150;
	
	public RevenueDashboardView(int x,int y, int width, int height) {
		// separate 2 windows ?????? 
		this.setBounds(x,y,width,height);
		this.WIDTH = width;
		Init();
	}
        
//        String columns[] = {"Order ID","Ordered On","Customer Name","Runner ID","Subtotal","Status","Actions"}; //Actions** ask ruiyi
//	JTable tableitem = null;
//	JScrollPane jsrcollpane; //scrollbar
//	DefaultTableModel model;
//	TableColumnModel columnModel;

        
        void Init() {
		
		// layout 
		this.setLayout(null);
		this.setBackground(Color.gray);
                Style style = new Style();
		

                // add content panel
                JPanel jpanel1 = new JPanel();
                jpanel1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		jpanel1.setBounds(0,0,WIDTH,50);
                jpanel1.setBackground(Color.red);
                JLabel notificationlabel = new JLabel("Revenue Dashboard");
		notificationlabel.setFont(style.title);
		jpanel1.add(notificationlabel);
                
                JPanel jpanel2 = new JPanel();
		jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT,20,10)); // left alignment
		jpanel2.setBounds(0,50,WIDTH,50);
		jpanel2.setBackground(Color.green);
                
                JPanel jpanel3 = new JPanel();
		jpanel3.setLayout(new FlowLayout(FlowLayout.LEFT,10,20)); // left alignment
		jpanel3.setBounds(50,150,250,275);
		jpanel3.setBackground(Color.YELLOW);
                JLabel TotalRevenue = new JLabel("TotalRevenue");
		TotalRevenue.setFont(style.title);
		jpanel3.add(TotalRevenue);
                
                JPanel jpanel4 = new JPanel();
		jpanel4.setLayout(new FlowLayout(FlowLayout.LEFT,10,20)); // left alignment
		jpanel4.setBounds(350,150,250,275);
		jpanel4.setBackground(Color.YELLOW);
                JLabel OrderPerMonth = new JLabel("Order /Month");
		OrderPerMonth.setFont(style.title);
		jpanel4.add(OrderPerMonth);
                
                JPanel jpanel5 = new JPanel();
		jpanel5.setLayout(new FlowLayout(FlowLayout.LEFT,10,20)); // left alignment
		jpanel5.setBounds(650,150,400,275);
		jpanel5.setBackground(Color.YELLOW);
                JLabel graph = new JLabel("Graph");
		graph.setFont(style.title);
		jpanel5.add(graph);
                
                // add button sample


                
//                JLabel jlabelid = new JLabel("Food Id:");
//		jpanel2.add(jlabelid);
//		JTextField jtextfieldid = new JTextField(3);
//		jpanel2.add(jtextfieldid);
//                
//		JLabel jlabel = new JLabel("Date | Time:");
//		jpanel2.add(jlabel);
//		JTextField jtextfield = new JTextField(10);
//		jpanel2.add(jtextfield);
                
                JLabel jlabel1 = new JLabel("Month:");
		jpanel2.add(jlabel1);
		JComboBox MonthlySales = new JComboBox();
		MonthlySales.addItem("--Select Month--");
		MonthlySales.addItem("January");
		MonthlySales.addItem("February");
                MonthlySales.addItem("March");
		MonthlySales.addItem("April");
                MonthlySales.addItem("May");
		MonthlySales.addItem("June");
                MonthlySales.addItem("July");
		MonthlySales.addItem("August");
                MonthlySales.addItem("September");
		MonthlySales.addItem("October");
                MonthlySales.addItem("Novmeber");
		MonthlySales.addItem("December");
		jpanel2.add(MonthlySales);
                




                JButton readitembutton = new JButton("Read ");
		jpanel2.add(readitembutton);
                
                
                
                
                
                
                
                
                
                // **** jpanel 1 for selection
                // **** jpanel 2 for table content 
                // if u have better design, just go through ur pattern
                
                this.add(jpanel1);
                this.add(jpanel2);
                this.add(jpanel3);
                this.add(jpanel4);
                this.add(jpanel5);

                
                
//                table();
//                this.add(jsrcollpane);
                
        }
//        void table() {
//		tableitem = TableSetup();
//		jsrcollpane = new JScrollPane(tableitem);
//		jsrcollpane.setPreferredSize(new Dimension(WIDTH-20,250));
//		tableitem.setPreferredSize(new Dimension(WIDTH-30,1000));
//		//jsrcollpane.setVerticalScrollBarPoicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//		jsrcollpane.setBounds(0,120,WIDTH-20,420);
//	}
//	
//	JTable TableSetup() {
//		tableitem = new JTable();
//		int[] columnWidth = {150,150,150,150,150,150,150};
//		model = new DefaultTableModel() {
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
//		};
//		model.setColumnIdentifiers(columns);
//		tableitem.setModel(model);
//		columnModel = tableitem.getColumnModel();
//		tableitem.getTableHeader().setReorderingAllowed(false);
//		tableitem.getTableHeader().setResizingAllowed(false);
//		int count = columnModel.getColumnCount();
//		for(int i=0;i<count;i++) {
//			javax.swing.table.TableColumn column = columnModel.getColumn(i);
//			column.setPreferredWidth(columnWidth[i]);
//		}
//		return tableitem;
//        }

}