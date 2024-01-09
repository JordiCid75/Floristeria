package entities;

import java.util.Collection;

import org.json.JSONObject;

public abstract class Product {

	protected double price;
	protected String name;
	protected int id;
	private static int contador = 1;
	protected String type;

	public Product(int _id) {
		this.setId(_id);
	}

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
		this.setId(contador++);
	}

	public String getTipo() {
		return type;
	}

	public void setTipo(String tipo) {
		this.type = tipo;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int _id) {
		this.id = _id;
		if (contador < _id) {
			contador = _id;
		}

	}

	public JSONObject getJSONFormat() {
		// TODO Auto-generated method stub
		JSONObject prd = new JSONObject();
		prd.put("Id", this.getId());
		prd.put("Name", this.getName());
		prd.put("Tipo", this.getTipo());
		prd.put("Price", this.getPrice());
		return prd;
	}

}
