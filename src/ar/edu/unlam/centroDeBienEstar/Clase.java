package ar.edu.unlam.centroDeBienEstar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public abstract class Clase {

	
	private Profesional profesionalADar;
	private Double duracionHoras;
	private LocalDate fecha;
	private LocalTime horario;
	private TIPODECLASE tipo;
	
	
	public Clase(Profesional profesionalADar, Double duracionHoras, LocalDate fecha, LocalTime horario, TIPODECLASE tipo) {
		super();
		this.profesionalADar = profesionalADar;
		this.duracionHoras = duracionHoras;
		this.fecha = fecha;
		this.horario = horario;
		this.tipo = tipo;
	}


	public Profesional getProfesionalADar() {
		return profesionalADar;
	}


	public void setProfesionalADar(Profesional profesionalADar) {
		this.profesionalADar = profesionalADar;
	}


	public Double getDuracionHoras() {
		return duracionHoras;
	}


	public void setDuracionHoras(Double duracionHoras) {
		this.duracionHoras = duracionHoras;
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


	public TIPODECLASE getTipo() {
		return tipo;
	}


	public void setTipo(TIPODECLASE tipo) {
		this.tipo = tipo;
	}


	public abstract Double calcularPrecio();

	
	public abstract Boolean hayLugar();


	@Override
	public int hashCode() {
		return Objects.hash(fecha, horario, profesionalADar);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clase other = (Clase) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(horario, other.horario)
				&& Objects.equals(profesionalADar, other.profesionalADar);
	}
	
	
	
}
