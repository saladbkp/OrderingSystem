package com.adminViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.dao.AddVendorDao;
import com.model.Vendors;
import com.tool.ImageRender;
import com.tool.JPEGImageFileFilter;
import com.tool.Tools;

public class AdminVendorView extends JPanel {
	int WIDTH;
	int HEIGHT = 150;
	
	public AdminVendorView(int x,int y, int width, int height) {
		// separate 2 windows ?????? 
		this.setBounds(x,y,width,height);
		this.WIDTH = width;
		Init();
	}
	
	String columns[] = {"VendorID","VendorPwd","VendorName","Type","Location","Sample"};
	JTable tableitem = null;
	JScrollPane jsrcollpane; //scrollbar
	DefaultTableModel model;
	TableColumnModel columnModel;
	AddVendorDao vendorfunc = new AddVendorDao();
	String iconpath;
	
	void Init() {
		
		// layout 
		
		this.setLayout(null);
		this.setBackground(Color.gray);
		
		JPanel jpanel1 = new JPanel();
		jpanel1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10)); // left alignment
		jpanel1.setBounds(0,0,WIDTH,50);
		jpanel1.setBackground(Color.YELLOW);
		
		JButton addvendorbutton = new JButton("Add vendor");;
		JButton removevendorbutton = new JButton("Remove vendor");
		JButton readvendorbutton = new JButton("Read vendor");
		JButton updatevendorbutton = new JButton("Update vendor");
		
		jpanel1.add(addvendorbutton);
		jpanel1.add(removevendorbutton);
		jpanel1.add(readvendorbutton);
		jpanel1.add(updatevendorbutton);
		
		JPanel jpanel2 = new JPanel();
		jpanel2.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		jpanel2.setBounds(0,60,WIDTH,50);
		jpanel2.setBackground(Color.LIGHT_GRAY);

		JLabel jlabelid = new JLabel("Vendor ID");
		jpanel2.add(jlabelid);
		JTextField jtextfieldid = new JTextField(3);
		jpanel2.add(jtextfieldid);
		
		JLabel jlabel = new JLabel("Vendor Name");
		jpanel2.add(jlabel);
		JTextField jtextfield = new JTextField(10);
		jpanel2.add(jtextfield);
		// password 
		JLabel jlabelpwd = new JLabel("Password");
		jpanel2.add(jlabelpwd);
		JPasswordField jtextfieldpwd = new JPasswordField(5);
		jpanel2.add(jtextfieldpwd);
		JLabel jlabel1 = new JLabel("Type");
		jpanel2.add(jlabel1);
		JComboBox cmbtype = new JComboBox();
		cmbtype.addItem("--Select Type--");
		cmbtype.addItem("Fast Food");
		cmbtype.addItem("Cafe");
		cmbtype.addItem("Mamak");
		jpanel2.add(cmbtype);
		JLabel jlabel2 = new JLabel("Location");
		jpanel2.add(jlabel2);
		JComboBox cmblocation = new JComboBox();
		cmblocation.addItem("--Select Location--");
		cmblocation.addItem("KL");
		cmblocation.addItem("Pahang");
		cmblocation.addItem("Johor");
		jpanel2.add(cmblocation);
		JLabel jlabel3 = new JLabel("Sample");
		jpanel2.add(jlabel3);
		JButton jbuttonbrowse = new JButton("Browse");
		jbuttonbrowse.setPreferredSize(new Dimension(80,25));
		jpanel2.add(jbuttonbrowse);
		JLabel jlabelpath = new JLabel("-");
		jpanel2.add(jlabelpath);
		
		
		this.add(jpanel2);
		this.add(jpanel1);
		table();
		model = Tools.addDataTable(vendorfunc.findData(), model);
		this.add(jsrcollpane);
		
		// button function 
		
		addvendorbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Id = jtextfieldid.getText();
				String Name = jtextfield.getText();
				String Pwd = String.valueOf(jtextfieldpwd.getPassword());
				String Lct = cmblocation.getSelectedItem().toString();
				String Type = cmbtype.getSelectedItem().toString();
				String Path = jlabelpath.getText();
				if((Id+Name+Pwd).equals("")||(cmblocation.getSelectedIndex()+cmblocation.getSelectedIndex())==0) {
					JOptionPane.showMessageDialog(null,"Fill Up Vendor Info","Invalid Operation",JOptionPane.WARNING_MESSAGE);
				}
				else {
					int existID = vendorfunc.checkVendor(Id);
					String iconname = Name+".jpg";
					if(existID==-1) {
						Vendors v = new Vendors(Id,Pwd,Name,Type,Lct,iconname);
						vendorfunc.addData(v);
						model = Tools.addDataTable(vendorfunc.findData(), model);
						 
						File copied = new File(iconpath);
					    File pasted = new File("src/img/"+iconname);
					    try {
							Files.copy(copied.toPath(), pasted.toPath(), StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					    
				        // using copy(InputStream,Path Target); method 
						JOptionPane.showMessageDialog(null,"Add successfully " + Id,"Vendor",JOptionPane.WARNING_MESSAGE);
						}
					else {JOptionPane.showMessageDialog(null, Id + " Existed","Vendor",JOptionPane.WARNING_MESSAGE);}
					
				}
			}
		});
		
		readvendorbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jtextfieldid.getText().equals("")) {
					
					model = Tools.addDataTable(vendorfunc.findData(), model);
				}
				else {
					String id = jtextfieldid.getText();
					int existID = vendorfunc.checkVendor(id);
					if(existID==-1) {JOptionPane.showMessageDialog(null,"Invalid: " + id,"Vendor",JOptionPane.WARNING_MESSAGE);}
					else {model = Tools.addDataTable(vendorfunc.findDataByID(id), model);}
				}
			}
		});

		removevendorbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = jtextfieldid.getText();
				if(id.equals("")) {
					JOptionPane.showMessageDialog(null,"Fill Up Vendor Id","Invalid Operation",JOptionPane.WARNING_MESSAGE);
				}
				else {
					int existID = vendorfunc.checkVendor(id);
					if(existID==-1) {JOptionPane.showMessageDialog(null,"Invalid: " + id,"Vendor",JOptionPane.WARNING_MESSAGE);}
					else {
						vendorfunc.deleteData(id);
						model = Tools.addDataTable(vendorfunc.findData(), model);
						JOptionPane.showMessageDialog(null,"Delete successfully " + id,"Vendor",JOptionPane.WARNING_MESSAGE);
						}
				}
			}
		});
		updatevendorbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Id = jtextfieldid.getText();
				String Name = jtextfield.getText();
				String Pwd = String.valueOf(jtextfieldpwd.getPassword());
				String Lct = cmblocation.getSelectedItem().toString();
				String Type = cmbtype.getSelectedItem().toString();
				String Path = jlabelpath.getText();
				if((Id+Name+Pwd).equals("")||(cmblocation.getSelectedIndex()+cmblocation.getSelectedIndex())==0) {
					JOptionPane.showMessageDialog(null,"Fill Up Vendor Info","Invalid Operation",JOptionPane.WARNING_MESSAGE);
					
				}
				else {
					int existID = vendorfunc.checkVendor(Id);
					if(existID!=-1) {
						Vendors v = new Vendors(Id,Pwd,Name,Type,Lct,Path);
						vendorfunc.updateData(v);
						model = Tools.addDataTable(vendorfunc.findData(), model);
						JOptionPane.showMessageDialog(null,"Update successfully " + Id,"Vendor",JOptionPane.WARNING_MESSAGE);
						}
					else {JOptionPane.showMessageDialog(null, Id + " Not Exist","Vendor",JOptionPane.WARNING_MESSAGE);}
					
				}
			}
		});
		jbuttonbrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				JFileChooser j = new JFileChooser();
