package Persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

public class DataBase {

	private ObjectMapper objectMapper;
	private File file;

	private final String filePath = "src/main/resources/FlowerShop.json";

	public DataBase() {
		objectMapper = new ObjectMapper();
		file = new File(filePath);

	}

	public void write(JSONObject jsonObj) {
		try (FileWriter fileWriter = new FileWriter(this.filePath)) {
			fileWriter.write(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
