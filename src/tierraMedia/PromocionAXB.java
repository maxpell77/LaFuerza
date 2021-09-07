package tierraMedia;

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
		this.costo = super.getCostoTotalAtracciones() - getCostoAtraccionesGratis();
	}

	private int getCostoAtraccionesGratis() {
		int costoAtraccionesGratis = 0;
		for (Atraccion atraccion : atraccionesGratis) {
			costoAtraccionesGratis += atraccion.getCosto();
		}
		return costoAtraccionesGratis;

	}

}
