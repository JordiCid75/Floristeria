package Factory;

import Factory.Product;
import entities.Material;
import entities.Reader;
import entities.Stock;

public class Decoration extends Product {
    private Material material;

	public Decoration(String name, float price, Material material) {
		super(name, price);
		this.material = material;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "Id: " + id + "\n" + "Name: " + name + "\n" + "Type: Decoration" + "\n" + "Price: " + price + "€\n"
				+ "Material: " + material.name() + "\n";

	}
}
