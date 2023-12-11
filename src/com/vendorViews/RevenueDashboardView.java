package com.vendorViews;

import com.dao.AddTransactionDao;
import com.model.Transactions;
import com.style.Style;
import com.tool.Tools;
import com.windows.Login;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author khwon
 */
public class RevenueDashboardView extends JPanel{
        int WIDTH;
	int HEIGHT = 150;
	AddTransactionDao transfuc = new AddTransactionDao();
	public RevenueDashboardView(int x,int y, int width, int height) {
		// separate 2 windows ?????? 
		this.setBounds(x,y,width,height);
		this.WIDTH = width;
		Init();
	}

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
                jpanel3.setLayout(new BoxLayout(jpanel3, BoxLayout.Y_AXIS));
                JLabel TotalRevenue = new JLabel("TotalRevenue");
		TotalRevenue.setFont(style.title);
		jpanel3.add(TotalRevenue);
                
                JPanel jpanel4 = new JPanel();
		jpanel4.setLayout(new FlowLayout(FlowLayout.LEFT,10,20)); // left alignment
		jpanel4.setBounds(350,150,250,275);
		jpanel4.setBackground(Color.YELLOW);
                jpanel4.setLayout(new BoxLayout(jpanel4, BoxLayout.Y_AXIS));
                JLabel OrderPerMonth = new JLabel("Order /Month");
		OrderPerMonth.setFont(style.title);
		jpanel4.add(OrderPerMonth);
                
//                JPanel jpanel5 = new JPanel();
//		jpanel5.setLayout(new FlowLayout(FlowLayout.LEFT,10,20)); // left alignment
//		jpanel5.setBounds(650,150,400,275);
//		jpanel5.setBackground(Color.YELLOW);
//                jpanel5.setLayout(new BoxLayout(jpanel5, BoxLayout.Y_AXIS));
//                JLabel graph = new JLabel("Graph");
//		graph.setFont(style.title);
//		jpanel5.add(graph);
                
                // add button sample
                
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
                
                this.add(jpanel1);
                this.add(jpanel2);
                this.add(jpanel3);
                this.add(jpanel4);
//                this.add(jpanel5);

                JLabel TotalRevenueValue = new JLabel("RM: 0");
                TotalRevenueValue.setFont(style.account);
                jpanel3.add(TotalRevenueValue);
                JLabel TotalOrderValue = new JLabel("0 order");
                TotalOrderValue.setFont(style.account);
                jpanel4.add(TotalOrderValue);
                
                String account = Login.account;                

                
                MonthlySales.addActionListener (new ActionListener () {
                public void actionPerformed(ActionEvent e) {
                        List<Transactions> translist  = transfuc.findDataByID(account);    
                        translist  = transfuc.findDataByPeriod(translist,MonthlySales.getSelectedIndex());
                        System.out.println(MonthlySales.getSelectedIndex());
                        double totalRevenue = 0;
                        int totalOrder = translist.size();
                        if(totalOrder>0){
                            for(int i = 0; i < translist.size(); i++){
                                totalRevenue +=  Double.parseDouble(translist.get(i).getContent()); 
                            }
                        TotalRevenueValue.setText("RM"+Tools.decimformatter.format(totalRevenue));
                        TotalOrderValue.setText(totalOrder+" orders");
                    }
                        else{
                            TotalRevenueValue.setText("RM 0");
                            TotalOrderValue.setText("0 orders");
                        }
                }
                
                });                

        }

}

