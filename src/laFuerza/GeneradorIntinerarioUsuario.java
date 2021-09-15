package laFuerza;

public abstract class GeneradorIntinerarioUsuario {

	public static String generarIntinerario(Usuario usuario) {
		String mensaje = "";

		String resumen = "Tipo de atracción preferida: " + usuario.getTipoAtraccionPreferida().getNombre() + "\n"
				+ "Saldo Inicial: " + usuario.getPresupuestoInicial() + " créditos galácticos" + "\n"
				+ "Tiempo Disponible Inicial: "
				+ ModificadorFormatoHora.obtenerHoraConFormato(usuario.getTiempoMaximoInicial()) + "\n\n";

		if (usuario.getPropuestasContratadas().size() == 0) {
			mensaje += "Muchas gracias " + usuario.getNombre() + " por participar.\n\n";
			mensaje += resumen;
			mensaje += "Lamentablemente no ha adquirido ninguna propuesta.\n\n";
		} else {
			mensaje += "¡Muchas gracias " + usuario.getNombre() + " por tu compra!\n\n";
			mensaje += resumen;
			mensaje += "Estas son las propuestas contratadas: \n\n";

			for (Propuesta propuesta : usuario.getPropuestasContratadas()) {
				mensaje += propuesta + "\n";
			}

			mensaje += "Total a pagar: " + (usuario.getPresupuestoInicial() - usuario.getPresupuestoDisponible())
					+ " créditos galácticos (saldo disponible: " + usuario.getPresupuestoDisponible()
					+ " créditos galácticos).\n";
			mensaje += "Tiempo utilizado "
					+ ModificadorFormatoHora.obtenerHoraConFormato(
							(usuario.getTiempoMaximoInicial() - usuario.getTiempoDisponible()))
					+ " (tiempo disponible "
					+ ModificadorFormatoHora.obtenerHoraConFormato(usuario.getTiempoDisponible()) + ").\n\n";

		}

		mensaje += "¡Que la Fuerza te acompañe!";

		return mensaje;

	}

}
