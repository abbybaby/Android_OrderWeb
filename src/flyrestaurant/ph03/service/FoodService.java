package flyrestaurant.ph03.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.androidtest8web.Db;
import com.androidtest8web.adapter.FoodAdaper;
import com.androidtest8web.adapter.FoodTypeAdaper;

import flyrestaurant.entity.Food;
import flyrestaurant.entity.FoodType;




public class FoodService {
	public List<Food> getFoods() {
		String sql = "select * from food";
		Connection connection = null;
		try {
			connection = Db.getConnection();
			return Db.query(connection, FoodAdaper.SINGLETON, sql);
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
		return new ArrayList<Food>(0);
	}

	public List<FoodType> getFoodTypes() {
		String sql = "select * from food_type";
		Connection connection = null;
		try {
			connection = Db.getConnection();
			return Db.query(connection, FoodTypeAdaper.SINGLETON, sql);
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
		return new ArrayList<FoodType>(0);
	}
}
