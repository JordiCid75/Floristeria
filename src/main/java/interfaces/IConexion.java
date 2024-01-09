package interfaces;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;

public interface IConexion {
	public void excribir();
	public String getField(String string);
	public void getContent();
	public JsonNode getContentNodes();
	public void setNombreTabla(String string);
	public void write(JSONObject jsonTienda);

}
