package App;

import entities.Menu;
import entities.Producto;
import entities.SelectorGeneric;
import entities.Stock;
import entities.StockItem;

public class App {

	public static void main(String[] args) {
		Stock st = new Stock();
		Producto p = new Producto(1);
		StockItem sti = new StockItem(p, 0);
		st.addItem(sti);
		System.out.println(st.findStockItem(1).toString());
		st.addQty(p, 3);
		System.out.println(st.findStockItem(1).toString());
		st.addQty(p, 2);
		System.out.println(st.findStockItem(1).toString());
		st.removeQty(p, 2);
		System.out.println(st.findStockItem(1).toString());
		st.removeQty(p, 2);
		System.out.println(st.findStockItem(1).toString());
		st.removeQty(p, 2);
		System.out.println(st.findStockItem(1).toString());
		Menu m = new Menu();
		SelectorGeneric sel = new SelectorGeneric();
		int opcion = 1;
		do {
			String op = sel.selecionarElemento(m.getListaMenu());
			opcion = m.getIndex(op);
			switch (opcion) {
			case 0: {
				System.out.println(m.getListaMenu().get(opcion));
				break;
			}
			case 1: {
				System.out.println(m.getListaMenu().get(opcion));
				break;
			}
			case 2: {
				System.out.println(m.getListaMenu().get(opcion));
				break;
			}
			case 3: {
				System.out.println(m.getListaMenu().get(opcion));
				break;
			}
			case 4: {
				System.out.println(m.getListaMenu().get(opcion));
				break;
			}
			case 5: {
				System.out.println(m.getListaMenu().get(opcion));
				break;
			}
			case 6: {
				System.out.println(m.getListaMenu().get(opcion));
				break;
			}
			case 7: {
				System.out.println(m.getListaMenu().get(opcion));
				break;
			}
			case 8: {
				System.out.println(m.getListaMenu().get(opcion));
				break;
			}
			case 9: {
				System.out.println(m.getListaMenu().get(opcion));
				break;
			}
			case 10: {
				System.out.println(m.getListaMenu().get(opcion));
				break;
			}
			case 11: {
				System.out.println(m.getListaMenu().get(opcion));
				break;
			}
			case 12: {
				System.out.println(m.getListaMenu().get(opcion));
				break;
			}
			case 13: {
				System.out.println(m.getListaMenu().get(opcion));
				break;

			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + op);
			}

		} while (opcion != 13);
	}

}
