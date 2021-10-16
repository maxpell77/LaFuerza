package dao;

import java.util.LinkedList;


import laFuerza.Atraccion;


public interface AtraccionesDAO extends GenericDAO<Atraccion> {

	public abstract Atraccion findByname(String name);
	
	public abstract LinkedList<Atraccion> encontrarAtraccionesdePromociones(int idPromocion);
	
	public abstract LinkedList<Atraccion> encontrarAtraccionesdePromosAXB(int idPromocion);
	
}