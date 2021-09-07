package main;

import java.io.IOException;
import java.util.List;

import tierraMedia.LectorYModificadorArchivos;
import tierraMedia.TierraMedia;

public class App {

	public static void main(String[] args) throws IOException {

		TierraMedia tierraMedia = new TierraMedia();

		List<String> atracciones = LectorYModificadorArchivos.leerArchivo("entrada/atracciones.txt");
		List<String> promociones = LectorYModificadorArchivos.leerArchivo("entrada/promociones.txt");
		List<String> usuarios = LectorYModificadorArchivos.leerArchivo("entrada/usuarios.txt");

		tierraMedia.agregarAtracciones(atracciones);
		tierraMedia.agregarPromociones(promociones);
		tierraMedia.agregarUsuarios(usuarios);

		tierraMedia.buscarPropuestasASugerir();

	}

}
