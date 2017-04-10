package javastud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DB {

	public Connection getDBConnection() throws ClassNotFoundException, SQLException {
		// 1. Load Driver
		Class.forName("com.mysql.jdbc.Driver");

		// 2. Connection
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studdb", "root", "");

		return conn;
	}
}
