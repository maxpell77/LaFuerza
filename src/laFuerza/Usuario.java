package laFuerza;

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

	public String getNombre() {
		return nombre;
	}

	public LinkedList<Atraccion> getAtraccionesContratadas() {
		return atraccionesContratadas;
	}

	public LinkedList<Propuesta> getPropuestasContratadas() {
		return propuestasCompradas;
	}

	public boolean puedeAdquirirPropuesta(Propuesta propuesta) {
		return puedepagarPropuesta(propuesta) && tieneTiempoDisponible(propuesta) && atraccionNoContratada(propuesta);
	}

	private boolean puedepagarPropuesta(Propuesta propuesta) {
		return presupuestoDisponible >= propuesta.getCosto();
	}

	private boolean tieneTiempoDisponible(Propuesta propuesta) {
		return tiempoDisponible >= propuesta.getTiempoUtilizado();
	}

	private boolean atraccionNoContratada(Propuesta propuesta) {
		boolean atraccionNoInculida = true;
		for (Atraccion atraccionContratada : atraccionesContratadas) {
			atraccionNoInculida &= !propuesta.getAtraccionesIncluidas().contains(atraccionContratada);
		}
		return atraccionNoInculida;
	}

	public void agregarPropuestaAceptada(Propuesta nuevaPropuesta) {
		propuestasCompradas.add(nuevaPropuesta);
		atraccionesContratadas.addAll(nuevaPropuesta.getAtraccionesIncluidas());
		tiempoDisponible -= nuevaPropuesta.getTiempoUtilizado();
		presupuestoDisponible -= nuevaPropuesta.getCosto();
	}

	public boolean aceptaPropuesta(Propuesta propuesta) {
		String entradaConsola = LectorConsola.esperarRespuestaUsuario();
		if (entradaConsola.equals("S")) {
			return true;
		} else {
			return false;
		}
	}

	public int getPresupuestoInicial() {
		return presupuestoInicial;
	}

	public double getTiempoMaximoInicial() {
		return tiempoMaximoInicial;
	}

}
