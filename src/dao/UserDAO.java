package dao;

import java.util.LinkedList;

import laFuerza.Propuesta;
import laFuerza.Usuario;


public interface UserDAO extends GenericDAO<Usuario> {

	public abstract Usuario findByname(String username);
	
	public abstract int insertPropuestaContratadas(Usuario user, Propuesta propuesta);
	
	
}

