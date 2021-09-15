package laFuerza;

import static org.junit.Assert.*;

import org.junit.Test;

public class ModificadorFormatoHoraTest {

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
