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

	
	//puede ser boolean no string. confirmacion usuario, y un dowhile se usa?
	public static String esperarRespuestaUsuario() {

		String entradaConsola = IN.nextLine().toUpperCase(); //codigo repetido
		
		//el while puede no ejecutarse nunca? 
		while (!entradaConsola.equals("S") && !entradaConsola.equals("N")) {
			System.out.println("No se ingreso un mensaje válido. Por favor intente nuevamente");
			entradaConsola = IN.nextLine().toUpperCase();
		}
		System.err.println(entradaConsola);
		return entradaConsola;
	}

}
