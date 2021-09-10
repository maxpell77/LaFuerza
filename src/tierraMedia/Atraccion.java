package tierraMedia;

import java.util.LinkedList;

public class Atraccion extends Propuesta {
	private int cupoDisponible;

	public Atraccion(int costo, double duracion, TipoAtraccion tipoAtraccion, int cupoMaximo, String nombre) {
		this.costo = costo;
		this.tiempoTotal = duracion;
		this.tipoAtraccion = tipoAtraccion;
		this.cupoDisponible = cupoMaximo;
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
		return "- Atraccion " + nombre + "\n\tCosto: " + costo + " monedas de oro\n\tTiempo: "
				+ ModificadorFormatoHora.obtenerHoraConFormato(tiempoTotal) + "\n";
	}

	public LinkedList<Atraccion> getAtraccionesIncluidas() {
		LinkedList<Atraccion> atraccionesIncluidas = new LinkedList<Atraccion>();
		atraccionesIncluidas.add(this);
		return atraccionesIncluidas;
	}

}
