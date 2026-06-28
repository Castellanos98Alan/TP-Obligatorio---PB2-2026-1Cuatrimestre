package ar.edu.unlam.centroDeBienEstar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import ar.edu.unlam.pbii.ClaseIndividualException;
import ar.edu.unlam.pbii.ClaseIndividualOcupadaException;
import ar.edu.unlam.pbii.ClaseRepetidaEnHorario;
import ar.edu.unlam.pbii.ClienteDuplicadoException;
import ar.edu.unlam.pbii.ClienteSinReservasException;
import ar.edu.unlam.pbii.CupoYaNoDisponibleException;
import ar.edu.unlam.pbii.ReservaDuplicadaException;

public class CentroDeBienEstar {

	private TreeSet<Cliente> clientes;
	private HashMap<Cliente, HashSet<Reserva>> reservasPorCliente;
	private HashSet<Profesional> profesionales;
	private ArrayList<Clase> clases;

	public CentroDeBienEstar() {
		this.clientes = new TreeSet<>();
		this.reservasPorCliente = new HashMap<>();
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

	public Boolean registrarClaseIndividual(Clase claseAAgregar) throws ClaseIndividualException {

		if (claseAAgregar.getTipo().equals(TIPODECLASE.MASAJES)) {
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

	public void registrarReserva(Reserva reserva1) throws CupoYaNoDisponibleException, ReservaDuplicadaException, ClaseIndividualOcupadaException {
	    Cliente clienteQueReserva = reserva1.getClienteAReservar();
	    Clase claseGenerica = reserva1.getClaseAAsistir();
	    
	    // si el cliente ya existe en el mapa, revisamos su conjunto de reservas
	    if (this.reservasPorCliente.containsKey(clienteQueReserva)) {
	        HashSet<Reserva> reservasDelCliente = this.reservasPorCliente.get(clienteQueReserva);
	        
	        // .contains() usa el equals de Reserva (debe comparar cliente y clase)
	        if (reservasDelCliente.contains(reserva1)) {
	            throw new ReservaDuplicadaException("El cliente ya tiene una reserva para esta clase");
	        }
	    }

	  
	    if (claseGenerica instanceof ClaseGrupal) {
	        ClaseGrupal claseGrupal = (ClaseGrupal) claseGenerica;
	        
	        if (!claseGrupal.hayLugar()) {
	            throw new CupoYaNoDisponibleException("Ya no hay cupos para esta clase grupal");
	        }
	        
	        claseGrupal.agregarCliente(clienteQueReserva); 
	    } else { 
	        // al no ser grupal, por descarte obligatorio es una ClaseIndividual
	        ClaseIndividual claseIndividual = (ClaseIndividual) claseGenerica;
	        
	        if (!claseIndividual.hayLugar()) {
	            throw new ClaseIndividualOcupadaException("Ya no hay cupos para esta clase Individual");
	        }
	        
	        claseIndividual.setClienteAsignado(clienteQueReserva); 
	    }
	    // Si el cliente nunca antes había reservado, le creamos su HashSet en el mapa
	    if (!this.reservasPorCliente.containsKey(clienteQueReserva)) {
	        this.reservasPorCliente.put(clienteQueReserva, new HashSet<Reserva>());
	    }
	    
	    // Buscamos el HashSet de ese cliente y le agregamos la reserva actual
	    this.reservasPorCliente.get(clienteQueReserva).add(reserva1);
	}
	
	public HashSet<Reserva> obtenerReservasDeUnCliente(Cliente cliente) throws ClienteSinReservasException {

	    if (!reservasPorCliente.containsKey(cliente)) {
	    	 throw new ClienteSinReservasException("El cliente no posee reservas.");
	    }

	    return reservasPorCliente.get(cliente);
	}
	
}
