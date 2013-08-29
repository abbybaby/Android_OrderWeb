package flyrestaurant.ph03.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


import com.androidtest8web.Db;
import com.androidtest8web.adapter.OrderAdaper;
import com.androidtest8web.adapter.OrderDetailAdaper;

import flyrestaurant.entity.Order;
import flyrestaurant.entity.OrderDetail;




public class OrderService {

	public void addOrder(Order order) {
		String sql1 = "update tables set customers = ? where id = ?";
		String sql2 = "insert into orders "
				+ "(code, table_id, waiter_id, order_time, customers, status, description) "
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		String sql3 = "insert into order_detail "
				+ "(order_id,food_id, num, description) "
				+ "values (?, ?, ?, ?)";
		Connection connection = null;
		try {
			connection = Db.getConnection();
			connection.setAutoCommit(false);
			Db.update(connection, sql1, order.customers, order.tableId);
			List<Object> list = Db.updateAndGetId(connection, sql2,
					new int[] { 1 },(int)(Math.random()*100) , order.tableId,
					order.waiterId, new Date(), order.customers, 0,
					order.description);

			List<Object[]> params = new ArrayList<Object[]>();
			for (OrderDetail orderDetail : order.orderDetails) {
				Object[] p = new Object[4];
				p[0] = list.get(1);
				p[1] = orderDetail.foodId;
				p[2] = orderDetail.num;
				p[3] = orderDetail.description;
				params.add(p);
			}
			Db.updateBatch(connection, sql3,
					params.toArray(new Object[params.size()][]));
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (connection != null) {
					connection.rollback();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
			}
		}
	}

	public Order getOrder(String code) {
		String sql1 = "select * from orders where code = ?";
		String sql2 = "select * from order_detail where order_id = ?";
		Connection connection = null;
		try {
			connection = Db.getConnection();
			Order order = Db.get(connection, OrderAdaper.SINGLETON,
					sql1, code);
			if (order == null) {
				return null;
			}
			List<OrderDetail> orderDetails = Db.query(connection,
					OrderDetailAdaper.SINGLETON, sql2, order.id);
			order.orderDetails = orderDetails;
			return order;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return null;
	}

	public void pay(int orderId) {
		String sql1 = "update orders set status = 1 where id = ?";
		String sql2 = "update tables set customers = 0 where id = "
				+ "(select table_id from orders where orders.id = ?)";
		Connection connection = null;
		try {
			connection = Db.getConnection();
			connection.setAutoCommit(false);

			Db.update(connection, sql1, orderId);
			Db.update(connection, sql2, orderId);

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
