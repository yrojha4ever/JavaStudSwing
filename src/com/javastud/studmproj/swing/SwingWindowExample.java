package com.javastud.studmproj.swing;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingWindowExample {
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		JButton btn = new JButton("OK");
		btn.setBounds(150, 150, 100, 40);
		// btn.setSize(100, 40);
		// btn.setLocation(150, 150);
		frame.add(btn);

		frame.setSize(500, 500);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
