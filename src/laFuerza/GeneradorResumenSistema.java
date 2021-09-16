package laFuerza;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public abstract class GeneradorResumenSistema {

	private static int TOTAL_INGRESOS = 0;
	private static int TOTAL_COMPRAS = 0;
	private static int TOTAL_RECHAZOS = 0;
	private static int CUPOS_TOTALES = 0;
	private static int TOTAL_CUPOS_DISPONIBLES = 0;
	private static int MAXIMO_VENTAS_NO_CONCRETADAS = 0;
	private static Map<String, Integer> RANKING_TIPO_ATRACCION;

	public static void generarResuemSistema(LinkedList<Propuesta> propuestas, int cantidadUsuariosCompradores)
			throws IOException {

		EscritorArchivosSalida.escribirArchivos("salida/resumen_Sistema/DetallePropuestas.csv",
				generarTablaDetallada(generarTablaOrdenada(propuestas)));

		EscritorArchivosSalida.escribirArchivos("salida/resumen_Sistema/ResuemGeneral.txt",
				generarArchivoGeneral(cantidadUsuariosCompradores));

	}

	private static Map<Integer, ArrayList<Propuesta>> generarTablaOrdenada(LinkedList<Propuesta> propuestas) {

		Map<Integer, ArrayList<Propuesta>> propuestasContratadas = new TreeMap<Integer, ArrayList<Propuesta>>(
				Collections.reverseOrder());
		RANKING_TIPO_ATRACCION = new TreeMap<String, Integer>(Collections.reverseOrder());

		ArrayList<Propuesta> aux;
		Integer key;

		Integer aux2;
		String key2;

		for (Propuesta propuesta : propuestas) {

			key = propuesta.getCosto() * propuesta.getCantidadComprada();
			if (propuestasContratadas.containsKey(key)) {
				aux = propuestasContratadas.get(key);
			} else {
				aux = new ArrayList<Propuesta>();
			}
			aux.add(propuesta);
			propuestasContratadas.put(key, aux);

			key2 = propuesta.getTipoAtraccion().getNombre();
			if (RANKING_TIPO_ATRACCION.containsKey(key2)) {
				aux2 = RANKING_TIPO_ATRACCION.get(key2);
			} else {
				aux2 = 0;
			}
			aux2 += propuesta.getCantidadComprada();
			RANKING_TIPO_ATRACCION.put(key2, aux2);

		}

		return propuestasContratadas;

	}

	private static String generarTablaDetallada(Map<Integer, ArrayList<Propuesta>> propuestasContratadas) {
		ArrayList<Propuesta> aux;

		String mensaje = "";
		mensaje += "Propuesta, Tipo de Atracción, Ingresos Totales, Compras, Rechazos\n";

		for (Entry<Integer, ArrayList<Propuesta>> cadaPropuesta : propuestasContratadas.entrySet()) {

			aux = cadaPropuesta.getValue();

			for (Propuesta propuesta : aux) {
				TOTAL_INGRESOS += cadaPropuesta.getKey();
				TOTAL_COMPRAS += propuesta.getCantidadComprada();
				TOTAL_RECHAZOS += propuesta.getCantidadRechazada();
				if (propuesta.getClass() == Atraccion.class) {
					calculosOtrosDatos(propuesta);
				}

				mensaje += propuesta.getNombre() + "," + propuesta.getTipoAtraccion().getNombre() + ","
						+ cadaPropuesta.getKey() + "," + propuesta.getCantidadComprada() + ","
						+ propuesta.getCantidadRechazada() + "\n";
			}
		}
		mensaje += "TOTALES" + ",," + TOTAL_INGRESOS + "," + TOTAL_COMPRAS + "," + TOTAL_RECHAZOS + "\n";

		return mensaje;
	}

	private static void calculosOtrosDatos(Propuesta propuesta) {
		Atraccion atraccion = (Atraccion) propuesta;
		CUPOS_TOTALES += atraccion.getCupoInicial();
		TOTAL_CUPOS_DISPONIBLES += atraccion.getCupoDisponible();
		MAXIMO_VENTAS_NO_CONCRETADAS += atraccion.getCupoDisponible() * atraccion.getCosto();

	}

	public static String generarArchivoGeneral(int cantidadUsuariosCompradores) {
		String mensaje = "";

		mensaje += "Resumen general.\n\n";

		if (cantidadUsuariosCompradores == 0) {
			mensaje += "No se han realizado ninguna venta.\n\n";
			mensaje += "Se ofrecieron " + TOTAL_RECHAZOS + " propuestas a "
					+ CargadorArchivosEntrada.getUsuarios().size() + " usuarios.\n\n";

		} else {
			float capacidadOciosa = (float) ((TOTAL_CUPOS_DISPONIBLES * 100) / CUPOS_TOTALES);

			int ticketPromedio = TOTAL_INGRESOS / cantidadUsuariosCompradores;

			mensaje += "Se han realizado ventas a " + cantidadUsuariosCompradores + " usuarios, por un total de "
					+ TOTAL_INGRESOS + " créditos galácticos (ticket promedio de " + ticketPromedio
					+ " créditos galácticos por usuario).\n\n";
			mensaje += "En total se contrataron " + TOTAL_COMPRAS + " propuestas y se han rechazado " + TOTAL_RECHAZOS
					+ ".\n\n";
			mensaje += "De un máximo de " + CUPOS_TOTALES + " cupos disponibles, quedaron " + TOTAL_CUPOS_DISPONIBLES
					+ " por vender. Lo que representa una capacidad ociosa del " + capacidadOciosa
					+ "%, equivalente a un máximo de ventas no concretadas de " + MAXIMO_VENTAS_NO_CONCRETADAS
					+ " créditos galácticos.\n\n";
			mensaje += ladoGanador() + "\n\n";

		}

		mensaje += "¡Que la Fuerza te acompañe!";

		return mensaje;

	}

	private static String ladoGanador() {
		String mensaje = "";
		int ladoOscuro = RANKING_TIPO_ATRACCION.get("Lado Oscuro");
		int ladoLuminoso = RANKING_TIPO_ATRACCION.get("Lado Luminoso");

		if (ladoOscuro > ladoLuminoso) {
			mensaje = "La Fuerza está del Lado Oscuro.";
		} else if (ladoLuminoso > ladoOscuro) {
			mensaje = "La Fuerza está del Lado Luminoso.";
		} else
			mensaje = "La Fuerza esta equilibrada.";

		return mensaje;

	}

}
