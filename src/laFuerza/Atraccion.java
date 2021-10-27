package laFuerza;

import java.util.LinkedList;

import dao.AtraccionesDAO;
import dao.DAOFactory;

public class Atraccion extends Propuesta {

	private int cupoDisponible;
	private int cupoInicial;
	private int id_atraccion;

	public Atraccion(int costo, double duracion, TipoAtraccion tipoAtraccion, int cupoMaximo, String nombre, int id_atraccion) {
		super(id_atraccion);
		this.costo = costo;
		this.tiempoTotal = duracion;
		this.tipoAtraccion = tipoAtraccion;
		this.cupoDisponible = cupoMaximo;
		this.cupoInicial = cupoMaximo;
		this.nombre = nombre;
		this.id_atraccion = id_atraccion;
	}
	

	@Override
	public void actualizarCupoDisponible() {
		cupoDisponible--;
		if (cupoDisponible == 0)
			hayCupoDisponible = false;
		
		AtraccionesDAO atraccionDAO = DAOFactory.getAtraccionesDAO();
		atraccionDAO.updateCupos(this);
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

	public int getId_atraccion() {
		return id_atraccion;
	}
	
	public int setId_atraccion(int id) {
		return id_atraccion = id;
	}

}
