package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	private Promocion toPromocion(ResultSet resultados) {

		try {
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
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	private PromoPorcentual toPromoPorcentual(ResultSet resultados, LinkedList<Atraccion> atracciones) {
		try {
			return new PromoPorcentual(TipoAtraccion.valueOf(resultados.getInt(3)), resultados.getString(4),
					resultados.getString(5), atracciones, resultados.getDouble(6), resultados.getInt(1));
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private PromoAbsoluta toPromoAbsoluta(ResultSet resultados, LinkedList<Atraccion> atracciones) {
		try {
			return new PromoAbsoluta(TipoAtraccion.valueOf(resultados.getInt(3)), resultados.getString(4),
					resultados.getString(5), atracciones, resultados.getInt(6), resultados.getInt(1));
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private PromocionAXB toPromoAXB(ResultSet resultados, LinkedList<Atraccion> atracciones,
			LinkedList<Atraccion> atraccionesGratis) {
		try {
			return new PromocionAXB(TipoAtraccion.valueOf(resultados.getInt(3)), resultados.getString(4),
					resultados.getString(5), atracciones, atraccionesGratis, resultados.getInt(1));
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public LinkedList<Promocion> encontrarPromocionesContratadasPorUsuarios(int idUsuario) {
		try {
			String sql =

					"SELECT Promociones.* FROM propuestas_compradas_por_usuarios  "
							+ "JOIN usuarios ON usuarios.id = propuestas_compradas_por_usuarios.id_usuario "
							+ "JOIN promociones ON promociones.id = propuestas_compradas_por_usuarios.id_promocion "
							+ "WHERE usuarios.id = ?";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idUsuario);
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


}
