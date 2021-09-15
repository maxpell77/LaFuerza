package main;

import java.io.IOException;
import java.util.List;

import laFuerza.LectorArchivosdeEntrada;
import laFuerza.Ofertador;
import laFuerza.Administrador;
import laFuerza.CargadorArchivosEntrada;

public class App {

	public static void main(String[] args) throws IOException {

		Administrador admin = new Administrador();
		admin.activarSistema();

	}

}
