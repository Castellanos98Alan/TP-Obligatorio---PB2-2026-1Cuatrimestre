package ar.edu.unlam.pbii;

public class Cliente extends Persona{
	
	private static  Integer proximoId = 1;
	private Integer id=0;

	public Cliente(String nombre, String apellido, String dni, Integer id) {
		super(nombre, apellido, dni);
		this.id= proximoId++;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	


}
