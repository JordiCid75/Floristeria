package persistence;

import interfaces.IConexion;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConexionFichero implements IConexion {
	private String camino;
	private String nombre;
	private JsonNode content;

	public String getCamino() {
		return camino;
	}

	public void setCamino(String camino) {
		this.camino = camino;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ConexionFichero() {
		// TODO Auto-generated constructor stub

	}

	private JsonNode leer() {
		JsonNode jsonNode = null;
		if (this.nombre != null) {
			// Crear un objeto ObjectMapper (de Jackson)
			ObjectMapper objectMapper = new ObjectMapper();

			try {
				// Leer el archivo JSON completo como un árbol de nodos JsonNode
				jsonNode = objectMapper.readTree(new File(nombre));

				// Ahora puedes trabajar con el árbol de nodos JsonNode
				System.out.println("Contenido JSON completo:");
				System.out.println(jsonNode.toPrettyString());
				return jsonNode;
				// También puedes convertir el JsonNode a un objeto Java si es necesario
				// Por ejemplo, si el JSON es un objeto:
				// MiObjeto miObjeto = objectMapper.treeToValue(jsonNode, MiObjeto.class);
				// return null;
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		}
		return jsonNode;

	}

	public void write(JSONObject jsonObj) {
		// Escribir el objeto JSON en un archivo
		try (FileWriter fileWriter = new FileWriter(this.nombre)) {
			fileWriter.write(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void excribir() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getField(String string) {
		// TODO Auto-generated method stub
		return content.get(string).asText();
	}

	@Override
	public void getContent() {
		// TODO Auto-generated method stub
		try {
			content = leer();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	

	@Override
	public void setNombreTabla(String string) {
		// TODO Auto-generated method stub
		setNombre(string);
	}

	@Override
	public JsonNode getContentNodes() {
		// TODO Auto-generated method stub
		return content;
	}

}
