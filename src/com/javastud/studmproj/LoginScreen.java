package com.javastud.studmproj;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class LoginScreen extends JFrame {

	private JPanel contentPane;
	private JPanel panelTop;
	private JPanel panelLeft;
	private JPanel panelRight;
	private JPanel panelBottom;
	private JPanel panelCenter;
	private JLabel lblUser;
	private JTextField userNameTxt;
	private JLabel lblPassword;
	private JTextField passwordTxt;
	private JPanel titlePanel;
	private JLabel lblStatus;
	private JButton btnSignin;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
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
	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelTop(), BorderLayout.NORTH);
		contentPane.add(getPanelLeft(), BorderLayout.WEST);
		contentPane.add(getPanelRight(), BorderLayout.EAST);
		contentPane.add(getPanelBottom(), BorderLayout.SOUTH);
		contentPane.add(getPanelCenter(), BorderLayout.CENTER);
	}

	private JPanel getPanelTop() {
		if (panelTop == null) {
			panelTop = new JPanel();
			panelTop.setBackground(SystemColor.activeCaption);
		}
		return panelTop;
	}
	private JPanel getPanelLeft() {
		if (panelLeft == null) {
			panelLeft = new JPanel();
			panelLeft.setBackground(SystemColor.activeCaption);
		}
		return panelLeft;
	}
	private JPanel getPanelRight() {
		if (panelRight == null) {
			panelRight = new JPanel();
			panelRight.setBackground(SystemColor.activeCaption);
		}
		return panelRight;
	}
	private JPanel getPanelBottom() {
		if (panelBottom == null) {
			panelBottom = new JPanel();
			panelBottom.setBackground(SystemColor.activeCaption);
		}
		return panelBottom;
	}
	private JPanel getPanelCenter() {
		if (panelCenter == null) {
			panelCenter = new JPanel();
			panelCenter.setBackground(new Color(144, 238, 144));
			panelCenter.setLayout(new FormLayout(new ColumnSpec[] {
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,},
				new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,}));
			panelCenter.add(getTitlePanel(), "2, 2, 1, 7, fill, fill");
			panelCenter.add(getLblStatus(), "2, 10");
			panelCenter.add(getBtnSignin(), "2, 14");
			panelCenter.add(getBtnCancel(), "4, 14");
		}
		return panelCenter;
	}
	private JLabel getLblUser() {
		if (lblUser == null) {
			lblUser = new JLabel("Username");
			lblUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		}
		return lblUser;
	}
	private JTextField getUserNameTxt() {
		if (userNameTxt == null) {
			userNameTxt = new JTextField();
			userNameTxt.setColumns(10);
		}
		return userNameTxt;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password");
			lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		}
		return lblPassword;
	}
	private JTextField getPasswordTxt() {
		if (passwordTxt == null) {
			passwordTxt = new JTextField();
			passwordTxt.setColumns(10);
		}
		return passwordTxt;
	}
	private JPanel getTitlePanel() {
		if (titlePanel == null) {
			titlePanel = new JPanel();
			titlePanel.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			titlePanel.setLayout(new GridLayout(2, 3, 2, 2));
			titlePanel.add(getLblUser());
			titlePanel.add(getUserNameTxt());
			titlePanel.add(getLblPassword());
			titlePanel.add(getPasswordTxt());
		}
		return titlePanel;
	}
	private JLabel getLblStatus() {
		if (lblStatus == null) {
			lblStatus = new JLabel("Status");
			lblStatus.setForeground(Color.RED);
		}
		return lblStatus;
	}
	private JButton getBtnSignin() {
		if (btnSignin == null) {
			btnSignin = new JButton("SignIn");
		}
		return btnSignin;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
		}
		return btnCancel;
	}
}
