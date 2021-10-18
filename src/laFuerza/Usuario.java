package laFuerza;

import java.util.LinkedList;

import dao.DAOFactory;
import dao.UserDAO;

public class Usuario {
	private TipoAtraccion tipoAtraccionPreferida;
	private int presupuestoInicial;
	private int presupuestoDisponible;
	private double tiempoMaximoInicial;
	private double tiempoDisponible;
	private String nombre;
	private LinkedList<Propuesta> propuestasCompradas = new LinkedList<Propuesta>();

	
	public Usuario(String nombre, TipoAtraccion tipoAtraccionPreferida, int presupuesto, double tiempoMaximo, LinkedList<Propuesta> propuestasCompradas  ) {
		this.tipoAtraccionPreferida = tipoAtraccionPreferida;
		this.presupuestoInicial = presupuesto;
		this.presupuestoDisponible = presupuesto;
		this.tiempoMaximoInicial = tiempoMaximo;
		this.tiempoDisponible = tiempoMaximo;
		this.nombre = nombre;
		this.propuestasCompradas = propuestasCompradas;

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
		for (Propuesta propuestaContratada : propuestasCompradas) {
			for(Atraccion atraccionContratada : propuestaContratada.getAtraccionesIncluidas() )		
				atraccionNoInculida &= !propuesta.getAtraccionesIncluidas().contains(atraccionContratada);
		}
		return atraccionNoInculida;
	}

	public void agregarPropuestaAceptada(Propuesta nuevaPropuesta) {
		propuestasCompradas.add(nuevaPropuesta);
		tiempoDisponible -= nuevaPropuesta.getTiempoUtilizado();
		presupuestoDisponible -= nuevaPropuesta.getCosto();
		UserDAO userDAO = DAOFactory.getUserDAO();
		userDAO.update(this);
		userDAO.insertPropuestaContratadas(this, nuevaPropuesta);
	}

	public boolean aceptaPropuesta(Propuesta propuesta) {
	 
		return LectorConsola.esperarRespuestaUsuario().equals("S");
	}

	public int getPresupuestoInicial() {
		return presupuestoInicial;
	}

	public double getTiempoMaximoInicial() {
		return tiempoMaximoInicial;
	}

}
