package com.tool;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class WindowSetup {
	public static void WindowCenterScreen(int WIDTH,int HEIGHT,JFrame jframe) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		int x = (width-WIDTH)/2;
		int y = (height - HEIGHT)/2;
		jframe.setBounds(x,y,WIDTH,HEIGHT);
	}
}
