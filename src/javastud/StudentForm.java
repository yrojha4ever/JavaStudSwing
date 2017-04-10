package javastud;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import javastud.dao.StudentDao;
import javastud.dao.StudentDaoImpl;
import javastud.model.Student;

public class StudentForm extends JFrame {

	private JPanel contentPane;
	private JPanel studentPnl;
	private JLabel nameLbl;
	private JTextField nameTxt;
	private JLabel rollNoLbl;
	private JTextField rollNoTxt;
	private JLabel phoneNoLbl;
	private JTextField phoneNoTxt;
	private JPanel genderPnl;
	private JRadioButton maleRdo;
	private JRadioButton femaleRdo;
	private JLabel emailLbl;
	private JTextField emailTxt;
	private JLabel collegeNameLbl;
	private JTextField collegeNameTxt;
	private JLabel addressLbl;
	private JTextField addressTxt;
	private JButton btnSave;
	private JTable studentTbl;
	private JScrollPane scrollPane;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem fileMenuExit;
	
	private ButtonGroup group;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentForm frame = new StudentForm();
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
	public StudentForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getStudentPnl());
		contentPane.add(getScrollPane());
		contentPane.add(getMenuBar_1());

		showAllStudents();
	}

	private JPanel getStudentPnl() {
		if (studentPnl == null) {
			studentPnl = new JPanel();
			studentPnl.setBorder(
					new TitledBorder(null, "Student Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			studentPnl.setBounds(10, 23, 570, 174);
			studentPnl.setLayout(null);
			studentPnl.add(getNameLbl());
			studentPnl.add(getNameTxt());
			studentPnl.add(getRollNoLbl());
			studentPnl.add(getRollNoTxt());
			studentPnl.add(getPhoneNoLbl());
			studentPnl.add(getPhoneNoTxt());
			studentPnl.add(getGenderPnl());
			studentPnl.add(getEmailLbl());
			studentPnl.add(getEmailTxt());
			studentPnl.add(getCollegeNameLbl());
			studentPnl.add(getCollegeNameTxt());
			studentPnl.add(getAddressLbl());
			studentPnl.add(getAddressTxt());
			studentPnl.add(getBtnSave());
		}
		return studentPnl;
	}

	private JLabel getNameLbl() {
		if (nameLbl == null) {
			nameLbl = new JLabel("Name*");
			nameLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
			nameLbl.setForeground(Color.RED);
			nameLbl.setBounds(10, 22, 59, 21);
		}
		return nameLbl;
	}

	private JTextField getNameTxt() {
		if (nameTxt == null) {
			nameTxt = new JTextField();
			nameTxt.setBounds(64, 23, 118, 20);
			nameTxt.setColumns(10);
		}
		return nameTxt;
	}

	private JLabel getRollNoLbl() {
		if (rollNoLbl == null) {
			rollNoLbl = new JLabel("Roll No*");
			rollNoLbl.setForeground(Color.RED);
			rollNoLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
			rollNoLbl.setBounds(192, 22, 66, 21);
		}
		return rollNoLbl;
	}

	private JTextField getRollNoTxt() {
		if (rollNoTxt == null) {
			rollNoTxt = new JTextField();
			rollNoTxt.setColumns(10);
			rollNoTxt.setBounds(255, 23, 118, 20);
		}
		return rollNoTxt;
	}

	private JLabel getPhoneNoLbl() {
		if (phoneNoLbl == null) {
			phoneNoLbl = new JLabel("Phone No:");
			phoneNoLbl.setForeground(Color.BLACK);
			phoneNoLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
			phoneNoLbl.setBounds(379, 22, 72, 21);
		}
		return phoneNoLbl;
	}

	private JTextField getPhoneNoTxt() {
		if (phoneNoTxt == null) {
			phoneNoTxt = new JTextField();
			phoneNoTxt.setColumns(10);
			phoneNoTxt.setBounds(445, 23, 118, 20);
		}
		return phoneNoTxt;
	}

	private JPanel getGenderPnl() {
		if (genderPnl == null) {
			genderPnl = new JPanel();
			genderPnl.setBorder(new TitledBorder(null, "Gender", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			genderPnl.setBounds(10, 54, 159, 75);
			genderPnl.setLayout(null);
			genderPnl.add(getMaleRdo());
			genderPnl.add(getFemaleRdo());
			
			group = new ButtonGroup();
			group.add(maleRdo);
			group.add(femaleRdo);
			
		}
		return genderPnl;
	}

	private JRadioButton getMaleRdo() {
		if (maleRdo == null) {
			maleRdo = new JRadioButton("Male");
			maleRdo.setFont(new Font("Tahoma", Font.BOLD, 13));
			maleRdo.setBounds(6, 23, 65, 23);
		}
		return maleRdo;
	}

	private JRadioButton getFemaleRdo() {
		if (femaleRdo == null) {
			femaleRdo = new JRadioButton("Female");
			femaleRdo.setFont(new Font("Tahoma", Font.BOLD, 13));
			femaleRdo.setBounds(73, 23, 74, 23);
		}
		return femaleRdo;
	}

	private JLabel getEmailLbl() {
		if (emailLbl == null) {
			emailLbl = new JLabel("Email:");
			emailLbl.setForeground(Color.BLACK);
			emailLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
			emailLbl.setBounds(179, 63, 51, 21);
		}
		return emailLbl;
	}

	private JTextField getEmailTxt() {
		if (emailTxt == null) {
			emailTxt = new JTextField();
			emailTxt.setColumns(10);
			emailTxt.setBounds(226, 64, 147, 20);
		}
		return emailTxt;
	}

	private JLabel getCollegeNameLbl() {
		if (collegeNameLbl == null) {
			collegeNameLbl = new JLabel("College Name:");
			collegeNameLbl.setForeground(Color.BLACK);
			collegeNameLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
			collegeNameLbl.setBounds(179, 108, 98, 21);
		}
		return collegeNameLbl;
	}

	private JTextField getCollegeNameTxt() {
		if (collegeNameTxt == null) {
			collegeNameTxt = new JTextField();
			collegeNameTxt.setColumns(10);
			collegeNameTxt.setBounds(282, 109, 135, 20);
		}
		return collegeNameTxt;
	}

	private JLabel getAddressLbl() {
		if (addressLbl == null) {
			addressLbl = new JLabel("Address:");
			addressLbl.setForeground(Color.BLACK);
			addressLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
			addressLbl.setBounds(379, 63, 72, 21);
		}
		return addressLbl;
	}

	private JTextField getAddressTxt() {
		if (addressTxt == null) {
			addressTxt = new JTextField();
			addressTxt.setColumns(10);
			addressTxt.setBounds(445, 64, 118, 20);
		}
		return addressTxt;
	}

	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("Save");
			btnSave.setForeground(Color.BLUE);
			btnSave.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnSave.setBounds(427, 106, 136, 57);
			btnSave.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					// 1. check Validation
					if (nameTxt.getText().trim().equals("")) {
						nameTxt.setBackground(Color.PINK);
						JOptionPane.showMessageDialog(contentPane, "Please Enter Name!");
						return;
					} else {
						nameTxt.setBackground(Color.WHITE);
					}

					if (rollNoTxt.getText().trim().equals("")) {
						rollNoTxt.setBackground(Color.PINK);
						JOptionPane.showMessageDialog(contentPane, "Please Enter Roll No!");
						return;
					} else {
						rollNoTxt.setBackground(Color.WHITE);
					}

					// Save Data to DB.
					saveDataToDB();

					// Show all records.
					showAllStudents();
					
					//Clear Fields
					clearFields();

				}
			});
		}
		return btnSave;
	}

	private void saveDataToDB() {

		Student stud = new Student();
		stud.setName(nameTxt.getText().trim());
		stud.setRollNo(rollNoTxt.getText());
		stud.setPhoneNo(phoneNoTxt.getText());
		if (maleRdo.isSelected()) {
			stud.setGender("MALE");
		} else if (femaleRdo.isSelected()) {
			stud.setGender("FEMALE");
		}
		stud.setEmail(emailTxt.getText());
		stud.setAddress(addressTxt.getText());
		stud.setCollegeName(collegeNameTxt.getText());

		// Save to DB
		try {
			StudentDao dao = new StudentDaoImpl();
			dao.saveStudent(stud);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void showAllStudents() {
		// clear table
		DefaultTableModel model = (DefaultTableModel) studentTbl.getModel();
		model.setRowCount(0);

		try {
			StudentDao dao = new StudentDaoImpl();
			List<Student> studList = dao.getAllStudents();

			for (Student stud : studList) {
				Object[] row = new Object[] { stud.getId(), stud.getName(), stud.getRollNo(),
						stud.getPhoneNo(), stud.getGender(), stud.getCollegeName(), 
						stud.getAddress(), stud.getEmail() };
				model.addRow(row);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void clearFields(){
		nameTxt.setText("");
		rollNoTxt.setText("");
		phoneNoTxt.setText("");
		emailTxt.setText("");
		addressTxt.setText("");
		collegeNameTxt.setText("");
		
		group.clearSelection();
	}

	private JTable getStudentTbl() {
		if (studentTbl == null) {
			studentTbl = new JTable();
			studentTbl
					.setModel(new DefaultTableModel(
							new Object[][] { { null, null, null, null, null, null, null, null }, { null, null, null,
									null, null, null, null, null }, { null, null, null, null, null, null, null, null },
					{ null, null, null, null, null, null, null, null },
					{ null, null, null, null, null, null, null, null }, },
					new String[] { "ID", "Name", "Roll No", "Phone No", "Gender", "College", "Address", "Email" }));
		}
		return studentTbl;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 208, 568, 174);
			scrollPane.setViewportView(getStudentTbl());
		}
		return scrollPane;
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.setBounds(10, 0, 97, 21);
			menuBar.add(getMnFile());
		}
		return menuBar;
	}

	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.add(getFileMenuExit());
		}
		return mnFile;
	}

	private JMenuItem getFileMenuExit() {
		if (fileMenuExit == null) {
			fileMenuExit = new JMenuItem("Exit");
			fileMenuExit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0); // Process kill
				}
			});
		}
		return fileMenuExit;
	}
}
