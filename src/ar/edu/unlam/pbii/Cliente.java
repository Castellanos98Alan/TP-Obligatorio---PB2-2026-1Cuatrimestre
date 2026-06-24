package ar.edu.unlam.pbii;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona{
	
	private static  Integer proximoId = 1;
	private Integer id=0;
	private ArrayList<Reserva> reservasDadas;

	public Cliente(String nombre, String apellido, String dni, Integer id) {
		super(nombre, apellido, dni);
		this.id= proximoId++;
		this.reservasDadas = new ArrayList<>();
	}
	public Boolean agregarReserva(Reserva r) {
		return this.reservasDadas.add(r);
	}
	public List<Reserva> getReservasDadas(){
		return this.reservasDadas;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	


}
