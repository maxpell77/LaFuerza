package laFuerza;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class PromocionTest {
	PromoAbsoluta pAbsC200T15;
	PromocionAXB pAXBC400T35;
	PromoPorcentual pPor200T15;

	@Before
	public void setUp() {

		Atraccion atC100T10 = new Atraccion(250, 10, TipoAtraccion.LADO_OSCURO, 100, "at1");
		Atraccion atC150T5 = new Atraccion(150, 5, TipoAtraccion.LADO_OSCURO, 1, "at2");
		Atraccion atC50T5 = new Atraccion(50, 20, TipoAtraccion.LADO_OSCURO, 20, "at3");
		LinkedList<Atraccion> atraccionesIncluidas = new LinkedList<Atraccion>();
		LinkedList<Atraccion> atraccionesGratis = new LinkedList<Atraccion>();
		LinkedList<Atraccion> atraccionesAXB = new LinkedList<Atraccion>();
		atraccionesIncluidas.add(atC100T10);
		atraccionesIncluidas.add(atC150T5);
		atraccionesGratis.add(atC50T5);
		atraccionesAXB.add(atC100T10);
		atraccionesAXB.add(atC150T5);
		atraccionesAXB.add(atC50T5);
		pAbsC200T15 = new PromoAbsoluta(TipoAtraccion.LADO_OSCURO, "promo1", "descripcion", atraccionesIncluidas, 200);
		pAXBC400T35 = new PromocionAXB(TipoAtraccion.LADO_OSCURO, "promo2", "descripcion", atraccionesAXB,
				atraccionesGratis);
		pPor200T15 = new PromoPorcentual(TipoAtraccion.LADO_OSCURO, "promo3", "descripcion", atraccionesIncluidas, 0.5);
		pAbsC200T15.setTiempoUtilizado();
		pAXBC400T35.setTiempoUtilizado();
		pPor200T15.setTiempoUtilizado();
		pAbsC200T15.setCosto();
		pAXBC400T35.setCosto();
		pPor200T15.setCosto();

	}

	@Test
	public void actualizarCupoDisponiblePromoTest() {
		pAbsC200T15.actualizarCupoDisponible();
		int cupoAT1 = pAbsC200T15.getAtraccionesIncluidas().get(0).getCupoDisponible();
		assertEquals(99, cupoAT1);

		int cupoAT2 = pAbsC200T15.getAtraccionesIncluidas().get(1).getCupoDisponible();
		assertEquals(0, cupoAT2);

		assertFalse(pAbsC200T15.hayCupoDisponible());

	}

	@Test
	public void getTiempoPromoAbsolutaTest() {
		assertEquals(15, pAbsC200T15.getTiempoUtilizado(), 0);
	}

	@Test
	public void getTiempoPromoAXBTest() {
		assertEquals(35, pAXBC400T35.getTiempoUtilizado(), 0);
	}

	@Test
	public void getCostoPromoAbsolutaTest() {
		assertEquals(200, pAbsC200T15.getCosto());
	}

	@Test
	public void getCostoAXBTest() {
		assertEquals(400, pAXBC400T35.getCosto());
	}

	public void getCostoPorcentualTest() {
		assertEquals(200, pPor200T15.getCosto());
	}

}
