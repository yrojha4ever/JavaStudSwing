package com.javastud.studmproj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.javastud.studmproj.model.Student;

public class StudentDao {

	private Connection conn = null;

	public StudentDao() throws ClassNotFoundException, SQLException {
		conn = MysqlConnection.getConnection();
	}

	public void saveStudent(Student stud) throws SQLException {

		PreparedStatement stat = conn
				.prepareStatement("INSERT INTO student (id, name, address, birthdate, rollno, faculty, semester, collegename, sex) VALUES (?,?,?,?,?,?,?,?,?)");
		stat.setInt(1, stud.getId());// 1 specifies the first parameter in the
										// query
		stat.setString(2, stud.getName());
		stat.setString(3, stud.getAddress());
		stat.setDate(4, new java.sql.Date(stud.getBirthDate().getTime()));
		stat.setString(5, stud.getRollno());
		stat.setString(6, stud.getFaculty());
		stat.setString(7, stud.getSemester());
		stat.setString(8, stud.getCollegeName());
		stat.setString(9, stud.getSex());

		stat.executeUpdate();
	}

	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<Student>();

		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("select * from student");
			while (rs.next()) {// Loop Each Row

				Student stud = copyResultToStudent(rs);
				students.add(stud);

				System.out.println(stud);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return students;
	}

	private Student copyResultToStudent(ResultSet rs) throws SQLException,
			ParseException {
		Student stud = new Student();
		stud.setId(rs.getInt("id"));
		stud.setName(rs.getString("name"));

		String bdayStr = rs.getString("birthdate");
		Date bday = new SimpleDateFormat("YYYY-MM-DD").parse(bdayStr);
		stud.setBirthDate(bday);

		stud.setRollno(rs.getString("rollno"));
		stud.setFaculty(rs.getString("faculty"));
		stud.setSemester(rs.getString("semester"));
		stud.setCollegeName(rs.getString("collegename"));
		stud.setSex(rs.getString("sex"));
		stud.setAddress(rs.getString("address"));
		return stud;
	}
}
