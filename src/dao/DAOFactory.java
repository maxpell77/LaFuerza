package dao;

public class DAOFactory {
	
	public static UserDAO getUserDAO() {
		return new UserDAOImpl();
	}
	
	public static AtraccionesDAO getAtraccionesDAO() {
		return new AtraccionesDAOImpl();
	}
	
	public static PromocionesDAO getPromocinoesDAO() {
		return new PromocinoesDAOImpl();
	}

}
