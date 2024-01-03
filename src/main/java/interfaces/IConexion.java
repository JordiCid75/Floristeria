package interfaces;

import org.json.JSONObject;

public interface IConexion {
	public void excribir();
	public String getField(String string);
	public void getContent();
	public void setNombreTabla(String string);
	public void write(JSONObject jsonTienda);

}
