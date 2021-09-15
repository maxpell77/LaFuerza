package laFuerza;

import static org.junit.Assert.*;

import org.junit.Test;

public class AtraccionTest {

	@Test
	public void actualizarCupoDisponibleTest() {
		Atraccion atC100T10 = new Atraccion(100, 10, TipoAtraccion.LADO_OSCURO, 5, "at1");
		assertTrue(atC100T10.hayCupoDisponible());
		atC100T10.actualizarCupoDisponible();
		assertEquals(4, atC100T10.getCupoDisponible());
	}

}
