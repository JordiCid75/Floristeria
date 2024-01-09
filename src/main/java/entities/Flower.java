package entities;

import org.json.JSONObject;

public class Flower extends Product {

	private String colour;

	public Flower(String name, float price, String colour) {
		super(name, price);
		this.colour = colour;
		this.type = Flower.class.toString();
	}

	public Flower(int _id, String name, double price, String colour) {
		super(_id);
		this.setName(name);
		this.setPrice(price);
		this.colour = colour;
		this.type = Flower.class.toString();
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	@Override
	public String toString() {
		return "Flower [colour=" + colour + ", price=" + price + ", name=" + name + ", id=" + id + "]";
	}

	@Override
	public JSONObject getJSONFormat() {
		JSONObject jsonProd = super.getJSONFormat();
		jsonProd.put("Colour", this.getColour());
		return jsonProd;
	}

}
