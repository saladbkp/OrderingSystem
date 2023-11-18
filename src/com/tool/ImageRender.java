package com.tool;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageRender extends DefaultTableCellRenderer{

		@Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
			String photopath = value.toString();
			ImageIcon imageicon = new ImageIcon(new ImageIcon("src/img/"+photopath).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
                        return new JLabel(imageicon);
		}
}
