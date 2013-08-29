package com.androidtest8web.adapter;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.androidtest8web.Db;

import flyrestaurant.entity.User;




public enum UserAdaper implements Db.ResultSetAdapter<User> {
	SINGLETON;

	@Override
	public User convert(ResultSet rs) throws SQLException {
		User user = new User();
		user.id = rs.getInt("id");
		user.code = rs.getString("code");
		user.password = rs.getString("password");
		user.name = rs.getString("name");
		return user;
	}
}
