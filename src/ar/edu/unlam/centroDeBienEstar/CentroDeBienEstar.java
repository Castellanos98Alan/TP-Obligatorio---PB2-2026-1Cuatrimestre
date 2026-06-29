package ar.edu.unlam.centroDeBienEstar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import ar.edu.unlam.pbii.ClaseIndividualException;
import ar.edu.unlam.pbii.ClaseIndividualOcupadaException;
import ar.edu.unlam.pbii.ClaseInexistenteException;
import ar.edu.unlam.pbii.ClaseRepetidaEnHorario;
import ar.edu.unlam.pbii.ClienteDuplicadoException;
import ar.edu.unlam.pbii.ClienteInexistenteException;
import ar.edu.unlam.pbii.ClienteSinReservasException;
import ar.edu.unlam.pbii.CostoInvalidoException;
import ar.edu.unlam.pbii.CupoYaNoDisponibleException;
import ar.edu.unlam.pbii.DuracionInvalidaException;
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

	public Boolean registrarClaseGrupal(Clase claseAAgregar) throws ClaseRepetidaEnHorario, CostoInvalidoException {
		
		if(claseAAgregar.calcularPrecio() < 0) {
			throw new CostoInvalidoException("Costo inválido: el curso tiene costo negativo");
		}
		
		for (Clase clase : clases) {
			if (clase.getProfesionalADar().equals(claseAAgregar.getProfesionalADar())
					&& clase.getHorario().equals(claseAAgregar.getHorario())) {

				throw new ClaseRepetidaEnHorario("El profesional ya tiene una clase en ese horario");
			}
		}

		return this.clases.add(claseAAgregar);

	}

	public Boolean registrarClaseIndividual(Clase claseAAgregar) throws ClaseIndividualException, CostoInvalidoException, DuracionInvalidaException {
		
		if(claseAAgregar.getDuracionHoras() < 0) {
			throw new DuracionInvalidaException("Carga horaria inválida: el curso tiene una duración negativa");
		}
		if(claseAAgregar.calcularPrecio() < 0) {
			throw new CostoInvalidoException("Costo inválido: el curso tiene costo negativo");
		}
		

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

	public void registrarReserva(Reserva reserva1) throws CupoYaNoDisponibleException, ReservaDuplicadaException, ClaseIndividualOcupadaException, ClienteInexistenteException, ClaseInexistenteException {
	    Cliente clienteQueReserva = reserva1.getClienteAReservar();
	    Clase claseGenerica = reserva1.getClaseAAsistir();
	    
	    if (!this.clientes.contains(clienteQueReserva)) {
	        throw new ClienteInexistenteException("El Cliente no esta registrado en el centro.");
	    }
	    if (!this.clases.contains(claseGenerica)) {
	        throw new ClaseInexistenteException("La clase no esta registrado en el centro.");
	    }
	    
	    
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
	
	public ArrayList<Cliente> obtenerListaDeClientesOrdenadasPorApellido () {
		ArrayList<Cliente> ClientesOrdenados = new ArrayList<>(this.clientes);
		return ClientesOrdenados;
	}

	public ArrayList<Clase>  obtenerListaDeClasesOrdenadasPorFecha () {
		return this.clases;
	}

	public Cliente obtenerClientePorDNI(Integer dni) {
		return this.clientes.stream().filter(cliente -> cliente.getDni().equals(dni)).findFirst().orElse(null);
		
	}

	public ArrayList<Clase> obtenerListaDeClasesOrdenadaPorCostosAsc() {
		ArrayList<Clase> ClasesOrdenadas = new ArrayList<>(this.clases);
		Comparator<Clase> clasesPorCostoAsc = (c1,c2) -> Double.compare(c1.calcularPrecio(), c2.calcularPrecio());
		ClasesOrdenadas.sort(clasesPorCostoAsc);
		
		return ClasesOrdenadas;
	}

	public ArrayList<Cliente> obtenerListaDeClientesOrdenadoPorCantidadDeActividadesDesc() {
		ArrayList<Cliente> listaClientes = new ArrayList<>(this.reservasPorCliente.keySet());
		
		Comparator<Cliente> ClientesOrdenadosPorCantidadDeActividadesDesc = new Comparator<Cliente>() {
			@Override
			public int compare(Cliente cli1, Cliente cli2) {
				HashSet<Reserva> reservasCli1 = reservasPorCliente.get(cli1);
				HashSet<Reserva> reservasCli2 = reservasPorCliente.get(cli2);
				
				Integer cantReservas1 =  (reservasCli1 != null)? reservasCli1.size() : 0;
				Integer cantReservas2 = (reservasCli2 != null)? reservasCli2.size() : 0;
				
				return Integer.compare(cantReservas2, cantReservas1);
			}
			
		};
		listaClientes.sort(ClientesOrdenadosPorCantidadDeActividadesDesc);
		return listaClientes;
	}
}
