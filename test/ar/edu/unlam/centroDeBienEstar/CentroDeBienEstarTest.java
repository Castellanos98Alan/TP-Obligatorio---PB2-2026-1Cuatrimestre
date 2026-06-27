package ar.edu.unlam.centroDeBienEstar;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pbii.ClaseIndividualException;
import ar.edu.unlam.pbii.ClaseIndividualOcupadaException;
import ar.edu.unlam.pbii.ClaseRepetidaEnHorario;
import ar.edu.unlam.pbii.ClienteDuplicadoException;
import ar.edu.unlam.pbii.CupoYaNoDisponibleException;

import ar.edu.unlam.pbii.Descuento;
import ar.edu.unlam.pbii.ReservaDuplicadaException;


public class CentroDeBienEstarTest {
	
	CentroDeBienEstar centro = new CentroDeBienEstar();
	
	
	@Before
	public void inicializacion() {
		centro = new CentroDeBienEstar();
	}

	@Test
	public void queSePuedaRegistrarUnClienteEnElCentroDeBienestar() throws ClienteDuplicadoException {
		String nombre = "Alan";
		String apellido = "Castellanos";
		CentroDeBienEstar centro = new CentroDeBienEstar();

		Cliente cliente1 = new Cliente(42005584, nombre, apellido);

		Cliente cliente2 = new Cliente(42005584, "juan", "Perez");

		assertTrue(centro.registrarCliente(cliente1));

	}

	@Test(expected = ClienteDuplicadoException.class)
	public void queNoSePuedaAgregarDosVecesElMismoCliente() throws ClienteDuplicadoException {

		String nombre = "Alan";
		String apellido = "Castellanos";
		CentroDeBienEstar centro = new CentroDeBienEstar();

		Cliente cliente1 = new Cliente(42005584, nombre, apellido);

		Cliente cliente2 = new Cliente(42005584, "juan", "Perez");

		centro.registrarCliente(cliente1);
		centro.registrarCliente(cliente2);

	}

	@Test
	public void queSePuedaRegistrarUnProfesionalEnElCentro() {
		CentroDeBienEstar centro = new CentroDeBienEstar();
		Persona profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez", "Lic. educacion Fisica");

		assertTrue(centro.registrarProfesional((Profesional) profesional1));

	}

	@Test
	public void queNoSePuedaRegistrarDosProfesionalesConLaMismaMatricula() {
		CentroDeBienEstar centro = new CentroDeBienEstar();
		Persona profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez", "Lic. educacion Fisica");
		Persona profesional2 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez", "Lic. educacion Fisica");

		assertTrue(centro.registrarProfesional((Profesional) profesional1));
		assertFalse(centro.registrarProfesional((Profesional) profesional2));

	}

