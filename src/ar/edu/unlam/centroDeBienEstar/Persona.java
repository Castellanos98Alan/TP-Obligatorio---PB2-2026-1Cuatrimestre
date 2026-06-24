package ar.edu.unlam.centroDeBienEstar;

import java.util.Objects;

public abstract class Persona {

	private Integer dni;
	private String nombre;
	private String apellido;

	public Persona(Integer dni, String nombre, String apellido) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Persona))
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(dni, other.dni);
	}



	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}
