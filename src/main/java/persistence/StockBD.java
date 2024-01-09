package persistence;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;

import entities.Decoration;
import entities.Flower;
import entities.Product;
import entities.Stock;
import entities.Tree;
import interfaces.IConexion;

public class StockBD {

	private IConexion conn;

	public StockBD() {
		this.conn = FactoryBD.getConexionBD("TXT");
		this.conn.setNombreTabla("Stock.txt");
	}

	public void LeerBD() {
		this.conn.getContent();
	}

	public String getNombre() {
		// return null;
		String nom = this.conn.getField("nombre");
		return nom;
	}

	public HashMap<Product, Integer> readListaStockBD(){
		HashMap<Product, Integer> lista = new HashMap<Product, Integer>();
		// aqui leeremos todos los elementos del stock
		
		JsonNode jsonNode;
		jsonNode = this.conn.getContentNodes();
		
		JsonNode st = jsonNode.get(Stock.class.toString());
		for (JsonNode itemst: st) {
			//primero vendrá el qty, y despues el producto en sí
			
			JsonNode prod = itemst.get("StockItem");
			for (JsonNode item: prod) {
			String tipo = item.get("tipo").asText();
			if (tipo.equals(Flower.class.toString()))
			{
				Flower fl = new Flower(item.get("Id").asInt(),item.get("Name").asText(),item.get("Price").asDouble(),item.get("Colour").asText());
				lista.put(fl, itemst.get("Qty").asInt());
				
			}else if (tipo.equals(Tree.class.toString()))
			{
				Tree tr = new Tree(item.get("Id").asInt(),item.get("Name").asText(),item.get("Price").asDouble(),item.get("Height").asDouble());
				lista.put(tr, itemst.get("Qty").asInt());

			}else if (tipo.equals(Decoration.class.toString()))
			{
				Decoration dc = new Decoration(prod.get("id").asInt(),prod.get("name").asText(),prod.get("material").asText());
				lista.put(dc, itemst.get("Qty").asInt());

			}
			}
		}

		return lista;
	}
	public void write(Stock stock) {
		JSONObject jsonStock = new JSONObject();
		ArrayList<JSONObject> jsonStockItems = new ArrayList<JSONObject>();
		stock.getListaStock().forEach((p, qty) -> {
			JSONObject jsonItemp = new JSONObject();
			JSONObject jsonItem = new JSONObject();
			jsonItemp.put("Product", p.getJSONFormat());
			jsonItem.put("Qty", qty);
			jsonItem.put("StockItem", jsonItemp);

			jsonStockItems.add(jsonItem);
		});

		jsonStock.put(Stock.class.toString(), jsonStockItems);
		this.conn.write(jsonStock);
	}

}
