package laFuerza;

import java.util.LinkedList;

public class Atraccion extends Propuesta {

	private int cupoDisponible;
	private int cupoInicial;

	public Atraccion(int costo, double duracion, TipoAtraccion tipoAtraccion, int cupoMaximo, String nombre) {
		this.costo = costo;
		this.tiempoTotal = duracion;
		this.tipoAtraccion = tipoAtraccion;
		this.cupoDisponible = cupoMaximo;
		this.cupoInicial = cupoMaximo;
		this.nombre = nombre;
	}

	@Override
	public void actualizarCupoDisponible() {
		cupoDisponible--;
		if (cupoDisponible == 0)
			hayCupoDisponible = false;
	}

	@Override
	public String toString() {
		String toString = "";
		toString += "- Atracción: " + nombre + "\n";
		toString += "\tTipo de Atracción: " + tipoAtraccion.getNombre() + "\n";
		toString += "\tCosto: " + getCosto() + " créditos galácticos\n";
		toString += "\tDuración de la Atracción: " + ModificadorFormatoHora.obtenerHoraConFormato(getTiempoUtilizado())
				+ "\n\n";

		return toString;

	}

	public LinkedList<Atraccion> getAtraccionesIncluidas() {
		LinkedList<Atraccion> atraccionesIncluidas = new LinkedList<Atraccion>();
		atraccionesIncluidas.add(this);
		return atraccionesIncluidas;
	}

	public int getCupoDisponible() {
		return cupoDisponible;
	}

	public int getCupoInicial() {
		return cupoInicial;
	}

}
