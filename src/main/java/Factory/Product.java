package Factory;

import entities.Reader;
import entities.Stock;

public abstract class Product {

    protected float price;
    protected String name;
    protected int id;
    private static int counter = 1;

    public Product(String name, float price) {

        this.name = name;
        this.price = price;
        this.setId(counter++);
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int _id) {
        this.id = _id;
        if (counter < _id) {
            counter = _id;
        }
    }
}
