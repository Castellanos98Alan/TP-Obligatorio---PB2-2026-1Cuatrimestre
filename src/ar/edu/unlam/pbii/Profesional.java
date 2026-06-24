package ar.edu.unlam.pbii;

import java.util.ArrayList;
import java.util.List;

public class Profesional extends Persona{
	private String especialidad;
	private String matricula;
	private ArrayList<Clase> clasesDadas;
	
	public Profesional(String nombre, String apellido, String dni, String especialidad, String matricula) {
		super(nombre, apellido, dni);
		this.especialidad = especialidad;
		this.matricula = matricula;
		this.clasesDadas = new ArrayList<>();
	}
	
	public Boolean agregarClase(Clase c) {
		return this.clasesDadas.add(c);
	}
	public List<Clase> getClasesDadas(){
		return this.clasesDadas;
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
