package entities;

import Factory.Product;

public class IncreaseProductQuantityCommand implements Command {
    private Stock stock;
    private Product product;
    private int quantity;

    public IncreaseProductQuantityCommand(Stock stock, Product product, int quantity) {
        this.stock = stock;
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public void execute() {
        stock.increaseProductQuantity(product, quantity);
    }
}