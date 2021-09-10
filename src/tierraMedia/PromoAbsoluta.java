package tierraMedia;

import java.util.LinkedList;

public class PromoAbsoluta extends Promocion {
	private int costoPromo;

	public PromoAbsoluta(TipoAtraccion tipoAtraccion, String titulo, String descrpicion,
			LinkedList<Atraccion> atraccionesIncluidas, int costoPromo) {
		super(tipoAtraccion, titulo, descrpicion, atraccionesIncluidas);
		this.costoPromo = costoPromo;
	}

	@Override
	public void setCosto() {
		costo = costoPromo;
	}

}
