package tierraMedia;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TierraMediaTest {
	TierraMedia tierraMedia;
	List<String> atracciones;
	List<String> promociones;
	List<String> usuarios;

	@Before
	public void cargarArchivos() {
		tierraMedia = new TierraMedia();
	}

	@Test
	public void leerArchivosTest() {

		atracciones = LectorArchivos.leerArchivo("test/entrada/atracciones.txt");
		promociones = LectorArchivos.leerArchivo("test/entrada/promociones.txt");
		usuarios = LectorArchivos.leerArchivo("test/entrada/usuarios.txt");

		String esperado = "AtTest1,10,5,2,AVENTURA";
		assertEquals(esperado, atracciones.get(0));

		esperado = "PORCENTUAL,  AVENTURA ,Pack Aventura 1 , AtTest1 y AtTest2 con 30%dto,       0.30 ,   AtTest1;AtTest2";
		assertEquals(esperado, promociones.get(0));

		esperado = "U1, AVENTURA, 100, 100";
		assertEquals(esperado, usuarios.get(0));
	}

	@Test
	public void cargarAtraccionTest() {

		LinkedList<String> atracciones = new LinkedList<String>();
		atracciones.add("AtTest1,10,5,2,AVENTURA");// nombre, costo, tiempo, cupo, tipoAtraccion
		tierraMedia.agregarAtracciones(atracciones);

		Propuesta atraccion1 = tierraMedia.getPropuestas().getFirst();

		assertEquals("AtTest1", atraccion1.getNombre());
		assertEquals(10, atraccion1.getCosto());
		assertEquals(5, atraccion1.getTiempoUtilizado(), 0);
		assertTrue(atraccion1.hayCupoDisponible());
		assertEquals(TipoAtraccion.AVENTURA, atraccion1.getTipoAtraccion());
	}

	@Test
	public void cargarPromocionesTest() {
		LinkedList<String> atracciones = new LinkedList<String>();
		atracciones.add("AtTest1,40,10,2,AVENTURA");// nombre, costo, tiempo, cupo, tipoAtraccion
		atracciones.add("AtTest2,60,10,4,AVENTURA");
		atracciones.add("AtTest3,50,10,2,AVENTURA");
		tierraMedia.agregarAtracciones(atracciones);

		LinkedList<String> promociones = new LinkedList<String>();

		promociones.add(
				"PORCENTUAL,  AVENTURA ,Pack Aventura 1 , AtTest1 y AtTest2 con 30%dto,       0.25 ,   AtTest1;AtTest2");
		tierraMedia.agregarPromociones(promociones);
		Propuesta promocion = tierraMedia.getPropuestas().get(3);
		assertEquals("Pack Aventura 1", promocion.getNombre());
		assertEquals(75, promocion.getCosto());
		assertEquals(20, promocion.getTiempoUtilizado(), 0);
		assertTrue(promocion.hayCupoDisponible());
		assertEquals(TipoAtraccion.AVENTURA, promocion.getTipoAtraccion());
		assertEquals(2, promocion.getAtraccionesIncluidas().size());

		promociones.clear();
		promociones.add(
				"ABSOLUTA,    AVENTURA, Pack Aventura 2,  AtTest1 y la AtTest2 a 80 monedas., 80,      AtTest1;AtTest2");
		tierraMedia.agregarPromociones(promociones);
		promocion = tierraMedia.getPropuestas().get(4);
		assertEquals("Pack Aventura 2", promocion.getNombre());
		assertEquals(80, promocion.getCosto());
		assertEquals(20, promocion.getTiempoUtilizado(), 0);
		assertTrue(promocion.hayCupoDisponible());
		assertEquals(TipoAtraccion.AVENTURA, promocion.getTipoAtraccion());
		assertEquals(2, promocion.getAtraccionesIncluidas().size());

		promociones.clear();
		promociones.add(
				"AXB,         AVENTURA, Pack Aventura 3,  AtTest2 y AtTest3 AtTest1 gratis ., AtTest1, AtTest1;AtTest2;AtTest3");
		tierraMedia.agregarPromociones(promociones);
		promocion = tierraMedia.getPropuestas().get(5);
		assertEquals("Pack Aventura 3", promocion.getNombre());
		assertEquals(110, promocion.getCosto());
		assertEquals(30, promocion.getTiempoUtilizado(), 0);
		assertTrue(promocion.hayCupoDisponible());
		assertEquals(TipoAtraccion.AVENTURA, promocion.getTipoAtraccion());
		assertEquals(3, promocion.getAtraccionesIncluidas().size());

	}

	@Test
	public void cargarUsuarioTest() {
		LinkedList<String> usuarios = new LinkedList<String>();
		usuarios.add("U1, AVENTURA, 100, 10"); // nombre, tipoAtraccion, presupuesto, tiempoDisponible
		tierraMedia.agregarUsuarios(usuarios);

		Usuario usuario = tierraMedia.getUsuarios().getFirst();
		assertEquals("U1", usuario.getNombre());
		assertEquals(TipoAtraccion.AVENTURA, usuario.getTipoAtraccionPreferida());
		assertEquals(100, usuario.getPresupuestoDisponible());
		assertEquals(10, usuario.getTiempoDisponible(), 0);
	}

	@Test
	public void ordenarPropuestasTest() {

		LinkedList<String> atracciones = new LinkedList<String>();
		atracciones.add("AtTest1, 40, 20,2,AVENTURA");// nombre, costo, tiempo, cupo, tipoAtraccion
		atracciones.add("AtTest2,  3,  4,2,AVENTURA");
		atracciones.add("AtTest3,  2,  1,5,AVENTURA");
		atracciones.add("AtTest4,100, 40,2,DEGUSTACION");
		atracciones.add("AtTest5,150, 10,4,AVENTURA");
		atracciones.add("AtTest6,  2,  2,1,DEGUSTACION");
		tierraMedia.agregarAtracciones(atracciones);

		LinkedList<String> promociones = new LinkedList<String>();
		promociones
				.add("PORCENTUAL, AVENTURA ,Pack Aventura 1 , AtTest1 y AtTest2 con 30%dto,  0.25 ,  AtTest1;AtTest2");
		promociones
				.add("ABSOLUTA, AVENTURA, Pack Aventura 2,  AtTest1 y la AtTest2 a 80 monedas., 80,  AtTest1;AtTest2");
		promociones.add(
				"ABSOLUTA, AVENTURA, Pack Aventura 3,  AtTest1 y la AtTest3 a 1000 monedas., 1000, AtTest1;AtTest3");
		promociones.add("ABSOLUTA, DEGUSTACION, Pack Degustaion 1,  AtTest4 y AtTest6 a 120., 120, AtTest4;AtTest6");

		tierraMedia.agregarPromociones(promociones);

		Collections.sort(tierraMedia.getPropuestas());

		Propuesta propuesta = tierraMedia.getPropuestas().getFirst();
		assertEquals("Pack Aventura 3", propuesta.getNombre()); // primero las promocinoes mas caras

		propuesta = tierraMedia.getPropuestas().getLast();
		assertEquals("AtTest3", propuesta.getNombre()); // ultimo las atracciones menos caras, menos tiempo

		propuesta = tierraMedia.getPropuestas().get(8);
		assertEquals("AtTest6", propuesta.getNombre()); // ante ultimo las atracciones menos caras, mas tiemp

		propuesta = tierraMedia.getPropuestas().get(7);
		assertEquals("AtTest2", propuesta.getNombre());

	}

	@Test
	public void usuarioAdquiriendoPropuestasTest() {

		int costo = 100;
		double tiempo = 100;
		Atraccion at1 = new Atraccion(costo, tiempo, TipoAtraccion.AVENTURA, 3, "at1");

		int presupuesto = 300;
		double tiempoDisponible = 300;
		Usuario u1 = new Usuario("u1", TipoAtraccion.AVENTURA, presupuesto, tiempoDisponible);
		assertTrue(u1.puedeAdquirirPropuesta(at1));
		u1.agregarPropuestaAceptada(at1);
		assertEquals(200, u1.getPresupuestoDisponible());
		assertEquals(200, u1.getTiempoDisponible(), 0);
		at1.actualizarCupoDisponible(); // cupo post compra = 2
		assertFalse(u1.puedeAdquirirPropuesta(at1)); // no puede adquirir una propuesta ya contratada

		presupuesto = 99;
		tiempoDisponible = 100;
		Usuario u2 = new Usuario("u1", TipoAtraccion.AVENTURA, presupuesto, tiempoDisponible);
		assertFalse(u2.puedeAdquirirPropuesta(at1)); // no tiene presupuesto suficiente

		presupuesto = 100;
		tiempoDisponible = 99;
		Usuario u3 = new Usuario("u1", TipoAtraccion.AVENTURA, presupuesto, tiempoDisponible);
		assertFalse(u3.puedeAdquirirPropuesta(at1)); // no tiene tiempo suficiente

		costo = 200;
		tiempo = 100;
		Atraccion at2 = new Atraccion(costo, tiempo, TipoAtraccion.AVENTURA, 5, "at2");
		LinkedList<Atraccion> atraccionesIncluidas = new LinkedList<Atraccion>();
		atraccionesIncluidas.add(at1);
		atraccionesIncluidas.add(at2);
		Promocion p1 = new PromoPorcentual(TipoAtraccion.AVENTURA, "promo1", "descripcion", atraccionesIncluidas, 0.5); // 150
		p1.setCosto();
		p1.setTiempoUtilizado();

		presupuesto = 400;
		tiempoDisponible = 200;
		Usuario u4 = new Usuario("u1", TipoAtraccion.AVENTURA, presupuesto, tiempoDisponible);

		assertTrue(u4.puedeAdquirirPropuesta(at1));
		u4.agregarPropuestaAceptada(at1);
		assertEquals(300, u4.getPresupuestoDisponible());
		assertEquals(100, u4.getTiempoDisponible(), 0);
		at1.actualizarCupoDisponible();// cupo post compra = 1
		assertTrue(at1.hayCupoDisponible());
		assertTrue(p1.hayCupoDisponible());
		assertFalse(u4.puedeAdquirirPropuesta(p1)); // no puede adquirir promo porque ya compró at1 (dentro de p1)

		presupuesto = 400;
		tiempoDisponible = 500;
		Usuario u5 = new Usuario("u1", TipoAtraccion.AVENTURA, presupuesto, tiempoDisponible);
		assertTrue(u5.puedeAdquirirPropuesta(p1));
		u5.agregarPropuestaAceptada(p1);
		p1.actualizarCupoDisponible(); // cupo post compra = 0 ( at1 se quedó sin cupo)
		assertFalse(p1.hayCupoDisponible());
		assertEquals(250, u5.getPresupuestoDisponible());
		assertEquals(300, u5.getTiempoDisponible(), 0);
		assertFalse(u5.puedeAdquirirPropuesta(p1)); // no puede adquirir una promo que ya compró

	}

	


	@Test
	public void resumenIntinerarioTest() throws IOException {
		Atraccion at1 = new Atraccion(150, 2, TipoAtraccion.AVENTURA, 3, "at1");
		Atraccion at2 = new Atraccion(150, 3, TipoAtraccion.AVENTURA, 5, "at2");
		LinkedList<Atraccion> atraccionesIncluidas = new LinkedList<Atraccion>();
		atraccionesIncluidas.add(at1);
		atraccionesIncluidas.add(at2);
		Promocion p1 = new PromoPorcentual(TipoAtraccion.AVENTURA, "promo1", "descripcion", atraccionesIncluidas, 0.5); // costo
																														// 150
		p1.setCosto();
		p1.setTiempoUtilizado();
		Usuario u1 = new Usuario("u1", TipoAtraccion.AVENTURA, 400, 10); // saldo inicial 400, tiempo disponible 10
		u1.agregarPropuestaAceptada(p1);

		Atraccion at3 = new Atraccion(50, 1, TipoAtraccion.AVENTURA, 2, "at3");
		u1.agregarPropuestaAceptada(at3);
		u1.escribirIntinerario();

		assertEquals(200, u1.getPresupuestoDisponible()); 
		assertEquals(4, u1.getTiempoDisponible(), 0); 
		
		//compró promo 1 ( costo 150 monedas, tiempo 5 horas) y at3 (costo 50, horas 1)
		//total a pagar 200 monedas, total tiempo usado 6 horas
		//saldo 200 monedas y 4 horas disponibles
		assertEquals(-1, compararArchivosLineaporLinea("test/salida/u1.txt", "salida/u1.txt"));
		
		
		Usuario u2 = new Usuario("u2", TipoAtraccion.AVENTURA, 200, 5); // saldo inicial 400, tiempo disponible 10
		u2.escribirIntinerario();
		
		
		//no compra ninguna propuesta, difiere mensaje inicial  y final
		assertEquals(-1, compararArchivosLineaporLinea("test/salida/u2.txt", "salida/u2.txt"));
		

	}

	private  long compararArchivosLineaporLinea(String string, String string2) throws IOException {
		FileReader fl1 = null;
		BufferedReader bf1 = null;
		FileReader fl2 = null;
		BufferedReader bf2 = null;
		long lineNumber = 1;

		try {
			fl1 = new FileReader(string);
			bf1 = new BufferedReader(fl1);
			fl2 = new FileReader(string2);
			bf2 = new BufferedReader(fl2);

			String line1 = "", line2 = "";
			while ((line1 = bf1.readLine()) != null) {
				line2 = bf2.readLine();
				if (line2 == null || !line1.equals(line2)) {
					return lineNumber;
				}
				lineNumber++;
			}
			if (bf2.readLine() == null) {
				return -1;
			} else {
				return lineNumber;
			}
		} catch (IOException e) {
			e.printStackTrace();

		} finally {

			try {
				if (fl1 != null) {
					fl1.close();
				}
				if (fl2 != null) {
					fl2.close();
				}
				

			} catch (Exception e2) {
				e2.printStackTrace();

			}
		
		}
		return lineNumber;

	}

	@Test
	public void modificadorFormatoHoraTest() {
		double hora = 2.0;
		
		String horaModificada = ModificadorFormatoHora.obtenerHoraConFormato(hora);
		String esperado = "2 horas";
		
		assertEquals(esperado, horaModificada);
		
		hora = 1.5;
		horaModificada = ModificadorFormatoHora.obtenerHoraConFormato(hora);
		esperado = "1 hora con 30 minutos";
		assertEquals(esperado, horaModificada);
		
		hora = 0.02;
		horaModificada = ModificadorFormatoHora.obtenerHoraConFormato(hora);
		esperado = "1 minuto";
		assertEquals(esperado, horaModificada);
		
		hora = 0.99;
		horaModificada = ModificadorFormatoHora.obtenerHoraConFormato(hora);
		esperado = "59 minutos";
		assertEquals(esperado, horaModificada);
		
		

	}

}
