package Persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;

import entities.Decoration;
import entities.Flower;
import entities.Material;
import entities.Product;
import entities.Stock;
import entities.Tree;

public class StockBD {

	private IConnection conn;
	private final String filePath = "src/main/resources/";
	private final String fileName = "Stock.json";

	public StockBD() {
		this.conn = FactoryBD.getConexionBD("TXT");
		this.conn.setNameTable(filePath + fileName);
	}

	public void readBD() {
		this.conn.getContent();
	}

	public HashMap<Product, Integer> readListStockBD() {
		HashMap<Product, Integer> lista = new HashMap<Product, Integer>();
		// aqui leeremos todos los elementos del stock

		JsonNode jsonNode;
		jsonNode = this.conn.getContentNodes();

		JsonNode st = jsonNode.get(Stock.class.toString());
		for (JsonNode itemst : st) {
			// primero vendrá el qty, y despues el producto en sí

			JsonNode stiN = itemst.get("StockItem");
			for (JsonNode item : stiN) {

				String type = item.get("Type").asText();
//				if (tipo.equals(Flower.class.toString())) {
//					Flower fl = new Flower(item.get("Id").asInt(), item.get("Name").asText(),
//							item.get("Price").asDouble(), item.get("Colour").asText());
//					lista.put(fl, itemst.get("Qty").asInt());
//
//				} else if (tipo.equals(Tree.class.toString())) {
//					Tree tr = new Tree(item.get("Id").asInt(), item.get("Name").asText(), item.get("Price").asDouble(),
//							item.get("Height").asDouble());
//					lista.put(tr, itemst.get("Qty").asInt());
//
//				} else if (tipo.equals(Decoration.class.toString())) {
//					Decoration dc = new Decoration(item.get("Id").asInt(), item.get("Name").asText(),
//							item.get("Price").asDouble(), Material.valueOf(item.get("Material").asText()));
//					lista.put(dc, itemst.get("Qty").asInt());
//
//				}
			}
		}

		return lista;

	}

	public void write(Stock stock) {
		JSONObject jsonStock = new JSONObject();
		ArrayList<JSONObject> jsonStockItems = new ArrayList<JSONObject>();
		stock.getProductList().forEach((p, qty) -> {
			JSONObject jsonItemp = new JSONObject();
			JSONObject jsonItem = new JSONObject();
			jsonItemp.put("Product", getJSONFormat(p));
			jsonItem.put("Qty", qty);
			jsonItem.put("StockItem", jsonItemp);

			jsonStockItems.add(jsonItem);
		});

		jsonStock.put(Stock.class.toString(), jsonStockItems);
		this.conn.write(jsonStock.toString());
	}

	private JSONObject getJSONFormat(Product p) {
		JSONObject obj = new JSONObject();
		obj.put("Type", p.getClass().toString());
		obj.put("Id", p.getId());
		obj.put("Name", p.getName());
		obj.put("Price", p.getPrice());
		
		if (p.getClass().equals(Flower.class)) {
			Flower fl = (Flower) p;
			obj.put("Colour", fl.getColour());
		}
		if (p.getClass().equals(Tree.class)) {
			Tree tr = (Tree) p;
			obj.put("Height", tr.getHeight());
		}
		if (p.getClass().equals(Decoration.class)) {
			Decoration dc = (Decoration) p;
			obj.put("Material", dc.getMaterial());
		}
		
		return obj;
	}

}
