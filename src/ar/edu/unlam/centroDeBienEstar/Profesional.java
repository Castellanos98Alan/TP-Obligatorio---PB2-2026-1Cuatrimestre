package ar.edu.unlam.centroDeBienEstar;

import java.util.Objects;

public class Profesional extends Persona {

	private String idMatricula;
	private String especialidad;
	
	
	public Profesional(String idMatricula, Integer dni, String nombre, String apellido, String especialidad) {
		super(dni, nombre, apellido);
		this.idMatricula = idMatricula;
		this.especialidad = especialidad;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(idMatricula);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Profesional))
			return false;
		Profesional other = (Profesional) obj;
		return Objects.equals(idMatricula, other.idMatricula);
	}

	
	
	
	
	
	
	
	
}
