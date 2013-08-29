package flyrestaurant.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	public int id;
	public String code;
	public int tableId;
	public int waiterId;
	public Date orderTime;
	public int customers;
	public int status;
	public String description;

	public List<OrderDetail> orderDetails;

	public Order() {
	}

	public Order(int id, String code, int tableId, int waiterId,
			Date orderTime, int customers, int status,
			List<OrderDetail> orderDetails) {
		this.id = id;
		this.code = code;
		this.tableId = tableId;
		this.waiterId = waiterId;
		this.orderTime = orderTime;
		this.customers = customers;
		this.status = status;
		this.orderDetails = orderDetails;
	}
}
