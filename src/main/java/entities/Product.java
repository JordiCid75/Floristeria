package entities;

public abstract class Product {

	protected float price;
	protected String name;
//    private static int quantity;
//    protected int quantityByProduct;
	protected int id;
	private static int contador = 1;

	public Product(int _id) {
		this.setId(_id);
	}

	public Product(String name, float price) // , int quantityByProduct )
	{
		this.name = name;
		this.price = price;
		this.setId(contador++);
//        this.quantityByProduct = quantityByProduct;
//        quantity++;
//        id = quantity;
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
		if (contador < _id) {
			contador = _id;
		}

	}

/*	public float calculateTotalPrice() {
		return price * quantityByProduct;
	}
*/
	
}
