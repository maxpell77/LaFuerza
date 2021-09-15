package laFuerza;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public abstract class LectorArchivosdeEntrada {

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

}