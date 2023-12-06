package com.adminViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dao.AddCustomerDao;
import com.model.Customers;
import com.style.Style;

public class CustomerTopUpView extends JPanel{
	int WIDTH;
	int HEIGHT = 150;
	public CustomerTopUpView(int x,int y, int width, int height) {
		// separate 2 windows ?????? 
		this.setBounds(x,y,width,height);
		this.WIDTH = width;
		Init();
	}
	AddCustomerDao cusfunc = new AddCustomerDao();
	public static List<Customers> updatearray;
	
	void Init() {
		this.setLayout(null);
		this.setBackground(Color.gray);
		Style style = new Style();
		
		JPanel jpanel2 = new JPanel();
		jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10)); // left alignment
		jpanel2.setBounds(0,0,WIDTH,50);
		jpanel2.setBackground(Color.CYAN);
		// show customer name 
		JLabel cuslabel = new JLabel("Customer Id:");
		jpanel2.add(cuslabel);
		JComboBox cmbcustomer = new JComboBox(cusfunc.updateCombox().toArray());
		cmbcustomer.insertItemAt("--Select Customer--", 0);
		cmbcustomer.setSelectedIndex(0);
		jpanel2.add(cmbcustomer);
		JLabel cuslabel1 = new JLabel("Customer Balance:");
		jpanel2.add(cuslabel1);
		JLabel cuslabel2 = new JLabel("--");
		jpanel2.add(cuslabel2);
		
		JPanel jpanel3 = new JPanel();
		jpanel3.setLayout(new FlowLayout(FlowLayout.LEFT,10,10)); // left alignment
		jpanel3.setBounds(0,60,WIDTH,50);
		jpanel3.setBackground(Color.GREEN);
		JLabel topuplabel = new JLabel("Top Up Section:");
		topuplabel.setFont(style.title);
		jpanel3.add(topuplabel);
		
		
		JPanel jpanel1 = new JPanel();
		jpanel1.setLayout(new FlowLayout(FlowLayout.RIGHT)); 
		jpanel1.setBackground(Color.YELLOW);
		jpanel1.setPreferredSize(new Dimension(200,450));
		jpanel1.setBounds(150,130,350,150);
		

		
		// customer id
		
		JLabel jlabel1 = new JLabel("Customer Name:");
		jlabel1.setPreferredSize(new Dimension(320,35));
		jlabel1.setFont(style.account);
		jpanel1.add(jlabel1);
		
		
		
		JLabel jlabel2 = new JLabel("Balance:");
		jlabel2.setPreferredSize(new Dimension(100,35));
		jlabel2.setFont(style.account);
		jpanel1.add(jlabel2);
				
		JTextField jtextfield2 = new JTextField(15);
		jtextfield2.setFont(style.accounttext);
		jpanel1.add(jtextfield2);
		
		JButton jbutton = new JButton("Add");
		jbutton.setFont(style.ok);
		jbutton.setPreferredSize(new Dimension(210,35));
		jpanel1.add(jbutton);
		
		this.add(jpanel3);
		this.add(jpanel2);
		this.add(jpanel1);
		
		cmbcustomer.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	String id = cmbcustomer.getSelectedItem().toString();
		    	double balance = cusfunc.getCustomerBalance(id);
		    	cuslabel2.setText(balance!=-1?""+balance:"--"); 
		    	jlabel1.setText("Customer Name: "+cusfunc.getCustomerName(id)); 
		    }
		});
		jbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbcustomer.getSelectedIndex()!=0 && !jtextfield2.getText().equals("")) {
					String id = cmbcustomer.getSelectedItem().toString();
					double balance = Double.parseDouble(jtextfield2.getText());
					cusfunc.customerTopUp(id, balance);
					double newbalance = cusfunc.getCustomerBalance(id);
			    	cuslabel2.setText(newbalance!=-1?""+newbalance:"--");
			    	updatearray = cusfunc.findData();
				}
				else {
					JOptionPane.showMessageDialog(null,"Fill up customer or top up amount","Invalid Operation",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
	}

}
