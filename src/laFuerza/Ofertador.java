package laFuerza;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Ofertador {

	public static void sugerirPropuestasAusuarios(LinkedList<Propuesta> propuestas, List<Usuario> usuarios)
			throws IOException {
		LectorConsola.abrirEscanner();

		for (Usuario usuario : usuarios) {

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
			VisualizadorMensajesConsola.mostrarFinalizacionCompra(usuario);
		}
		LectorConsola.cerrarEscanner();
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

	}

	private static void propuestaRechazada(Propuesta propuesta) {
		VisualizadorMensajesConsola.confirmaRechazoPropuesta(propuesta);

	}

}
