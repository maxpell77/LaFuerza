package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import laFuerza.Atraccion;
import laFuerza.TipoAtraccion;

public class AtraccionesDAOImpl implements AtraccionesDAO {

	public List<Atraccion> findAll() {
		try {
			String sql = "SELECT * FROM ATRACCIONES";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Atraccion> atracciones = new LinkedList<Atraccion>();
			while (resultados.next()) {
				atracciones.add(toAtraccion(resultados));
			}

			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Atraccion toAtraccion(ResultSet resultados) {
		try {
			return new Atraccion(resultados.getInt(3), resultados.getDouble(4),
					TipoAtraccion.valueOf(resultados.getInt(6)), resultados.getInt(5), resultados.getString(2),
					resultados.getInt(1));
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public LinkedList<Atraccion> encontrarAtraccionesdePromociones(int idPromocion) {
		try {
			String sql =

					"SELECT ATRACCIONES.* FROM ATRACCIONES_DE_PROMOCIONES "
							+ "JOIN PROMOCIONES ON PROMOCIONES.ID = ATRACCIONES_DE_PROMOCIONES.ID_PROMOCION "
							+ "JOIN ATRACCIONES ON ATRACCIONES.ID = ATRACCIONES_DE_PROMOCIONES.ID_ATRACCION "
							+ "WHERE PROMOCIONES.ID = ?";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idPromocion);
			ResultSet resultados = statement.executeQuery();

			LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
			while (resultados.next()) {
				atracciones.add(toAtraccion(resultados));
			}

			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	public LinkedList<Atraccion> encontrarAtraccionesdePromosAXB(int idPromocion) {
		try {
			String sql =

					"SELECT ATRACCIONES.* FROM ATRACCIONES_DE_PROMOS_AXB "
							+ "JOIN PROMOCIONES ON PROMOCIONES.ID = ATRACCIONES_DE_PROMOS_AXB.ID_PROMOCION "
							+ "JOIN ATRACCIONES ON ATRACCIONES.ID = ATRACCIONES_DE_PROMOS_AXB.ID_ATRACCION "
							+ "WHERE PROMOCIONES.ID = ?";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idPromocion);
			ResultSet resultados = statement.executeQuery();

			LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
			while (resultados.next()) {
				atracciones.add(toAtraccion(resultados));
			}

			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	public LinkedList<Atraccion> encontraAtraccionesContratadasPorUsuarios(int idUsuario) {
		try {
			String sql =

					"SELECT ATRACCIONES.* FROM propuestas_compradas_por_usuarios "
							+ "JOIN usuarios ON usuarios.id = propuestas_compradas_por_usuarios.id_usuario "
							+ "JOIN atracciones ON atracciones.id = propuestas_compradas_por_usuarios.id_atraccion "
							+ "WHERE usuarios.id = ?";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idUsuario);
			ResultSet resultados = statement.executeQuery();

			LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
			while (resultados.next()) {
				atracciones.add(toAtraccion(resultados));
			}

			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	public int updateCupos(Atraccion atraccion) {

		try {
			String sql = "UPDATE ATRACCIONES SET  CUPO = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, atraccion.getCupoDisponible());
			statement.setInt(2, atraccion.getId_atraccion());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int insert(Atraccion atraccion) {
		try {
			String sql = "INSERT INTO ATRACCIONES (NOMBRE, COSTO, TIEMPO, CUPO, TIPO_ATRACCION) VALUES (?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setInt(2, atraccion.getCosto());
			statement.setDouble(3, atraccion.getTiempoUtilizado());
			statement.setInt(4, atraccion.getCupoInicial());
			statement.setInt(5, atraccion.getTipoAtraccion().getNumeroId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(Atraccion atraccion) {
		try {
			String sql = "DELETE FROM ATRACCIONES WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getId_atraccion());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Atraccion findByname(String nombreAtraccion) {
		try {
			String sql = "SELECT * FROM ATRACCIONES WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombreAtraccion);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = toAtraccion(resultados);
			}

			return atraccion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM ATRACCIONES";
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
