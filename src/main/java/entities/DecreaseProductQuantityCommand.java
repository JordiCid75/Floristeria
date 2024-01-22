package entities;

import Factory.Product;

public class DecreaseProductQuantityCommand implements Command {

    private Stock stock;
    private Product product;
    private int quantity;

    public DecreaseProductQuantityCommand(Stock stock, Product product, int quantity) {
        this.stock = stock;
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public void execute() {
        stock.decreaseProductQuantity(product, quantity);
    }
}
