package ar.edu.unlam.pbii;

public class Profesional extends Persona{
	private String especialidad;
	private String matricula;
	
	public Profesional(String nombre, String apellido, String dni, String especialidad, String matricula) {
		super(nombre, apellido, dni);
		this.especialidad = especialidad;
		this.matricula = matricula;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	

	}
