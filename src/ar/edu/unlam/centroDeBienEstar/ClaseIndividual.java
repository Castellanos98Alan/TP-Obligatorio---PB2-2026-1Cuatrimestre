package ar.edu.unlam.centroDeBienEstar;


import java.time.LocalDate;
import java.time.LocalTime;


public class ClaseIndividual extends Clase{
	private Cliente clienteATomarClase;
	private TIPODECLASE claseIndividual;
	static final private Double precioEquipamentoExtra = 2800.0;

	public ClaseIndividual(Profesional profesionalADar, Double duracionHoras, LocalDate fecha, LocalTime horario,
			TIPODECLASE tipo) {
		super(profesionalADar, duracionHoras, fecha, horario, tipo);
		this.claseIndividual= tipo;
		// TODO Auto-generated constructor stub
	}

	
	
	
	public Cliente getClienteATomarClase() {
		return clienteATomarClase;
	}




	public void setClienteATomarClase(Cliente clienteATomarClase) {
		this.clienteATomarClase = clienteATomarClase;
	}




	public TIPODECLASE getClaseIndividual() {
		return claseIndividual;
	}




	public void setClaseIndividual(TIPODECLASE claseIndividual) {
		this.claseIndividual = claseIndividual;
	}




	public static Double getPrecioequipamentoextra() {
		return precioEquipamentoExtra;
	}

	public Cliente getClienteAsignado() {
        return clienteATomarClase;
    }

    public void setClienteAsignado(Cliente cliente) {
        this.clienteATomarClase = cliente;
    }



	@Override
	public Double calcularPrecio() {
		
		Double precioFinal=0.0;
		
		if(claseIndividual==TIPODECLASE.MASAJES) {
			
			precioFinal= (getDuracionHoras() * claseIndividual.getPrecio()) + precioEquipamentoExtra;
			
		}
		return precioFinal;
	}




	@Override
	public Boolean hayLugar() {
		return this.clienteATomarClase==null; //siempre esta llena ya que se crea con el cliente
	}
	
	
	
	
	
}
