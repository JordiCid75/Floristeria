package Factory;

import Factory.Product;
import entities.Reader;
import entities.Stock;

public class Tree extends Product {

    private float height;
    public Tree(String name, float price, float height) {
        super(name, price);
        this.height = height;
    }

    @Override
    public String toString()
    {
        return "Id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Type: Tree" + "\n" +
                "Price: " + price + "€\n" +
                "Height: " + height + "m\n";

    }


}
