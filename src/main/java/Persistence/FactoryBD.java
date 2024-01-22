package Persistence;

public class FactoryBD {

	public static IConnection getConexionBD(String tipo) {
		switch (tipo) {
		case "TXT": {
			return new FileConnection();
		}
		

		default: {
			throw new IllegalArgumentException("Unexpected value: " + tipo);
		}
		}
	}

}
