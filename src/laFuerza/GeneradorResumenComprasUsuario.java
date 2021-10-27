package laFuerza;

public abstract class GeneradorResumenComprasUsuario {

	public static String generarResumen(Usuario usuario) {
		String mensaje = "";
		int totalApagar = 0;
		double tiempoUtilizado = 0;


		if (usuario.getPropuestasContratadas().size() == 0) {
			mensaje += "-----------------------------------------------------------------------------\n\n";
			mensaje += usuario.getNombre() +" no has adquirido ninguna propuesta.\n\n";
		} else {
			mensaje += "-----------------------------------------------------------------------------\n\n";
			mensaje += usuario.getNombre()+" estas son las propuestas contratadas: \n\n";

			for (Propuesta propuesta : usuario.getPropuestasContratadas()) {
				mensaje += propuesta + "\n";
				tiempoUtilizado += propuesta.getTiempoUtilizado();
				totalApagar += propuesta.getCosto();
			}

			mensaje += "Total a pagar: " + totalApagar
					+ " créditos galácticos (saldo disponible: " + usuario.getPresupuestoDisponible()
					+ " créditos galácticos).\n";
			mensaje += "Tiempo utilizado "
					+ ModificadorFormatoHora.obtenerHoraConFormato(tiempoUtilizado)
					+ " (tiempo disponible "
					+ ModificadorFormatoHora.obtenerHoraConFormato(usuario.getTiempoDisponible()) + ").\n\n";

		}

		mensaje += "-----------------------------------------------------------------------------\n";
		
		return mensaje;

	}

}
