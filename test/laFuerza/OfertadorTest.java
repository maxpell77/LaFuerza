package laFuerza;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;



public class OfertadorTest {

	List<Propuesta> propuestas = new LinkedList<Propuesta>();

	@Before
	public void setUp() {
		Atraccion testA1 = new Atraccion(40, 10, TipoAtraccion.LADO_OSCURO, 10, "AtTest1", 1);
		propuestas.add(testA1);
		Atraccion testA2 = new Atraccion(60, 20, TipoAtraccion.LADO_OSCURO, 10, "AtTest2", 2);
		propuestas.add(testA2);
		Atraccion testA3 = new Atraccion(50, 30, TipoAtraccion.LADO_OSCURO, 10, "AtTest3", 3);
		propuestas.add(testA3);
		Atraccion testA4 = new Atraccion(20, 40, TipoAtraccion.LADO_LUMINOSO, 10, "AtTest4", 4);
		propuestas.add(testA4);
		Atraccion testA5 = new Atraccion(30, 50, TipoAtraccion.LADO_LUMINOSO, 10, "AtTest5", 5);
		propuestas.add(testA5);
		Atraccion testA6 = new Atraccion(40, 60, TipoAtraccion.LADO_LUMINOSO, 10, "AtTest6", 6);
		propuestas.add(testA6);

		LinkedList<Atraccion> atraccionesIncludias = new LinkedList<Atraccion>();
		atraccionesIncludias.add(testA1);

		//costo100	
		Promocion promo1 = new PromoAbsoluta(TipoAtraccion.LADO_OSCURO, "Pack Oscuro 1", "descripcion", atraccionesIncludias, 100, 7);
		propuestas.add(promo1);
		
		//costo 50000
		Promocion promo2 = new PromoAbsoluta(TipoAtraccion.LADO_LUMINOSO, "Pack Luminoso 1", "descripcion", atraccionesIncludias, 100, 7);
		propuestas.add(promo2);
		
		
		//costo1000
		Promocion promo3 = new PromoAbsoluta(TipoAtraccion.LADO_OSCURO, "Pack Oscuro 2", "descripcion", atraccionesIncludias, 1000, 7);
		propuestas.add(promo3);
	

	}

	@Test
	public void ordenarPropuestasTest2() {
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
		for (Propuesta propuesta : this.propuestas) {
			if (propuesta.getTipoAtraccion() == tipoAtraccion) {
				propuestaFiltrada.add(propuesta);
			}
		}
		return propuestaFiltrada;
	}

}
