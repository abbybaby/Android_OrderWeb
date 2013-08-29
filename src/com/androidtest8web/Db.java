package com.androidtest8web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Db {

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/ordersystemdb", "root", "tzj88679917");
	}

	public static <T> List<T> query(Connection connection,
			ResultSetAdapter<T> adapter, String sql, Object... params)
			throws SQLException {
		List<T> list = new ArrayList<T>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 1;
		try {
			ps = connection.prepareStatement(sql);
			for (Object p : params) {
				if (p instanceof java.util.Date) {
					ps.setTimestamp(i++, new java.sql.Timestamp(
							((java.util.Date) p).getTime()));
				} else {
					ps.setObject(i++, p);
				}
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(adapter.convert(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		}
		return list;
	}

	public static <T> T get(Connection connection, ResultSetAdapter<T> adapter,
			String sql, Object... params) throws SQLException {
		List<T> list = query(connection, adapter, sql, params);
		if (list.isEmpty()) {
			return null;
		}
		System.out.println(list.get(0));
		return list.get(0);
	}

	public static int update(Connection connection, String sql,
			Object... params) throws SQLException {
		PreparedStatement ps = null;
		int i = 1;
		int resultCode = -1;
		try {
			ps = connection.prepareStatement(sql);
			for (Object p : params) {
				if (p instanceof java.util.Date) {
					ps.setTimestamp(i++, new java.sql.Timestamp(
							((java.util.Date) p).getTime()));
				} else {
					ps.setObject(i++, p);
				}
			}
			resultCode = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
		return resultCode;
	}

	public static List<Object> updateAndGetId(Connection connection,
			String sql, int[] keyColumns, Object... params) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Object> r = new ArrayList<Object>();
		int i = 1;
		try {
			ps = connection.prepareStatement(sql, keyColumns);
			for (Object p : params) {
				if (p instanceof java.util.Date) {
					ps.setTimestamp(i++, new java.sql.Timestamp(
							((java.util.Date) p).getTime()));
				} else {
					ps.setObject(i++, p);
				}
			}
			r.add(ps.executeUpdate());
			rs = ps.getGeneratedKeys();
			while (rs.next()) {
				for (int k : keyColumns) {
					r.add(rs.getObject(k));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		}
		return r;
	}

	public static int[] updateBatch(Connection connection, String sql,
			Object[]... params) throws SQLException {
		PreparedStatement ps = null;
		int[] resultCodes = null;
		try {
			ps = connection.prepareStatement(sql);
			for (Object[] pp : params) {
				int i = 1;
				for (Object p : pp) {
					if (p instanceof java.util.Date) {
						ps.setTimestamp(i++, new java.sql.Timestamp(
								((java.util.Date) p).getTime()));
					} else {
						ps.setObject(i++, p);
					}
				}
				ps.addBatch();
			}
			resultCodes = ps.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
		return resultCodes;
	}

	public static interface ResultSetAdapter<T> {
		T convert(ResultSet aRowInResultSet) throws SQLException;
	}
}
