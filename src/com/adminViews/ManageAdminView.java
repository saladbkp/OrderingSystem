package com.adminViews;

import com.customerViews.ManageCustomerView;
import static com.customerViews.ManageCustomerView.client;
import com.services.Client;
import com.services.Server;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.tool.Tools;
import com.windows.Login;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageAdminView {
	final int WIDTH = 1300;
	final int HEIGHT = 600;
	final int viewWidth = 1100;
	final int viewHeight = 550;
	
	JFrame jframe = new JFrame();
	FlowLayout flowlayout;
        private Socket socket;
	public static Client client;
	public ManageAdminView() throws IOException{
		Init();
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.validate();
                //startClient("admin");
	}

        void startClient(String username) throws IOException{
            socket = new Socket("localhost",1234);
            client = new Client(socket,username);
            client.listenForMessage();
            client.sendMessasge("init");
        }
	void Init() {
		// title 
		jframe.setLayout(null);
		jframe.setTitle("Administrator View");
		Tools.WindowCenterScreen(WIDTH, HEIGHT, jframe);
		
		JPanel jpanel1 = new JPanel();
		JLayeredPane jpanel2 = new JLayeredPane();
		jpanel1.setBounds(5,5,150,HEIGHT-50);
		jpanel1.setBackground(Color.BLUE);
		jpanel1.setLayout(new FlowLayout(flowlayout.CENTER));
		
//		JButton vendorButton = new JButton("Vendor");
//		JButton customerButton = new JButton("Customer");
//		vendorButton.setPreferredSize(new Dimension(100,30));
//		customerButton.setPreferredSize(new Dimension(100,30));
//		jpanel1.add(vendorButton);
//		jpanel1.add(customerButton);
		
		// add tab of Customer View
		AdminVendorView vendor = new AdminVendorView(0,0,viewWidth,viewHeight);
		// default vendor view
		jpanel2.add(vendor,(Integer)(JLayeredPane.PALETTE_LAYER));
		AdminCustomerView customer = new AdminCustomerView(0,0,viewWidth,viewHeight);
		AdminRunnerView runner = new AdminRunnerView(0,0,viewWidth,viewHeight);
		CustomerTopUpView customertopup = new CustomerTopUpView(0,0,viewWidth,viewHeight);
		CustomerReceiptView customerrpt = new CustomerReceiptView(0,0,viewWidth,viewHeight);
		jpanel2.setBounds(215-50,5,viewWidth,viewHeight);

		// dynamic set button
		String buttonName[] = {"Vendor","Customer","Runner","Top Up","Receipt"};
		
		for(int i=0;i<buttonName.length;i++) {
			JButton btn = new JButton(buttonName[i]);
			btn.setPreferredSize(new Dimension(100,30));
			jpanel1.add(btn);
			btn.setName(buttonName[i]);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jpanel2.removeAll();
					JButton jbl = (JButton)e.getSource();
					if(jbl.getName().equals(buttonName[0])) {
						jpanel2.add(vendor,(Integer)(JLayeredPane.PALETTE_LAYER));
						jpanel2.moveToFront(vendor);
					}
					if(jbl.getName().equals(buttonName[1])) {
						jpanel2.add(customer,(Integer)(JLayeredPane.PALETTE_LAYER));
						jpanel2.moveToFront(customer);
						customer.refreshTable();
					}
					if(jbl.getName().equals(buttonName[2])) {
						jpanel2.add(runner,(Integer)(JLayeredPane.PALETTE_LAYER));
						jpanel2.moveToFront(runner);
					}
					if(jbl.getName().equals(buttonName[3])) {
						jpanel2.add(customertopup,(Integer)(JLayeredPane.PALETTE_LAYER));
						jpanel2.moveToFront(customertopup);
					}
					if(jbl.getName().equals(buttonName[4])) {
						jpanel2.add(customerrpt,(Integer)(JLayeredPane.PALETTE_LAYER));
						jpanel2.moveToFront(customerrpt);
					}
				}
			});
		}
		
		
		jframe.add(jpanel2);
		jframe.add(jpanel1);
		
		jframe.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent  windowEvent) {
                        jframe.dispose();
                        Login login = new Login();
		    }
		});
		
	}
	
	
}
