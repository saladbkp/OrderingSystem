package com.tool;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableCellRenderer;

public class TableWithButtons {

//    public class TableWithButtonsExample extends JFrame {
//
//    private DefaultTableModel tableModel;
//    private JTable table;
//
//    public TableWithButtonsExample() {
//        setTitle("JTable with Add and Minus Buttons");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Create the table model and set column names
//        tableModel = new DefaultTableModel(new Object[]{"Item", "Quantity", "Actions"}, 0) {
//            @Override
//            public Class<?> getColumnClass(int columnIndex) {
//                if (columnIndex == 2) {
//                    return ButtonsPanel.class;
//                }
//                return super.getColumnClass(columnIndex);
//            }
//        };
//
//        table = new JTable(tableModel);
//
//        // Set custom renderer and editor for the Actions column
//        table.getColumnModel().getColumn(2).setCellRenderer(new ButtonsRenderer());
//        table.getColumnModel().getColumn(2).setCellEditor(new ButtonsEditor(new JTextField()));
//
//        // Add sample data to the table
//        addSampleData();
//
//        // Add components to the frame
//        add(new JScrollPane(table), BorderLayout.CENTER);
//
//        pack();
//        setLocationRelativeTo(null);
//    }
    public static void initializeTableWithButtons(JTable table) {
        // Set custom renderer and editor for the Actions column
        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonsRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new ButtonsEditor(new JTextField()));
    }
    // Custom renderer for the Actions column
    public static class ButtonsRenderer extends ButtonsPanel implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setButtons((Buttons) value);
            return this;
        }
        
    }

    // Custom editor for the Actions column
    public static class ButtonsEditor extends DefaultCellEditor {
        private ButtonsPanel buttonsPanel;

        public ButtonsEditor(JTextField textField) {
            super(textField);
            buttonsPanel = new ButtonsPanel();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            buttonsPanel.setButtons((Buttons) value);
            return buttonsPanel;
        }

        @Override
        public Object getCellEditorValue() {
            return buttonsPanel.getButtons();
        }

    }

    // Custom panel for the Add and Minus buttons
    private static class ButtonsPanel extends JPanel {
        private JButton addButton;
        private JButton minusButton;

        public ButtonsPanel() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

            addButton = new JButton("Add");
            minusButton = new JButton("Minus");

            add(addButton);
            add(minusButton);
            
            Dimension buttonSize = new Dimension(80, 30);
            addButton.setPreferredSize(buttonSize);
            minusButton.setPreferredSize(buttonSize);
        }

    public void setButtons(Buttons buttons) {
        addButton.setAction(buttons.getAddAction());
        addButton.setText("+");
        minusButton.setAction(buttons.getMinusAction());
        minusButton.setText("-");
    }

        public Buttons getButtons() {
            return new Buttons(addButton.getAction(), minusButton.getAction());
        }
    }

    // Class to represent the Actions for a row
    public static class Buttons {
        private Action addAction;
        private Action minusAction;

        public Buttons(Action addAction, Action minusAction) {
            this.addAction = addAction;
            this.minusAction = minusAction;
        }

        public Action getAddAction() {
            return addAction;
        }

        public Action getMinusAction() {
            return minusAction;
        }
    }
    
       // Sample action for the "Add" button
    public static class AddAction extends AbstractAction {
        private JTable table;
        private DefaultTableModel model;

        public AddAction(JTable table, DefaultTableModel model) {
            this.table = table;
            this.model = model;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            int selectedRow = table.getEditingRow();
            int quantity = Integer.parseInt(model.getValueAt(selectedRow, 2).toString());
            System.out.println("add "+quantity);
            model.setValueAt(quantity + 1, selectedRow, 2);
            

        }
    }

    // Sample action for the "Minus" button
    public static class MinusAction extends AbstractAction {
        private JTable table;
        private DefaultTableModel model;

        public MinusAction(JTable table, DefaultTableModel model) {
            this.table = table;
            this.model = model;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getEditingRow();
            int quantity = Integer.parseInt(model.getValueAt(selectedRow, 2).toString());
            System.out.println("minus "+quantity);
            if (quantity > 0) {
                model.setValueAt(quantity - 1, selectedRow, 2);
            }
            
        }
    }
    
}
