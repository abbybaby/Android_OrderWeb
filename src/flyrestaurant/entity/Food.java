package flyrestaurant.entity;

import java.io.Serializable;

public class Food implements Serializable {
	private static final long serialVersionUID = 1L;

	public int id;
	public String code;
	public int typeId;
	public String name;
	public int price;
	public String description;

	public Food() {
	}

	public Food(int id, String code, int typeId, String name, int price) {
		this.id = id;
		this.code = code;
		this.typeId = typeId;
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return this.code + " " + this.name + " гд" + this.price;
	}
}
