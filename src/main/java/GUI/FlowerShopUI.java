package GUI;

import java.util.Scanner;

import entities.Decoration;
import entities.Flower;
import entities.FlowerShop;
import entities.Material;
import entities.Product;
import entities.Tree;
import exceptions.FlowerShopDoesNotExistException;

public class FlowerShopUI {

	static Scanner sc = new Scanner(System.in);
	
	static Product f1 = new Flower("flor1", 12,"amarillo");
	static Product d2 = new Decoration("deco1", 10,Material.WOOD);
	static Product t3 = new Tree("Tree1", 16, 45);

	public static void crearArbol(FlowerShop fl) {
		// para crear un arbol hay que pedir la altura
		/*
		System.out.println("Inserta nombre del arbol:");
		String name = sc.nextLine();
		System.out.println("Inserta precio del arbol:");
		float price = sc.nextFloat();
		System.out.println("Inserta la altura del arbol:");
		double heith = sc.nextDouble();
		*/
		//Tree t = fl.createTree(name, price, heith);
		fl.createTree((Tree) t3);

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

	public static void crearFlor(FlowerShop fl) {
		fl.createFlower((Flower) f1);
	}

	public static void crearDecoracion(FlowerShop fl) {
		fl.createDecoration((Decoration) d2);
	}

	public static void retirarArbol(FlowerShop fl) {

	}

	public static void retirarFlor(FlowerShop fl) {

	}

	public static void retirarDecoracion(FlowerShop fl) {

	}

	public static void imprimirStock(FlowerShop fl) {

	}

	public static void imprimirStockCantidades(FlowerShop fl) {

	}

	public static void imprimirValorTotal(FlowerShop fl) {

	}

	public static void crearTicket(FlowerShop fl) {
	}

	public static void mostrarListaComprasAntiguas(FlowerShop fl) {
	}

	public static void visualizarGananciasVentas(FlowerShop fl) {
	}
}
