package tierraMedia;


import java.util.LinkedList;

public abstract class Promocion extends Propuesta {

	protected String descrpicion;
	protected LinkedList<Atraccion> atraccionesIncluidas = new LinkedList<Atraccion>();

	public Promocion(TipoAtraccion tipoAtraccion, String titulo, String descrpicion,
			LinkedList<Atraccion> atraccionesIncluidas) {
		this.tipoAtraccion = tipoAtraccion;
		this.nombre = titulo;
		this.descrpicion = descrpicion;
		this.atraccionesIncluidas = atraccionesIncluidas;
	}

	public LinkedList<Atraccion> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}

	public String getDescripcion() {
		return this.descrpicion;
	}

	@Override
	public String toString() {
		return "- " + nombre + ": " + descrpicion + "\n\tCosto: " + getCosto() + " monedas de oro\n\tTiempo: "
				+ ModificadorFormatoHora.obtenerHoraConFormato(getTiempoUtilizado()) + "\n";
	}

	public void setTiempoUtilizado() {
		for (Atraccion atraccion : atraccionesIncluidas) {
			this.tiempoTotal += atraccion.getTiempoUtilizado();
		}
	}

	public abstract void setCosto();

	protected int getCostoTotalAtracciones(LinkedList<Atraccion> listadoAtracciones) {
		int costoTotal = 0;
		for (Atraccion atraccion : listadoAtracciones) {
			costoTotal += atraccion.getCosto();
		}
		return costoTotal;
	}

	@Override
	public void actualizarCupoDisponible() {
		for (Atraccion atraccion : atraccionesIncluidas) {
			atraccion.actualizarCupoDisponible();
			hayCupoDisponible &= atraccion.hayCupoDisponible();
		}
	}


}