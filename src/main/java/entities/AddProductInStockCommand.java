package entities;

import Factory.Product;

public class AddProductInStockCommand implements Command {
    private Stock stock;
    private Product product;
    private int quantity;

    public AddProductInStockCommand(Stock stock, Product product, int quantity) {
        this.stock = stock;
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public void execute() {
        stock.addProduct(product, quantity);
    }
}



