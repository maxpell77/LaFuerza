package laFuerza;

import java.util.LinkedList;

public class PromoAbsoluta extends Promocion {
	protected int tipo_promocion_id = 1;

	public PromoAbsoluta(TipoAtraccion tipoAtraccion, String titulo, String descrpicion,
			LinkedList<Atraccion> atraccionesIncluidas, int costoPromo, int id_promocion) {
		super(tipoAtraccion, titulo, descrpicion, atraccionesIncluidas, id_promocion);
		this.costo = costoPromo;
	}

}
