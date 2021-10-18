package dao;

import java.util.LinkedList;

import laFuerza.Atraccion;
import laFuerza.Promocion;

public interface PromocionesDAO extends GenericDAO<Promocion> {
	
	public abstract Promocion findByname(String name);
	
	public abstract LinkedList<Promocion> encontrarPromocionesContratadasPorUsuarios(String idUsuario);

}
