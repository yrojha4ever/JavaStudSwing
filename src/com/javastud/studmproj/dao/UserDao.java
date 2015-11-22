package com.javastud.studmproj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javastud.studmproj.model.User;

public class UserDao {
	private Connection conn = null;

	public UserDao() throws ClassNotFoundException, SQLException {
		conn = MysqlConnection.getConnection();
	}

	public boolean validateUser(User user) throws SQLException {
		PreparedStatement stmt = conn
				.prepareStatement("select * from user where username = ? and password = ?");
		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getPassword());
		ResultSet resultset = stmt.executeQuery();
		if (resultset.next()) {
			return true;
		} else {
			return false;
		}
	}
}
