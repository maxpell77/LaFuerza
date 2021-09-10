package tierraMedia;

import java.util.LinkedList;

public class PromoPorcentual extends Promocion {
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

}
