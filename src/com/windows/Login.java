package com.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.adminViews.ManageAdminView;
import com.customerViews.ManageCustomerView;
import com.dao.LoginDao;
import com.runnerViews.ManageRunnerView;
import com.style.Style;
import com.tool.Tools;
import com.vendorViews.ManageVendorView;

public class Login {
	
	final int WIDTH = 500;
	final int HEIGHT = 330;
	
	String title;
	JFrame jframe = new JFrame();
	FlowLayout flowlayout;
	
	LoginDao login = new LoginDao();
	
	public Login(){
		this.title = "Login";
		Init();
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.validate();
	}
	
	void Init() {
		// title 
		jframe.setTitle(title);
		Tools.WindowCenterScreen(WIDTH, HEIGHT, jframe);
		
		// simple config
		flowlayout = new FlowLayout(flowlayout.CENTER);
		Style style = new Style();
		jframe.setLayout(null);
		
		// background pic
		ImageIcon img = new ImageIcon("src/img/bg.png");
		JLabel bgimg = new JLabel(img);
		bgimg.setBounds(0, 0, WIDTH, HEIGHT);
		
		// panel 1
		JPanel jpanel1 = new JPanel();
		jpanel1.setLayout(flowlayout);
		jpanel1.setBounds(0, 0, WIDTH, 45);
		
		// add textblock - title
		JLabel jlabel1 = new JLabel("APU ORDERING SYSTEM");
		jlabel1.setFont(style.title);
		jlabel1.setForeground(new Color(255,228,181));
		jpanel1.add(jlabel1);
		jpanel1.setOpaque(false);
		
		// panel 2
		JPanel jpanel2 = new JPanel();
		jpanel2.setLayout(flowlayout);
		jpanel2.setBounds(125, 45, 240, 230);
		//jpanel1.setOpaque(false);
		
		// account
		JLabel jlabel2 = new JLabel("Account");
		jlabel2.setFont(style.account);
		jpanel2.add(jlabel2);
				
		JTextField jtextfield = new JTextField(15);
		jtextfield.setFont(style.accounttext);
		jpanel2.add(jtextfield);
		
		// password 
		JLabel jlabel3 = new JLabel("Password");
		jlabel3.setFont(style.account);
		jpanel2.add(jlabel3);
		
		JPasswordField jtextfield1 = new JPasswordField(15);
		jtextfield1.setFont(style.accounttext);
		jpanel2.add(jtextfield1);
		
		// role 
		JRadioButton r1=new JRadioButton("Admin");    
		JRadioButton r2=new JRadioButton("Vendor");   
		JRadioButton r3=new JRadioButton("Customer");   
		JRadioButton r4=new JRadioButton("Delivery Runner");   
		r1.setBounds(75,50,100,30);    
		r2.setBounds(75,100,100,30);    
		r3.setBounds(75,100,100,30);    
		r4.setBounds(75,100,100,30);    
		ButtonGroup bg=new ButtonGroup();    
		bg.add(r1);bg.add(r2); bg.add(r3);  bg.add(r4);     
		jpanel2.add(r1);      
		jpanel2.add(r2);      
		jpanel2.add(r3);       
		jpanel2.add(r4);       
		
		// button
		JButton jbutton = new JButton("Login");
		jbutton.setFont(style.ok);
		jbutton.setPreferredSize(new Dimension(210,35));
		jpanel2.add(jbutton);
		
		// button function 
		jbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String account = jtextfield.getText();
				String password = String.valueOf(jtextfield1.getPassword());
				int role = -1;
				if(r1.isSelected()) role = 1;
				if(r2.isSelected()) role = 2;
				if(r3.isSelected()) role = 3;
				if(r4.isSelected()) role = 4;

				
				int userposition = login.checkLogin(account,password,role);
				
				if(userposition==-1) {JOptionPane.showMessageDialog(null, "Invalid Account");}
				else {
					System.out.println("My role: "+role);
					switch(role) {
					case 1:
						jframe.dispose();
						ManageAdminView manageAdmin = new ManageAdminView();
						break;
                                        case 2:
						jframe.dispose();
						ManageVendorView manageVendor = new ManageVendorView();
						break;
                                        case 3:
						jframe.dispose();
						ManageCustomerView managecustomer = new ManageCustomerView();
						break;
                                        case 4:
						jframe.dispose();
						ManageRunnerView manageRunner = new ManageRunnerView();
						break;
					}
                                        
                                        
					
				}

			}
		});
		
		// forget password / register
		
		// Menubar
//		JMenuBar menubar = new JMenuBar();
//		Jmenu menu = new JMenu("Logout");
//		Jmenu menu = new JMenu("");
//		Jemnu 
		
		// add panel in window
		jframe.add(jpanel2); 
		jframe.add(jpanel1); 
		jframe.add(bgimg); 
		
		
	}
	

}