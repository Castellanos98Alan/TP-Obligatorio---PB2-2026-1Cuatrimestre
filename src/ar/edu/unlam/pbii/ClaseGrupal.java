package ar.edu.unlam.pbii;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;


public  class ClaseGrupal extends Clase {
	
	static final private Double precioEquipamentoExtra = 1800.0;
	static final private Integer cupoMaximo= 30;
	private HashSet<Cliente> inscriptos;
	private TipoDeClase claseGrupal;


	
	public ClaseGrupal(Profesional profesionalADar, LocalDate fecha, LocalTime horario, Double duracionHoras,
			HashSet<Cliente> inscriptos, TipoDeClase claseGrupal) {
		super(profesionalADar, fecha, horario, duracionHoras);
		this.inscriptos = new HashSet<Cliente>();
		this.claseGrupal = claseGrupal;
	}

	public boolean hayLugar() {
		return inscriptos.size() < cupoMaximo;
	
	}
	
	public void agregarCliente(Cliente clienteAAgregar) {
		
		inscriptos.add(clienteAAgregar);
	}


	@Override
	public Double calcularPrecio() {
		Double precioFinal=0.0;
		
		switch(claseGrupal) {
		
		case YOGA:
			precioFinal= getDuracionHoras() * claseGrupal.getPrecio();
			break;
		case FUNCIONAL:
			precioFinal= (getDuracionHoras() * claseGrupal.getPrecio()) + precioEquipamentoExtra;
			break;
		case SPINNING:
			precioFinal= (getDuracionHoras() * claseGrupal.getPrecio()) + precioEquipamentoExtra;
			break;
		default:
			break;
		
		}
		
		return precioFinal;
	}
	


}
