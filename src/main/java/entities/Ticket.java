package entities;

import java.util.HashMap;

public class Ticket {
	private HashMap<Product, Integer> listaTicket = new HashMap<Product, Integer>();

	public void addItem(Product prod, int qty) {
		listaTicket.put(prod, qty);
	}

	public void addQty(Product prod, int qty) {

		listaTicket.merge(prod, qty, Integer::sum);

	}

	public void removeQty(Product prod, int qty) {
		listaTicket.merge(prod, -qty, Integer::sum);
		/*
		 * StockItem sti = this.findStockItem(prod.getId()); if (sti.getCantidad() >
		 * qty) { sti.setCantidad(sti.getCantidad() - qty); } else { sti.setCantidad(0);
		 * }
		 */

	}

}
