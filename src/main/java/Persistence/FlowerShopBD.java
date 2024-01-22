package Persistence;

import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.Statement;

import entities.FlowerShop;

public class FlowerShopBD implements IConnectionType {

	private IConnection conn;
	private final String fileName = "FlowerShop.json";
	private final String mySqlTableName = "FlowerShop";
	private Statement stmt = null;
	private ResultSet rs = null;

	public FlowerShopBD() {

		this.conn = FactoryBD.getConexionBD(connType);
		if (connType.equals("TXT")) {
			this.conn.setNameTable(fileName);
		}
		if (connType.equals("MYSQL")) {
			this.conn.setNameTable(mySqlTableName);
		}
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
		if (connType.equals("TXT")) {
			JSONObject jsonShop = new JSONObject();
			jsonShop.put("Name", shop.getName());
			this.conn.write(jsonShop.toString());
		}
		if (connType.equals("MYSQL")) {
		}
	}

}
