package Persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.json.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import entities.*;
import Factory.*;

public class StockBD {
    private IConnection conn;
    private final String filePath = "src/main/resources/";
    private String fileName = "Stock.json";

    public StockBD() {
        this.conn = FactoryBD.getConexionBD("TXT");
        this.conn.setNameTable(filePath + fileName);
    }

    public void readBD() {
        this.conn.getContent();
    }

    public void readListStockBD(Stock stock) {
        // aqui leeremos todos los elementos del stock
        readBD();
        JsonNode jsonNode;
        jsonNode = this.conn.getContentNodes();
        if (jsonNode != null) {
            JsonNode st = jsonNode.get(Stock.class.toString());
            for (JsonNode itemst : st) {
                // primero vendrá el qty, y despues el producto en sí
                JsonNode stiN = itemst.get("StockItem");
                for (JsonNode item : stiN) {
                    String type = item.get("Type").asText();
                    Product p = null;
                    if (type.equals(Flower.class.toString())) {
                        p = ProductFactory.create(item.get("Name").asText(), (float) item.get("Price").asDouble(),
                                item.get("Colour").asText());
                        p.setId(item.get("Id").asInt());
                    } else if (type.equals(Tree.class.toString())) {
                        p = ProductFactory.create(item.get("Name").asText(), (float) item.get("Price").asDouble(),
                                (float) item.get("Height").asDouble());
                        p.setId(item.get("Id").asInt());
                    } else if (type.equals(Decoration.class.toString())) {
                        p = ProductFactory.create(item.get("Name").asText(), (float) item.get("Price").asDouble(),
                                Material.valueOf(item.get("Material").asText()));
                        p.setId(item.get("Id").asInt());
                    }
                    if (p != null) {
                        stock.addProduct(p, itemst.get("Qty").asInt());
                    }
                }
            }
        }
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

    public void setFileName(String fileName) {
        this.fileName = fileName;
        this.conn.setNameTable(filePath + fileName);
    }
}
