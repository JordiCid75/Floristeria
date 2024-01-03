package App;

public abstract class Product {

    protected float price;
    protected String name;
    private static int quantity;
    protected int quantityByProduct;
    protected final int id;

    public Product(String name, float price, int quantityByProduct )
    {
        this.name = name;
        this.price = price;
        this.quantityByProduct = quantityByProduct;
        quantity++;
        id = quantity;
    }

    public float calculateTotalPrice()
    {
        return price*quantityByProduct;
    }
}
