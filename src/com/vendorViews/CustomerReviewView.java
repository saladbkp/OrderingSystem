package com.vendorViews;

import com.customerdao.AddReviewDao;
import com.style.Style;
import com.tool.Tools;
import com.windows.Login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author khwon
 */
public class CustomerReviewView extends JPanel {

    int WIDTH;
    int HEIGHT = 150;

    public CustomerReviewView(int x, int y, int width, int height) {
        // separate 2 windows ?????? 
        this.setBounds(x, y, width, height);
        this.WIDTH = width;
        Init();
    }

    String columns[] = {"CustomerID", "VendorID", "Order ID", "Review"};
    JTable tableitem = null;
    JScrollPane jsrcollpane; //scrollbar
    DefaultTableModel model;
    TableColumnModel columnModel;
    AddReviewDao reviewfunc = new AddReviewDao();

    void Init() {

        // layout 
        this.setLayout(null);
        this.setBackground(Color.gray);
        Style style = new Style();

//		JPanel jpanel1 = new JPanel();
//		jpanel1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10)); // left alignment
//		jpanel1.setBounds(0,0,WIDTH,100);
//		jpanel1.setBackground(Color.YELLOW);
        // add content panel
        JPanel jpanel2 = new JPanel();
        jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jpanel2.setBounds(0, 0, WIDTH, 50);
        jpanel2.setBackground(Color.YELLOW);
        JLabel notificationlabel = new JLabel("Customer Review");
        notificationlabel.setFont(style.title);
        jpanel2.add(notificationlabel);

        // **** jpanel 1 for selection
        // **** jpanel 2 for table content 
        // if u have better design, just go through ur pattern
        this.add(jpanel2);
        //this.add(jpanel1);
        table();
        String account = Login.account;
        model = Tools.addDataTable(reviewfunc.findDataByID(account), model);
        tableitem.removeColumn(tableitem.getColumnModel().getColumn(0));
        tableitem.removeColumn(tableitem.getColumnModel().getColumn(0));
        this.add(jsrcollpane);

    }

    void table() {
        tableitem = TableSetup();
        jsrcollpane = new JScrollPane(tableitem);
        jsrcollpane.setPreferredSize(new Dimension(WIDTH - 20, 250));
        tableitem.setPreferredSize(new Dimension(WIDTH - 30, 1000));
        //jsrcollpane.setVerticalScrollBarPoicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsrcollpane.setBounds(10, 85, WIDTH - 20, 450);
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
