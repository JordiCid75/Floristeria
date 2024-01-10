package Persistence;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;

public interface IConnection {
	public void write();
	public String getField(String string);
	public void getContent();
	public JsonNode getContentNodes();
	public void setNameTable(String name);
	public void write(String content);

}
