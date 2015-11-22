package com.javastud.studmproj.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/studentdb", "root", "");
		return con;
	}

	// INSERT INTO `studentdb`.`student` (`id`, `name`, `address`, `birthdate`,
	// `rollno`, `faculty`, `semester`, `collegename`, `sex`) VALUES ('1',
	// 'YRO', 'KTM', '2015-11-21', '1', 'PCM', '6', 'ASCOL', 'Male');

	/***** update ***/
	// UPDATE `studentdb`.`student` SET `semester` = '8' WHERE `id` = '1' AND
	// `name` = 'YRO' AND `address` = 'KTM' AND `birthdate` = '2015-11-21' AND
	// `rollno` = '1' AND `faculty` = 'PCM' AND `semester` = '6' AND
	// `collegename` = 'ASCOL' AND `sex` = 'Male';

	/*** delete ***/
	// delete from `student` where `id`=1;

	/*** select ***/
	// select * from student

	/*** Modify field name from user->username ***/
	// ALTER TABLE `studentdb`.`user` CHANGE `name` `username` VARCHAR(255)
	// CHARSET latin1 COLLATE latin1_swedish_ci NULL;

}
