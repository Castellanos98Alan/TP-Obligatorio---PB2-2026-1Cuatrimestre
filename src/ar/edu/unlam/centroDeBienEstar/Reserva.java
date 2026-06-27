package ar.edu.unlam.centroDeBienEstar;

import java.util.Objects;

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

		@Override
		public int hashCode() {
			return Objects.hash(claseAAsistir, clienteAReservar);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Reserva other = (Reserva) obj;
			return Objects.equals(claseAAsistir, other.claseAAsistir)
					&& Objects.equals(clienteAReservar, other.clienteAReservar);
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

