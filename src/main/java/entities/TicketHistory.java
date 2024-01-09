package entities;

import java.util.Iterator;
import java.util.List;

public class TicketHistory {
    List<Ticket> ticketsList;

    public TicketHistory() {
    }

    public void addTicket(Ticket ticket) {
        this.ticketsList.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        this.ticketsList.remove(ticket);
    }

    public void printAllTickets() {
        ticketsList.forEach(Ticket::printTicket);
    }
}
