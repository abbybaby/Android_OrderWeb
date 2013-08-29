package com.androidtest8web.adapter;

import java.sql.ResultSet;

import com.androidtest8web.Db;

import flyrestaurant.entity.Order;

import java.sql.SQLException;




public enum OrderAdaper implements Db.ResultSetAdapter<Order> {
	SINGLETON;

	@Override
	public Order convert(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.id = rs.getInt("id");
		order.code = rs.getString("code");
		order.tableId = rs.getInt("table_id");
		order.waiterId = rs.getInt("waiter_id");
		order.orderTime = rs.getTimestamp("order_time");
		order.customers = rs.getInt("customers");
		order.status = rs.getInt("status");
		order.description = rs.getString("description");
		return order;
	}
}
