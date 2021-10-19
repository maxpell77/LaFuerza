package laFuerza;

import java.util.Scanner;

public abstract class LectorConsola {
	private static Scanner IN;

	public static void abrirEscanner() {
		IN = new Scanner(System.in);
	}

	public static void cerrarEscanner() {
		IN.close();
	}

	
	
	public static String esperarRespuestaUsuario(Usuario usuario) {

		String entradaConsola = IN.nextLine().toUpperCase(); 
		
		while (!entradaConsola.equals("S") && !entradaConsola.equals("N")) {
			if(entradaConsola.equals("R")) {
				System.out.println(GeneradorResumenComprasUsuario.generarResumen(usuario));
				System.out.println("Esperando confirmacion de compra (S / N)");
				
			} else {
				System.out.println("No se ingreso un mensaje válido. Por favor intente nuevamente");

			}
			
			entradaConsola = IN.nextLine().toUpperCase();
		}
		System.err.println(entradaConsola);
		return entradaConsola;
	}

}
