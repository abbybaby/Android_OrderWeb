package com.androidtest8web.adapter;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.androidtest8web.Db;

import flyrestaurant.entity.Food;




public enum FoodAdaper implements Db.ResultSetAdapter<Food> {
	SINGLETON;

	@Override
	public Food convert(ResultSet rs) throws SQLException {
		Food food = new Food();
		food.id = rs.getInt("id");
		food.code = rs.getString("code");
		food.typeId = rs.getInt("type_id");
		food.price = rs.getInt("price");
		food.name = rs.getString("name");
		food.description = rs.getString("description");
		return food;
	}
}
