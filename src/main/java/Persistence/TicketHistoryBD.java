package Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;

import entities.*;
import exceptions.ProductNotFoundException;
import Factory.*;

public class TicketHistoryBD implements IConnectionType {

	private IConnection conn;
	private final String filePath = "src/main/resources/";
	private final String fileName = "TicketHistory.json";

	public TicketHistoryBD() {
		this.conn = FactoryBD.getConexionBD(connType);
		this.conn.setNameTable(filePath + fileName);
	}

	public void readBD() {
		this.conn.getContent();
	}

	public void readListTicketHistoryBD(TicketHistory ticketHistory, Stock stock) {
		// aqui leeremos todos los elementos del stock

		readBD();
		JsonNode jsonNode;
		jsonNode = this.conn.getContentNodes();

		JsonNode th = jsonNode.get(TicketHistory.class.toString());
		for (JsonNode tk : th) {
			// primero vendrá el qty, y despues el producto en sí

			JsonNode itmTk = tk.get(Ticket.class.toString());
			Ticket ticket = new Ticket();
			ticket.setId(itmTk.get("Id").asInt());
			ticket.setCreationDate(LocalDate.parse(itmTk.get("Date").asText()));
			JsonNode itmsTk = tk.get("TicketItems");
			for (JsonNode tkItm : itmsTk) {
				JsonNode tkprd = tkItm.get("TicketProduct");

				int p_id = tkprd.get("Id").asInt();

				// String type = prd.get("Type").asText();
				Product p = null;
				try {
					p = stock.findProductById(p_id);
				} catch (ProductNotFoundException e) {
					e.printStackTrace();
				}
				p.setPrice(tkprd.get("Price").floatValue());
				if (p != null) {
					ticket.addProductInTicket(p, tkItm.get("ProductQty").asInt());
				}
			}
			ticketHistory.addTicketToHistory(ticket, (int) itmTk.get("Price").asDouble());
		}
	}

	public void write(TicketHistory ticketHistory) {
		JSONObject jsonTicketHistory = new JSONObject(); // para el ticketHistory
		ArrayList<JSONObject> jsonTickets = new ArrayList<JSONObject>(); // los tickets en Ticket history
		ticketHistory.getTicketsInHistory().forEach((t, ticketPrice) -> { // Cada ticket tiene el ticket y el precio
																			// total
			JSONObject jsonTicket = new JSONObject(); // Ticket
			ArrayList<JSONObject> jsonTicketLine = new ArrayList<JSONObject>(); // cada linea de tocket tiene qty,
																				// precio, id producto y cantidad
			t.getProductsInTicket().forEach((p, pd_qty) -> {
				JSONObject jsonTicketProduct = new JSONObject();
				jsonTicketProduct.put("TicketProduct", getJSONFormatTicketProduct(p));
				jsonTicketProduct.put("ProductQty", pd_qty);
				jsonTicketLine.add(jsonTicketProduct);
			});

			jsonTicket.put(Ticket.class.toString(), getJSONFormatTicket(t));
			jsonTicket.put("TicketItems", jsonTicketLine);
			jsonTickets.add(jsonTicket);
		});

		jsonTicketHistory.put(TicketHistory.class.toString(), jsonTickets);
		this.conn.write(jsonTicketHistory.toString());
	}

	private JSONObject getJSONFormatTicketProduct(Product p) {
		JSONObject obj = new JSONObject();
		// obj.put("Type", p.getClass().toString());
		obj.put("Id", p.getId());
		// obj.put("Name", p.getName());
		obj.put("Price", p.getPrice());
		return obj;
	}

	private JSONObject getJSONFormatTicket(Ticket tk) {
		JSONObject obj = new JSONObject();
		// obj.put("Type", tk.getClass().toString());
		obj.put("Id", tk.getId());
		// obj.put("Name", p.getName());
		obj.put("Date", tk.getDate());
		obj.put("Price", tk.getTotalPrice());
		return obj;
	}

}
