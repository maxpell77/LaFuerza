package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import laFuerza.TipoAtraccion;
import laFuerza.Usuario;


public class UserDAOImpl implements UserDAO {

	public int insert(Usuario user) {
		try {
			String sql = "INSERT INTO USUARIOS (NOMBRE, TIPO_ATRACCION, PRESUPUESTO, TIEMPO) VALUES (?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getNombre());
			statement.setInt(2, user.getTipoAtraccionPreferida().getNumeroId());
			statement.setInt(3, user.getPresupuestoDisponible());
			statement.setDouble(4, user.getTiempoDisponible());
;
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

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

	public int delete(Usuario user) {
		try {
			String sql = "DELETE FROM USUARIOS WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Usuario findByname(String username) {
		try {
			String sql = "SELECT * FROM USUARIOS WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet resultados = statement.executeQuery();

			Usuario user = null;

			if (resultados.next()) {
				user = toUser(resultados);
			}

			return user;
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

	//string, tipoat, int prsu, double tiempo
	private Usuario toUser(ResultSet resultados) throws SQLException {
		return new Usuario(resultados.getString(2), TipoAtraccion.valueOf(resultados.getInt(3) ) ,resultados.getInt(4), resultados.getDouble(5));
	}

}
