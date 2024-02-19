package entities;

import Factory.Product;

import java.util.HashMap;

public class OldStock extends Stock {

    private HashMap<Product, Integer> productList;
    public OldStock() {
        super();
    }

    @Override
    public HashMap<Product, Integer> getProductList() {
        return this.productList;
    }
}