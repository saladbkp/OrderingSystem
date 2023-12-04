/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customerViews;

import com.dao.AddItemDao;
import com.model.Notifications;
import com.tool.TableRowFilter;
import com.tool.TableWithButtons;
import com.tool.Tools;
import com.windows.Login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ray
 */
public class CustomerCartView extends JPanel {

    int WIDTH;
    int HEIGHT = 150;
    int buttocol = 4;
    //table
    TableRowSorter tableSorter;
    String columns[] = {"ID","Food", "Cost", "Amount", "Action"};
    JTable tableitem = null;
    JScrollPane jsrcollpane; //scrollbar
    TableModel tableModel;
    DefaultTableModel model;
    TableColumnModel columnModel;
    String VendorID;
    AddItemDao itemfunc = new AddItemDao();

    public CustomerCartView(int x, int y, int width, int height, TableModel tableModel, String vendorId) {
        // separate 2 windows ?????? 
        this.setBounds(x, y, width, height);
        this.WIDTH = width;
        this.tableModel = tableModel;
        this.VendorID = vendorId;
        Init();
    }

    void Init() {

        // layout 
        this.setLayout(null);
        this.setBackground(Color.gray);

        // add content panel
        JPanel jpanel2 = new JPanel();
        jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jpanel2.setBounds(0, 200, WIDTH, 50);

        JLabel jlabelfoodservice = new JLabel("Food Service:");
        jpanel2.add(jlabelfoodservice);
        JComboBox cmbfoodservice = new JComboBox();
        cmbfoodservice.addItem("--Select Food Service--");
        cmbfoodservice.addItem("Dine-in");
        cmbfoodservice.addItem("Takeaway");
        cmbfoodservice.addItem("Delivery");
        jpanel2.add(cmbfoodservice);

        JPanel jpanel6 = new JPanel();
//                jpanel6.setLayout(new FlowLayout(FlowLayout.LEFT,70,10));
        jpanel6.setBounds(0, 420, WIDTH, 50);
        jpanel6.setBackground(Color.YELLOW);
        JButton paybutton = new JButton("Pay");
        JButton backbutton = new JButton("Back");
        jpanel6.add(paybutton, new FlowLayout(FlowLayout.LEFT, 70, 10));
        jpanel6.add(backbutton, new FlowLayout(FlowLayout.RIGHT, 70, 10));

        // **** jpanel 1 for selection
        // **** jpanel 2 for table content 
        // if u have better design, just go through ur pattern
        //this.add(jpanel1);
        tablefood();
        model = (DefaultTableModel) tableModel;
        tableitem.setModel(model);

        // remove action column
        tableitem.removeColumn(tableitem.getColumnModel().getColumn(buttocol));
        //model = Tools.addDataTableWithButton(itemfunc.findFoodByVendor("v1"),tableitem, model);
        tableSorter = new TableRowSorter(model);
        tableitem.setRowSorter(tableSorter);
        tableSorter.setRowFilter(new TableRowFilter(buttocol-1, "0")); //poly
        this.add(jsrcollpane);

        // do cal after init table
        JPanel jpanel3 = new JPanel();
        jpanel3.setLayout(new FlowLayout(FlowLayout.LEFT, 70, 10));
        jpanel3.setBounds(0, 260, WIDTH, 50);
        String subtotal = "Subtotal: RM" + Tools.decimformatter.format(subTotal());
        JLabel jlabelsubtotal = new JLabel(subtotal);
        jpanel3.add(jlabelsubtotal);

        JPanel jpanel4 = new JPanel();
        jpanel4.setLayout(new FlowLayout(FlowLayout.LEFT, 70, 10));
        jpanel4.setBounds(0, 300, WIDTH, 50);
        JLabel jlabeldeliveryfee = new JLabel("Delivery fee: RM0");
        jpanel4.add(jlabeldeliveryfee);

        JPanel jpanel5 = new JPanel();
        jpanel5.setLayout(new FlowLayout(FlowLayout.LEFT, 70, 10));
        jpanel5.setBounds(0, 360, WIDTH, 50);
        String totalcost = "Total cost: RM" + Tools.decimformatter.format(subTotal());
        JLabel jlabeltotalcost = new JLabel(totalcost);
        jpanel5.add(jlabeltotalcost);

        JPanel jpanel7 = new JPanel();
        jpanel7.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 10));
        jpanel7.setBounds(0, 470, WIDTH, 100);
        // icon.setPreferredSize(new Dimension(70,70));

        // load the gif 
        ImageIcon icon = new ImageIcon("src/img/loading.gif");
        int width = 70;
        int height = 70;
        icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        JLabel labelpic = new JLabel("", icon, JLabel.CENTER);
        JLabel labelmsg = new JLabel("Waiting Confirmation from Vendor...");
        jpanel7.add(labelpic);
        jpanel7.add(labelmsg);
        jpanel7.setVisible(false);

        this.add(jpanel2);
        this.add(jpanel3);
        this.add(jpanel4);
        this.add(jpanel5);
        this.add(jpanel6);
        this.add(jpanel7);

        // display calculation 
        cmbfoodservice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int Deliveryfee = DeliveryFee(cmbfoodservice.getSelectedIndex());
                jlabeldeliveryfee.setText("Delivery fee: RM" + Deliveryfee);
                jlabeltotalcost.setText("Total cost: RM" + Tools.decimformatter.format(Deliveryfee + subTotal()));

            }
        });

        // pay button 
        paybutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cmbfoodservice.getSelectedIndex() != 0) {
                    String account = Login.account;
                    String vendor = VendorID;
                    Calendar cal = Calendar.getInstance();
                    String date = Tools.formatter.format(cal.getTime());
                    String timeStamp = new SimpleDateFormat("MMddHHmmss").format(new java.util.Date());
                    String orderid = "o" + timeStamp;
                    String serviceMethod =  cmbfoodservice.getSelectedItem().toString();
                    // write notification
                    Notifications noti = new Notifications(account, vendor, orderid, "NEW ORDER + 1", date);
                    Tools.appendFile("src/data/notifications.txt", noti.toString());
                    // pending order
                    String pendingtxt = vendor + "," + orderid;
                    Tools.appendFile("src/data/pending.txt", pendingtxt);
                    // make an order 
                    String runnerid = serviceMethod.equals("Delivery")?"r":"-";
                    for (int i = 0; i < tableitem.getRowCount(); i++) {
                        String itemid = tableitem.getValueAt(i, 0).toString();
                        String Amount = tableitem.getValueAt(i, 3).toString();
                        String ordertxt = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", 
                                orderid,account,itemid,vendor,runnerid,Amount,serviceMethod,date,"0");
                        Tools.appendFile("src/data/orders.txt", ordertxt);
                    }
                    
                    
                    jpanel7.setVisible(true);
                    paybutton.setEnabled(false);
                    backbutton.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Select a Service Method", "Invalid Operation", JOptionPane.WARNING_MESSAGE);

                }

            }
        });

    }

    double subTotal() {
        double total = 0;
        for (int i = 0; i < tableitem.getRowCount(); i++) {
            double Price = Double.parseDouble(tableitem.getValueAt(i, 2).toString());
            double Amount = Double.parseDouble(tableitem.getValueAt(i, 3).toString());
            total += Amount * Price;
        }
        return Math.floor(total * 100) / 100;

    }

    int DeliveryFee(int index) {
        return index == 3 ? 5 : 0;
    }

    void tablefood() {
        tableitem = TableSetup();
        jsrcollpane = new JScrollPane(tableitem);
        jsrcollpane.setPreferredSize(new Dimension(WIDTH - 20, 250));
        tableitem.setPreferredSize(new Dimension(WIDTH - 30, 1000));
        //jsrcollpane.setVerticalScrollBarPoicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsrcollpane.setBounds(0, 10, WIDTH - 10, 180);

    }

    //String [] columnname
    JTable TableSetup() {
        tableitem = new JTable();
        int[] columnWidth = {150,150, 150, 150, 150};
        model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return column != buttocol ? false : true;
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
