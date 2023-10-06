package com.style;

import java.awt.Font;

public class Style {
	public static Font title;
	public static Font account;
	public static Font accounttext;
	public static Font ok;
	
	public Style() {
		title = new Font("Calibri",Font.BOLD,28);
		account = new Font("Calibri",Font.BOLD,18);
		accounttext = new Font("Calibri",Font.PLAIN,18);
	}
}
