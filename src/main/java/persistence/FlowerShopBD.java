package persistence;

import org.json.JSONObject;

import entities.FlowerShop;
import interfaces.IConexion;

public class FlowerShopBD {
	
	private IConexion conn;
	
	public FlowerShopBD() {
		this.conn  = FactoryBD.getConexionBD("TXT");
		this.conn.setNombreTabla("FlowerShop.txt");
		this.conn.getContent();
		
	}
	
	public String getNombre()
	{
		//return null;
		String nom = this.conn.getField("nombre");
		return nom;
	}
	public void write(FlowerShop tienda)
	{
		JSONObject jsonTienda = new JSONObject();
		jsonTienda.put("nombre", tienda.getNombre());
		this.conn.write(jsonTienda);
	}

}
