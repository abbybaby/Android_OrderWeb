package flyrestaurant.entity;

import java.io.Serializable;

public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	public int id;
	public int orderId;
	public int foodId;
	public int num = 1;
	public String description;

	public OrderDetail() {
	}

	public OrderDetail(int id, int orderId, int foodId, int num) {
		this.id = id;
		this.orderId = orderId;
		this.foodId = foodId;
		this.num = num;
	}
}
