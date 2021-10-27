package laFuerza;

import java.util.LinkedList;

public class PromocionAXB extends Promocion {
	protected int tipo_promocion_id = 3;
	private LinkedList<Atraccion> atraccionesGratis = new LinkedList<Atraccion>();

	public PromocionAXB(TipoAtraccion tipoAtraccion, String titulo, String descrpicion,
			LinkedList<Atraccion> atraccionesIncluidas, LinkedList<Atraccion> atraccionesGratis, int id_promocion) {
		super(tipoAtraccion, titulo, descrpicion, atraccionesIncluidas, id_promocion);
		this.atraccionesGratis = atraccionesGratis;
		this.costo = obtenerCosto();
	}

	private int obtenerCosto() {
		return getCostoTotalAtracciones(atraccionesIncluidas) - getCostoTotalAtracciones(atraccionesGratis);
	}

	public LinkedList<Atraccion> getAtraccionesGratis() {
		return atraccionesGratis;
	}

}
