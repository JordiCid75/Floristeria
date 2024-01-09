package entities;

import java.util.HashMap;

import persistence.StockBD;

public class Stock {
	// private ArrayList<StockItem> listaStock = new ArrayList<StockItem>();
	private StockBD stBD = new StockBD();

	private HashMap<Product, Integer> listaStock = new HashMap<Product, Integer>();

	public Stock() {
		// TODO Auto-generated constructor stub
		stBD.LeerBD(); // esto carga el contenido del fichero en memoria
		this.readListaStockBD(); // esto carga lo leido desde el fichero a la lista de stock
	}

	private void readListaStockBD() {
		listaStock = stBD.readListaStockBD();
	}
	public void addItem(Product prod, int qty) {
		listaStock.put(prod, qty);
	}

	public void addQty(Product prod, int qty) {

		listaStock.merge(prod, qty, Integer::sum);

	}

	public void removeQty(Product prod, int qty) {
		listaStock.merge(prod, -qty, Integer::sum);
		/*
		 * StockItem sti = this.findStockItem(prod.getId()); if (sti.getCantidad() >
		 * qty) { sti.setCantidad(sti.getCantidad() - qty); } else { sti.setCantidad(0);
		 * }
		 */

	}

	public HashMap<Product, Integer> getListaStock() {
		return listaStock;
	}

	public void guardar() {
		stBD.write(this);
	}

	/*
	 * public StockItem findStockItem(int prodId) { for (StockItem it : listaStock)
	 * { if (it.getProducto().getId() == prodId) { return it; } } return null; }
	 */

}
