package ar.edu.unlam.pbii;

public class Reserva {
	private Cliente clienteAReservar;
	private Clase claseAAsistir;
	
	public Reserva(Cliente clienteAReservar, Clase claseAAsistir) {
		this.clienteAReservar = clienteAReservar;
		this.claseAAsistir = claseAAsistir;
	}

	public Cliente getClienteAReservar() {
		return clienteAReservar;
	}

	public void setClienteAReservar(Cliente clienteAReservar) {
		this.clienteAReservar = clienteAReservar;
	}

	public Clase getClaseAAsistir() {
		return claseAAsistir;
	}

	public void setClaseAAsistir(Clase claseAAsistir) {
		this.claseAAsistir = claseAAsistir;
	}
	
	public Double obtenerPrecio() {
		
		return claseAAsistir.calcularPrecio();
	}
	
	
}
