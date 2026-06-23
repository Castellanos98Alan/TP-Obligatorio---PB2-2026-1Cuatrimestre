package ar.edu.unlam.pbii;

import java.util.Objects;

public abstract class Persona{
	
	private String nombre;
	private String apellido;
	private String dni;
	
	public Persona(String nombre, String apellido, String dni) {
		this.nombre = nombre;
		this.dni = dni;
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	@Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Persona)) return false;
        Persona other = (Persona) obj;
        return Objects.equals(this.dni, other.dni);
    }
	
	
	
}