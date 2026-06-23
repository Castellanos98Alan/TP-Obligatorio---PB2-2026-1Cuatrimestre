package ar.edu.unlam.pbii;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClaseIndividual extends Clase {
	
	private Cliente clienteATomarClase;
	private TipoDeClase claseIndividual;
	static final private Double precioEquipamentoExtra = 2800.0;

	public ClaseIndividual(Profesional profesionalADar, LocalDate fecha, LocalTime horario, Double duracionHoras,
			Cliente clienteATomarClase, TipoDeClase claseIndividual) {
		super(profesionalADar, fecha, horario, duracionHoras);
		this.clienteATomarClase = clienteATomarClase;
		this.claseIndividual= claseIndividual;
	}


	public Cliente getClienteATomarClase() {
		return clienteATomarClase;
	}


	public void setClienteATomarClase(Cliente clienteATomarClase) {
		this.clienteATomarClase = clienteATomarClase;
	}


	public TipoDeClase getClaseIndividual() {
		return claseIndividual;
	}


	public void setClaseIndividual(TipoDeClase claseIndividual) {
		this.claseIndividual = claseIndividual;
	}


	public static Double getPrecioequipamentoextra() {
		return precioEquipamentoExtra;
	}


	@Override
	public Double calcularPrecio() {
		
		Double precioFinal=0.0;
		
		if(claseIndividual==TipoDeClase.MASAJES) {
			
			precioFinal= (getDuracionHoras() * claseIndividual.getPrecio()) + precioEquipamentoExtra;
			
		}
		return precioFinal;
	}
	
	
	

}
