/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.runnerViews;

import com.customerViews.CustomerCartView;
import com.customerViews.ManageCustomerView;
import com.dao.AddPendingTaskDao;
import com.model.Notifications;
import com.model.Orders;
import com.model_run.Tasks;
import com.runnerdao.AddTaskDao;
import com.tool.Tools;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import com.style.Style;
import com.windows.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author khwon
 */
public class RunnerTaskView extends JPanel {

    int WIDTH;
    int HEIGHT = 150;
    final int viewWidth = 1100;
    final int viewHeight = 550;

    String columns[] = {"Order ID", "Vendor ID", "RunnerID", "Customer ID", "TotalCost", "Time", "Status"};
    public static JTable tasktable = null;
    JScrollPane jscrollpane;
    public static DefaultTableModel model;
    TableColumnModel columnModel;
    AddTaskDao runnerfunc = new AddTaskDao();
    AddPendingTaskDao penorderfunc = new AddPendingTaskDao();
    Style style = new Style();

    public RunnerTaskView(int x, int y, int width, int height) {
        // separate 2 windows ?????? 
        this.setBounds(x, y, width, height);
        this.WIDTH = width;
        Init();
    }
    public static JLayeredPane jpanel1;
    public static JLayeredPane jpanel2;

    void Init() {

        // layout 
        this.setLayout(null);
        this.setBackground(Color.gray);

        JPanel jpanel1 = new JPanel();
        jpanel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // left alignment
        jpanel1.setBounds(0, 0, WIDTH, 50);
        jpanel1.setBackground(Color.YELLOW);

        JPanel jpanel2 = new JPanel();

        // add button sample
        JButton updateitembutton = new JButton("Update");

        JLabel topuplabel = new JLabel("Task");
        topuplabel.setFont(style.title);

        //add combo box
        JComboBox cmb = new JComboBox();
        cmb.addItem("Preparing Food");
        cmb.addItem("Delivering Food");
        cmb.addItem("Settled");

        jpanel1.add(topuplabel);

        // add content panel
        jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jpanel2.setBounds(0, 60, WIDTH, 50);
        jpanel2.add(cmb);
        jpanel2.add(updateitembutton);

        // **** jpanel 1 for selection
        // **** jpanel 2 for table content 
        // if u have better design, just go through ur pattern
        this.add(jpanel2);
        this.add(jpanel1);
        table();
        //model = Tools.addDataTable(runnerfunc.findTaskData(), model);

        this.add(jscrollpane);
        
        updateitembutton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (cmb.getSelectedIndex() != -1) {
                String statusStr = cmb.getSelectedItem().toString();
                tasktable.setValueAt(statusStr, tasktable.getSelectedRow(), 5);
                String account = Login.account;
                Calendar cal = Calendar.getInstance();
                String date = Tools.formatter.format(cal.getTime());
                String customerId = tasktable.getValueAt(tasktable.getSelectedRow(), 2).toString();
                String orderid = tasktable.getValueAt(tasktable.getSelectedRow(), 0).toString();
                Notifications noti = new Notifications(account, customerId, orderid, "Your ORDER is "+statusStr, date);
                Tools.appendFile("src/data/notifications.txt", noti.toString());
                // when task completed notify customer
                if(statusStr.equals("Settled")){
                    String pendingtxt = customerId + "," + orderid + ",Done";
                    Tools.writeFile("src/data/pending.txt", pendingtxt);
                    var orderList = penorderfunc.findDataByOrder(orderid);
                    if(orderList.size()>0){
                        Tasks order = orderList.get(0);
                        penorderfunc.removeOrders(order);
                        List<Tasks> orderarray = penorderfunc.findData();
                        Tools.writeFile("src/data/pendingTasks.txt", "");
                        for(int i=0;i<orderarray.size();i++){
                            Tools.appendFile("src/data/pendingTasks.txt", orderarray.get(i).toString());
                        }
                    }
                     ManageCustomerView.orderingStatus=1;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Select a Task", "Invalid Operation", JOptionPane.WARNING_MESSAGE);

            }

            // stop here 2023/11/14
        }
    });
        
    }

    void table() {
        tasktable = TableSetup();
        jscrollpane = new JScrollPane(tasktable);
        jscrollpane.setPreferredSize(new Dimension(WIDTH - 20, 250));
        tasktable.setPreferredSize(new Dimension(WIDTH - 30, 1000));
        //jsrcollpane.setVerticalScrollBarPoicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jscrollpane.setBounds(0, 120, WIDTH - 20, 420);
    }

    JTable TableSetup() {
        tasktable = new JTable();
        int[] columnWidth = {150, 150, 150, 150, 150, 150, 150};
        model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(columns);
        tasktable.setModel(model);
        columnModel = tasktable.getColumnModel();
        tasktable.getTableHeader().setReorderingAllowed(false);
        tasktable.getTableHeader().setResizingAllowed(false);
        int count = columnModel.getColumnCount();
        for (int i = 0; i < count; i++) {
            javax.swing.table.TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(columnWidth[i]);
        }
        return tasktable;

    }

}
