package laFuerza;

public enum TipoPromocion {
	PORCENTUAL(1), ABSOLUTA(2), AXB(3);

	private int numero_id;
	
	private TipoPromocion(int numero_id) {
		this.numero_id = numero_id;
	}

	public int getNumero_id() {
		return numero_id;
	}
		
	
}
