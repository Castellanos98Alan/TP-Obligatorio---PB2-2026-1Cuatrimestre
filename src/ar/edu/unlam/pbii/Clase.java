package ar.edu.unlam.pbii;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Clase {
	private Profesional profesionalADar;
	private Double duracionHoras;
	private LocalDate fecha;
	private LocalTime horario;
	
	public Clase(Profesional profesionalADar, LocalDate fecha, LocalTime horario, Double duracionHoras) {
		this.profesionalADar = profesionalADar;
		this.fecha = fecha;
		this.horario = horario;
		this.duracionHoras=duracionHoras;
		
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}


	public Profesional getProfesionlADar() {
		return profesionalADar;
	}

	

	public Double getDuracionHoras() {
		return duracionHoras;
	}
	
	public Profesional getProfesionalADar() {
		return profesionalADar;
	}

	public void setProfesionalADar(Profesional profesionalADar) {
		this.profesionalADar = profesionalADar;
	}

	public void setDuracionHoras(Double duracionHoras) {
		this.duracionHoras = duracionHoras;
	}

	public abstract Double calcularPrecio();
	
	
	

}
