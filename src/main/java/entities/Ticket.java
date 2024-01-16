package entities;

import Factory.Product;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Ticket {
	private HashMap<Product, Integer> productsInTicket;
	private int id;
	private LocalDate creationDate;
	private static int counter = 1;
	private float totalPrice;


	public Ticket() {
		productsInTicket = new HashMap<>();
		this.setId(counter++);
		this.creationDate = LocalDate.now();
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public HashMap<Product, Integer> getProductsInTicket() {
		return productsInTicket;
	}
	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Ticket(HashMap<Product, Integer> productsInTicket, int id, LocalDate startDate) {
		this.productsInTicket = productsInTicket;
		this.setId(counter++);
		this.creationDate = LocalDate.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int _id) {
		this.id = _id;
		if (counter < _id) {
			counter = _id;
		}
	}


	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	public void Setdate(int year, int month, int day) {
		LocalDate startDate = LocalDate.of(year, month, day);
	}

	public LocalDate getDate() {
		return creationDate;
	}

	public void addProductInTicket(Product product, int quantity) {
		productsInTicket.put(product, quantity);
	}

	public int getProductQuantityTicket(Product product) {
		return productsInTicket.getOrDefault(product, 0);
	}

	@Override
	public String toString() {
		return "Ticket [products=" + productsInTicket + "]";
	}

	public void printTicket() {
		float totalPrice = 0;
		System.out.println(this.getDate());
		for (Iterator<Product> iterator = productsInTicket.keySet().iterator(); iterator.hasNext();) {
			Product product = iterator.next();
			String productName = product.getName();
			int qty = getProductQuantityTicket(product);
			float productPrice = product.getPrice() * qty;
			System.out.println("Name: " + productName + ", Price: " + productPrice + " €");
			totalPrice = totalPrice + productPrice;
		}
		System.out.println("TOTAL PRICE " + totalPrice + " €");
		this.setTotalPrice(totalPrice);
	}
}
