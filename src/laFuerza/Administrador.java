package laFuerza;

import java.io.IOException;

public class Administrador {

	public void activarSistema() throws IOException {

		CargadorDeObjetos.agregarAtracciones();
		CargadorDeObjetos.agregarPromociones();
		CargadorDeObjetos.agregarUsuarios();

		Ofertador.sugerirPropuestasAusuarios(CargadorDeObjetos.getPropuestas(), CargadorDeObjetos.getUsuarios());

	}

}
