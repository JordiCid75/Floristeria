package Persistence;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileConnection implements IConnection {
	private String path;
	private String name;
	private JsonNode content;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public FileConnection() {
		// TODO Auto-generated constructor stub

	}

	private JsonNode read() {
		JsonNode jsonNode = null;
		if (this.name != null) {
			// Crear un objeto ObjectMapper (de Jackson)
			ObjectMapper objectMapper = new ObjectMapper();

			try {
				// Leer el archivo JSON completo como un árbol de nodos JsonNode
				jsonNode = objectMapper.readTree(new File(name));

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

	public void write(String content) {
		// Escribir el objeto JSON en un archivo
		try (FileWriter fileWriter = new FileWriter(this.name)) {
			fileWriter.write(content);
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void write() {
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
			content = read();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public JsonNode getContentNodes() {
		// TODO Auto-generated method stub
		return content;
	}

	@Override
	public void setNameTable(String name) {
		// TODO Auto-generated method stub
		setName(name);
	}

}
