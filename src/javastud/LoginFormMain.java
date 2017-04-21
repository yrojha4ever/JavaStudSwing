package javastud;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import javastud.dao.UserDao;
import javastud.dao.UserDaoImpl;
import javax.swing.JPasswordField;

public class LoginFormMain extends JFrame {

	private JPanel contentPane;
	private JPanel imagePNL;
	private SpringLayout sl_contentPane;
	private JLabel companyName;
	private JLabel userNameLBL;
	private JTextField usernameTXT;
	private JLabel passwordLBL;
	private JPasswordField passwordTXT;
	private JButton loginBTN;
	private JButton cancelBTN;
	
	private static LoginFormMain frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginFormMain();
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
	public LoginFormMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 282);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		contentPane.add(getImagePNL());
		contentPane.add(getCompanyName());
		contentPane.add(getUserNameLBL());
		contentPane.add(getUsernameTXT());
		contentPane.add(getPasswordLBL());
		contentPane.add(getPasswordTXT());
		contentPane.add(getLoginBTN());
		contentPane.add(getCancelBTN());
	}
	private JPanel getImagePNL() {
		if (imagePNL == null) {
			imagePNL = new LogoPanel();
			sl_contentPane.putConstraint(SpringLayout.NORTH, imagePNL, 0, SpringLayout.NORTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.WEST, imagePNL, 194, SpringLayout.WEST, contentPane);
			sl_contentPane.putConstraint(SpringLayout.EAST, imagePNL, -10, SpringLayout.EAST, contentPane);
		}
		return imagePNL;
	}
	private JLabel getCompanyName() {
		if (companyName == null) {
			companyName = new JLabel("Student Corp., 2017");
			sl_contentPane.putConstraint(SpringLayout.EAST, companyName, 175, SpringLayout.WEST, contentPane);
			companyName.setFont(new Font("Tahoma", Font.BOLD, 15));
			companyName.setForeground(Color.MAGENTA);
			sl_contentPane.putConstraint(SpringLayout.NORTH, companyName, 21, SpringLayout.NORTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.WEST, companyName, 10, SpringLayout.WEST, contentPane);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, companyName, 64, SpringLayout.NORTH, contentPane);
		}
		return companyName;
	}
	private JLabel getUserNameLBL() {
		if (userNameLBL == null) {
			userNameLBL = new JLabel("User Name:");
			sl_contentPane.putConstraint(SpringLayout.NORTH, userNameLBL, 40, SpringLayout.SOUTH, getCompanyName());
			sl_contentPane.putConstraint(SpringLayout.WEST, userNameLBL, 25, SpringLayout.WEST, contentPane);
			userNameLBL.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return userNameLBL;
	}
	private JTextField getUsernameTXT() {
		if (usernameTXT == null) {
			usernameTXT = new JTextField();
			sl_contentPane.putConstraint(SpringLayout.SOUTH, getImagePNL(), -15, SpringLayout.NORTH, usernameTXT);
			sl_contentPane.putConstraint(SpringLayout.NORTH, usernameTXT, 104, SpringLayout.NORTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.EAST, getUserNameLBL(), -6, SpringLayout.WEST, usernameTXT);
			sl_contentPane.putConstraint(SpringLayout.WEST, usernameTXT, 163, SpringLayout.WEST, contentPane);
			usernameTXT.setColumns(10);
		}
		return usernameTXT;
	}
	private JLabel getPasswordLBL() {
		if (passwordLBL == null) {
			passwordLBL = new JLabel("Password:");
			sl_contentPane.putConstraint(SpringLayout.SOUTH, getUserNameLBL(), -11, SpringLayout.NORTH, passwordLBL);
			sl_contentPane.putConstraint(SpringLayout.EAST, passwordLBL, 0, SpringLayout.EAST, getUserNameLBL());
			sl_contentPane.putConstraint(SpringLayout.WEST, passwordLBL, 25, SpringLayout.WEST, contentPane);
			sl_contentPane.putConstraint(SpringLayout.NORTH, passwordLBL, 135, SpringLayout.NORTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, passwordLBL, -74, SpringLayout.SOUTH, contentPane);
			passwordLBL.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return passwordLBL;
	}
	private JPasswordField getPasswordTXT() {
		if (passwordTXT == null) {
			passwordTXT = new JPasswordField();
			sl_contentPane.putConstraint(SpringLayout.NORTH, passwordTXT, 135, SpringLayout.NORTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, getUsernameTXT(), -6, SpringLayout.NORTH, passwordTXT);
			sl_contentPane.putConstraint(SpringLayout.EAST, getUsernameTXT(), 0, SpringLayout.EAST, passwordTXT);
			sl_contentPane.putConstraint(SpringLayout.WEST, passwordTXT, 163, SpringLayout.WEST, contentPane);
			sl_contentPane.putConstraint(SpringLayout.EAST, passwordTXT, -78, SpringLayout.EAST, contentPane);
			passwordTXT.setColumns(10);
		}
		return passwordTXT;
	}
	private JButton getLoginBTN() {
		if (loginBTN == null) {
			loginBTN = new JButton("Login");
			loginBTN.setFont(new Font("Tahoma", Font.BOLD, 13));
			sl_contentPane.putConstraint(SpringLayout.NORTH, loginBTN, 176, SpringLayout.NORTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, getPasswordTXT(), -16, SpringLayout.NORTH, loginBTN);
			sl_contentPane.putConstraint(SpringLayout.WEST, loginBTN, 160, SpringLayout.WEST, contentPane);
			sl_contentPane.putConstraint(SpringLayout.EAST, loginBTN, -179, SpringLayout.EAST, contentPane);
			loginBTN.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UserDao userDao = new UserDaoImpl();
					boolean isValidUser = userDao.validateUser( usernameTXT.getText(), new String(passwordTXT.getPassword()) );
					if(isValidUser){
						StudentForm studForm = new StudentForm();
						studForm.setVisible(true);
						frame.dispose();
					}else{
						usernameTXT.setBackground(Color.PINK);
						passwordTXT.setBackground(Color.PINK);
					}
				}
			});
		}
		return loginBTN;
	}
	private JButton getCancelBTN() {
		if (cancelBTN == null) {
			cancelBTN = new JButton("Cancel");
			cancelBTN.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					System.exit(0);
				}
			});
			sl_contentPane.putConstraint(SpringLayout.SOUTH, cancelBTN, 25, SpringLayout.NORTH, getLoginBTN());
			cancelBTN.setFont(new Font("Tahoma", Font.BOLD, 13));
			sl_contentPane.putConstraint(SpringLayout.NORTH, cancelBTN, 0, SpringLayout.NORTH, getLoginBTN());
			sl_contentPane.putConstraint(SpringLayout.WEST, cancelBTN, 11, SpringLayout.EAST, getLoginBTN());
			sl_contentPane.putConstraint(SpringLayout.EAST, cancelBTN, -79, SpringLayout.EAST, contentPane);
		}
		return cancelBTN;
	}
}