//				j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//				Integer opt = j.showSaveDialog(this);
				JFileChooser fc = new JFileChooser();
			    fc.setFileFilter(new JPEGImageFileFilter());
			    int res = fc.showOpenDialog(null);
			    // We have an image!
			    try {
			        if (res == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            jlabelpath.setText(file.getName());
			            iconpath = file.getAbsolutePath();
			        }
			    } catch (Exception iOException) {
			    }

			}
		});
		
//		cmbox listener
//		cmblocation.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e) {
//				
//			}
//		});
	}
	
	void table() {
		tableitem = TableSetup();
		jsrcollpane = new JScrollPane(tableitem);
		jsrcollpane.setPreferredSize(new Dimension(WIDTH-20,250));
		tableitem.setPreferredSize(new Dimension(WIDTH-30,1000));
		//jsrcollpane.setVerticalScrollBarPoicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsrcollpane.setBounds(0,120,WIDTH-20,420);
	}
	
	JTable TableSetup() {
		tableitem = new JTable();
		int[] columnWidth = {150,150,150,150,150,150};
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
		tableitem.setRowHeight(50);
		// column 5 is logo 
		tableitem.getColumnModel().getColumn(5).setCellRenderer(new ImageRender());
		int count = columnModel.getColumnCount();

		for(int i=0;i<count;i++) {
			javax.swing.table.TableColumn column = columnModel.getColumn(i);
			column.setPreferredWidth(columnWidth[i]);
		}
		return tableitem;
	}

	
}
