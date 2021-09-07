package tierraMedia;

import java.util.LinkedList;

public abstract class Propuesta implements Comparable<Propuesta> {

	protected TipoAtraccion tipoAtraccion;
	protected int costo;
	protected double tiempoTotal;
	protected String nombre;
	protected boolean hayCupoDisponible = true;

	public int getCosto() {
		return costo;
	}

	public double getTiempoUtilizado() {
		return tiempoTotal;
	}

	public TipoAtraccion getTipoAtraccion() {
		return tipoAtraccion;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean hayCupoDisponible() {
		return hayCupoDisponible;
	}

	@Override
	public int compareTo(Propuesta o) {
		int c = 0;
	
		if (this.getClass() != Atraccion.class && o.getClass() == Atraccion.class) {
			c = -1;
		} else if (this.getClass() == Atraccion.class && o.getClass() != Atraccion.class) {
			c = 1;
		}

		else {
			c = o.getCosto() - this.getCosto();
			if (c == 0) {
				double tiempoDif = o.getTiempoUtilizado() - this.getTiempoUtilizado();
				c = tiempoDif > 0 ? 1 : (tiempoDif < 0 ? -1 : 0);
			}
		}
		return c;
	}

	public abstract void actualizarCupoDisponible();
	
//	public abstract int  getCupoDisponible();
	
	public abstract LinkedList<Atraccion>  getAtraccionesIncluidas();

}
