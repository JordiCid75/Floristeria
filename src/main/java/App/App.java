package App;

import java.util.Scanner;

import GUI.FlowerShopUI;
import GUI.Menu;
import entities.FlowerShop;
import entities.Producto;
import entities.SelectorGeneric;
import entities.Stock;
import entities.StockItem;
import exceptions.FlowerShopDoesNotExistException;

public class App {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		FlowerShop floristeria = null;
		// si existe una floristeria se deberia leer de la base de datos/ficheros
		// dentro de la floristeria se debería leer el Stock de cada uno de los
		// productos y crearlos en memoria
		// lo que hay que ver es si se pueden hacer lecturas parciales para que sea
		// efectivo en caso de tener muchos productos

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
			try {
				if (floristeria != null) {
					System.out.println("Menu de " + floristeria.getNombre());
				}
				String op = sel.selecionarElemento(m.getListaMenu());
				opcion = m.getIndex(op);
				switch (opcion) {
				case 0: {
					floristeria = FlowerShopUI.crearFloristeria(floristeria);
					break;
				}
				case 1: {
					// Añadir un Arbol
					try {
						FlowerShopUI.crearArbol(floristeria);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 2: {
					// Añadir Flor
					try {
						FlowerShopUI.crearFlor(floristeria);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 3: {
					// Añadir Decoración
					try {
						FlowerShopUI.crearDecoracion(floristeria);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 4: {
					// Imprimir el Stock
					try {
						FlowerShopUI.imprimirStock(floristeria);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 5: {
					// Retirar Arbol
					try {
						FlowerShopUI.retirarArbol(floristeria);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 6: {
					// Retirar Flor
					try {
						FlowerShopUI.retirarFlor(floristeria);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 7: {
					// Retirar Decoración
					try {
						FlowerShopUI.retirarDecoracion(floristeria);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 8: {
					// Imprimir Stock con Cantidades
					try {
						FlowerShopUI.imprimirStockCantidades(floristeria);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 9: {
					// Imprimir Valor Total de la tienda
					try {
						FlowerShopUI.imprimirValorTotal(floristeria);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 10: {
					// Crear tickets de compra
					try {
						FlowerShopUI.crearTicket(floristeria);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 11: {
					// Mostrar lista de compras antiguas
					try {
						FlowerShopUI.mostrarListaComprasAntiguas(floristeria);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 12: {
					// visualizar el total de dinero ganado por las ventas
					try {
						FlowerShopUI.visualizarGananciasVentas(floristeria);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 13: {
					// Salir de la aplicación
					System.out.println(m.getListaMenu().get(opcion));
					break;

				}
				default:
					System.out.println("Unexpected value: " + opcion);
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				break;
			}
		} while (opcion != 13);
	}

}
