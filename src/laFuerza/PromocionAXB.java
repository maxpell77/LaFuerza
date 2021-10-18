package laFuerza;

import java.util.LinkedList;

public class PromocionAXB extends Promocion {
	protected int tipo_promocion_id = 3;
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

	public LinkedList<Atraccion> getAtraccionesGratis(){
		return atraccionesGratis;
	}

}
