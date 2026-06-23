package ar.edu.unlam.centroDeBienEstar;

public enum TIPODECLASE {
	YOGA(500.0), FUNCIONAL(1000.0), SPINNING(5000.0), MASAJES(10000.0),;

	private Double precio;
	
	private TIPODECLASE(Double precio) {
		this.precio = precio;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	} 
	
	
	
	
}
