package laFuerza;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OfertadorTest {

	List<String> atracciones = new LinkedList<String>();
	List<String> promociones = new LinkedList<String>();

	@Before
	public void setUp() {
		atracciones.add("AtTest1,40,10,2,LADO_OSCURO");// nombre, costo, tiempo, cupo, tipoAtraccion
		atracciones.add("AtTest2,60,20,4,LADO_OSCURO");
		atracciones.add("AtTest3,50,30,2,LADO_OSCURO");
		atracciones.add("AtTest4,20,40,2,LADO_LUMINOSO");
		atracciones.add("AtTest5,30,50,3,LADO_LUMINOSO");
		atracciones.add("AtTest6,40,60,3,LADO_LUMINOSO");
		// costo 50
		promociones.add(
				"PORCENTUAL,  LADO_OSCURO ,Pack Oscuro 1 , AtTest1 y AtTest2 con 50%dto,       0.50 ,   AtTest1;AtTest2");
		// costo 20
		promociones.add(
				"ABSOLUTA,    LADO_LUMINOSO, Pack Luminoso 1,  AtTest4 y la AtTest5 a 20 monedas., 20,      AtTest4;AtTest5");
		// costo 100
		promociones.add(
				"AXB,         LADO_OSCURO, Pack Oscuro 2,  AtTest1 y AtTest2 AtTest3 gratis ., AtTest3, AtTest1;AtTest2;AtTest3");
		CargadorArchivosEntrada.agregarAtracciones(atracciones);// 6 atracciones en setUp
		CargadorArchivosEntrada.agregarPromociones(promociones); // 3 promocinoes en setUp

	}

	@Test
	public void ordenarPropuestasTest() {
		List<Propuesta> propuestasFiltradas = new ArrayList<Propuesta>();
		propuestasFiltradas = separarPropuestas(TipoAtraccion.LADO_OSCURO);
		Collections.sort(propuestasFiltradas);

		int i = 0;
		String[] propuestaEsperada = { "Pack Oscuro 2", "Pack Oscuro 1", "AtTest2", "AtTest3", "AtTest1" };

		for (Propuesta propuesta : propuestasFiltradas) {
			assertEquals(propuestaEsperada[i], propuesta.getNombre());
			i++;
		}
	}

	private List<Propuesta> separarPropuestas(TipoAtraccion tipoAtraccion) {
		List<Propuesta> propuestaFiltrada = new ArrayList<Propuesta>();
		for (Propuesta propuesta : CargadorArchivosEntrada.getPropuestas()) {
			if (propuesta.getTipoAtraccion() == tipoAtraccion) {
				propuestaFiltrada.add(propuesta);
			}
		}
		return propuestaFiltrada;
	}

}
