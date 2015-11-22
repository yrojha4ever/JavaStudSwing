package com.javastud.studmproj.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class SwingCompTest extends JFrame {

	private JPanel contentPane;
	private JLabel lblUsername;
	private JButton loginBtn;
	private JTextField usernameTxt;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingCompTest frame = new SwingCompTest();
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
	public SwingCompTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500,500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblUsername());
		contentPane.add(getLoginBtn());
		contentPane.add(getUsernameTxt());
		contentPane.add(getLblPassword());
		contentPane.add(getPasswordField());
		contentPane.add(getComboBox());
	}

	private JLabel getLblUsername() {
		if (lblUsername == null) {
			lblUsername = new JLabel("UserName");
			lblUsername.setForeground(new Color(0, 191, 255));
			lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblUsername.setBounds(21, 33, 64, 29);
		}
		return lblUsername;
	}
	private JButton getLoginBtn() {
		if (loginBtn == null) {
			loginBtn = new JButton("OK");
			loginBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			loginBtn.setBounds(137, 199, 89, 23);
		}
		return loginBtn;
	}
	private JTextField getUsernameTxt() {
		if (usernameTxt == null) {
			usernameTxt = new JTextField();
			usernameTxt.setBounds(79, 37, 119, 25);
			usernameTxt.setColumns(10);
			usernameTxt.setText("JAVA");
		}
		return usernameTxt;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password");
			lblPassword.setForeground(new Color(0, 255, 255));
			lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblPassword.setBounds(6, 73, 79, 29);
		}
		return lblPassword;
	}
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(79, 73, 119, 29);
		}
		return passwordField;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"---Select Age group--", "1-10", "10-20", "20-30", "40-50", "50+"}));
			comboBox.setBounds(71, 113, 127, 29);
		}
		return comboBox;
	}
}
