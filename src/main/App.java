package main;

import java.io.IOException;
import java.util.List;

import tierraMedia.LectorArchivos;
import tierraMedia.TierraMedia;

public class App {

	public static void main(String[] args) throws IOException {

		TierraMedia tierraMedia = new TierraMedia();

		List<String> atracciones = LectorArchivos.leerArchivo("entrada/atracciones.txt");
		List<String> promociones = LectorArchivos.leerArchivo("entrada/promociones.txt");
		List<String> usuarios = LectorArchivos.leerArchivo("entrada/usuarios.txt");

		tierraMedia.agregarAtracciones(atracciones);
		tierraMedia.agregarPromociones(promociones);
		tierraMedia.agregarUsuarios(usuarios);

		tierraMedia.sugerirPropuestasAusuarios();

	}

}
