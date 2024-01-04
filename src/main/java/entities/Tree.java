package entities;

public class Tree extends Product{

    private float height;
    public Tree(String name, float price, int quantityByProduct, float height) {
        super(name, price, quantityByProduct);
        this.height = height;
    }

    @Override
    public String toString()
    {
        return "Id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Price: " + price + "\n" +
                "Height: " + height + "\n" +
                "Quantity: " + quantityByProduct + "\n";
    }
}
