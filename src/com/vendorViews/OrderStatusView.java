package com.vendorViews;

import com.dao.AddPendingOrderDao;
import com.model.Notifications;
import com.model.Orders;
import com.style.Style;
import com.tool.TableRowFilter;
import com.tool.Tools;
import com.windows.Login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author khwon
 */
public class OrderStatusView extends JPanel {

    int WIDTH;
    int HEIGHT = 150;

    public OrderStatusView(int x, int y, int width, int height) {
        // separate 2 windows ?????? 
        this.setBounds(x, y, width, height);
        this.WIDTH = width;
        Init();

    }

    String columns[] = {"Order ID", "CusID", "Item ID", "Vendor ID", "Runner ID", "Amount", "Service Method", "DateTime", "Status"}; //Actions** ask ruiyi
    public static JTable tableitem = null;
    JScrollPane jsrcollpane; //scrollbar
    public static DefaultTableModel model;
    TableColumnModel columnModel;
    TableRowSorter tableSorter;
    AddPendingOrderDao penorderfunc = new AddPendingOrderDao();
    
    void Init() {

        // layout 
        this.setLayout(null);
        this.setBackground(Color.gray);
        Style style = new Style();

        JPanel jpanel1 = new JPanel();
        jpanel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20)); // left alignment
        jpanel1.setBounds(0, 50, WIDTH - 200, 50);
        jpanel1.setBackground(Color.YELLOW);

        // add content panel
        JPanel jpanel2 = new JPanel();
        jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jpanel2.setBounds(0, 0, WIDTH, 50);
        jpanel2.setBackground(Color.red);
        JLabel notificationlabel = new JLabel("Order Status");
        notificationlabel.setFont(style.title);
        jpanel2.add(notificationlabel);

        // add button sample
        JLabel jlabelid = new JLabel("Order Id:");
        jpanel1.add(jlabelid);
        JTextField jtextfieldid = new JTextField(15);
        jpanel1.add(jtextfieldid);

        JLabel jlabel3 = new JLabel("Status:");
        jpanel1.add(jlabel3);
        JComboBox status = new JComboBox();
        status.addItem("--Select Status--");
        status.addItem("Pending");
        status.addItem("Complete");
        jpanel1.add(status);

        JButton readitembutton = new JButton("Read item");
        jpanel1.add(readitembutton);

        // done button
        JPanel jpanel3 = new JPanel();
        jpanel3.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10)); // left alignment
        jpanel3.setBounds(WIDTH - 200 + 10, 50, 170, 50);
        jpanel3.setBackground(Color.YELLOW);
        JButton donebutton = new JButton("Done");
        jpanel3.add(donebutton);
        // **** jpanel 1 for selection
        // **** jpanel 2 for table content 
        // if u have better design, just go through ur pattern
        this.add(jpanel1);
        this.add(jpanel2);
        this.add(jpanel3);
        table();
        this.add(jsrcollpane);

        // button function 
        donebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableitem.getSelectedRow();
                if (selectedRow != -1) {
                    tableitem.setValueAt("Complete", selectedRow, 7);
                    // remove complete tasks from pending 
                    String orderId = tableitem.getValueAt(selectedRow, 0).toString();
                    Orders order = penorderfunc.findDataByOrder(orderId);
                    penorderfunc.removeOrders(order);
                    List<Orders> orderarray = penorderfunc.findData();
                    Tools.writeFile("src/data/pendingOrders.txt", "");
                    for(int i=0;i<orderarray.size();i++){
                        Tools.appendFile("src/data/pendingOrders.txt", orderarray.get(i).toString());
                    }
                    String account = Login.account;
                    String cusId = tableitem.getValueAt(selectedRow, 1).toString();
                    Calendar cal = Calendar.getInstance();
                    String date = Tools.formatter.format(cal.getTime());
                    Notifications noti = new Notifications(account, cusId, orderId, "Your ORDER is Completed, finding Runner...", date);
                    Tools.appendFile("src/data/notifications.txt", noti.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Please select food", "Invalid Operation", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        readitembutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jtextfieldid.getText().equals("")) {
                    // why no display 
                    tableitem.setRowSorter(null);
                } else {
                    String id = jtextfieldid.getText();
                    tableSorter = new TableRowSorter(model);
                    tableitem.setRowSorter(tableSorter);
                    tableSorter.setRowFilter(new TableRowFilter(0, id));

                }
            }
        });
        status.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (status.getSelectedIndex() < 1) {
                    // why no display 
                    tableitem.setRowSorter(null);
                } else {
                    String statusKey = status.getSelectedItem().toString();
                    tableSorter = new TableRowSorter(model);
                    tableitem.setRowSorter(tableSorter);
                    tableSorter.setRowFilter(new TableRowFilter(8, statusKey));

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
        int[] columnWidth = {150, 150, 150, 150, 150, 150, 150, 150, 150};
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
