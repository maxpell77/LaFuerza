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

	public int getCostoTotalAtracciones() {
		int costoTotal = 0;
		for (Atraccion atraccion : atraccionesIncluidas) {
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

//	@Override
//	public int getCupoDisponible() {
//		LinkedList<Integer> cupos = new LinkedList<Integer>();
//		for (Atraccion atraccion : atraccionesIncluidas) {
//			cupos.add(atraccion.getCupoDisponible());
//		}
//		return Collections.min(cupos);
//	}

}