/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customerViews;

import com.customerdao.AddReviewDao;
import com.dao.AddOrderDao;
import com.model.Orders;
import com.model_cus.Reviews;
import com.windows.Login;
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

/**
 *
 * @author User
 */
public class CustomerReview extends JPanel {

    int WIDTH;
    int HEIGHT = 150;
    AddReviewDao reviewfunc = new AddReviewDao();
    AddOrderDao orderfunc = new AddOrderDao();
    JComboBox cmbfoodservice;

    public CustomerReview(int x, int y, int width, int height) {
        // separate 2 windows ?????? 
        this.setBounds(x, y, width, height);
        this.WIDTH = width;
        Init();
    }

    List<String> refreshPage() {
        AddReviewDao newreviewfunc = new AddReviewDao();
        AddOrderDao neworderfunc = new AddOrderDao();
        String account = Login.account;
        List<String> removeReviewedOrder = neworderfunc.updateComboxOrd(neworderfunc.findDataByCus(account));
        removeReviewedOrder.removeAll(newreviewfunc.updateCombox());
        return removeReviewedOrder;
    }

    void Init() {

        // layout 
        this.setLayout(null);
        this.setBackground(Color.gray);

        JPanel jpanel1 = new JPanel();
        jpanel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // left alignment
        jpanel1.setBounds(0, 0, WIDTH, 50);
        jpanel1.setBackground(Color.YELLOW);

        // add button sample
        JButton reviewbutton = new JButton("Post Review");
        //JButton removeitembutton = new JButton("Remove item");
        //JButton readitembutton = new JButton("Read item");
        //JButton updateitembutton = new JButton("Update item");

        jpanel1.add(reviewbutton);
        //jpanel1.add(removeitembutton);
        //jpanel1.add(readitembutton);
        //jpanel1.add(updateitembutton);

        // add content panel
        JPanel jpanel2 = new JPanel();
        jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jpanel2.setBounds(0, 100, WIDTH, 50);

        JLabel jlabelvendor = new JLabel("Order:");
        jpanel2.add(jlabelvendor);

        cmbfoodservice = new JComboBox(refreshPage().toArray());
        cmbfoodservice.insertItemAt("--Select Order--", 0);

        jpanel2.add(cmbfoodservice);

        JPanel jpanel3 = new JPanel();
        jpanel3.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jpanel3.setBounds(0, 160, WIDTH, 100);

        JLabel jlabelreview = new JLabel("Review for vendor:");
        jpanel3.add(jlabelreview);

        JTextField jtextfieldreview = new JTextField(50);
        jpanel3.add(jtextfieldreview);

        Dimension textFieldSize = jtextfieldreview.getPreferredSize();
        textFieldSize.height = 70; // Set the desired height in pixels
        jtextfieldreview.setPreferredSize(textFieldSize);

        JPanel jpanel4 = new JPanel();
        jpanel4.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jpanel4.setBounds(0, 260, WIDTH, 100);

        JLabel jlabelreviewrun = new JLabel("Review for runner:");
        jpanel4.add(jlabelreviewrun);

        JTextField jtextfieldreviewrun = new JTextField(50);
        jpanel4.add(jtextfieldreviewrun);

        jtextfieldreviewrun.setPreferredSize(textFieldSize);

        reviewbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String account = Login.account;
                String orderid = cmbfoodservice.getSelectedItem().toString();
                Orders order = orderfunc.findDataByOrder(orderid);
                Reviews revVendor = new Reviews(account, order.getVendorId(), orderid, jtextfieldreview.getText());
                Reviews revCus = new Reviews(account, order.getRunnerId(), orderid, jtextfieldreviewrun.getText());
                reviewfunc.addData(revVendor);
                reviewfunc.addData(revCus);
                JOptionPane.showMessageDialog(null, "Review added", "Valid Operation", JOptionPane.WARNING_MESSAGE);
                cmbfoodservice.removeAllItems();
                var array = refreshPage();
                cmbfoodservice.addItem("--Select Order--");
                for (int i = 0; i < array.size(); i++) {
                    cmbfoodservice.addItem(array.get(i));
                }
                jtextfieldreview.setText("");
                jtextfieldreviewrun.setText("");
            }
        });

        // **** jpanel 1 for selection
        // **** jpanel 2 for table content 
        // if u have better design, just go through ur pattern
        this.add(jpanel4);
        this.add(jpanel3);
        this.add(jpanel2);
        this.add(jpanel1);
    }

}
