package com.vendorViews;

import com.dao.AddCustomerDao;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author khwon
 */
public class OrderStatusView extends JPanel{
        int WIDTH;
	int HEIGHT = 150;
	
	public OrderStatusView(int x,int y, int width, int height) {
		// separate 2 windows ?????? 
		this.setBounds(x,y,width,height);
		this.WIDTH = width;
		Init();
	}
        
        String columns[] = {"Order ID","Ordered On","Customer Name","Runner ID","Subtotal","Status","Actions"}; //Actions** ask ruiyi
	JTable tableitem = null;
	JScrollPane jsrcollpane; //scrollbar
	DefaultTableModel model;
	TableColumnModel columnModel;

	
        
        void Init() {
		
		// layout 
		this.setLayout(null);
		this.setBackground(Color.gray);
                Style style = new Style();
		
		JPanel jpanel1 = new JPanel();
		jpanel1.setLayout(new FlowLayout(FlowLayout.LEFT,10,20)); // left alignment
		jpanel1.setBounds(0,50,WIDTH,50);
		jpanel1.setBackground(Color.YELLOW);

                // add content panel
                JPanel jpanel2 = new JPanel();
                jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		jpanel2.setBounds(0,0,WIDTH,50);
                jpanel2.setBackground(Color.red);
                JLabel notificationlabel = new JLabel("Order Status");
		notificationlabel.setFont(style.title);
		jpanel2.add(notificationlabel);
                
                JPanel jpanel3 = new JPanel();
		jpanel3.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10)); // left alignment
		jpanel3.setBounds(0,50,WIDTH,50);
		jpanel3.setBackground(Color.green);
                
                // add button sample


                
                JLabel jlabelid = new JLabel("Food Id:");
		jpanel1.add(jlabelid);
		JTextField jtextfieldid = new JTextField(3);
		jpanel1.add(jtextfieldid);
                
		JLabel jlabel = new JLabel("Date | Time:");
		jpanel1.add(jlabel);
		JTextField jtextfield = new JTextField(10);
		jpanel1.add(jtextfield);

		JLabel jlabel3 = new JLabel("Status:");
		jpanel1.add(jlabel3);
		JComboBox status = new JComboBox();
		status.addItem("--Select Status--");
		status.addItem("Pending");
		status.addItem("Complete");
		jpanel1.add(status);

                JButton readitembutton = new JButton("Read item");
		jpanel1.add(readitembutton);
                
                
                
                
                
                
                
                
                
                // **** jpanel 1 for selection
                // **** jpanel 2 for table content 
                // if u have better design, just go through ur pattern
                
                this.add(jpanel1);
                this.add(jpanel2);
                this.add(jpanel3);
                table();
                this.add(jsrcollpane);
                
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
}
