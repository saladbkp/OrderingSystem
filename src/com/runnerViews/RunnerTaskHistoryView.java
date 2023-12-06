/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.runnerViews;

import com.dao.AddCustomerDao;
import com.dao.AddItemDao;
import com.dao.AddOrderDao;
import com.dao.AddRunnerDao;
import com.dao.AddVendorDao;
import com.model.Items;
import com.model.Orders;
import com.model.Vendors;
import com.model_run.Tasks;
import com.runnerdao.AddTaskDao;
import com.style.Style;
import com.tool.Tools;
import com.windows.Login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ray
 */
public class RunnerTaskHistoryView extends JPanel {
    int WIDTH;
    int HEIGHT = 150;

    String columns[] = {"Order", "Vendor", "Runner", "Customer", "Fee", "Time", "Status"};
    JTable tableitem = null;
    JScrollPane jsrcollpane; //scrollbar
    DefaultTableModel model;
    TableColumnModel columnModel;
    AddCustomerDao addcustomerdao = new AddCustomerDao();
    AddVendorDao addvendordao = new AddVendorDao();
    AddRunnerDao addrunnerdao = new AddRunnerDao();
    AddOrderDao addorderdao = new AddOrderDao();
    AddTaskDao addtaskdao = new AddTaskDao();
    AddItemDao additemdao = new AddItemDao();
    Style style = new Style();

    public RunnerTaskHistoryView(int x, int y, int width, int height) {
        // separate 2 windows ?????? 
        this.setBounds(x, y, width, height);
        this.WIDTH = width;
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
//              
        JLabel orderhistory = new JLabel("Task History");
        orderhistory.setFont(style.title);
        jpanel1.add(orderhistory);
        // add button sample
        
        
        table();
        assignOrderTable();
        
        this.add(jsrcollpane);
        this.add(jpanel1);

    }

    void assignOrderTable() {

        List<Tasks> orderlist = addtaskdao.findDataByRun(Login.account);

        for (int i = 0; i < orderlist.size(); i++) {
            String orderid = orderlist.get(i).getOrderID();
            String vendorid = orderlist.get(i).getVendorID();
            String runnerid = orderlist.get(i).getRunnerID();
            String customerid = orderlist.get(i).getCustomerID();
            Vendors vendor = addvendordao.findDataByID(vendorid).get(0);
            var runnerCheck = addrunnerdao.findDataByID(runnerid);
            String vendorName = vendor.getUsername();
            String runnerName = runnerCheck.get(0).getUsername() ;
            double Total = orderlist.get(i).getTotalCost();
            String Datetime = Tools.formatter.format(orderlist.get(i).getTime());
            String status = orderlist.get(i).getStatus().equals("Accepted") ? "Success" : "Failed";
            Object[] row = {orderid, vendorName,runnerName, customerid,Total, Datetime, status};

            model.addRow(row);
        }

    }

    void table() {
        tableitem = TableSetup();
        jsrcollpane = new JScrollPane(tableitem);
        jsrcollpane.setPreferredSize(new Dimension(WIDTH - 20, 250));
        tableitem.setPreferredSize(new Dimension(WIDTH - 30, 1000));
        //jsrcollpane.setVerticalScrollBarPoicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsrcollpane.setBounds(0, 60, WIDTH - 0, 420);
    }

    JTable TableSetup() {
        tableitem = new JTable();
        int[] columnWidth = {150, 150, 150, 100, 150, 150, 150};
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
