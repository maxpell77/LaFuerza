package laFuerza;

public enum TipoAtraccion {
	LADO_OSCURO("Lado Oscuro"), LADO_LUMINOSO("Lado Luminoso"), LADO_GRIS("Lado Gris");

	private String nombre;

	private TipoAtraccion(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

}
