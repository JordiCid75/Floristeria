package entities;

public class Producto {

	private int id;
	private static int contador = 1;

	public Producto(int _id) {
		this.setId(_id);
	}

	public void setId(int _id) {
		id = _id;
		if (contador < _id) {
			contador = _id;
		}

	}

	public int getId() {
		return id;
	}

}
