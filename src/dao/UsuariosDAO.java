package dao;

import laFuerza.Propuesta;
import laFuerza.Usuario;

public interface UsuariosDAO extends GenericDAO<Usuario> {

	public abstract int update(Usuario usuario);

	public abstract int insertPropuestaContratadas(Usuario user, Propuesta propuesta);
	
	public abstract int countAll();

}
