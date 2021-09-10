package tierraMedia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TierraMedia {

	private LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
	private LinkedList<Propuesta> propuestas = new LinkedList<Propuesta>();

	public void agregarAtracciones(List<String> atraccionesAIngresar) {
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

	public void agregarPromociones(List<String> promocionesAIngresar) {

		for (String promocion : promocionesAIngresar) {
			String[] datosPromociones = promocion.split(",");
			TipoPromocion tipoPromocion = TipoPromocion.valueOf(datosPromociones[0].trim());
			TipoAtraccion tipoAtraccion = TipoAtraccion.valueOf(datosPromociones[1].trim());
			String nombre = datosPromociones[2].trim();
			String descripcion = datosPromociones[3].trim();

			String[] atraccionesString = datosPromociones[5].split(";");
			LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
			for (int i = 0; i < atraccionesString.length; i++) {
				atracciones.add(this.obtenerAtraccionPorNombre(atraccionesString[i].trim()));
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
					atraccionesGratis.add(this.obtenerAtraccionPorNombre(atraccionesGratsString[i].trim()));
				}

				nuevaPromocion = new PromocionAXB(tipoAtraccion, nombre, descripcion, atracciones, atraccionesGratis);
			}
			propuestas.add(nuevaPromocion);
			nuevaPromocion.setCosto();
			nuevaPromocion.setTiempoUtilizado();

		}
	}

	public void agregarUsuarios(List<String> usuariosAIngresar) {

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

	public void sugerirPropuestasAusuarios() throws IOException {
		LectorConsola.abrirEscanner();

		for (Usuario usuario : usuarios) {
			List<Propuesta> propuestasOrdenadas = ordenarPropuestas(usuario.getTipoAtraccionPreferida());
			usuario.mostrarBienvenida();

			for (Propuesta propuesta : propuestasOrdenadas) {
				if (propuesta.hayCupoDisponible() && usuario.puedeAdquirirPropuesta(propuesta)) {
					usuario.ofrecerPropuesta(propuesta);
					if (usuario.aceptaPropuesta(propuesta)) {
						usuario.agregarPropuestaAceptada(propuesta);
						propuesta.actualizarCupoDisponible();
					}
				}
			}
			usuario.escribirIntinerario();
		}
		LectorConsola.cerrarEscanner();
	}

	private List<Propuesta> ordenarPropuestas(TipoAtraccion tipoAtraccion) {

		List<Propuesta> propuestasFiltradas = new ArrayList<Propuesta>();
		List<Propuesta> restoPropuestas = new ArrayList<Propuesta>();

		for (Propuesta propuesta : this.propuestas) {
			if (propuesta.getTipoAtraccion() == tipoAtraccion) {
				propuestasFiltradas.add(propuesta);
			} else {
				restoPropuestas.add(propuesta);
			}
		}

		Collections.sort(propuestasFiltradas);
		Collections.sort(restoPropuestas);
		propuestasFiltradas.addAll(restoPropuestas);

		return propuestasFiltradas;
	}

	private Atraccion obtenerAtraccionPorNombre(String nombre) {
		for (Propuesta atraccion : propuestas) {
			if (atraccion.getNombre().equals(nombre)) {
				return (Atraccion) atraccion;
			}
		}
		return null;
	}
	
	public LinkedList<Usuario> getUsuarios(){
		return usuarios;
	}
	
	public LinkedList<Propuesta> getPropuestas(){
		return propuestas;
	}

}
