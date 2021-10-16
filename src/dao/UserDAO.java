package dao;

import laFuerza.Usuario;


public interface UserDAO extends GenericDAO<Usuario> {

	public abstract Usuario findByUsername(String username);
	
}

