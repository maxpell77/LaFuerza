package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import laFuerza.Atraccion;
import laFuerza.PromoAbsoluta;
import laFuerza.PromoPorcentual;
import laFuerza.Promocion;
import laFuerza.PromocionAXB;
import laFuerza.TipoAtraccion;

public class PromocinoesDAOImpl implements PromocionesDAO {

	public int insert(Promocion promocion) {
		try {
			String sql = "INSERT INTO ATRACCIONES (NOMBRE, COSTO, TIEMPO, CUPO, TIPO_ATRACCION) VALUES (?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promocion.getNombre());
			statement.setInt(2, promocion.getCosto());
			statement.setDouble(3, promocion.getTiempoUtilizado());
//			statement.setInt(4, promocion.getCupoInicial());
			statement.setInt(5, promocion.getTipoAtraccion().getNumeroId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int update(Promocion promocion) {
		try {
			return updateTablaPromocion(promocion);
			
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private int updateTablaPromocion(Promocion promocion) {

		try {
			String sql = "UPDATE PROMOCIONES SET  TIPO_PROMOCION = ?, TIPO_ATRACCIONES = ?,  DESCRIPCION = ?, VARIABLE = ?, WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, promocion.getTipoPromocionID());
			statement.setInt(2, promocion.getTipoAtraccion().getNumeroId());
			statement.setString(3, promocion.getNombre());
			statement.setString(5, promocion.getNombre());
			
			if(promocion.getTipoPromocionID() == 1) {
				PromoPorcentual promo = (PromoPorcentual) promocion;
				statement.setDouble(4, promo.getPorcentajeDescuento());
			} else if (promocion.getTipoPromocionID() == 2) {
				PromoAbsoluta promo = (PromoAbsoluta) promocion;
				statement.setInt(4, promo.getCosto());
			} else {
				updateTablaAtraccionesDePromoAXP(promocion);
			}

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}
	
	
	
	private int updateTablaAtraccionesDePromocion() {
		
		return 0;
	}
	
	private int updateTablaAtraccionesDePromoAXP(Promocion promocion ) {
//		try {
//			String sql = "UPDATE ATRACCIONES_DE_PROMOS_AXB SET  TIPO_PROMOCION = ?, TIPO_ATRACCIONES = ?,  DESCRIPCION = ?, VARIABLE = ?, WHERE NOMBRE = ?";
//			Connection conn = ConnectionProvider.getConnection();
//
//			PreparedStatement statement = conn.prepareStatement(sql);
//
//			statement.setInt(1, promocion.getTipoPromocionID());
//			statement.setInt(2, promocion.getTipoAtraccion().getNumeroId());
//			statement.setString(3, promocion.getNombre());
//			statement.setInt(4, promocion.getDescripcion());
//			statement.setString(5, promocion.getNombre());
//
//			int rows = statement.executeUpdate();
//
//			return rows;
//		} catch (Exception e) {
//			throw new MissingDataException(e);
//		}
		return 0;

	}
	
	

	public int delete(Promocion promocion) {
		try {
			String sql = "DELETE FROM PROMOCIONES WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promocion.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Promocion findByname(String name) {
		try {
			String sql = "SELECT * FROM PROMOCIONES WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;

			if (resultados.next()) {
				promocion = toPromocion(resultados);
			}

			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM PROMOCIONES";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<Promocion> findAll() {
		try {
			String sql = "SELECT * FROM PROMOCIONES";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promocion> promociones = new LinkedList<Promocion>();
			while (resultados.next()) {
				promociones.add(toPromocion(resultados));
			}

			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Promocion toPromocion(ResultSet resultados) throws SQLException {

		AtraccionesDAO atraccionesDAO = DAOFactory.getAtraccionesDAO();
		LinkedList<Atraccion> atracciones = atraccionesDAO.encontrarAtraccionesdePromociones(resultados.getInt(1));

		if (resultados.getInt(2) == 1) {
			return toPromoPorcentual(resultados, atracciones);
		} else if (resultados.getInt(2) == 2) {
			return toPromoAbsoluta(resultados, atracciones);
		} else {
			LinkedList<Atraccion> atraccionesGratisAXB = atraccionesDAO
					.encontrarAtraccionesdePromosAXB(resultados.getInt(1));
			return toPromoAXB(resultados, atracciones, atraccionesGratisAXB);
		}

	}

	private PromoPorcentual toPromoPorcentual(ResultSet resultados, LinkedList<Atraccion> atracciones)
			throws SQLException {
		return new PromoPorcentual(TipoAtraccion.valueOf(resultados.getInt(3)), resultados.getString(4),
				resultados.getString(5), atracciones, resultados.getDouble(6));
	}

	private PromoAbsoluta toPromoAbsoluta(ResultSet resultados, LinkedList<Atraccion> atracciones) throws SQLException {
		return new PromoAbsoluta(TipoAtraccion.valueOf(resultados.getInt(3)), resultados.getString(4),
				resultados.getString(5), atracciones, resultados.getInt(6));
	}

	private PromocionAXB toPromoAXB(ResultSet resultados, LinkedList<Atraccion> atracciones,
			LinkedList<Atraccion> atraccionesGratis) throws SQLException {
		return new PromocionAXB(TipoAtraccion.valueOf(resultados.getInt(3)), resultados.getString(4),
				resultados.getString(5), atracciones, atraccionesGratis);
	}
	
	
	public LinkedList<Promocion> encontrarPromocionesContratadasPorUsuarios(String nombreUsuario){
		try {
			String sql = 
			
			"SELECT Promociones.* FROM propuestas_compradas_por_usuarios  "
			+ "JOIN usuarios ON usuarios.Nombre = propuestas_compradas_por_usuarios.nombre_usuario "
			+ "JOIN promociones ON promociones.Nombre = propuestas_compradas_por_usuarios.nombre_promocion "
			+ "WHERE usuarios.nombre = ?";
			
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombreUsuario);
			ResultSet resultados = statement.executeQuery();

			LinkedList<Promocion> promociones = new LinkedList<Promocion>();
			while (resultados.next()) {
				promociones.add(toPromocion(resultados));
			}

			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
			
	}

}
