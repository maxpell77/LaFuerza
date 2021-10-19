package laFuerza;

import java.util.LinkedList;

public abstract class Promocion extends Propuesta {
	protected int tipo_promocion_id; 
	protected String descrpicion;
	protected LinkedList<Atraccion> atraccionesIncluidas = new LinkedList<Atraccion>();
	

	public Promocion(TipoAtraccion tipoAtraccion, String titulo, String descrpicion,
			LinkedList<Atraccion> atraccionesIncluidas, int id_promocion) {
		super(id_promocion);
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
		String toString = "";
		toString += "- " + nombre + ": " + descrpicion + "\n\n";
		toString += "\tTipo de Atracción: " + tipoAtraccion.getNombre() + "\n";
		toString += "\tCosto: " + getCosto() + " créditos galácticos\n";
		toString += "\tDuración Total: " + ModificadorFormatoHora.obtenerHoraConFormato(getTiempoUtilizado()) + "\n\n";

		return toString;
	}

	public void setTiempoUtilizado() {
		for (Atraccion atraccion : atraccionesIncluidas) {
			this.tiempoTotal += atraccion.getTiempoUtilizado();
		}
	}

	public abstract void setCosto();
	
	
	public int getTipoPromocionID() {
		return tipo_promocion_id;
	}

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

	@Override
	public boolean hayCupoDisponible() {
		for (Atraccion atraccion : atraccionesIncluidas) {
			hayCupoDisponible &= atraccion.hayCupoDisponible();
		}
		return hayCupoDisponible;
	}

}