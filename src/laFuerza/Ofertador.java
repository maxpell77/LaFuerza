package laFuerza;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Ofertador {
	private static int CANTIDAD_USUARIOS_COMPRADORES = 0;
	private static boolean COMPRO_PROPUESTA = false;

	public static void sugerirPropuestasAusuarios(LinkedList<Propuesta> propuestas, LinkedList<Usuario> usuarios)
			throws IOException {
		LectorConsola.abrirEscanner();

		for (Usuario usuario : usuarios) {
			COMPRO_PROPUESTA = false;
			List<Propuesta> propuestasOrdenadas = ordenarPropuestas(propuestas, usuario.getTipoAtraccionPreferida());
			VisualizadorMensajesConsola.mostrarBienvenida(usuario);

			for (Propuesta propuesta : propuestasOrdenadas) {
				if (propuesta.hayCupoDisponible() && usuario.puedeAdquirirPropuesta(propuesta)) {
					VisualizadorMensajesConsola.ofrecerPropuesta(usuario, propuesta);
					if (usuario.aceptaPropuesta(propuesta)) {
						propuestaAceptada(usuario, propuesta);
					} else {
						propuestaRechazada(propuesta);
					}
				}
			}
			finalizaOfertadorAUsuario(usuario);

		}
		LectorConsola.cerrarEscanner();
		GeneradorResumenSistema.generarResuemSistema(propuestas, CANTIDAD_USUARIOS_COMPRADORES);
	}

	private static List<Propuesta> ordenarPropuestas(LinkedList<Propuesta> propuestas, TipoAtraccion tipoAtraccion) {

		List<Propuesta> propuestasFiltradas = new ArrayList<Propuesta>();
		List<Propuesta> restoPropuestas = new ArrayList<Propuesta>();

		for (Propuesta propuesta : propuestas) {
			if (propuesta.getTipoAtraccion() == tipoAtraccion) {
				propuestasFiltradas.add(propuesta);
			} else {
				restoPropuestas.add(propuesta);
			}
		}

		Collections.sort(propuestasFiltradas);
		Collections.sort(restoPropuestas);
		propuestasFiltradas.addAll(restoPropuestas);

		return propuestasFiltradas;
	}

	private static void propuestaAceptada(Usuario usuario, Propuesta propuesta) {
		VisualizadorMensajesConsola.confirmaCompraPropuesta(propuesta);
		usuario.agregarPropuestaAceptada(propuesta);
		propuesta.actualizarCupoDisponible();
		propuesta.actualizarCompraPropuesta();
		COMPRO_PROPUESTA = true;
	}

	private static void propuestaRechazada(Propuesta propuesta) {
		VisualizadorMensajesConsola.confirmaRechazoPropuesta(propuesta);
		propuesta.actualizarRechazoPropuesta();
	}

	private static void finalizaOfertadorAUsuario(Usuario usuario) throws IOException {
		String file = "salida/intinerarios_Usuarios/" + usuario.getNombre() + ".txt";
		String intinerario = GeneradorIntinerarioUsuario.generarIntinerario(usuario);
		EscritorArchivosSalida.escribirArchivos(file, intinerario);

		if (COMPRO_PROPUESTA) {
			CANTIDAD_USUARIOS_COMPRADORES++;
			VisualizadorMensajesConsola.mostrarFinlizacionConCompra(usuario);
		} else {
			VisualizadorMensajesConsola.mostrarFinlizacionSinCompra(usuario);
		}

	}

}
