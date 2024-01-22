package Persistence;

import org.json.JSONObject;

import entities.FlowerShop;

public class FlowerShopBD {

	private IConnection conn;
	private final String filePath = "src/main/resources/";
	private final String fileName = "FlowerShop.json";

	public FlowerShopBD() {
		this.conn = FactoryBD.getConexionBD("TXT");
		this.conn.setNameTable(filePath + fileName);
	}

	public void readBD() {
		this.conn.getContent();

	}

	public String getShopName() {
		this.readBD();
		String nom = this.conn.getField("Name");
		return nom;
	}

	public void write(FlowerShop shop) {
		JSONObject jsonShop = new JSONObject();
		jsonShop.put("Name", shop.getName());
		this.conn.write(jsonShop.toString());
	}

}
