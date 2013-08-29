package com.androidtest8web.adapter;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.androidtest8web.Db;

import flyrestaurant.entity.Table;




public enum TableAdaper implements Db.ResultSetAdapter<Table> {
	SINGLETON;

	@Override
	public Table convert(ResultSet rs) throws SQLException {
		Table table = new Table();
		table.id = rs.getInt("id");
		table.code = rs.getString("code");
		table.seats = rs.getInt("seats");
		table.customers = rs.getInt("customers");
		table.description = rs.getString("description");
		return table;
	}
}
