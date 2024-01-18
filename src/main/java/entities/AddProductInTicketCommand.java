package entities;

import Factory.Product;

public class AddProductInTicketCommand implements Command {
    private Ticket ticket;
    private Product product;
    private int quantity;

    public AddProductInTicketCommand(Ticket ticket, Product product, int quantity) {
        this.ticket = ticket;
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public void execute() {
        ticket.addProductInTicket(product, quantity);
    }
}

