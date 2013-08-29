package flyrestaurant.entity;

import java.io.Serializable;

public class FoodType implements Serializable {
	private static final long serialVersionUID = 1L;

	public int id;
	public String name;

	public FoodType() {
	}

	public FoodType(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
