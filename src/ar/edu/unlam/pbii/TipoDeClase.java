package ar.edu.unlam.pbii;

public enum TipoDeClase {
	YOGA (5000.0), SPINNING (6000.0), FUNCIONAL(3000.0), MASAJES(10000.0);

	private Double precio;

	private TipoDeClase(Double precio) {
		this.precio = precio;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
}
