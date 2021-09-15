package laFuerza;

import java.util.LinkedList;

public class PromocionAXB extends Promocion {
	private LinkedList<Atraccion> atraccionesGratis = new LinkedList<Atraccion>();

	public PromocionAXB(TipoAtraccion tipoAtraccion, String titulo, String descrpicion,
			LinkedList<Atraccion> atraccionesIncluidas, LinkedList<Atraccion> atraccionesGratis) {
		super(tipoAtraccion, titulo, descrpicion, atraccionesIncluidas);
		this.atraccionesGratis = atraccionesGratis;
	}

	@Override
	public void setCosto() {
		costo = getCostoTotalAtracciones(atraccionesIncluidas) - getCostoTotalAtracciones(atraccionesGratis);
	}

}
