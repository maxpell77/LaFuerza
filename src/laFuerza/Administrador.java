package laFuerza;

import java.io.IOException;
import java.util.List;

public class Administrador {

	public void activarSistema() throws IOException {
		
		//una sola variable de archivos 
		List<String> atracciones = LectorArchivosdeEntrada.leerArchivo("entrada/atracciones.txt");
		List<String> promociones = LectorArchivosdeEntrada.leerArchivo("entrada/promociones.txt");
		List<String> usuarios = LectorArchivosdeEntrada.leerArchivo("entrada/usuarios.txt");

		CargadorArchivosEntrada.agregarAtracciones(atracciones);
		CargadorArchivosEntrada.agregarPromociones(promociones);
		CargadorArchivosEntrada.agregarUsuarios(usuarios);

		Ofertador.sugerirPropuestasAusuarios(CargadorArchivosEntrada.getPropuestas(),
				CargadorArchivosEntrada.getUsuarios());

	}

}
