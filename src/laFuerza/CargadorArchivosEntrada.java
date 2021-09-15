package laFuerza;

import java.util.LinkedList;
import java.util.List;

public abstract class CargadorArchivosEntrada {
	private static LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
	private static LinkedList<Propuesta> propuestas = new LinkedList<Propuesta>();

	public static void agregarAtracciones(List<String> atraccionesAIngresar) {
		for (String atraccion : atraccionesAIngresar) {
			String[] datosAtracciones = atraccion.split(",");
			String nombre = datosAtracciones[0].trim();
			int costo = Integer.parseInt(datosAtracciones[1].trim());
			double tiempo = Double.parseDouble(datosAtracciones[2].trim());
			int cupo = Integer.parseInt(datosAtracciones[3].trim());
			TipoAtraccion tipoAtraccion = TipoAtraccion.valueOf(datosAtracciones[4].trim());

			Atraccion nuevaAtraccion = new Atraccion(costo, tiempo, tipoAtraccion, cupo, nombre);
			propuestas.add(nuevaAtraccion);

		}

	}

	public static void agregarPromociones(List<String> promocionesAIngresar) {

		for (String promocion : promocionesAIngresar) {
			String[] datosPromociones = promocion.split(",");
			TipoPromocion tipoPromocion = TipoPromocion.valueOf(datosPromociones[0].trim());
			TipoAtraccion tipoAtraccion = TipoAtraccion.valueOf(datosPromociones[1].trim());
			String nombre = datosPromociones[2].trim();
			String descripcion = datosPromociones[3].trim();

			String[] atraccionesString = datosPromociones[5].split(";");
			LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
			for (int i = 0; i < atraccionesString.length; i++) {
				atracciones.add(obtenerAtraccionPorNombre(atraccionesString[i].trim()));
			}

			Promocion nuevaPromocion;
			if (tipoPromocion == TipoPromocion.PORCENTUAL) {
				Double porcentajeDescuento = Double.parseDouble(datosPromociones[4].trim());
				nuevaPromocion = new PromoPorcentual(tipoAtraccion, nombre, descripcion, atracciones,
						porcentajeDescuento);
			} else if (tipoPromocion == TipoPromocion.ABSOLUTA) {
				int costoTotal = Integer.parseInt(datosPromociones[4].trim());
				nuevaPromocion = new PromoAbsoluta(tipoAtraccion, nombre, descripcion, atracciones, costoTotal);
			} else {
				LinkedList<Atraccion> atraccionesGratis = new LinkedList<Atraccion>();
				String[] atraccionesGratsString = datosPromociones[4].split(";");
				for (int i = 0; i < atraccionesGratsString.length; i++) {
					atraccionesGratis.add(obtenerAtraccionPorNombre(atraccionesGratsString[i].trim()));
				}

				nuevaPromocion = new PromocionAXB(tipoAtraccion, nombre, descripcion, atracciones, atraccionesGratis);
			}
			propuestas.add(nuevaPromocion);
			nuevaPromocion.setCosto();
			nuevaPromocion.setTiempoUtilizado();

		}
	}

	public static void agregarUsuarios(List<String> usuariosAIngresar) {

		for (String usuario : usuariosAIngresar) {
			String[] datosUsuarios = usuario.split(",");
			String nombre = datosUsuarios[0].trim();
			TipoAtraccion tipoAtraccion = TipoAtraccion.valueOf(datosUsuarios[1].trim());
			int costo = Integer.parseInt(datosUsuarios[2].trim());
			double tiempo = Double.parseDouble(datosUsuarios[3].trim());

			Usuario nuevoUsuario = new Usuario(nombre, tipoAtraccion, costo, tiempo);
			usuarios.add(nuevoUsuario);

		}

	}

	private static Atraccion obtenerAtraccionPorNombre(String nombre) {
		for (Propuesta atraccion : propuestas) {
			if (atraccion.getNombre().equals(nombre)) {
				return (Atraccion) atraccion;
			}
		}
		return null;
	}

	public static LinkedList<Usuario> getUsuarios() {
		return usuarios;
	}

	public static LinkedList<Propuesta> getPropuestas() {
		return propuestas;
	}

}
