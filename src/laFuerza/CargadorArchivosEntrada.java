package laFuerza;

import java.util.LinkedList;
import java.util.List;

import dao.AtraccionesDAO;
import dao.DAOFactory;
import dao.PromocionesDAO;
import dao.UserDAO;

public abstract class CargadorArchivosEntrada {
	private static List<Usuario> usuarios = new LinkedList<Usuario>();
	private static LinkedList<Propuesta> propuestas = new LinkedList<Propuesta>();

	public static void agregarAtracciones() {
		AtraccionesDAO atraccionesDAO = DAOFactory.getAtraccionesDAO();
		List<Atraccion> atracciones = atraccionesDAO.findAll();

		for (Atraccion atraccion : atracciones) {

			propuestas.add(atraccion);
		}

	}

	public static void agregarPromociones() {

		PromocionesDAO promocionesDAO = DAOFactory.getPromocinoesDAO();
		List<Promocion> promociones = promocionesDAO.findAll();

		for (Promocion promocion : promociones) {
			propuestas.add(promocion);
			promocion.setCosto();
			promocion.setTiempoUtilizado();
		}

	}

	public static void agregarUsuarios() {
		UserDAO userDAO = DAOFactory.getUserDAO();
		usuarios = userDAO.findAll();

	}

	private static Atraccion obtenerAtraccionPorNombre(String nombre) {
		for (Propuesta atraccion : propuestas) {
			if (atraccion.getNombre().equals(nombre)) {
				return (Atraccion) atraccion;
			}
		}
		return null;
	}

	public static List<Usuario> getUsuarios() {
		return usuarios;
	}

	public static LinkedList<Propuesta> getPropuestas() {
		return propuestas;
	}

}
