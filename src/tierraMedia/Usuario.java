package tierraMedia;

import java.util.LinkedList;

public class Usuario {
	private TipoAtraccion tipoAtraccionPreferida;
	private int presupuestoInicial;
	private int presupuestoDisponible;
	private double tiempoMaximoInicial;
	private double tiempoDisponible;
	private String nombre;
	private LinkedList<Atraccion> atraccionesContratadas = new LinkedList<Atraccion>();
	private LinkedList<Propuesta> propuestasCompradas = new LinkedList<Propuesta>();

	public Usuario(String nombre, TipoAtraccion tipoAtraccionPreferida, int presupuesto, double tiempoMaximo) {
		this.tipoAtraccionPreferida = tipoAtraccionPreferida;
		this.presupuestoInicial = presupuesto;
		this.presupuestoDisponible = presupuesto;
		this.tiempoMaximoInicial = tiempoMaximo;
		this.tiempoDisponible = tiempoMaximo;
		this.nombre = nombre;

	}

	public TipoAtraccion getTipoAtraccionPreferida() {
		return tipoAtraccionPreferida;
	}

	public int getPresupuestoDisponible() {
		return presupuestoDisponible;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public int getPresupuesto() {
		return presupuestoInicial;
	}

	public double getTiempoMaximo() {
		return tiempoMaximoInicial;
	}

	public String getNombre() {
		return nombre;
	}

	public LinkedList<Atraccion> getAtraccionesContratadas() {
		return atraccionesContratadas;
	}

	public LinkedList<Propuesta> getPropuestasContratadas() {
		return propuestasCompradas;
	}

	public void agregarPropuestaAceptada(Propuesta nuevaPropuesta) {
		propuestasCompradas.add(nuevaPropuesta);
		atraccionesContratadas.addAll(nuevaPropuesta.getAtraccionesIncluidas());
		actualizarTiempoDispoible(nuevaPropuesta.getAtraccionesIncluidas());
		this.presupuestoDisponible -= nuevaPropuesta.getCosto();
	}

	private void actualizarTiempoDispoible(LinkedList<Atraccion> nuevaAtracciones) {
		for (Atraccion atraccion : nuevaAtracciones) {
			this.tiempoDisponible -= atraccion.getTiempoUtilizado();
		}

	}

}
