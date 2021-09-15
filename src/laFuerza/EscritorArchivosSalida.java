package laFuerza;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class EscritorArchivosSalida {

	public static void escribirArchivos(String file, String mensaje) throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter(file));
		salida.println(mensaje);
		salida.close();
	}

}
