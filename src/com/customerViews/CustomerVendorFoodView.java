/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customerViews;

import com.customerdao.AddReviewDao;
import com.dao.AddItemDao;
import com.tool.TableWithButtons;
import com.tool.Tools;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.*;
import java.util.ArrayList;

/**
 *
 * @author khwon
 */
public class CustomerVendorFoodView extends JPanel {

    int WIDTH;
    int HEIGHT = 150;
    final int viewWidth = 1100;
    final int viewHeight = 550;
    //table
    String columns[] = {"ID","Food", "Cost", "Amount", "Action"};
    JTable tableitem = null;
    JScrollPane jsrcollpane; //scrollbar
    DefaultTableModel model;
    TableColumnModel columnModel;
    String vendorid;
    AddItemDao itemfunc = new AddItemDao();
    AddReviewDao reviewfunc = new AddReviewDao();

    public CustomerVendorFoodView(int x, int y, int width, int height, String vendorid) {
        // separate 2 windows ?????? 
        this.setBounds(x, y, width, height);
        this.WIDTH = width;
        this.vendorid = vendorid;
        Init();

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
        JButton addtocartbutton = new JButton("Add to cart");
        //JButton removeitembutton = new JButton("Remove item");
        //JButton readitembutton = new JButton("Read item");
        //JButton updateitembutton = new JButton("Update item");

        addtocartbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double sumQty = Tools.sumColumnJTable(tableitem, 3);
                if (sumQty > 0) {
                    CustomerCartView cart = new CustomerCartView(0, 0, viewWidth, viewHeight, tableitem.getModel(), vendorid);
                    ManageCustomerView.jpanel2.removeAll();
                    ManageCustomerView.jpanel2.add(cart, (Integer) (JLayeredPane.PALETTE_LAYER));
                    ManageCustomerView.jpanel2.moveToFront(cart);
                } else {
                    JOptionPane.showMessageDialog(null, "Select a Food", "Invalid Operation", JOptionPane.WARNING_MESSAGE);

                }

                // stop here 2023/11/14
            }
        });

        jpanel1.add(addtocartbutton);
        //jpanel1.add(removeitembutton);
        //jpanel1.add(readitembutton);
        //jpanel1.add(updateitembutton);

        // add content panel
        JPanel jpanel2 = new JPanel();
        jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jpanel2.setBounds(0, 360, WIDTH, 35);

        JLabel jlabelreview = new JLabel("Review ");
        jpanel2.add(jlabelreview);
        // **** jpanel 1 for selection
        // **** jpanel 2 for table content 
        // if u have better design, just go through ur pattern

        JPanel jpanel3 = new JPanel();
        jpanel3.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jpanel3.setBounds(0, 395, WIDTH, 155);

        // Create an ArrayList of Strings   **********GET DATA FROM TXT FILE THEN INPUT*******************
        ArrayList<String> reviewList = new ArrayList<>();
        var reviewlist = reviewfunc.findDataByID(vendorid);
        for (int i = 0; i < reviewlist.size(); i++) {
            reviewList.add(reviewlist.get(i).getContent());
        }
        // Create a JList and populate it with the ArrayList
        JList<String> list = new JList<>(reviewList.toArray(new String[0]));
        // Set the desired width and height for the JList
        //list.setPreferredSize(new Dimension(150, 70));

        // Increase the height(gap) of each item in the JList
        list.setFixedCellHeight(22); // Set the desired height in pixels

        // Increase the width of the JList
        list.setFixedCellWidth(450); // Set the desired width in pixels

        // Create a JScrollPane to enable scrolling if needed
        JScrollPane scrollpanel = new JScrollPane(list);
        //scrollpanel.setViewportView(list);
        // Set the desired height for the JScrollPane (fixed height)
        scrollpanel.setPreferredSize(new Dimension(500, 130)); // Set the desired width and height

        //jpanel3.add(scrollpanel);
        // Add the panel to the jpanel3
        jpanel3.add(scrollpanel);

        this.add(jpanel3);
        this.add(jpanel2);
        this.add(jpanel1);
        tablefood();
        model = Tools.addDataTableWithButton(itemfunc.findFoodByVendor(vendorid), tableitem, model);
        this.add(jsrcollpane);
    }

    void tablefood() {
        tableitem = TableSetup();
        jsrcollpane = new JScrollPane(tableitem);
        jsrcollpane.setPreferredSize(new Dimension(WIDTH - 20, 250));
        tableitem.setPreferredSize(new Dimension(WIDTH - 30, 1000));
        //jsrcollpane.setVerticalScrollBarPoicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsrcollpane.setBounds(0, 60, WIDTH - 20, 300);

    }

    //String [] columnname
    JTable TableSetup() {
        tableitem = new JTable();
        int[] columnWidth = {150, 150, 150, 150,150};
        model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return column != 4 ? false : true;

            }
        };
        model.setColumnIdentifiers(columns);
        tableitem.setModel(model);
        columnModel = tableitem.getColumnModel();
        tableitem.getTableHeader().setReorderingAllowed(false);
        tableitem.getTableHeader().setResizingAllowed(false);
        tableitem.setRowHeight(30);
        // add button
        TableWithButtons.initializeTableWithButtons(tableitem);

        int count = columnModel.getColumnCount();
        for (int i = 0; i < count; i++) {
            javax.swing.table.TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(columnWidth[i]);
        }
        return tableitem;
    }

}
