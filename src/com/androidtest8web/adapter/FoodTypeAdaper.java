package com.androidtest8web.adapter;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.androidtest8web.Db;

import flyrestaurant.entity.FoodType;




public enum FoodTypeAdaper implements Db.ResultSetAdapter<FoodType> {
	SINGLETON;

	@Override
	public FoodType convert(ResultSet rs) throws SQLException {
		FoodType foodType = new FoodType();
		foodType.id = rs.getInt("id");
		foodType.name = rs.getString("name");
		return foodType;
	}
}
