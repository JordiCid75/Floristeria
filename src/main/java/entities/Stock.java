package entities;

import java.util.ArrayList;

public class Stock {
	private ArrayList<StockItem> listaStock = new ArrayList<StockItem>();

	public void addItem(StockItem element) {
		listaStock.add(element);
	}

	public void addQty(Producto prod, double qty) {
		StockItem sti = this.findStockItem(prod.getId());
		sti.setCantidad(sti.getCantidad() + qty);

	}

	public void removeQty(Producto prod, double qty) {
		StockItem sti = this.findStockItem(prod.getId());
		if (sti.getCantidad() > qty) {
			sti.setCantidad(sti.getCantidad() - qty);
		} else {
			sti.setCantidad(0);
		}
	}

	public StockItem findStockItem(int prodId) {
		for (StockItem it : listaStock) {
			if (it.getProducto().getId() == prodId) {
				return it;
			}
		}
		return null;
	}
}
