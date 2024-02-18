package Persistence;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import entities.*;
import exceptions.ProductNotFoundException;
import Factory.*;

public class TicketHistoryBD {
	private IConnection conn;
	private final String filePath = "src/main/resources/";
	private final String fileName = "TicketHistory.json";

	public TicketHistoryBD() {
		this.conn = FactoryBD.getConexionBD("TXT");
		this.conn.setNameTable(filePath + fileName);
	}

	public void readBD() {
		this.conn.getContent();
	}

	public void readListTicketHistoryBD(TicketHistory ticketHistory, Stock stock, Stock oldStock) {
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
				Product p = null;
				try {
					p = stock.findProductById(p_id);
				} catch (ProductNotFoundException e) {
					try {
						p = oldStock.findProductById(p_id);
					} catch (ProductNotFoundException ex) {
						ex.printStackTrace();
					}
				}
				if (p != null) {
					if (p.getPrice() != tkprd.get("Price").floatValue()) {
						String type = p.getClass().toString();
						if (type.equals(Flower.class.toString())) {
							Flower fl = (Flower) p;
							Product p_new = ProductFactory.create(fl.getName(), (float) tkprd.get("Price").asDouble(),
									fl.getColour());
							p_new.setId(p_id);
							p = p_new;
						} else if (type.equals(Tree.class.toString())) {
							Tree tr = (Tree) p;
							Product p_new = ProductFactory.create(tr.getName(), (float) tkprd.get("Price").asDouble(),
									tr.getHeight());
							p_new.setId(p_id);
							p = p_new;
						} else if (type.equals(Decoration.class.toString())) {
							Decoration dc = (Decoration) p;
							Product p_new = ProductFactory.create(dc.getName(), (float) tkprd.get("Price").asDouble(),
									dc.getMaterial());
							p_new.setId(p_id);
							p = p_new;
						}
					}
					if (p != null) {
						ticket.addProductInTicket(p, tkItm.get("ProductQty").asInt());
					}
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
		obj.put("Id", p.getId());
		obj.put("Price", p.getPrice());
		return obj;
	}

	private JSONObject getJSONFormatTicket(Ticket tk) {
		JSONObject obj = new JSONObject();
		obj.put("Id", tk.getId());
		obj.put("Date", tk.getDate());
		obj.put("Price", tk.getTotalPrice());
		return obj;
	}
}
