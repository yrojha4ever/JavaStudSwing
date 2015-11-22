package com.javastud.studmproj;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.javastud.studmproj.dao.StudentDao;
import com.javastud.studmproj.model.Student;

public class StudentMangement extends JFrame {

	private static final long serialVersionUID = -8360585084201808315L;
	private JPanel contentPane;
	private JPanel mainPanel;
	private JPanel panel;
	private JLabel nameLbl;
	private JTextField nameTxt;
	private JLabel lblRollNo;
	private JTextField rollNoTxt;
	private JLabel lblSubject;
	private JTextField facultyTxt;
	private JLabel lblFaculty;
	private JTextField collegeNameTxt;
	private JLabel lblBirthDate;
	private JTextField birthDateTxt;
	private JLabel lblCollegeName;
	private JTextField semesterTxt;
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private JPanel sexPanel;
	private JButton saveBtn;
	private JTable studTable;
	private JLabel lblUser;
	private JLabel activeUser;
	private JButton btnLogout;

	private JButton btnExit;
	private JScrollPane scrollPane;
	private JLabel addrellLbl;
	private JTextField addressTxt;
	private StudentDao studDao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMangement frame = new StudentMangement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StudentMangement(String activeUser) {
		this();
		this.getActiveUser().setText(activeUser);
	}

