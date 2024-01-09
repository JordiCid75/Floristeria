package entities;

import exceptions.FlowerShopDoesNotExistException;
import persistence.FlowerShopBD;

public class FlowerShop {
// aqui implementamos la clase con el patrón singleton

	private String nombre;
	private double valorTotal;
	private static FlowerShop floristeria;
	private Stock stock = new Stock();
	private Ticket tickets = new Ticket();

	private FlowerShopBD fsBD = new FlowerShopBD();

	final String msg = "No existe una floristeria, se debe crear una.";

	private FlowerShop() throws FlowerShopDoesNotExistException {
		// si existe una floristería guardada en la BD deberíamos seleccionarla
		// si no existe se debería pedir por pantalla el nombre de la floristería o
		// lanzar la excepción de que no existe ninguna y hacer que el frontend la pida
		// por pantalla
		// tambien se debería hacer la lectura de todos los productos y tickets que
		// tengamos en la base de datos
		// ya que todo pertenece a la misma floristería
		String nom = fsBD.getNombre();
		if (nom == null) {
			throw new FlowerShopDoesNotExistException(msg);
		} else {
			this.nombre = nom;
		}
		
	}

	private FlowerShop(String nombre) {
		this.nombre = nombre;
		
	}

	public static FlowerShop crearFloristeria(String nombre) {
		if (floristeria == null) {
			floristeria = new FlowerShop(nombre);
		}
		return floristeria;
	}

	public static FlowerShop getInstance() throws FlowerShopDoesNotExistException {
		if (floristeria == null) {
			floristeria = new FlowerShop();
		}
		return floristeria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void guardar() {
		fsBD.write(floristeria);
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public void createFlower(Flower f)
	{
		//creacion flor
		//añadir cantidad al stock
		this.stock.addItem(f, 1);
		
	}
	public void createDecoration(Decoration d)
	{
		//creacion flor
		//añadir cantidad al stock
		this.stock.addItem(d, 1);
		
	}
	public void createTree(Tree t)
	{
		//creacion flor
		//añadir cantidad al stock
		this.stock.addItem(t, 1);
		this.stock.guardar();
	}
	

}
