package ar.edu.unlam.centroDeBienEstar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;

public class ClaseGrupal extends Clase{
	
	public final static  Integer CUPO_MAXIMO_DE_CLASE= 30;;
	static final private Double precioEquipamentoExtra = 1800.0;
	private HashSet<Cliente> inscriptos;
	private TIPODECLASE claseGrupal;

		public ClaseGrupal(Profesional profesionalADar, LocalDate fecha, LocalTime horario, Double duracionHoras,
				TIPODECLASE tipo) {
			super(profesionalADar, duracionHoras, fecha, horario, tipo);
			this.inscriptos = new HashSet<Cliente>();
			this.claseGrupal= tipo;
		}

		
	

	public static Integer getCUPO_MAXIMO_DE_CLASE() {
		return CUPO_MAXIMO_DE_CLASE;
	}


	@Override
	public boolean hayLugar() {
	    return inscriptos.size() < CUPO_MAXIMO_DE_CLASE;
	}
	
	
	public void agregarCliente(Cliente clienteAAgregar) {
		
		inscriptos.add(clienteAAgregar);
	}


	@Override
	public Double calcularPrecio() {
		Double precioFinal= 0.0;
		
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
