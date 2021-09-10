package tierraMedia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Usuario {
	private TipoAtraccion tipoAtraccionPreferida;
	private int presupuestoInicial;
	private int presupuestoDisponible;
	private double tiempoMaximoInicial;
	private double tiempoDisponible;
	private String nombre;
	private LinkedList<Atraccion> atraccionesContratadas = new LinkedList<Atraccion>();
	private LinkedList<Propuesta> propuestasCompradas = new LinkedList<Propuesta>();

	public Usuario(String nombre, TipoAtraccion tipoAtraccionPreferida, int presupuesto, double tiempoMaximo) {
		this.tipoAtraccionPreferida = tipoAtraccionPreferida;
		this.presupuestoInicial = presupuesto;
		this.presupuestoDisponible = presupuesto;
		this.tiempoMaximoInicial = tiempoMaximo;
		this.tiempoDisponible = tiempoMaximo;
		this.nombre = nombre;

	}

	public TipoAtraccion getTipoAtraccionPreferida() {
		return tipoAtraccionPreferida;
	}

	public int getPresupuestoDisponible() {
		return presupuestoDisponible;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}


	public String getNombre() {
		return nombre;
	}

	public LinkedList<Atraccion> getAtraccionesContratadas() {
		return atraccionesContratadas;
	}

	public LinkedList<Propuesta> getPropuestasContratadas() {
		return propuestasCompradas;
	}
	
	
	public boolean puedeAdquirirPropuesta(Propuesta propuesta) {
		return puedepagarPropuesta(propuesta) && tieneTiempoDisponible(propuesta)
				&& atraccionNoContratada(propuesta);
	}
	
	private boolean puedepagarPropuesta(Propuesta propuesta) {
		return presupuestoDisponible >= propuesta.getCosto();
	}
	
	private boolean tieneTiempoDisponible(Propuesta propuesta) {		
		return tiempoDisponible >= propuesta.getTiempoUtilizado();
	}
	
	private boolean atraccionNoContratada(Propuesta propuesta) {
		boolean atraccionNoInculida = true;
		for (Atraccion atraccionContratada : atraccionesContratadas) {
			atraccionNoInculida &= !propuesta.getAtraccionesIncluidas().contains(atraccionContratada);
		}
		return atraccionNoInculida;	
	}
	
	
	public void agregarPropuestaAceptada(Propuesta nuevaPropuesta) {
		propuestasCompradas.add(nuevaPropuesta);
		atraccionesContratadas.addAll(nuevaPropuesta.getAtraccionesIncluidas());
		tiempoDisponible -= nuevaPropuesta.getTiempoUtilizado();
		presupuestoDisponible -= nuevaPropuesta.getCosto();
	}


	public void mostrarBienvenida() {
		System.out.println("---------------------------------------------------\n");
		System.out.println("¡Bienvenid@ " + nombre + "!\n");
		System.out.println("Preferencia: " + tipoAtraccionPreferida);
	}

	public void ofrecerPropuesta(Propuesta propuesta) {
		System.out.println("Saldo Disponible: " + presupuestoDisponible + " monedas de Oro");
		System.out
				.println("Tiempo Disponible: " + ModificadorFormatoHora.obtenerHoraConFormato(tiempoDisponible) + "\n");

		System.out.println("Te sugerimos la siguiente propuesta:\n ");
		System.out.println(propuesta);
	}

	public boolean aceptaPropuesta(Propuesta propuesta) {
		System.out.println("¿Desea adquirirla? ");
		System.out.println("(por favor responder con la letra 'S' en caso afirmativo, o 'N' en caso negativo.)");
		String entradaConsola = LectorConsola.esperarRespuestaUsuario();

		if (entradaConsola.equals("S")) {
			System.out.println("Felicitaciones! Ha adquirido la propuesta " + propuesta.getNombre() + "\n");
			return true;
		} else {
			System.out.println("\nHa rechazado la propuesta " + propuesta.getNombre() + ".\n");
			return false;
		}

	}

	public void escribirIntinerario() throws IOException {

		String resumen = "Tipo de atraccion preferida: " + tipoAtraccionPreferida + "\n" + "Saldo Inicial: "
				+ presupuestoInicial + " monedas de oro" + "\n" + "Tiempo Disponible Inicial: "
				+ ModificadorFormatoHora.obtenerHoraConFormato(tiempoMaximoInicial) + "\n";

		String file = nombre + ".txt";
		PrintWriter salida = new PrintWriter(new FileWriter("salida/" + file));

		if (propuestasCompradas.size() == 0) {
			salida.println("Muchas gracias " + nombre + " por participar.\n");
			salida.println(resumen);
			salida.println("Lamentablemente no ha adquirido ninguna propuesta.\n");
		} else {
			salida.println("Muchas gracias " + nombre + " por tu compra.\n");
			salida.println(resumen);
			salida.println("Estas son las propuestas contratadas: \n");

			for (Propuesta propuesta : propuestasCompradas) {
				salida.println(propuesta);
			}

			salida.println("\nTotal a pagar: " + (presupuestoInicial - presupuestoDisponible)
					+ " monedas de oro (saldo disponible: " + presupuestoDisponible + " monedas de oro).");
			salida.println("Tiempo utilizado "
					+ ModificadorFormatoHora.obtenerHoraConFormato((tiempoMaximoInicial - tiempoDisponible))
					+ " (tiempo disponible " + ModificadorFormatoHora.obtenerHoraConFormato(tiempoDisponible) + ").");
		}
		salida.close();
	}

}
