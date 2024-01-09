package entities;

import org.json.JSONObject;

public class Tree extends Product{

    private double height;

	public Tree(String name, double price, double height) {
		super(name, price);
		this.height = height;
		this.type = Tree.class.toString();
	}

	public Tree(int _id, String name, double price, double height) {
		super(_id);
		this.setName(name);
		this.setPrice(price);
		this.height = height;
		this.type = Tree.class.toString();
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
    @Override
	public String toString() {
		return "Tree [height=" + height + ", price=" + price + ", name=" + name + ", id=" + id + "]";
	}
	@Override
	public JSONObject getJSONFormat() {
		JSONObject jsonProd = super.getJSONFormat();
		jsonProd.put("Height", this.getHeight());
		return jsonProd;
	}
}
