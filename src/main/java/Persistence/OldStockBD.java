package Persistence;

import Factory.Decoration;
import Factory.Flower;
import Factory.Product;
import Factory.Tree;
import com.fasterxml.jackson.databind.JsonNode;
import entities.Material;
import entities.ProductFactory;
import entities.Stock;
import org.json.JSONObject;

import java.util.ArrayList;

public class OldStockBD extends StockBD {
    public OldStockBD() {
        super();
        setFileName("OldStock.json");
    }


}
