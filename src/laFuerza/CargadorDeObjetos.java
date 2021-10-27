package laFuerza;

import java.util.LinkedList;
import java.util.List;

import dao.AtraccionesDAO;
import dao.DAOFactory;
import dao.PromocionesDAO;
import dao.UsuariosDAO;

public abstract class CargadorDeObjetos {
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

		}

	}

	public static void agregarUsuarios() {
		UsuariosDAO userDAO = DAOFactory.getUserDAO();
		usuarios = userDAO.findAll();

	}

	public static List<Usuario> getUsuarios() {
		return usuarios;
	}

	public static LinkedList<Propuesta> getPropuestas() {
		return propuestas;
	}

}
