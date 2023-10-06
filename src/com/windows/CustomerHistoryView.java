package com.windows;

import java.awt.Color;

import javax.swing.JPanel;

public class CustomerHistoryView extends JPanel {
	public CustomerHistoryView(int x,int y, int width, int height) {
		this.setBounds(x,y,width,height);
		Init();
	}
	void Init() {
		this.setLayout(null);
		this.setBackground(Color.YELLOW);
	}
}
