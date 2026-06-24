package ar.edu.unlam.centroDeBienEstar;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClaseGrupal extends Clase{
	
	public static  Integer CUPO_MAXIMO_DE_CLASE;

	public ClaseGrupal(Profesional profesionalADar, Double duracionHoras, LocalDate fecha, LocalTime horario,
			TIPODECLASE tipo) {
		super(profesionalADar, duracionHoras, fecha, horario, tipo);
		this.CUPO_MAXIMO_DE_CLASE = 30;
		
		
	}

	public static Integer getCUPO_MAXIMO_DE_CLASE() {
		return CUPO_MAXIMO_DE_CLASE;
	}

	public static void setCUPO_MAXIMO_DE_CLASE(Integer cUPO_MAXIMO_DE_CLASE) {
		CUPO_MAXIMO_DE_CLASE = cUPO_MAXIMO_DE_CLASE;
	}

	
	

	
	
	
	

}
