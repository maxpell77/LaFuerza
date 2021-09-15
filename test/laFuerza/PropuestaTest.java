package laFuerza;

import static org.junit.Assert.*;

import org.junit.Test;

public class PropuestaTest {

	@Test
	public void actualizarCupoDisponibleTest() {
		Propuesta atC100T10 = new Atraccion(100, 10, TipoAtraccion.LADO_OSCURO, 1, "at1");
		assertTrue(atC100T10.hayCupoDisponible());
		atC100T10.actualizarCupoDisponible();
		assertFalse(atC100T10.hayCupoDisponible());
	}

}
