package tierraMedia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public abstract class LectorYModificadorArchivos {

	private static Scanner IN;

	public static LinkedList<String> leerArchivo(String archivo) {
		LinkedList<String> datos = new LinkedList<String>();
		Scanner sc = null;
		try {
			sc = new Scanner(new File(archivo));

			while (sc.hasNext()) {
				String linea = sc.nextLine();
				datos.add(linea);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		sc.close();

		return datos;
	}

	public static void abrirEscanner() {
		IN = new Scanner(System.in);
	}

	public static void cerrarEscanner() {
		IN.close();
	}

	public static boolean usuarioAceptaPropuesta(Usuario usuario, Propuesta propuesta, int propuestaMostrada) {

		if (propuestaMostrada == 1) {
			System.out.println("---------------------------------------------------\n");
			System.out.println("¡Bienvenid@ " + usuario.getNombre() + "!\n");
			System.out.println("Preferencia: " + usuario.getTipoAtraccionPreferida());
		}

		System.out.println("Saldo Disponible: " + usuario.getPresupuestoDisponible() + " monedas de Oro");
		System.out.println("Tiempo Disponible: "
				+ ModificadorFormatoHora.obtenerHoraConFormato(usuario.getTiempoDisponible()) + "\n");

		System.out.println("Te sugerimos la siguiente propuesta:\n ");
		System.out.println(propuesta);
		System.out.println("¿Desea adquirirla? ");
		System.out.println("(por favor responder con la letra 'S' en caso afirmativo, o 'N' en caso negativo.)");

		String entradaConsola = esperarRespuestaUsuario();

		if (entradaConsola.equals("S")) {
			System.out.println("Felicitaciones! Ha adquirido la propuesta " + propuesta.getNombre() + "\n");
			return true;
		} else {
			System.out.println("\nHa rechazado la propuesta " + propuesta.getNombre() + ".\n");
			return false;
		}

	}

	private static String esperarRespuestaUsuario() {
		
		String entradaConsola = IN.nextLine().toUpperCase();
		while (!entradaConsola.equals("S") && !entradaConsola.equals("N")) {
			System.out.println("No se ingreso un mensaje válido. Por favor intente nuevamente");
			entradaConsola = IN.nextLine().toUpperCase();
		}
		System.err.println(entradaConsola);

		return entradaConsola;

	}

	public static void escribirIntinerario(Usuario usuario) throws IOException {
		String mensajeBienvenida;
		String mensajePropuestasContratadas;
		String file = usuario.getNombre() + ".txt";
		PrintWriter salida = new PrintWriter(new FileWriter("salida/" + file));

		if (usuario.getPropuestasContratadas().size() == 0) {
			mensajeBienvenida = "Muchas gracias " + usuario.getNombre() + " por participar.\n";
			mensajePropuestasContratadas = "Lamentablemente no ha adquirido ninguna propuesta.\n";
		} else {
			mensajeBienvenida = "Muchas gracias " + usuario.getNombre() + " por tu compra.\n";
			mensajePropuestasContratadas = "Estas son las propuestas contratadas: \n";
		}

		salida.println(mensajeBienvenida);

		salida.println("Tipo de atraccion preferida: " + usuario.getTipoAtraccionPreferida());
		salida.println("Saldo Inicial: " + usuario.getPresupuesto() + " monedas de oro");
		salida.println("Tiempo Disponible Inicial: "
				+ ModificadorFormatoHora.obtenerHoraConFormato(usuario.getTiempoMaximo()) + "\n");

		salida.println(mensajePropuestasContratadas);

		if (usuario.getPropuestasContratadas().size() > 0) {
			for (Propuesta propuesta : usuario.getPropuestasContratadas()) {
				salida.println(propuesta);
			}

			salida.println("\nTotal a pagar: " + (usuario.getPresupuesto() - usuario.getPresupuestoDisponible())
					+ " monedas de oro (saldo disponible: " + usuario.getPresupuestoDisponible() + " monedas de oro).");
			salida.println("Tiempo utilizado "
					+ ModificadorFormatoHora.obtenerHoraConFormato(
							(usuario.getTiempoMaximo() - usuario.getTiempoDisponible()))
					+ " (tiempo disponible "
					+ ModificadorFormatoHora.obtenerHoraConFormato(usuario.getTiempoDisponible()) + ").");

		}

		salida.close();
	}

}