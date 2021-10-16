package dao;

import laFuerza.Atraccion;


public interface AtraccionesDAO extends GenericDAO<Atraccion> {

	public abstract Atraccion findByUsername(String username);
	
}