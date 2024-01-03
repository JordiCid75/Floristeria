package GUI;

import java.util.Scanner;

import entities.FlowerShop;
import exceptions.FlowerShopDoesNotExistException;

public class FlowerShopUI {

	static Scanner sc = new Scanner(System.in);
	
	
	public static void crearArbol(FlowerShop fl) {
		// para crear un arbol hay que pedir la altura
		System.out.println("Inserta la altura del arbol:");
		double altura = sc.nextDouble();
		

	}
	public static FlowerShop crearFloristeria(FlowerShop fl) {
		try {
			fl = FlowerShop.getInstance();
			System.out.println("floristeria existente:" + fl.getNombre());
		} catch (FlowerShopDoesNotExistException e) {
			// pedir nombre floristería y crearla
			System.out.println(e.getMessage());
			System.out.println("Introduce una nueva:");
			String nombreTienda = sc.nextLine();
			fl = FlowerShop.crearFloristeria(nombreTienda);
			fl.guardar();

		}
		return fl;
	}

	public static void crearFlor(FlowerShop f) {

	}

	public static void crearDecoracion(FlowerShop f) {

	}

	public static void retirarArbol(FlowerShop f) {

	}

	public static void retirarFlor(FlowerShop f) {

	}

	public static void retirarDecoracion(FlowerShop f) {

	}

	public static void imprimirStock(FlowerShop f) {

	}

	public static void imprimirStockCantidades(FlowerShop f) {

	}

	public static void imprimirValorTotal(FlowerShop f) {

	}

	public static void crearTicket(FlowerShop f) {
	}

	public static void mostrarListaComprasAntiguas(FlowerShop f) {
	}

	public static void visualizarGananciasVentas(FlowerShop f) {
	}
}
