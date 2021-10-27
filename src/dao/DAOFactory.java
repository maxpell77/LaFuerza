package dao;

public class DAOFactory {
	
	public static UsuariosDAO getUserDAO() {
		return new UsuariosDAOImpl();
	}
	
	public static AtraccionesDAO getAtraccionesDAO() {
		return new AtraccionesDAOImpl();
	}
	
	public static PromocionesDAO getPromocinoesDAO() {
		return new PromocinoesDAOImpl();
	}

}
