package App;

public class Flower extends Product{

    private String colour;
    public Flower(String name, float price, int quantityByProduct, String colour) {
        super(name, price, quantityByProduct);
        this.colour = colour;
    }

    @Override
    public String toString()
    {
        return "Id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Price: " + price + "\n" +
                "Colour: " + colour + "\n" +
                "Quantity: " + quantityByProduct + "\n";
    }
}
