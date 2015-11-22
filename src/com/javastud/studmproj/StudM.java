package com.javastud.studmproj;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class StudM extends JFrame {

	private JPanel contentPane;
	private JLabel lblActiveUser;
	private JLabel lblActiveusername;
	private JButton btnLogout;
	private JPanel panel;
	private JLabel lblUsername;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudM frame = new StudM();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudM() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblActiveUser());
		contentPane.add(getLblActiveusername());
		contentPane.add(getBtnLogout());
		contentPane.add(getPanel());
	}

	private JLabel getLblActiveUser() {
		if (lblActiveUser == null) {
			lblActiveUser = new JLabel("Active User");
			lblActiveUser.setBounds(33, 11, 99, 14);
		}
		return lblActiveUser;
	}
	private JLabel getLblActiveusername() {
		if (lblActiveusername == null) {
			lblActiveusername = new JLabel("activeUserName");
			lblActiveusername.setBounds(142, 11, 92, 14);
		}
		return lblActiveusername;
	}
	private JButton getBtnLogout() {
		if (btnLogout == null) {
			btnLogout = new JButton("Logout");
			btnLogout.setBounds(260, 7, 89, 23);
		}
		return btnLogout;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(10, 33, 269, 199);
			panel.setLayout(null);
			panel.add(getLblUsername());
			panel.add(getTextField());
		}
		return panel;
	}
	private JLabel getLblUsername() {
		if (lblUsername == null) {
			lblUsername = new JLabel("Username");
			lblUsername.setBounds(10, 11, 74, 19);
		}
		return lblUsername;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(83, 10, 87, 20);
			textField.setColumns(10);
		}
		return textField;
	}
}
