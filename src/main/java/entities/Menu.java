package entities;

import java.util.ArrayList;

public class Menu {

	private ArrayList<String> listMenu = new ArrayList<String>();

	public Menu() {
		listMenu.add("Crear Floristeria.");
		listMenu.add("Añadir Arbol.");
		listMenu.add("Añadir Flor.");
		listMenu.add("Añadir Decoración.");
		listMenu.add("Imprimir Stock.");
		listMenu.add("Retirar Arbol.");
		listMenu.add("Retirar Flor.");
		listMenu.add("Retirar Decoración.");
		listMenu.add("Imprimir Stock con Cantidades.");
		listMenu.add("Imprimir Valor Total de la Floristeria.");
		listMenu.add("Crear Tickets de Compra.");
		listMenu.add("Mostrar lista de compras antiguas.");
		listMenu.add("Visualizar el total de dinero Ganado con las Ventas.");
		listMenu.add("Salir de la aplicación.");
	}

	public ArrayList<String> getListaMenu() {
		return listMenu;
	}

	public int getIndex(String element) {
		return listMenu.indexOf(element);
	}
}
