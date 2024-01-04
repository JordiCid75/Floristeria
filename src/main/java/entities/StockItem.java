package entities;

public class StockItem {

	private Producto producto;
	private double cantidad;

	public StockItem(Producto p, double qty) {
		this.producto = p;
		this.cantidad = qty;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "StockItem [producto=" + producto + ", cantidad=" + cantidad + "]";
	}

}
