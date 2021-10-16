package laFuerza;

import java.util.HashMap;
import java.util.Map;

public enum TipoAtraccion {
	LADO_OSCURO("Lado Oscuro", 1), LADO_LUMINOSO("Lado Luminoso", 2), LADO_GRIS("Lado Gris", 3);

	private String nombre;
	private int numero_id;

	private static Map<Integer, TipoAtraccion> map = new HashMap<Integer, TipoAtraccion>();

	private TipoAtraccion(String nombre, int numero_id) {
		this.nombre = nombre;
		this.numero_id = numero_id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getNumeroId() {
		return numero_id;
	}

	static {
		for (TipoAtraccion TipoAtraccion : TipoAtraccion.values()) {
			map.put(TipoAtraccion.numero_id, TipoAtraccion);
		}
	}

	public static TipoAtraccion valueOf(int numero_id) {
		return map.get(numero_id);
	}

}