	/**
	 * Create the frame.
	 */
	public StudentMangement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 937, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getMainPanel());

		try {
			// Prepare Student Dao
			studDao = new StudentDao();
			// Show Student Records
			showAllStudents();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	private JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel();
			mainPanel.setBounds(0, 0, 911, 487);
			mainPanel.setLayout(null);
			mainPanel.add(getPanel());
			mainPanel.add(getScrollPane());
			mainPanel.add(getLblUser());
			mainPanel.add(getActiveUser());
			mainPanel.add(getBtnLogout());
			mainPanel.add(getBtnExit());
		}
		return mainPanel;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager
					.getBorder("TitledBorder.border"), "Student Form",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0,
							0, 0)));
			panel.setBounds(10, 43, 725, 128);
			panel.setLayout(null);
			panel.add(getNameLbl());
			panel.add(getNameTxt());
			panel.add(getLblRollNo());
			panel.add(getRollNoTxt());
			panel.add(getLblSubject());
			panel.add(getFacultyTxt());
			panel.add(getLblFaculty());
			panel.add(getCollegeNameTxt());
			panel.add(getLblBirthDate());
			panel.add(getBirthDateTxt());
			panel.add(getLblCollegeName());
			panel.add(getSemesterTxt());
			panel.add(getSexPanel());
			panel.add(getSaveBtn());
			panel.add(getAddrellLbl());
			panel.add(getAddressTxt());
		}
		return panel;
	}

	private JLabel getNameLbl() {
		if (nameLbl == null) {
			nameLbl = new JLabel("Name");
			nameLbl.setBounds(21, 22, 48, 14);
		}
		return nameLbl;
	}

	private JTextField getNameTxt() {
		if (nameTxt == null) {
			nameTxt = new JTextField();
			nameTxt.setBounds(65, 22, 121, 20);
			nameTxt.setColumns(10);
		}
		return nameTxt;
	}

	private JLabel getLblRollNo() {
		if (lblRollNo == null) {
			lblRollNo = new JLabel("Roll No:");
			lblRollNo.setBounds(452, 22, 76, 14);
		}
		return lblRollNo;
	}

	private JTextField getRollNoTxt() {
		if (rollNoTxt == null) {
			rollNoTxt = new JTextField();
			rollNoTxt.setBounds(528, 19, 121, 20);
			rollNoTxt.setColumns(10);
		}
		return rollNoTxt;
	}

	private JLabel getLblSubject() {
		if (lblSubject == null) {
			lblSubject = new JLabel("Semester");
			lblSubject.setBounds(196, 51, 72, 14);
		}
		return lblSubject;
	}

	private JTextField getFacultyTxt() {
		if (facultyTxt == null) {
			facultyTxt = new JTextField();
			facultyTxt.setColumns(10);
			facultyTxt.setBounds(65, 48, 121, 20);
		}
		return facultyTxt;
	}

	private JLabel getLblFaculty() {
		if (lblFaculty == null) {
			lblFaculty = new JLabel("Faculty");
			lblFaculty.setBounds(21, 47, 46, 14);
		}
		return lblFaculty;
	}

	private JTextField getCollegeNameTxt() {
		if (collegeNameTxt == null) {
			collegeNameTxt = new JTextField();
			collegeNameTxt.setColumns(10);
			collegeNameTxt.setBounds(528, 45, 121, 20);
		}
		return collegeNameTxt;
	}

	private JLabel getLblBirthDate() {
		if (lblBirthDate == null) {
			lblBirthDate = new JLabel("Birth Date");
			lblBirthDate.setBounds(196, 22, 72, 14);
		}
		return lblBirthDate;
	}

	private JTextField getBirthDateTxt() {
		if (birthDateTxt == null) {
			birthDateTxt = new JTextField();
			birthDateTxt.setColumns(10);
			birthDateTxt.setBounds(278, 22, 121, 20);
		}
		return birthDateTxt;
	}

	private JLabel getLblCollegeName() {
		if (lblCollegeName == null) {
			lblCollegeName = new JLabel("College Name");
			lblCollegeName.setBounds(419, 48, 109, 14);
		}
		return lblCollegeName;
	}

	private JTextField getSemesterTxt() {
		if (semesterTxt == null) {
			semesterTxt = new JTextField();
			semesterTxt.setColumns(10);
			semesterTxt.setBounds(278, 48, 121, 20);
		}
		return semesterTxt;
	}

	private JRadioButton getMaleRadio() {
		if (maleRadio == null) {
			maleRadio = new JRadioButton("Male");
			maleRadio.setBounds(6, 18, 54, 23);
		}
		return maleRadio;
	}

	private JRadioButton getFemaleRadio() {
		if (femaleRadio == null) {
			femaleRadio = new JRadioButton("Female");
			femaleRadio.setBounds(73, 18, 93, 23);
		}
		return femaleRadio;
	}

	private JPanel getSexPanel() {
		if (sexPanel == null) {
			sexPanel = new JPanel();
			sexPanel.setBorder(new TitledBorder(null, "Sex",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			sexPanel.setBounds(21, 72, 169, 48);
			sexPanel.setLayout(null);
			sexPanel.add(getMaleRadio());
			sexPanel.add(getFemaleRadio());
		}
		return sexPanel;
	}

	private JButton getSaveBtn() {
		if (saveBtn == null) {
			saveBtn = new JButton("Save");
			saveBtn.setBounds(560, 79, 89, 23);
			saveBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {

						// Save Student Record to DB.
						Student stud = getStudentFormData();
						studDao.saveStudent(stud);

						// Display Student Record
						showAllStudents();

					} catch (ClassNotFoundException | SQLException
							| ParseException e1) {
						e1.printStackTrace();

					}
				}
			});
		}
		return saveBtn;
	}

	private Student getStudentFormData() throws ParseException {
		Student stud = new Student();
		stud.setName(nameTxt.getText());
		stud.setBirthDate(new SimpleDateFormat("YYYY-MM-DD").parse(birthDateTxt
				.getText()));
		stud.setRollno(rollNoTxt.getText());
		stud.setFaculty(facultyTxt.getText());
		stud.setSemester(semesterTxt.getText());
		stud.setCollegeName(collegeNameTxt.getText());
		if (maleRadio.isSelected()) {
			stud.setSex("Male");
		} else if (femaleRadio.isSelected()) {
			stud.setSex("Female");
		}
		stud.setAddress(addressTxt.getText());
		return stud;
	}

	private void showAllStudents() throws ClassNotFoundException, SQLException {

		/*** Clear Table data ***/
		DefaultTableModel model = (DefaultTableModel) studTable.getModel();
		model.setRowCount(0); // Clear Table

		/** fetch all students record ***/
		studDao = new StudentDao();
		List<Student> studentList = studDao.getAllStudents();
		for (Student stud : studentList) {
			System.out.println(stud);
			model.addRow(new Object[] { stud.getId(), stud.getName(),
					stud.getBirthDate(), stud.getRollno(), stud.getFaculty(),
					stud.getSemester(), stud.getCollegeName(), stud.getSex(),
					stud.getAddress() });
		}
	}

	private JTable getStudTable() {
		if (studTable == null) {
			studTable = new JTable();
			studTable.setModel(new DefaultTableModel(new Object[][] {},
					new String[] { "ID", "Name", "Birth Date", "Roll No",
							"Faculty", "Semester", "College Name", "Sex",
							"Address" }));
		}
		return studTable;
	}

	private JLabel getLblUser() {
		if (lblUser == null) {
			lblUser = new JLabel("Active User");
			lblUser.setForeground(new Color(0, 0, 128));
			lblUser.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblUser.setBounds(556, 19, 76, 23);
		}
		return lblUser;
	}

	private JLabel getActiveUser() {
		if (activeUser == null) {
			activeUser = new JLabel();
			activeUser.setBackground(new Color(245, 245, 245));
			activeUser.setForeground(new Color(95, 158, 160));
			activeUser.setFont(new Font("Tahoma", Font.BOLD, 12));
			activeUser.setBounds(637, 20, 111, 22);
		}
		return activeUser;
	}

	private JButton getBtnLogout() {
		if (btnLogout == null) {
			btnLogout = new JButton("");
			btnLogout.setIcon(new ImageIcon("logout.png"));
			btnLogout.setBounds(752, 11, 54, 37);
			btnLogout.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					switchToLoginScreen();
				}
			});
		}
		return btnLogout;
	}

	private JButton getBtnExit() {
		if (btnExit == null) {
			btnExit = new JButton("Exit");
			btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnExit.setForeground(new Color(255, 0, 0));
			btnExit.setBounds(799, 446, 89, 30);
			btnExit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					WindowManager.ui.clear();
					System.exit(0);
				}
			});
		}
		return btnExit;
	}

	private void switchToLoginScreen() {
		LoginScreenLayout loginWindow = (LoginScreenLayout) WindowManager.ui
				.get("LoginScreenLayout");
		loginWindow.setVisible(true);

		StudentMangement studManagWindow = (StudentMangement) WindowManager.ui
				.get("StudentMangement");
		studManagWindow.dispose();
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 214, 878, 195);
			scrollPane.setViewportView(getStudTable());
		}
		return scrollPane;
	}

	private JLabel getAddrellLbl() {
		if (addrellLbl == null) {
			addrellLbl = new JLabel("Address");
			addrellLbl.setBounds(206, 83, 48, 19);
		}
		return addrellLbl;
	}

	private JTextField getAddressTxt() {
		if (addressTxt == null) {
			addressTxt = new JTextField();
			addressTxt.setColumns(10);
			addressTxt.setBounds(278, 80, 208, 20);
		}
		return addressTxt;
	}

}
