package laFuerza;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class GeneradorIntinerarioUsuarioTest {

	Atraccion atC100T10;
	Atraccion atC150T5;
	Usuario usC300T30;
	Usuario usC100T30;
	Usuario usC200T20;
	LinkedList<Atraccion> atraccionesIncluidas = new LinkedList<Atraccion>();
	Promocion prC150T15;

	@Before
	public void setUp() {
		// C=costo , T=tiempo
		atC100T10 = new Atraccion(100, 10, TipoAtraccion.LADO_OSCURO, 100, "at1");
		atC150T5 = new Atraccion(150, 5, TipoAtraccion.LADO_OSCURO, 100, "at2");
		usC300T30 = new Usuario("u1", TipoAtraccion.LADO_OSCURO, 300, 30);
		usC100T30 = new Usuario("u2", TipoAtraccion.LADO_OSCURO, 100, 30);
		usC200T20 = new Usuario("u3", TipoAtraccion.LADO_OSCURO, 200, 20);
		atraccionesIncluidas.add(atC100T10);
		atraccionesIncluidas.add(atC150T5);
		prC150T15 = new PromoAbsoluta(TipoAtraccion.LADO_OSCURO, "promo1", "descripcion", atraccionesIncluidas, 150);
		prC150T15.setCosto();
		prC150T15.setTiempoUtilizado();
	}

	@Test
	public void resumenIntinerarioNoCompraTest() throws IOException {
		String mensaje = GeneradorIntinerarioUsuario.generarIntinerario(usC300T30);
		EscritorArchivosSalida.escribirArchivos("test/salida/u1_Real.txt", mensaje);
		assertEquals(-1, compararArchivosLineaporLinea("test/salida/u1_Esperado.txt", "test/salida/u1_Real.txt"));
	}
	
	@Test
	public void resumenIntinerarioCompraAtraccionTest() throws IOException {
		usC100T30.agregarPropuestaAceptada(atC100T10);
		String mensaje = GeneradorIntinerarioUsuario.generarIntinerario(usC100T30);
		EscritorArchivosSalida.escribirArchivos("test/salida/u2_Real.txt", mensaje);
		assertEquals(-1, compararArchivosLineaporLinea("test/salida/u2_Esperado.txt", "test/salida/u2_Real.txt"));
	}
	
	@Test
	public void resumenIntinerarioCompraPromocionTest() throws IOException {
		usC200T20.agregarPropuestaAceptada(prC150T15);
		String mensaje = GeneradorIntinerarioUsuario.generarIntinerario(usC200T20);
		EscritorArchivosSalida.escribirArchivos("test/salida/u3_Real.txt", mensaje);
		assertEquals(-1, compararArchivosLineaporLinea("test/salida/u3_Esperado.txt", "test/salida/u3_Real.txt"));
	}

	private long compararArchivosLineaporLinea(String string, String string2) throws IOException {
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

}
