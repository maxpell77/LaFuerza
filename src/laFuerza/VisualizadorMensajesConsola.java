package laFuerza;

public abstract class VisualizadorMensajesConsola {

	public static void mostrarBienvenida(Usuario usuario) {
		String mensaje = "";
		mensaje += "---------------------------------------------------\n\n";
		mensaje += "¡Bienvenid@ " + usuario.getNombre() + "!\n\n";
		mensaje += "Tu preferencia es el " + usuario.getTipoAtraccionPreferida().getNombre();
		System.out.println(mensaje);
	}

	public static void ofrecerPropuesta(Usuario usuario, Propuesta propuesta) {
		String mensaje = "";
		mensaje += "Saldo Disponible: " + usuario.getPresupuestoDisponible() + " créditos galácticos\n";
		mensaje += "Tiempo Disponible: " + ModificadorFormatoHora.obtenerHoraConFormato(usuario.getTiempoDisponible())
				+ "\n\n";
		mensaje += "Te sugerimos la siguiente propuesta:\n\n ";
		mensaje += propuesta + "\n";
		mensaje += "¿Desea adquirirla?\n";
		mensaje += "(por favor responder con la letra 'S' en caso afirmativo,  'N' en caso negativo\n. "
				+ "Para ver el resumen de lo contratado hasta el momento presione la tecla 'R')";
		System.out.println(mensaje);
	}

	public static void mostrarFinalizacionCompra(Usuario usuario) {
		if (usuario.getPropuestasContratadas().size() > 0)
			mostrarFinlizacionConCompra(usuario);
		else
			mostrarFinlizacionSinCompra(usuario);

	}

	private static void mostrarFinlizacionConCompra(Usuario usuario) {
		String mensaje = "";
		mensaje += "Muchas gracias " + usuario.getNombre() + " por tu compra.\n\n";
		if (usuario.getPresupuestoDisponible() > 0) {
			mensaje += "Tu saldo final es de " + usuario.getPresupuestoDisponible() + " créditos galácticos.\n";
		} else {
			mensaje += "No tienes saldo disponible.\n";
		}
		if (usuario.getTiempoDisponible() > 0) {
			mensaje += "Tienes disponible "
					+ ModificadorFormatoHora.obtenerHoraConFormato(usuario.getTiempoDisponible()) + ".\n\n";
		} else {
			mensaje += "No tienes más tiempo disponible.\n\n";
		}

		saludoFinal(mensaje);
	}

	private static void mostrarFinlizacionSinCompra(Usuario usuario) {
		String mensaje = "";
		mensaje += "Muchas gracias " + usuario.getNombre() + " por tu participar.\n\n";
		saludoFinal(mensaje);
	}

	private static void saludoFinal(String mensaje) {
		mensaje += "Lamentablemente ya no tenemos más propuestas disponibles que ofrecer.\n\n";
		mensaje += "Esperamos nos vuelvas a visitar pronto.\n\n";
		mensaje += "¡Que la Fuerza te acompañe!\n\n";
		System.out.println(mensaje);
	}

	public static void confirmaCompraPropuesta(Propuesta propuesta) {
		System.out.println("¡Felicitaciones! Ha adquirido la propuesta '" + propuesta.getNombre() + "'\n");
	}

	public static void confirmaRechazoPropuesta(Propuesta propuesta) {
		System.out.println("\nHa rechazado la propuesta " + propuesta.getNombre() + ".\n");
	}

}
