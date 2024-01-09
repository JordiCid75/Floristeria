package entities;

public class Decoration extends Product{
    private Material material;

    public Decoration(String name, float price, Material material) {
        super(name, price);
        this.material = material;
    }

    @Override
    public String toString()
    {
        return "Id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Type: Decoration" + "\n" +
                "Price: " + price + "€\n" +
                "Material: " + material.name() + "\n";

    }
}
