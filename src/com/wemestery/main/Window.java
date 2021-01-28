package com.wemestery.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {

	private static final long serialVersionUID = 1L;

	public Window(int width, int height, int scale, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width*scale, height*scale));
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
