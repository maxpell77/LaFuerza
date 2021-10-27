package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import laFuerza.Atraccion;
import laFuerza.Promocion;
import laFuerza.Propuesta;
import laFuerza.TipoAtraccion;
import laFuerza.Usuario;

public class UsuariosDAOImpl implements UsuariosDAO {

	public int update(Usuario user) {
		try {
			String sql = "UPDATE USUARIOS SET TIPO_ATRACCION = ?, PRESUPUESTO = ?, TIEMPO = ? WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, user.getTipoAtraccionPreferida().getNumeroId());
			statement.setInt(2, user.getPresupuestoDisponible());
			statement.setDouble(3, user.getTiempoDisponible());
			statement.setString(4, user.getNombre());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int insertPropuestaContratadas(Usuario user, Propuesta propuesta) {

		try {
			String sql = "INSERT INTO PROPUESTAS_COMPRADAS_POR_USUARIOS (ID_USUARIO, ID_PROMOCION, ID_ATRACCION) VALUES (?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, user.getUsuario_id());

			if (propuesta.getClass() == Atraccion.class) {
				statement.setInt(3, propuesta.getPropuestaId());

			} else {
				statement.setInt(2, propuesta.getPropuestaId());

			}

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	public List<Usuario> findAll() {
		try {
			String sql = "SELECT * FROM USUARIOS";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Usuario> usuarios = new LinkedList<Usuario>();
			while (resultados.next()) {

				usuarios.add(toUser(resultados));
			}

			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Usuario toUser(ResultSet resultados) {

		try {
			AtraccionesDAO atraccionesDAO = DAOFactory.getAtraccionesDAO();
			LinkedList<Atraccion> atracciones = atraccionesDAO
					.encontraAtraccionesContratadasPorUsuarios(resultados.getInt(1));

			PromocionesDAO promocionesDAO = DAOFactory.getPromocinoesDAO();
			LinkedList<Promocion> promociones = promocionesDAO
					.encontrarPromocionesContratadasPorUsuarios(resultados.getInt(1));

			LinkedList<Propuesta> propuestasCompradas = new LinkedList<Propuesta>();
			propuestasCompradas.addAll(atracciones);
			propuestasCompradas.addAll(promociones);

			return new Usuario(resultados.getString(2), TipoAtraccion.valueOf(resultados.getInt(3)),
					resultados.getInt(4), resultados.getDouble(5), propuestasCompradas, resultados.getInt(1));
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM USUARIOS";
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
