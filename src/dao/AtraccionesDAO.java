package dao;

import java.util.LinkedList;


import laFuerza.Atraccion;


public interface AtraccionesDAO extends GenericDAO<Atraccion> {

	public abstract Atraccion findByname(String name);
	
	public abstract int updateCupos(Atraccion atraccion);
	
	public abstract LinkedList<Atraccion> encontrarAtraccionesdePromociones(int idPromocion);
	
	public abstract LinkedList<Atraccion> encontrarAtraccionesdePromosAXB(int idPromocion);
	
	public abstract LinkedList<Atraccion> encontraAtraccionesContratadasPorUsuarios(String idUsuario);
	
}