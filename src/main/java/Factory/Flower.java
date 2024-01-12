package Factory;

import Factory.Product;
import entities.Reader;
import entities.Stock;

public class Flower extends Product {

	private String colour;

	public Flower(String name, float price, String colour) {
		super(name, price);
		this.colour = colour;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	@Override
	public String toString() {

		return "Id: " + id + "\n" + "Name: " + name + "\n" + "Type: Flower" + "\n" + "Price: " + price + "€\n"
				+ "Colour: " + colour + "\n";
	}


}
