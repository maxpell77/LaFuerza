package dao;

import laFuerza.Usuario;


public interface UserDAO extends GenericDAO<Usuario> {

	public abstract Usuario findByname(String username);
	
}

