package flyrestaurant.ph03.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.androidtest8web.Db;
import com.androidtest8web.adapter.UserAdaper;

import flyrestaurant.entity.User;




public class UserService {
	public User login(String code, String password) {
		String sql = "select * from users where code = ? and password = ?";
		Connection connection = null;
		try {
			connection = Db.getConnection();
			System.out.println("Got it");
			return Db.get(connection, UserAdaper.SINGLETON, sql, code,
					password);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
