package entities;

public class Flower extends Product {

	private String colour;

//    public Flower(String name, float price, int quantityByProduct, String colour) {
	public Flower(String name, float price, String colour) {
		super(name, price);
		this.colour = colour;
	}

	public Flower(int _id, String name, float price, String colour) {
		super(_id);
		this.setName(name);
		this.setPrice(price);
		this.colour = colour;
	}

	@Override
	public String toString() {
		return "Flower [colour=" + colour + ", price=" + price + ", name=" + name + ", id=" + id + "]";
	}
}