	@Test
	public void queSePuedaAgregarUnaClaseGrupalAlSistema() {

		CentroDeBienEstar centro = new CentroDeBienEstar();

		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");

		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0),
				2.0, TIPODECLASE.YOGA);

		try {
			assertTrue(centro.registrarClaseGrupal(clase1));
		} catch (ClaseRepetidaEnHorario e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void queSePuedaAgregarUnaClaseIndividualAlSistema() throws ClaseIndividualException {
		CentroDeBienEstar centro = new CentroDeBienEstar();

		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez", "Masajista");

		Clase clase1 = new ClaseIndividual(profesional1, 1.0, LocalDate.of(2026, 6, 15), LocalTime.of(13, 0),
				TIPODECLASE.MASAJES);

		assertTrue(centro.registrarClaseIndividual((ClaseIndividual) clase1));
	}

	@Test(expected = ClaseIndividualException.class)
	public void queSePuedaAgregarUnaClaseIndividualAlSistemaYLaMismaNoEsDeTipoMasajes()
			throws ClaseIndividualException {
		CentroDeBienEstar centro = new CentroDeBienEstar();

		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");

		Clase clase1 = new ClaseIndividual(profesional1, 1.0, LocalDate.of(2026, 6, 15), LocalTime.of(13, 0),
				TIPODECLASE.YOGA);

		assertTrue(centro.registrarClaseIndividual((ClaseIndividual) clase1));
	}

	@Test
	public void queUnProfesionalPuedaDictarVariasClases() throws ClaseRepetidaEnHorario {
		CentroDeBienEstar centro = new CentroDeBienEstar();

		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");

		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 2.0,
				TIPODECLASE.YOGA);

		ClaseGrupal clase2 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(14, 0), 2.0,
				TIPODECLASE.YOGA);
		ClaseGrupal clase3 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(15, 0), 2.0,
				TIPODECLASE.YOGA);

		assertTrue(centro.registrarClaseGrupal(clase1));
		assertTrue(centro.registrarClaseGrupal(clase2));
		assertTrue(centro.registrarClaseGrupal(clase3));

	}

	@Test(expected = ClaseRepetidaEnHorario.class)
	public void queUnProfesionalPuedaDictarVariasClasesSiUnaClaseRepiteHorarioTirarExceptionPorClaseRepetidaEnHorario()
			throws ClaseRepetidaEnHorario {
		CentroDeBienEstar centro = new CentroDeBienEstar();

		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");

		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0),
				2.0, TIPODECLASE.YOGA);

		ClaseGrupal clase2 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0),
				2.0, TIPODECLASE.YOGA);

		assertTrue(centro.registrarClaseGrupal(clase1));
		assertTrue(centro.registrarClaseGrupal(clase2));

	}

	@Test
	public void queSeObtengaUnaListaDeTodasLasClasesQueBrindaUnProfesional() throws ClaseRepetidaEnHorario {
		CentroDeBienEstar centro = new CentroDeBienEstar();

		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");
		Profesional profesional2 = new Profesional("JLP-2810", 16966658, "Susana", "Fernandez",
				"Lic. educacion Fisica");
		
		centro.registrarProfesional(profesional2);
		centro.registrarProfesional(profesional1);
		

		ClaseGrupal clase1 = new ClaseGrupal(profesional2, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0),
				2.0, TIPODECLASE.YOGA);

		ClaseGrupal clase2 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(14, 0),
				2.0, TIPODECLASE.YOGA);
		
		ClaseGrupal clase3 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(15, 0),
				2.0, TIPODECLASE.YOGA);
		
		centro.registrarClaseGrupal(clase2);
		centro.registrarClaseGrupal(clase1);
		centro.registrarClaseGrupal(clase3);
		
		ArrayList<Clase> clasesQueRealizaUnProfesional = centro.obtenerTodasLasClasesQueRealizaCiertoProfesional(profesional2);
		
		
		assertEquals(1, clasesQueRealizaUnProfesional.size());
		
	}

	@Test

	public void queSePuedaReservarUnaClaseConCuposDisponibles() throws CupoYaNoDisponibleException, ReservaDuplicadaException, ClaseIndividualOcupadaException {

		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");
		
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0),
				2.0, TIPODECLASE.YOGA);
		
		Cliente cliente1 = new Cliente(16555125, "Pablo", "Fernandez");
		
		
		Reserva reserva1 = new Reserva(cliente1, clase1);
		
		centro.registrarReserva(reserva1);
		
		assertTrue(clase1.hayLugar());
		
		
		
	}
	


	
	//
	
	@Test(expected = CupoYaNoDisponibleException.class)
	public void queNoSePuedaReservarUnaClaseCuandoNoHayCuposDisponibles() throws CupoYaNoDisponibleException, ReservaDuplicadaException, ClaseIndividualOcupadaException {
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 1.0,
				TIPODECLASE.SPINNING);
		
		Cliente cliente1 = new Cliente(16555125, "Pablo", "Fernandez");
		Cliente cliente2 = new Cliente(16555126, "Pablo", "Fernandez");
		Cliente cliente3 = new Cliente(16555127, "Pablo", "Fernandez");
		Cliente cliente4 = new Cliente(16555128, "Pablo", "Fernandez");
		
		Reserva reserva1 = new Reserva(cliente1, clase1);
		Reserva reserva2 = new Reserva(cliente2, clase1);
		Reserva reserva3 = new Reserva(cliente3, clase1);
		Reserva reserva4 = new Reserva(cliente4, clase1);
		
		centro.registrarReserva(reserva1);
	    centro.registrarReserva(reserva2);
	    centro.registrarReserva(reserva3);
	    centro.registrarReserva(reserva4);
		
	}
	
	@Test
	public void queAlReservarUnaClaseSeActualiceLaCantidadDeCuposDisponibles() throws CupoYaNoDisponibleException, ReservaDuplicadaException, ClaseIndividualOcupadaException {
		
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 1.0,
				TIPODECLASE.SPINNING);
		
		Cliente cliente1 = new Cliente(16555125, "Pablo", "Fernandez");
		Cliente cliente2 = new Cliente(16555126, "Pablo", "Fernandez");
		
		Reserva reserva1 = new Reserva(cliente1, clase1);
		Reserva reserva2 = new Reserva(cliente2, clase1);
		centro.registrarReserva(reserva1);
	    centro.registrarReserva(reserva2);
	    
	    assertEquals(1, clase1.getCuposDisponiblesRestantes().intValue());
		

	}
	
	@Test(expected = ReservaDuplicadaException.class)
	public void queUnClienteNoPuedaReservarDosVecesLaMismaClase() throws CupoYaNoDisponibleException, ReservaDuplicadaException, ClaseIndividualOcupadaException {
		
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 1.0,
				TIPODECLASE.SPINNING);
		
		Cliente cliente1 = new Cliente(16555125, "Pablo", "Fernandez");
		Reserva reserva1 = new Reserva(cliente1, clase1);
		Reserva reserva2 = new Reserva(cliente1, clase1);
		centro.registrarReserva(reserva1);
	    centro.registrarReserva(reserva2);
	    
		

	}
	
	@Test
	public void queSePuedaReservarUnaSesionDeMasajeDisponible() throws CupoYaNoDisponibleException, ReservaDuplicadaException,ClaseIndividualOcupadaException {
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez", "Masajista");
		ClaseIndividual clase1 = new ClaseIndividual(profesional1,1.0, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0),
				TIPODECLASE.MASAJES);
		
		Cliente cliente1 = new Cliente(16555125, "Pablo", "Fernandez");
		Reserva reserva1 = new Reserva(cliente1, clase1);
		centro.registrarReserva(reserva1);

	}

	@Test(expected = ClaseIndividualOcupadaException.class)
	public void queNoSePuedaReservarUnaSesionIndividualYaReservada() throws CupoYaNoDisponibleException, ReservaDuplicadaException, ClaseIndividualOcupadaException {
		
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez", "Masajista");
		ClaseIndividual clase1 = new ClaseIndividual(profesional1,1.0, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0),
				TIPODECLASE.MASAJES);
		
		Cliente cliente1 = new Cliente(16555125, "Pablo", "Fernandez");
		Cliente cliente2 = new Cliente(16555126, "Pablo", "Fernandez");
		Reserva reserva1 = new Reserva(cliente1, clase1);
		Reserva reserva2 = new Reserva(cliente2, clase1);
		centro.registrarReserva(reserva1);
		centro.registrarReserva(reserva2);
		

	}

	@Test
	public void queSeCalculeCorrectamenteElCostoDeUnaClaseDeYoga() {
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 1.0,
				TIPODECLASE.YOGA);

		Double duracion = 1.0;
		Double precio = 500.0;
		Double precioEsperado = (duracion * precio);
		assertEquals(precioEsperado, clase1.calcularPrecio());
	}


	
	
	
	
	
	}
	

	
	


