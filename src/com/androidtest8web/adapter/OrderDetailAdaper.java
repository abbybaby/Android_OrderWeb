package com.androidtest8web.adapter;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.androidtest8web.Db;

import flyrestaurant.entity.OrderDetail;




public enum OrderDetailAdaper implements
		Db.ResultSetAdapter<OrderDetail> {
	SINGLETON;

	@Override
	public OrderDetail convert(ResultSet rs) throws SQLException {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.id = rs.getInt("id");
		orderDetail.foodId = rs.getInt("food_id");
		orderDetail.num = rs.getInt("num");
		orderDetail.description = rs.getString("description");
		return orderDetail;
	}
}
