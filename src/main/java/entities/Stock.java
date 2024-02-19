package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import Factory.Product;
import exceptions.ProductNotFoundException;

public class Stock {
    private HashMap<Product, Integer> productList;
    private double totalValue;

    public Stock() {
        productList = new HashMap<>();
        totalValue = 0;
    }

    public void executeCommand(Command command) {
        command.execute();
    }

    public void addProduct(Product product, int quantity) {
        productList.put(product, quantity);
        totalValue += product.getPrice() * quantity;
    }

    public void increaseProductQuantity(Product product, int quantity) {
        productList.merge(product, quantity, Integer::sum);
        totalValue += product.getPrice() * quantity;
    }

    public void decreaseProductQuantity(Product product, int quantity) {
        productList.merge(product, -quantity, Integer::sum);
        totalValue -= product.getPrice() * quantity;
    }

    public void removeProduct(Product product) {
        int quantity = productList.get(product);
        totalValue -= product.getPrice() * quantity;
        productList.remove(product);
    }

    public Product findProductById(int id) throws ProductNotFoundException {
        List<Product> list = new ArrayList<>(productList.keySet());
        Optional<Product> foundProduct = list.stream().filter(product -> product.getId() == id).findFirst();
        if (foundProduct.isPresent()) {
            return foundProduct.get();
        } else {
            throw new ProductNotFoundException("Product not found in stock");
        }
    }

    public int getProductQuantity(Product product) {
        return productList.getOrDefault(product, 0);
    }

    public double getTotalValue() {
        return totalValue;
    }

    public HashMap<Product, Integer> getProductList() {
        return productList;
    }
}

