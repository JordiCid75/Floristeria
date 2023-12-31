package entities;

public class Flower extends Product {

	private String colour;

	public Flower(String name, float price, String colour) {
		super(name, price);
		this.colour = colour;
	}


	@Override
	public String toString() {

		return "Id: " + id + "\n" +
				"Name: " + name + "\n" +
				"Type: Flower" + "\n" +
				"Price: " + price + "€\n" +
				"Colour: " + colour + "\n";
	}
}
