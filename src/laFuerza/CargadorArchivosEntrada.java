package laFuerza;

import java.util.LinkedList;
import java.util.List;

import dao.AtraccionesDAO;
import dao.DAOFactory;
import dao.UserDAO;

public abstract class CargadorArchivosEntrada {
	private static List<Usuario> usuarios = new LinkedList<Usuario>();
	private static LinkedList<Propuesta> propuestas = new LinkedList<Propuesta>();


	
	public static void agregarAtracciones() {
		AtraccionesDAO atraccionesDAO = DAOFactory.getAtraccionesDAO();
		List<Atraccion> atracciones = atraccionesDAO.findAll();
		
		System.out.println(atracciones);
		
		for(Atraccion atraccion : atracciones) {
			propuestas.add(atraccion);
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


	
	public static void agregarUsuarios() {
		UserDAO userDAO = DAOFactory.getUserDAO();
		usuarios =  userDAO.findAll() ;
			
	}

	
	
	private static Atraccion obtenerAtraccionPorNombre(String nombre) {
		for (Propuesta atraccion : propuestas) {
			if (atraccion.getNombre().equals(nombre)) {
				return (Atraccion) atraccion;
			}
		}
		return null;
	}

	public static List<Usuario> getUsuarios() {
		return usuarios;
	}

	public static LinkedList<Propuesta> getPropuestas() {
		return propuestas;
	}

}
