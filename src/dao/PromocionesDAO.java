package dao;

import java.util.LinkedList;

import laFuerza.Promocion;

public interface PromocionesDAO extends GenericDAO<Promocion> {

	public abstract LinkedList<Promocion> encontrarPromocionesContratadasPorUsuarios(int idUsuario);
	
	public abstract int countAll();
	

}
