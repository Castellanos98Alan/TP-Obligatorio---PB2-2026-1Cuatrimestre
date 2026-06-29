package ar.edu.unlam.centroDeBienEstar;

public class Cliente extends Persona implements Comparable<Cliente>{

	

	private Integer idCliente = 0;
	private static Integer proximoId = 1;
	

	public Cliente( Integer dni, String nombre, String apellido) {
		super(dni, nombre, apellido);
		
		this.idCliente = proximoId++;
		
		// TODO Auto-generated constructor stub
	}


	public Integer getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	
	

	@Override
	public int compareTo(Cliente o) {
		return this.getApellido().compareTo(o.getApellido());
	}
	
	
	
	
	
	
	
	
	
	
}
