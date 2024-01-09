package entities;

import org.json.JSONObject;

public class Decoration extends Product {
	private Material material;

	public Decoration(String name, double price, Material material) {
		super(name, price);
		this.material = material;
		this.type = Decoration.class.toString();
	}

	public Decoration(int _id, String name, double price, Material material) {
		super(_id);
		this.setName(name);
		this.setPrice(price);
		this.material = material;
		this.type = Decoration.class.toString();
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "Decoration [material=" + material + ", price=" + price + ", name=" + name + ", id=" + id + "]";
	}

	@Override
	public JSONObject getJSONFormat() {
		JSONObject jsonProd = super.getJSONFormat();
		jsonProd.put("Material", this.getMaterial());
		return jsonProd;
	}
}
