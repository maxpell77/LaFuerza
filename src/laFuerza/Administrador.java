package laFuerza;

import java.io.IOException;
import java.util.List;

import dao.AtraccionesDAO;
import dao.DAOFactory;
import dao.PromocionesDAO;
import dao.UserDAO;

public class Administrador {

	public void activarSistema() throws IOException {

		CargadorArchivosEntrada.agregarAtracciones();
		CargadorArchivosEntrada.agregarPromociones();
		CargadorArchivosEntrada.agregarUsuarios();

		Ofertador.sugerirPropuestasAusuarios(CargadorArchivosEntrada.getPropuestas(),
				CargadorArchivosEntrada.getUsuarios());

	}

}
