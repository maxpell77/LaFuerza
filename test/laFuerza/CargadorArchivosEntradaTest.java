package laFuerza;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CargadorArchivosEntradaTest {

	List<String> atracciones = new LinkedList<String>();
	List<String> promociones = new LinkedList<String>();
	List<String> usuarios = new LinkedList<String>();

//	@Before
//	public void setUp() {
//		atracciones.add("AtTest1,40,10,2,LADO_OSCURO");// nombre, costo, tiempo, cupo, tipoAtraccion
//		atracciones.add("AtTest2,60,20,4,LADO_OSCURO");
//		atracciones.add("AtTest3,50,30,2,LADO_OSCURO");
//		atracciones.add("AtTest4,20,40,2,LADO_LUMINOSO");
//		atracciones.add("AtTest5,30,50,3,LADO_LUMINOSO");
//		atracciones.add("AtTest6,40,60,3,LADO_LUMINOSO");
//		// costo 50
//		promociones.add(
//				"PORCENTUAL,  LADO_OSCURO ,Pack Oscuro 1 , AtTest1 y AtTest2 con 50%dto,       0.50 ,   AtTest1;AtTest2");
//		// costo 20
//		promociones.add(
//				"ABSOLUTA,    LADO_LUMINOSO, Pack Luminoso 1,  AtTest4 y la AtTest5 a 20 monedas., 20,      AtTest4;AtTest5");
//		// costo 100
//		promociones.add(
//				"AXB,         LADO_OSCURO, Pack Oscuro 2,  AtTest1 y AtTest2 AtTest3 gratis ., AtTest3, AtTest1;AtTest2;AtTest3");
//
//		CargadorArchivosEntrada.agregarAtracciones(atracciones);// 6 atracciones en setUp
//		CargadorArchivosEntrada.agregarPromociones(promociones); // 3 promocinoes en setUp
//		usuarios.add("U1, LADO_OSCURO, 100, 10");
//		CargadorArchivosEntrada.agregarUsuarios(usuarios);
//	}

	@Test
	public void cargarAtraccionTest() {
		Propuesta atraccion1 = CargadorArchivosEntrada.getPropuestas().getFirst();
		assertEquals("AtTest1", atraccion1.getNombre());
		assertEquals(40, atraccion1.getCosto());
		assertEquals(10, atraccion1.getTiempoUtilizado(), 0);
		assertTrue(atraccion1.hayCupoDisponible());
		assertEquals(TipoAtraccion.LADO_OSCURO, atraccion1.getTipoAtraccion());
	}

	@Test
	public void cargarPromocionesPorcentualTest() {
		Propuesta promocion = CargadorArchivosEntrada.getPropuestas().get(6); // primera promo cargada
		assertEquals("Pack Oscuro 1", promocion.getNombre());
		assertEquals(50, promocion.getCosto());
		assertEquals(30, promocion.getTiempoUtilizado(), 0);
		assertTrue(promocion.hayCupoDisponible());
		assertEquals(TipoAtraccion.LADO_OSCURO, promocion.getTipoAtraccion());
		assertEquals(2, promocion.getAtraccionesIncluidas().size());
	}

	@Test
	public void cargarPromocionesAbsolutaTest() {
		Propuesta promocion = CargadorArchivosEntrada.getPropuestas().get(7); // segunda promo cargada
		assertEquals("Pack Luminoso 1", promocion.getNombre());
		assertEquals(20, promocion.getCosto());
		assertEquals(90, promocion.getTiempoUtilizado(), 0);
		assertTrue(promocion.hayCupoDisponible());
		assertEquals(TipoAtraccion.LADO_LUMINOSO, promocion.getTipoAtraccion());
		assertEquals(2, promocion.getAtraccionesIncluidas().size());
	}

	@Test
	public void cargarPromocionesAXB() {
		Propuesta promocion = CargadorArchivosEntrada.getPropuestas().get(8); // tercera promo cargada
		assertEquals("Pack Oscuro 2", promocion.getNombre());
		assertEquals(100, promocion.getCosto());
		assertEquals(60, promocion.getTiempoUtilizado(), 0);
		assertTrue(promocion.hayCupoDisponible());
		assertEquals(TipoAtraccion.LADO_OSCURO, promocion.getTipoAtraccion());
		assertEquals(3, promocion.getAtraccionesIncluidas().size());
	}

//	@Test
//	public void cargarUsuarioTest() {
//		Usuario usuario = CargadorArchivosEntrada.getUsuarios().getFirst();
//		assertEquals("U1", usuario.getNombre());
//		assertEquals(TipoAtraccion.LADO_OSCURO, usuario.getTipoAtraccionPreferida());
//		assertEquals(100, usuario.getPresupuestoDisponible());
//		assertEquals(10, usuario.getTiempoDisponible(), 0);
//	}

}
