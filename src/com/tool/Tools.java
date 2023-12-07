package com.tool;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import com.tool.TableWithButtons.AddAction;
import com.tool.TableWithButtons.Buttons;
import com.tool.TableWithButtons.MinusAction;
import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JTable;

public class Tools {
        public static DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        public static DecimalFormat decimformatter = new DecimalFormat("0.00");
	public static void WindowCenterScreen(int WIDTH,int HEIGHT,JFrame jframe) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		int x = (width-WIDTH)/2;
		int y = (height - HEIGHT)/2;
		jframe.setBounds(x,y,WIDTH,HEIGHT);
	}
	// return data count
	public static <T> DefaultTableModel addDataTable(List<T> list,DefaultTableModel model) {
		model.setNumRows(0);
		for (T  element: list) {
			//System.out.println(element.toString());
			//stringslist.add(element.toString()); 
			String[] strlist = element.toString().split(",");
			Vector rowData = new Vector();
			rowData.addAll(Arrays.asList(strlist));
			model.addRow(rowData);
		}
		
		return model;
	}
        	public static <T> DefaultTableModel addDataTableWithButton(List<T> list,JTable jtable,DefaultTableModel model) {
		model.setNumRows(0);
		for (T  element: list) {
			//System.out.println(element.toString());
			//stringslist.add(element.toString()); 
			String[] strlist = element.toString().split(",");
			Vector rowData = new Vector();
			rowData.addAll(Arrays.asList(strlist));
                        // Create a new Buttons instance with AddAction and MinusAction
                        Buttons buttons = new Buttons(new AddAction(jtable,model),new MinusAction(jtable,model));
                        rowData.add(buttons);
                        
			model.addRow(rowData);
		}
		
		return model;
	}

	// return JComboBox
	public static <T> DefaultComboBoxModel addJComboBox(List<T> list,DefaultComboBoxModel model) {
		String[] selectedItem = (String[])model.getSelectedItem();
		String firstitem = selectedItem[0];
		model.removeAllElements();
		
		Vector data = new Vector();		
		data.add(firstitem);
		for (T  element: list) {
			data.add(list.get(0));
		}
		model.addAll(data);
		
		return model;
	}
	public static ArrayList<Object> loadTableToArr(JTable table,int numObj){
            ArrayList<Object> list = new ArrayList<Object>();
            System.out.println(table.getRowCount() + " "+ table.getColumnCount());
            for(int row = 0; row < table.getRowCount(); row++) {
                Object obj[] = new Object[numObj]; 
                for(int column = 0; column < table.getColumnCount(); column++) {
                    obj[column] = table.getValueAt(row, column);
                }
                list.add(obj);
            }
            return list;
        }

        public static void writeFile(String filename,String content){
                    try {
                        FileWriter myWriter = new FileWriter(filename);
                        myWriter.write(content);
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                      } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                      }
                }
               public static void appendFile(String filename,String content){
                    try {
                        File file = new File(filename);
                        FileWriter myWriter = new FileWriter(filename,true);
                        content = file.length()!=0?"\n"+content:content;
                        myWriter.write(content);
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                      } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                      }
                }
        // sum a column 
        public static double sumColumnJTable(JTable jtable,int col){
            double total = 0;
            for(int i = 0; i < jtable.getRowCount(); i++){

                double Amount = Double.parseDouble(jtable.getValueAt(i, col).toString());
                total += Amount;
            }
            return total;
        }
}
