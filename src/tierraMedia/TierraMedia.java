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
			TipoAtraccion tripoAtraccion = TipoAtraccion.valueOf(datosAtracciones[4].trim());

			Atraccion nuevaAtraccion = new Atraccion(costo, tiempo, tripoAtraccion, cupo, nombre);
			propuestas.add(nuevaAtraccion);

		}

	}

	public void agregarPromociones(List<String> promocionesAIngresar) {

		for (String promocion : promocionesAIngresar) {
			String[] datosPromociones = promocion.split(",");
			TipoPromocion tipoPromocion = TipoPromocion.valueOf(datosPromociones[0].trim());
			TipoAtraccion tripoAtraccion = TipoAtraccion.valueOf(datosPromociones[1].trim());
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
				nuevaPromocion = new PromoPorcentual(tripoAtraccion, nombre, descripcion, atracciones,
						porcentajeDescuento);
			} else if (tipoPromocion == TipoPromocion.ABSOLUTA) {
				int costoTotal = Integer.parseInt(datosPromociones[4].trim());
				nuevaPromocion = new PromoAbsoluta(tripoAtraccion, nombre, descripcion, atracciones, costoTotal);
			} else {		
				LinkedList<Atraccion> atraccionesGratis = new LinkedList<Atraccion>();
				String[] atraccionesGratsString = datosPromociones[4].split(";");
				for (int i = 0; i < atraccionesGratsString.length; i++) {
					atraccionesGratis.add(this.obtenerAtraccionPorNombre(atraccionesGratsString[i].trim()));
				}
				
				nuevaPromocion = new PromocionAXB(tripoAtraccion, nombre, descripcion, atracciones, atraccionesGratis);
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
			TipoAtraccion tripoAtraccion = TipoAtraccion.valueOf(datosUsuarios[1].trim());
			int costo = Integer.parseInt(datosUsuarios[2].trim());
			double tiempo = Double.parseDouble(datosUsuarios[3].trim());

			Usuario nuevoUsuario = new Usuario(nombre, tripoAtraccion, costo, tiempo);
			usuarios.add(nuevoUsuario);

		}

	}

	public void buscarPropuestasASugerir() throws IOException {
		LectorYModificadorArchivos.abrirEscanner();
		
		for (Usuario usuario : usuarios) {
			TipoAtraccion tipoAtraccionPreferida = usuario.getTipoAtraccionPreferida();
			List<Propuesta> propuestasOrdenadas = ordenarPropuestas(tipoAtraccionPreferida);
			int primerPropuestaAusuario = 0;

			for (Propuesta propuesta : propuestasOrdenadas) {
				if (propuesta.hayCupoDisponible() && usuarioPuedeAdquirirPropuesta(usuario, propuesta)) {
					primerPropuestaAusuario++;
					if (LectorYModificadorArchivos.usuarioAceptaPropuesta(usuario, propuesta,
							primerPropuestaAusuario)) {
						usuario.agregarPropuestaAceptada(propuesta);
						propuesta.actualizarCupoDisponible();
					}
				}
			}
			LectorYModificadorArchivos.escribirIntinerario(usuario);
		}
		LectorYModificadorArchivos.cerrarEscanner();
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

	private boolean usuarioPuedeAdquirirPropuesta(Usuario usuario, Propuesta propuesta) {
		return usuarioPuedePagar(usuario, propuesta) && usuarioTieneTiempo(usuario, propuesta)
				&& atraccionNoIncluida(usuario, propuesta);
	}

	private boolean usuarioPuedePagar(Usuario usuario, Propuesta propuesta) {
		return (usuario.getPresupuestoDisponible() >= propuesta.getCosto());
	}

	private boolean usuarioTieneTiempo(Usuario usuario, Propuesta propuesta) {
		return (usuario.getTiempoDisponible() >= propuesta.getTiempoUtilizado());
	}

	private boolean atraccionNoIncluida(Usuario usuario, Propuesta propuesta) {
		boolean atraccionNoInculida = true;
		for (Atraccion atraccionContratada : usuario.getAtraccionesContratadas()) {
			atraccionNoInculida &= !propuesta.getAtraccionesIncluidas().contains(atraccionContratada);

		}
		return atraccionNoInculida;
	}

	private Atraccion obtenerAtraccionPorNombre(String nombre) {
		for (Propuesta atraccion : propuestas) {
			if (atraccion.getNombre().equals(nombre)) {
				return (Atraccion) atraccion;
			}
		}
		return null;
	}

//	public List<Propuesta> getPropuestas() {
//		return propuestas;
//	}

}
