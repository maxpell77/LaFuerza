package laFuerza;

import java.util.LinkedList;

public class PromoPorcentual extends Promocion {
	protected int tipo_promocion_id = 2;
	private double porcentajeDescuento;

	public PromoPorcentual(TipoAtraccion tipoAtraccion, String titulo, String descrpicion,
			LinkedList<Atraccion> atraccionesIncluidas, double porcentajeDescuento) {
		super(tipoAtraccion, titulo, descrpicion, atraccionesIncluidas);
		this.porcentajeDescuento = porcentajeDescuento;
	}

	@Override
	public void setCosto() {
		costo = (int) Math.round(getCostoTotalAtracciones(atraccionesIncluidas) * (1 - this.porcentajeDescuento));
	}
	
	public double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

}
