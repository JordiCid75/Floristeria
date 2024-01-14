package entities;

import java.util.HashMap;
import java.util.Map;

public class TicketHistory {
    private HashMap<Ticket, Float> ticketsInHistory;

    public void addTicketToHistory(Ticket ticket, float prize) {
        ticketsInHistory.put(ticket, prize);
    }

    public TicketHistory() {
        ticketsInHistory = new HashMap<>();
    }


    public HashMap<Ticket, Float> getTicketsInHistory() {
		return ticketsInHistory;
	}

	public void setTicketsInHistory(HashMap<Ticket, Float> ticketsInHistory) {
		this.ticketsInHistory = ticketsInHistory;
	}

	public float totalSells() {
        float suma = 0;
        // Iterar sobre las entradas del mapa
        for (Map.Entry<Ticket, Float> entry : ticketsInHistory.entrySet()) {
            // Sumar el valor actual al resultado
            suma += entry.getValue();
        }
        return suma;
    }

    public void removeTicket(Ticket ticket) {
        this.ticketsInHistory.remove(ticket);
    }

    public void printAllTickets() {
        for (Map.Entry<Ticket, Float> entry : ticketsInHistory.entrySet()) {
            Ticket ticket = entry.getKey();
            Float prize = entry.getValue();
            System.out.println("Ticket: " + ticket + ", TOTAL: " + prize);
        }
    }
}
