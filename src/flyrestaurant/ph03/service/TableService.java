package flyrestaurant.ph03.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.androidtest8web.Db;
import com.androidtest8web.adapter.TableAdaper;

import flyrestaurant.entity.Table;




public class TableService {
	public List<Table> getTables() {
		String sql = "select * from tables";
		Connection connection = null;
		try {
			connection = Db.getConnection();
			return Db.query(connection, TableAdaper.SINGLETON, sql);
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
		return new ArrayList<Table>(0);
	}

	public List<Table> query(int seats, boolean needEmpty) {
		String sql = "select * from tables where seats - customers >= ? ";
		if (needEmpty) {
			sql += "and customers = 0";
		}
		Connection connection = null;
		try {
			connection = Db.getConnection();
			return Db.query(connection, TableAdaper.SINGLETON, sql,
					seats);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (connection != null) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return new ArrayList<Table>(0);
	}
}
