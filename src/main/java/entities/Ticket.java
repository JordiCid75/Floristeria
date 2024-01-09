package entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ticket {
    List<Product> products;
    private int id;

    public Ticket() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return "Ticket [products=" + products + "]";
    }

    public void printTicket() {
        double totalPrice = 0;
        for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
            Product product = (Product) iterator.next();
            String productName = product.getName();
            double productPrice = product.getPrice();
            System.out.println("Name: " + productName + ", Price: " + productPrice);
            totalPrice = totalPrice + productPrice;
        }
        System.out.println("TOTAL "+totalPrice+ " €");
    }
}
