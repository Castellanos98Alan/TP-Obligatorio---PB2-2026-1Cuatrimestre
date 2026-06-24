package ar.edu.unlam.centroDeBienEstar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import ar.edu.unlam.pbii.ClaseIndividualException;
import ar.edu.unlam.pbii.ClienteDuplicadoException;
import ar.edu.unlam.pbii.CupoYaNoDisponibleException;

public class CentroDeBienEstar {

	private TreeSet<Cliente> clientes;
	private HashMap<Reserva, Cliente> reservas;
	private HashSet<Profesional> profesionales;
	private ArrayList<Clase> clases;

	public CentroDeBienEstar() {
		this.clientes = new TreeSet<>();
		this.reservas = new HashMap<>();
		this.profesionales = new HashSet<Profesional>();
		this.clases = new ArrayList<>();

	}

	public Boolean registrarCliente(Cliente clienteAAgregar) throws ClienteDuplicadoException {

		for (Cliente cliente : clientes) {
			if (cliente.getDni().equals(clienteAAgregar.getDni()))
				throw new ClienteDuplicadoException("El cliente ya se encuentra agregado");
		}

		return clientes.add(clienteAAgregar);

	}

	public Boolean registrarProfesional(Profesional profesional) {

		return profesionales.add(profesional);

	}

	public Boolean registrarClaseGrupal(Clase claseAAgregar) throws ClaseRepetidaEnHorario {

		for (Clase clase : clases) {
			if (clase.getProfesionalADar().equals(claseAAgregar.getProfesionalADar())
					&& clase.getHorario().equals(claseAAgregar.getHorario())) {

				throw new ClaseRepetidaEnHorario("El profesional ya tiene una clase en ese horario");
			}
		}

		return this.clases.add(claseAAgregar);

	}

	public boolean registrarClaseIndividual(Clase claseAAgregar) throws ClaseIndividualException {

		if (claseAAgregar.getTipo().equals(claseAAgregar.getTipo().MASAJES)) {
			return this.clases.add(claseAAgregar);
		}
		throw new ClaseIndividualException("La clase ingresada no puede ser individual, unicamente los masajes lo son");

	}

	public ArrayList<Clase> obtenerTodasLasClasesQueRealizaCiertoProfesional(Profesional profesional) {
		ArrayList<Clase> ClasesQueBrindaCiertoProfesional = new ArrayList<Clase>();
		
		for (Clase clase : clases) {
			if(clase.getProfesionalADar().equals(profesional)) {
				ClasesQueBrindaCiertoProfesional.add(clase);
			}
				
		}
		
		
		
		
		return ClasesQueBrindaCiertoProfesional;
	}

	public void registrarReserva(Reserva reserva1) throws CupoYaNoDisponibleException {
		
		if(reserva1.getClase1().getCUPO_MAXIMO_DE_CLASE() > 0) {
			reserva1.getClase1().setCUPO_MAXIMO_DE_CLASE(reserva1.getClase1().getCUPO_MAXIMO_DE_CLASE() - 1);
			return;
		}
		throw new  CupoYaNoDisponibleException("Ya no hay cupos para esta clase");
		
		
	}

	
	
}
