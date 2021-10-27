package laFuerza;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import dao.AtraccionesDAO;
import dao.DAOFactory;
import dao.PromocionesDAO;
import dao.UsuariosDAO;

public class CargadorDeObjetosTest {

	@Test
	public void agregarAtraccionesTest() {

		LinkedList<Propuesta> propuestas = CargadorDeObjetos.getPropuestas();
		int cantidadCargada = propuestas.size();
		assertEquals(0, cantidadCargada);

		CargadorDeObjetos.agregarAtracciones();
		propuestas = CargadorDeObjetos.getPropuestas();
		cantidadCargada = propuestas.size();

		AtraccionesDAO atraccionesDAO = DAOFactory.getAtraccionesDAO();
		int atraccionesenBD = atraccionesDAO.countAll();
		assertEquals(cantidadCargada, atraccionesenBD);

	}

	@Test
	public void agregarPromocionesTest() {

		LinkedList<Propuesta> propuestas = CargadorDeObjetos.getPropuestas();
		int cantidadCargada = propuestas.size();
		assertEquals(0, cantidadCargada);

		CargadorDeObjetos.agregarPromociones();
		propuestas = CargadorDeObjetos.getPropuestas();
		cantidadCargada = propuestas.size();

		PromocionesDAO promocionesDAO = DAOFactory.getPromocinoesDAO();
		int promocionesenBD = promocionesDAO.countAll();
		assertEquals(cantidadCargada, promocionesenBD);

	}

	@Test
	public void agregarUsuariosTest() {

		List<Usuario> usuarios = CargadorDeObjetos.getUsuarios();
		int cantidadCargada = usuarios.size();
		assertEquals(0, cantidadCargada);

		CargadorDeObjetos.agregarUsuarios();
		usuarios = CargadorDeObjetos.getUsuarios();
		cantidadCargada = usuarios.size();

		UsuariosDAO usuarioDAO = DAOFactory.getUserDAO();
		int usuariosBD = usuarioDAO.countAll();
		assertEquals(cantidadCargada, usuariosBD);

	}

}
