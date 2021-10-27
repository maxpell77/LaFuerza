package laFuerza;

import java.util.LinkedList;
import java.util.Objects;

public abstract class Propuesta implements Comparable<Propuesta> {

	protected TipoAtraccion tipoAtraccion;
	protected int costo;
	protected double tiempoTotal;
	protected String nombre;
	protected boolean hayCupoDisponible = true;
	protected int propuestaID;

	public Propuesta(int propuestaID) {
		this.propuestaID = propuestaID;

	}

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

	public int getPropuestaId() {
		return propuestaID;
	}

	@Override
	public int compareTo(Propuesta o) {
		int c = 0;
		if (this.getNombre() == o.getNombre())
			c = 0;

		if (this.getClass() != Atraccion.class && o.getClass() == Atraccion.class) {
			c = -1;
		} else if (this.getClass() == Atraccion.class && o.getClass() != Atraccion.class) {
			c = 1;
		}

		else {
			c = o.getCosto() - this.getCosto();
			if (c == 0) {
				c = Double.compare(o.getTiempoUtilizado(), this.getTiempoUtilizado());
			}
		}
		return c;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Propuesta other = (Propuesta) obj;
		return Objects.equals(nombre, other.nombre);
	}

	public abstract void actualizarCupoDisponible();

	public abstract LinkedList<Atraccion> getAtraccionesIncluidas();

	public abstract String toString();

}
