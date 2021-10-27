package dao;

import java.util.LinkedList;

import laFuerza.Atraccion;

public interface AtraccionesDAO extends GenericDAO<Atraccion> {

	public abstract int updateCupos(Atraccion atraccion);
	
	public abstract int insert(Atraccion atraccion);
	
	public abstract int delete(Atraccion atraccion);
	
	public abstract int countAll();
	
	public abstract Atraccion findByname(String nombreAtraccion);

	public abstract LinkedList<Atraccion> encontrarAtraccionesdePromociones(int idPromocion);

	public abstract LinkedList<Atraccion> encontrarAtraccionesdePromosAXB(int idPromocion);

	public abstract LinkedList<Atraccion> encontraAtraccionesContratadasPorUsuarios(int idUsuario);

}