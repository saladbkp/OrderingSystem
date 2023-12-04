/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vendorViews;

import com.dao.AddItemDao;
import com.model.Items;
import com.tool.Tools;
import com.windows.Login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class VendorItemView extends JPanel {

    int WIDTH;
    int HEIGHT = 150;

    public VendorItemView(int x, int y, int width, int height) {
        // separate 2 windows ?????? 
        this.setBounds(x, y, width, height);
        this.WIDTH = width;
        Init();
    }
    ///// ASK FOR TEAMATE DISCUSSION
    String columns[] = {"Food ID", "Food Name", "Food Cost", "VendorID"};
    JTable tableitem = null;
    JScrollPane jsrcollpane; //scrollbar
    DefaultTableModel model;
    TableColumnModel columnModel;
    AddItemDao itemfunc = new AddItemDao();

    void Init() {

        // layout 
        this.setLayout(null);
        this.setBackground(Color.gray);

        JPanel jpanel1 = new JPanel();
        jpanel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // left alignment
        jpanel1.setBounds(0, 0, WIDTH, 50);
        jpanel1.setBackground(Color.YELLOW);

        // add button sample
        JButton additembutton = new JButton("Add item");
        JButton removeitembutton = new JButton("Remove item");
        JButton readitembutton = new JButton("Read item");
        JButton updateitembutton = new JButton("Update item");

        jpanel1.add(additembutton);
        jpanel1.add(removeitembutton);
        jpanel1.add(readitembutton);
        jpanel1.add(updateitembutton);

        // add content panel
        JPanel jpanel2 = new JPanel();
        jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));
        jpanel2.setBounds(0, 60, WIDTH, 50);
        jpanel2.setBackground(Color.LIGHT_GRAY);
        // **** jpanel 1 for selection
        // **** jpanel 2 for table content 
        // if u have better design, just go through ur pattern

        JLabel jlabelid = new JLabel("Food ID");
        jpanel2.add(jlabelid);
        JTextField jtextfieldid = new JTextField(5);
        jpanel2.add(jtextfieldid);

        JLabel jlabel = new JLabel("Food Name");
        jpanel2.add(jlabel);
        JTextField jtextfield = new JTextField(10);
        jpanel2.add(jtextfield);

        JLabel jlabelprice = new JLabel("Food Cost");
        jpanel2.add(jlabelprice);
        JTextField jtextfieldprice = new JTextField(10); //Currency use TextFeild??
        jpanel2.add(jtextfieldprice);

        String account = Login.account;

        this.add(jpanel2);
        this.add(jpanel1);
        table();
        model = Tools.addDataTable(itemfunc.findDataByVendor(account), model);

        this.add(jsrcollpane);
        tableitem.removeColumn(tableitem.getColumnModel().getColumn(3));

        // button function 
        additembutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Id = jtextfieldid.getText();
                String Name = jtextfield.getText();
                String Price = jtextfieldprice.getText();

                if ((Id + Name + Price).equals("")) {
                    JOptionPane.showMessageDialog(null, "Fill Up Food Info", "Invalid Operation", JOptionPane.WARNING_MESSAGE);
                } else {
                    int existID = itemfunc.checkItem(Id);
                    if (existID == -1) {
                        Items c = new Items(Id, Name, Price, account);
                        itemfunc.addData(c);
                        model = Tools.addDataTable(itemfunc.findDataByVendor(account), model);
                        JOptionPane.showMessageDialog(null, "Add successfully " + Id, "Food", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, Id + " Existed", "Food", JOptionPane.WARNING_MESSAGE);
                    }

                }
            }
        });

        readitembutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jtextfieldid.getText().equals("")) {

                    model = Tools.addDataTable(itemfunc.findDataByVendor(account), model);

                } else {
                    String id = jtextfieldid.getText();
                    int existID = itemfunc.checkItem(id);
                    if (existID == -1) {
                        JOptionPane.showMessageDialog(null, "Invalid: " + id, "Food", JOptionPane.WARNING_MESSAGE);
                    } else {
                        model = Tools.addDataTable(itemfunc.findDataByItem(id), model);
                    }
                }
            }
        });
        removeitembutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = jtextfieldid.getText();
                if (id.equals("")) {
                    JOptionPane.showMessageDialog(null, "Fill Up Customer Id", "Invalid Operation", JOptionPane.WARNING_MESSAGE);
                } else {
                    int existID = itemfunc.checkItem(id);
                    if (existID == -1) {
                        JOptionPane.showMessageDialog(null, "Invalid: " + id, "Food", JOptionPane.WARNING_MESSAGE);
                    } else {
                        itemfunc.deleteData(id);
                        model = Tools.addDataTable(itemfunc.findDataByVendor(account), model);
                        JOptionPane.showMessageDialog(null, "Delete successfully " + id, "Food", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        updateitembutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Id = jtextfieldid.getText();
                String Name = jtextfield.getText();
                String Price = jtextfieldprice.getText();
                if ((Id + Name + Price).equals("")) {
                    JOptionPane.showMessageDialog(null, "Fill Up Food Info", "Invalid Operation", JOptionPane.WARNING_MESSAGE);

                } else {
                    int existID = itemfunc.checkItem(Id);
                    if (existID != -1) {
                        Items c = new Items(Id, Name, Price, account);
                        itemfunc.updateData(c);
                        model = Tools.addDataTable(itemfunc.findDataByVendor(account), model);
                        JOptionPane.showMessageDialog(null, "Update successfully " + Id, "Food", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, Id + " Not Exist", "Food", JOptionPane.WARNING_MESSAGE);
                    }

                }
            }
        });
    }

    void table() {
        tableitem = TableSetup();
        jsrcollpane = new JScrollPane(tableitem);
        jsrcollpane.setPreferredSize(new Dimension(WIDTH - 20, 250));
        tableitem.setPreferredSize(new Dimension(WIDTH - 30, 1000));
        //jsrcollpane.setVerticalScrollBarPoicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsrcollpane.setBounds(0, 120, WIDTH - 20, 420);
    }

    JTable TableSetup() {
        tableitem = new JTable();
        int[] columnWidth = {150, 150, 150, 150};
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
        for (int i = 0; i < count; i++) {
            javax.swing.table.TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(columnWidth[i]);
        }
        return tableitem;
    }

}
