package com.adminViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.dao.AddVendorDao;

public class AdminVendorView extends JPanel {
	int WIDTH;
	int HEIGHT = 150;
	
	public AdminVendorView(int x,int y, int width, int height) {
		// separate 2 windows ?????? 
		this.setBounds(x,y,width,height);
		this.WIDTH = width;
		Init();
	}
	
	String columns[] = {"VendorID","VendorPwd","VendorName","Type","Location","Sample"};
	JTable tableitem = null;
	JScrollPane jsrcollpane; //scrollbar
	DefaultTableModel model;
	TableColumnModel columnModel;
	Vector rows;
	AddVendorDao vendorfunc = new AddVendorDao();
	
	void Init() {
		this.setLayout(null);
		this.setBackground(Color.gray);
		
		JPanel jpanel1 = new JPanel();
		jpanel1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10)); // left alignment
		jpanel1.setBounds(0,0,WIDTH,50);
		jpanel1.setBackground(Color.YELLOW);
		
		JButton addvendorbutton = new JButton("Add vendor");;
		JButton removevendorbutton = new JButton("Remove vendor");
		JButton readvendorbutton = new JButton("Read vendor");
		JButton updatevendorbutton = new JButton("Update vendor");
		
		jpanel1.add(addvendorbutton);
		jpanel1.add(removevendorbutton);
		jpanel1.add(readvendorbutton);
		jpanel1.add(updatevendorbutton);
		
		JPanel jpanel2 = new JPanel();
		jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		jpanel2.setBounds(0,60,WIDTH,50);
		jpanel2.setBackground(Color.LIGHT_GRAY);
		
		JLabel jlabel = new JLabel("Vendor Name");
		jpanel2.add(jlabel);
		JTextField jtextfield = new JTextField(10);
		jpanel2.add(jtextfield);
		// password 
		JLabel jlabelpwd = new JLabel("Password");
		jpanel2.add(jlabelpwd);
		JPasswordField jtextfieldpwd = new JPasswordField(5);
		jpanel2.add(jtextfieldpwd);
		JLabel jlabel1 = new JLabel("Type");
		jpanel2.add(jlabel1);
		JComboBox cmbtype = new JComboBox();
		cmbtype.addItem("--Select Type--");
		cmbtype.addItem("Fast Food");
		cmbtype.addItem("Cafe");
		cmbtype.addItem("Mamak");
		jpanel2.add(cmbtype);
		JLabel jlabel2 = new JLabel("Location");
		jpanel2.add(jlabel2);
		JComboBox cmblocation = new JComboBox();
		cmblocation.addItem("--Select Location--");
		cmblocation.addItem("KL");
		cmblocation.addItem("Pahang");
		cmblocation.addItem("Johor");
		jpanel2.add(cmblocation);
		JLabel jlabel3 = new JLabel("Sample");
		jpanel2.add(jlabel3);
		JButton jbutton = new JButton("Browse");
		jbutton.setPreferredSize(new Dimension(80,25));
		jpanel2.add(jbutton);
		
		
		this.add(jpanel2);
		this.add(jpanel1);
		table();
		this.add(jsrcollpane);
		
		addvendorbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jtextfield.getText().equals("") || jtextfieldpwd.getPassword().length == 0 || cmblocation.getSelectedIndex()==0|| cmbtype.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null,"Fill Up Vendor Info","Invalid Operation",JOptionPane.WARNING_MESSAGE);
					
				}
				else {
					String Name = jtextfield.getText();
					int existID = vendorfunc.checkVendor(Name);
					if(existID==-1) {JOptionPane.showMessageDialog(null,"Add successfully " + Name,"Vendor",JOptionPane.WARNING_MESSAGE);}
					else {JOptionPane.showMessageDialog(null, Name + " Existed","Vendor",JOptionPane.WARNING_MESSAGE);}
					
				}
			}
		});
		
//		cmbox listener
//		cmblocation.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e) {
//				
//			}
//		});
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
		int[] columnWidth = {150,150,150,150,150,150};
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
		rows = new Vector(5); //???? not yet import data
		return tableitem;
	}

	
}
