package laFuerza;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {
	Atraccion atC100T10;
	Atraccion atC150T5;
	Usuario usC300T30;
	Usuario usC10T30;
	Usuario usC200T1;
	LinkedList<Atraccion> atraccionesIncluidas = new LinkedList<Atraccion>();
	Promocion prC150T15;

	@Before
	public void setUp() {
		// C=costo , T=tiempo
		atC100T10 = new Atraccion(100, 10, TipoAtraccion.LADO_OSCURO, 100, "at1");
		atC150T5 = new Atraccion(150, 5, TipoAtraccion.LADO_OSCURO, 100, "at2");
		usC300T30 = new Usuario("u1", TipoAtraccion.LADO_OSCURO, 300, 30);
		usC10T30 = new Usuario("u2", TipoAtraccion.LADO_OSCURO, 10, 30);
		usC200T1 = new Usuario("u3", TipoAtraccion.LADO_OSCURO, 200, 1);
		atraccionesIncluidas.add(atC100T10);
		atraccionesIncluidas.add(atC150T5);
		prC150T15 = new PromoAbsoluta(TipoAtraccion.LADO_OSCURO, "promo1", "descripcion", atraccionesIncluidas, 150);
		prC150T15.setCosto();
		prC150T15.setTiempoUtilizado();
	}

	@Test
	public void usuarioPuedeAdquirirAtraccionTest() {
		assertTrue(usC300T30.puedeAdquirirPropuesta(atC100T10));
	}

	@Test
	public void usuarioAdquiereAtraccionTest() {
		usC300T30.agregarPropuestaAceptada(atC100T10);
		assertEquals(200, usC300T30.getPresupuestoDisponible());
		assertEquals(20, usC300T30.getTiempoDisponible(), 0);
		String atraccionContratada = usC300T30.getAtraccionesContratadas().getFirst().getNombre();
		assertEquals("at1", atraccionContratada);
	}

	@Test
	public void usuarioNoPuedeAdquirirAtraccionPorPresupuestoTest() {
		assertFalse(usC10T30.puedeAdquirirPropuesta(atC100T10));
	}

	@Test
	public void usuarioNoPuedeAdquirirAtraccionPorTiempoTest() {
		assertFalse(usC200T1.puedeAdquirirPropuesta(atC100T10));
	}

	@Test
	public void usuarioNoPuedeAdquirirAtraccionYaContratadaTest() {
		usC300T30.agregarPropuestaAceptada(atC100T10);
		assertFalse(usC300T30.puedeAdquirirPropuesta(atC100T10));
	}

	@Test
	public void usuarioPuedeAdquirirPromocionTest() {
		assertTrue(usC300T30.puedeAdquirirPropuesta(prC150T15));
	}

	@Test
	public void usuarioAdquierePromocionTest() {
		usC300T30.agregarPropuestaAceptada(prC150T15);
		assertEquals(150, usC300T30.getPresupuestoDisponible());
		assertEquals(15, usC300T30.getTiempoDisponible(), 0);
	}

	@Test
	public void usuarioNoPuedeAdquirirPromocionPorPresupuestoTest() {
		assertFalse(usC10T30.puedeAdquirirPropuesta(prC150T15));
	}

	@Test
	public void usuarioNoPuedeAdquirirPromocionPorTiempoTest() {
		assertFalse(usC200T1.puedeAdquirirPropuesta(prC150T15));
	}

	@Test
	public void usuarioNoPuedeAdquirirAtraccionYaContratadaEnPromocionTest() {
		usC300T30.agregarPropuestaAceptada(atC100T10);
		assertFalse(usC300T30.puedeAdquirirPropuesta(prC150T15));
	}

}
