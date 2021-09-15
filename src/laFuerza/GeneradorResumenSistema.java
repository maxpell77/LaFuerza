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
		ArrayList<Propuesta> aux;

		for (Propuesta propuesta : propuestas) {

			Integer key = propuesta.getCosto() * propuesta.getCantidadComprada();
			if (propuestasContratadas.containsKey(key)) {
				aux = propuestasContratadas.get(key);
			} else {
				aux = new ArrayList<Propuesta>();
			}
			aux.add(propuesta);
			propuestasContratadas.put(key, aux);
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

		float capacidadOciosa = (float) ((TOTAL_CUPOS_DISPONIBLES * 100) / CUPOS_TOTALES);
		int ticketPromedio = TOTAL_INGRESOS / cantidadUsuariosCompradores;

		String mensaje = "";

		mensaje += "Resumen general.\n\n";
		mensaje += "Se han realizado ventas a " + cantidadUsuariosCompradores + " usuarios, por un total de "
				+ TOTAL_INGRESOS + " créditos galácticos (ticket promedio de " + ticketPromedio
				+ " créditos galácticos por usuario).\n\n";

		mensaje += "En total se contrataron " + TOTAL_COMPRAS + " propuestas y se han rechazado " + TOTAL_RECHAZOS
				+ ".\n\n";
		mensaje += "De un máximo de " + CUPOS_TOTALES + " cupos disponibles, quedaron " + TOTAL_CUPOS_DISPONIBLES
				+ " por vender. Lo que representa una capacidad ociosa del " + capacidadOciosa
				+ "%, equivalente a un máximo de ventas no concretadas de " + MAXIMO_VENTAS_NO_CONCRETADAS
				+ " créditos galácticos.\n\n";

		mensaje += "¡Que la Fuerza te acompañe!";

		return mensaje;

	}

}
