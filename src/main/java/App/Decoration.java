package App;

 enum Material{WOOD, PLASTIC}

public class Decoration extends Product{
    private Material material;

    public Decoration(String name, float price, int quantityByProduct, Material material) {
        super(name, price, quantityByProduct);
        this.material = material;
    }

    @Override
    public String toString()
    {
        return "Id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Price: " + price + "\n" +
                "Material: " + material.name() + "\n" +
                "Quantity: " + quantityByProduct + "\n";
    }
}
