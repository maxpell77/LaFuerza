package laFuerza;

import static org.junit.Assert.*;


import org.junit.Test;

import dao.AtraccionesDAO;
import dao.DAOFactory;

public class AtraccionTest {

	@Test
	public void actualizarCupoDisponibleTest() {

		Atraccion testAT = new Atraccion(100, 10, TipoAtraccion.LADO_OSCURO, 2, "at1", 21);
		assertTrue(testAT.hayCupoDisponible());
		testAT.actualizarCupoDisponible();
		assertEquals(1, testAT.getCupoDisponible());

	}

	@Test
	public void actualizarCupoDisponibleBDTest() {

		AtraccionesDAO atraccionesDAO = DAOFactory.getAtraccionesDAO();
		Atraccion testAT = new Atraccion(100, 10, TipoAtraccion.LADO_OSCURO, 2, "at1", 21);

		atraccionesDAO.insert(testAT);
		Atraccion at1BD = atraccionesDAO.findByname("at1");

		testAT.setId_atraccion(at1BD.getId_atraccion());

		testAT.actualizarCupoDisponible();
		assertEquals(1, testAT.getCupoDisponible());

		atraccionesDAO.delete(testAT);

	}

}
