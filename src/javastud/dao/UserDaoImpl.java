package javastud.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImpl extends DB implements UserDao {

	@Override
	public boolean validateUser(String username, String password) {
		try {

			Connection conn = getDBConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(
					"SELECT * FROM user where username = '" + username + "' and password = '" + password + "'");
			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

}
