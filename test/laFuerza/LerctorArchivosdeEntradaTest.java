package laFuerza;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class LerctorArchivosdeEntradaTest {

	@Test
	public void leerArchivosTest() {

		List<String> atracciones = LectorArchivosdeEntrada.leerArchivo("test/entrada/atracciones.txt");
		List<String> promociones = LectorArchivosdeEntrada.leerArchivo("test/entrada/promociones.txt");
		List<String> usuarios = LectorArchivosdeEntrada.leerArchivo("test/entrada/usuarios.txt");

		String esperado = "AtTest1,10,5,2,LADO_OSCURO";
		assertEquals(esperado, atracciones.get(0));

		esperado = "PORCENTUAL,  LADO_OSCURO ,Pack Oscuro 1 , AtTest1 y AtTest2 con 30%dto,       0.30 ,   AtTest1;AtTest2";
		assertEquals(esperado, promociones.get(0));

		esperado = "U1, LADO_OSCURO, 100, 100";
		assertEquals(esperado, usuarios.get(0));
	}
}
