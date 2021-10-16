package dao;

import laFuerza.Promocion;

public interface PromocionesDAO extends GenericDAO<Promocion> {
	
	public abstract Promocion findByname(String name);

}
