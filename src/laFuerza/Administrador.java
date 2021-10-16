package laFuerza;

import java.io.IOException;
import java.util.List;

import dao.AtraccionesDAO;
import dao.DAOFactory;
import dao.PromocionesDAO;
import dao.UserDAO;

public class Administrador {

//	public void activarSistema() throws IOException {
//		
//		//una sola variable de archivos 
//		List<String> atracciones = LectorArchivosdeEntrada.leerArchivo("entrada/atracciones.txt");
//		List<String> promociones = LectorArchivosdeEntrada.leerArchivo("entrada/promociones.txt");
//		List<String> usuarios = LectorArchivosdeEntrada.leerArchivo("entrada/usuarios.txt");
//
//		CargadorArchivosEntrada.agregarAtracciones(atracciones);
//		CargadorArchivosEntrada.agregarPromociones(promociones);
//		CargadorArchivosEntrada.agregarUsuarios(usuarios);
//
//		Ofertador.sugerirPropuestasAusuarios(CargadorArchivosEntrada.getPropuestas(),
//				CargadorArchivosEntrada.getUsuarios());
//
//	}

	public void activarSistema() throws IOException {


		CargadorArchivosEntrada.agregarAtracciones();
		CargadorArchivosEntrada.agregarPromociones();
		CargadorArchivosEntrada.agregarUsuarios();
		

		Ofertador.sugerirPropuestasAusuarios(CargadorArchivosEntrada.getPropuestas(),
				CargadorArchivosEntrada.getUsuarios());

	}
	
	
//	public void activarSistema() throws IOException {
//		PromocionesDAO promocionesDAO = DAOFactory.getPromocinoesDAO();
//		System.out.println(promocionesDAO.findAll()) ;
//	
//
//	}

}
