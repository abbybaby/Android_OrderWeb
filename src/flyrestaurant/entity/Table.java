package flyrestaurant.entity;

import java.io.Serializable;

public class Table implements Serializable {
	private static final long serialVersionUID = 1L;

	public int id;
	public String code;
	public int seats;
	public int customers;
	public String description;

	public Table() {
	}

	public Table(int id, String code, int seats, int customers,
			String description) {
		this.id = id;
		this.code = code;
		this.seats = seats;
		this.customers = customers;
		this.description = description;
	}

	@Override
	public String toString() {
		return this.code;
	}
}
