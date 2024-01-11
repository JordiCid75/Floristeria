package entities;

import java.util.HashMap;
import java.util.Map;

public class TicketHistory {
    private HashMap<Ticket, Integer> ticketsInHistory;

    public void addTicketToHistory(Ticket ticket, int prize) {
        ticketsInHistory.put(ticket, prize);
    }

    public TicketHistory() {
        ticketsInHistory = new HashMap<>();
    }


    public int totalSells() {
        int suma = 0;
        // Iterar sobre las entradas del mapa
        for (Map.Entry<Ticket, Integer> entry : ticketsInHistory.entrySet()) {
            // Sumar el valor actual al resultado
            suma += entry.getValue();
        }
        return suma;
    }

    public void removeTicket(Ticket ticket) {
        this.ticketsInHistory.remove(ticket);
    }

    public void printAllTickets() {
        for (Map.Entry<Ticket, Integer> entry : ticketsInHistory.entrySet()) {
            Ticket ticket = entry.getKey();
            Integer prize = entry.getValue();
            System.out.println("Ticket: " + ticket + ", TOTAL: " + prize);
        }
    }
}
