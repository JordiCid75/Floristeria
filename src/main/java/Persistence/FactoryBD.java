package Persistence;

public class FactoryBD {

	public static IConnection getConexionBD(String tipo) {
		switch (tipo) {
		case "TXT": {
			return new FileConnection();
		}
		
		case "MYSQL": {
			return new MySqlConnection();
		}

		default: {
			throw new IllegalArgumentException("Unexpected value: " + tipo);
		}
		}
	}

}
