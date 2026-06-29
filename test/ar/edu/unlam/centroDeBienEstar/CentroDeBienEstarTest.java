package ar.edu.unlam.centroDeBienEstar;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pbii.ClaseIndividualException;
import ar.edu.unlam.pbii.ClaseIndividualOcupadaException;
import ar.edu.unlam.pbii.ClaseInexistenteException;
import ar.edu.unlam.pbii.ClaseRepetidaEnHorario;
import ar.edu.unlam.pbii.ClienteDuplicadoException;
import ar.edu.unlam.pbii.ClienteInexistenteException;
import ar.edu.unlam.pbii.ClienteSinReservasException;
import ar.edu.unlam.pbii.CostoInvalidoException;
import ar.edu.unlam.pbii.CupoYaNoDisponibleException;
import ar.edu.unlam.pbii.Descuento;
import ar.edu.unlam.pbii.DuracionInvalidaException;
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
	public void queSePuedaAgregarUnaClaseGrupalAlSistema() throws CostoInvalidoException {

		CentroDeBienEstar centro = new CentroDeBienEstar();

		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");

		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 2.0,
				TIPODECLASE.YOGA);

		try {
			assertTrue(centro.registrarClaseGrupal(clase1));
		} catch (ClaseRepetidaEnHorario e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void queSePuedaAgregarUnaClaseIndividualAlSistema() throws ClaseIndividualException, CostoInvalidoException, DuracionInvalidaException {
		CentroDeBienEstar centro = new CentroDeBienEstar();

		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez", "Masajista");

		Clase clase1 = new ClaseIndividual(profesional1, 1.0, LocalDate.of(2026, 6, 15), LocalTime.of(13, 0),
				TIPODECLASE.MASAJES);

		assertTrue(centro.registrarClaseIndividual((ClaseIndividual) clase1));
	}

	@Test(expected = ClaseIndividualException.class)
	public void queSePuedaAgregarUnaClaseIndividualAlSistemaYLaMismaNoEsDeTipoMasajes()
			throws ClaseIndividualException, CostoInvalidoException, DuracionInvalidaException {
		CentroDeBienEstar centro = new CentroDeBienEstar();

		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");

		Clase clase1 = new ClaseIndividual(profesional1, 1.0, LocalDate.of(2026, 6, 15), LocalTime.of(13, 0),
				TIPODECLASE.YOGA);

		assertTrue(centro.registrarClaseIndividual((ClaseIndividual) clase1));
	}

	@Test
	public void queUnProfesionalPuedaDictarVariasClases() throws ClaseRepetidaEnHorario, CostoInvalidoException {
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
			throws ClaseRepetidaEnHorario, CostoInvalidoException {
		CentroDeBienEstar centro = new CentroDeBienEstar();

		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");

		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 2.0,
				TIPODECLASE.YOGA);

		ClaseGrupal clase2 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 2.0,
				TIPODECLASE.YOGA);

		assertTrue(centro.registrarClaseGrupal(clase1));
		assertTrue(centro.registrarClaseGrupal(clase2));

	}

	@Test
	public void queSeObtengaUnaListaDeTodasLasClasesQueBrindaUnProfesional() throws ClaseRepetidaEnHorario, CostoInvalidoException {
		CentroDeBienEstar centro = new CentroDeBienEstar();

		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");
		Profesional profesional2 = new Profesional("JLP-2810", 16966658, "Susana", "Fernandez",
				"Lic. educacion Fisica");

		centro.registrarProfesional(profesional2);
		centro.registrarProfesional(profesional1);

		ClaseGrupal clase1 = new ClaseGrupal(profesional2, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 2.0,
				TIPODECLASE.YOGA);

		ClaseGrupal clase2 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(14, 0), 2.0,
				TIPODECLASE.YOGA);

		ClaseGrupal clase3 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(15, 0), 2.0,
				TIPODECLASE.YOGA);

		centro.registrarClaseGrupal(clase2);
		centro.registrarClaseGrupal(clase1);
		centro.registrarClaseGrupal(clase3);

		ArrayList<Clase> clasesQueRealizaUnProfesional = centro
				.obtenerTodasLasClasesQueRealizaCiertoProfesional(profesional2);

		assertEquals(1, clasesQueRealizaUnProfesional.size());

	}



	public void queSePuedaReservarUnaClaseConCuposDisponibles() throws CupoYaNoDisponibleException, ReservaDuplicadaException, ClaseIndividualOcupadaException, ClienteInexistenteException, ClienteDuplicadoException, ClaseInexistenteException {
		CentroDeBienEstar centro = new CentroDeBienEstar();
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");

		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 2.0,
				TIPODECLASE.YOGA);

		Cliente cliente1 = new Cliente(16555125, "Pablo", "Fernandez");

		Reserva reserva1 = new Reserva(cliente1, clase1);
		centro.registrarCliente(cliente1);
		centro.registrarReserva(reserva1);
		assertTrue(clase1.hayLugar());
		
		
		
	}


	
	
	@Test(expected = CupoYaNoDisponibleException.class)
	public void queNoSePuedaReservarUnaClaseCuandoNoHayCuposDisponibles() throws CupoYaNoDisponibleException, ReservaDuplicadaException, ClaseIndividualOcupadaException, ClienteInexistenteException, ClienteDuplicadoException, ClaseInexistenteException, ClaseRepetidaEnHorario, CostoInvalidoException {
		CentroDeBienEstar centro = new CentroDeBienEstar();
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
		
		centro.registrarCliente(cliente1);
		centro.registrarCliente(cliente2);
		centro.registrarCliente(cliente3);
		centro.registrarCliente(cliente4);
		
		centro.registrarClaseGrupal(clase1);
		
		centro.registrarReserva(reserva1);
	    centro.registrarReserva(reserva2);
	    centro.registrarReserva(reserva3);
	    centro.registrarReserva(reserva4);
		
	}
	
	@Test
	public void queAlReservarUnaClaseSeActualiceLaCantidadDeCuposDisponibles() throws CupoYaNoDisponibleException, ReservaDuplicadaException, ClaseIndividualOcupadaException, ClienteInexistenteException, ClienteDuplicadoException, ClaseInexistenteException, ClaseRepetidaEnHorario, CostoInvalidoException {
		CentroDeBienEstar centro = new CentroDeBienEstar();
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 1.0,
				TIPODECLASE.SPINNING);
		
		Cliente cliente1 = new Cliente(16555125, "Pablo", "Fernandez");
		Cliente cliente2 = new Cliente(16555126, "Pablo", "Fernandez");
		
		Reserva reserva1 = new Reserva(cliente1, clase1);
		Reserva reserva2 = new Reserva(cliente2, clase1);
		centro.registrarCliente(cliente1);
		centro.registrarCliente(cliente2);
		centro.registrarClaseGrupal(clase1);
		centro.registrarReserva(reserva1);
	    centro.registrarReserva(reserva2);
	    
	    assertEquals(1, clase1.getCuposDisponiblesRestantes().intValue());
		

	}
	
	@Test(expected = ReservaDuplicadaException.class)
	public void queUnClienteNoPuedaReservarDosVecesLaMismaClase() throws CupoYaNoDisponibleException, ReservaDuplicadaException, ClaseIndividualOcupadaException, ClienteInexistenteException, ClienteDuplicadoException, ClaseInexistenteException, ClaseRepetidaEnHorario, CostoInvalidoException {
		CentroDeBienEstar centro = new CentroDeBienEstar();
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 1.0,
				TIPODECLASE.SPINNING);
		
		Cliente cliente1 = new Cliente(16555125, "Pablo", "Fernandez");
		Reserva reserva1 = new Reserva(cliente1, clase1);
		Reserva reserva2 = new Reserva(cliente1, clase1);
		centro.registrarCliente(cliente1);
		centro.registrarClaseGrupal(clase1);
		centro.registrarReserva(reserva1);
	    centro.registrarReserva(reserva2);
	}
	
	@Test
	public void queSePuedaReservarUnaSesionDeMasajeDisponible() throws CupoYaNoDisponibleException, ReservaDuplicadaException,ClaseIndividualOcupadaException, ClienteInexistenteException, ClienteDuplicadoException, ClaseInexistenteException, ClaseIndividualException, CostoInvalidoException, DuracionInvalidaException {
		CentroDeBienEstar centro = new CentroDeBienEstar();
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez", "Masajista");
		ClaseIndividual clase1 = new ClaseIndividual(profesional1,1.0, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0),
				TIPODECLASE.MASAJES);
		
		Cliente cliente1 = new Cliente(16555125, "Pablo", "Fernandez");
		Reserva reserva1 = new Reserva(cliente1, clase1);
		centro.registrarCliente(cliente1);
		centro.registrarClaseIndividual(clase1);
		centro.registrarReserva(reserva1);

	}

	@Test(expected = ClaseIndividualOcupadaException.class)
	public void queNoSePuedaReservarUnaSesionIndividualYaReservada() throws CupoYaNoDisponibleException, ReservaDuplicadaException, ClaseIndividualOcupadaException, ClienteInexistenteException, ClienteDuplicadoException, ClaseInexistenteException, ClaseIndividualException, CostoInvalidoException, DuracionInvalidaException {
		CentroDeBienEstar centro = new CentroDeBienEstar();
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez", "Masajista");
		ClaseIndividual clase1 = new ClaseIndividual(profesional1,1.0, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0),
				TIPODECLASE.MASAJES);
		
		Cliente cliente1 = new Cliente(16555125, "Pablo", "Fernandez");
		Cliente cliente2 = new Cliente(16555126, "Pablo", "Fernandez");
		Reserva reserva1 = new Reserva(cliente1, clase1);
		Reserva reserva2 = new Reserva(cliente2, clase1);
		centro.registrarCliente(cliente1);
		centro.registrarCliente(cliente2);
		centro.registrarClaseIndividual(clase1);
		centro.registrarReserva(reserva1);
		centro.registrarReserva(reserva2);
		

	}
	
	@Test
	public void queSeObtenganLasReservasDeUnCliente() throws ClienteSinReservasException, CupoYaNoDisponibleException, ReservaDuplicadaException, ClaseIndividualOcupadaException, ClienteInexistenteException, ClienteDuplicadoException, ClaseInexistenteException, ClaseRepetidaEnHorario, CostoInvalidoException{
		CentroDeBienEstar centro = new CentroDeBienEstar();
		
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 1.0,
				TIPODECLASE.YOGA);
		ClaseGrupal clase2 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(14, 0), 1.0,
				TIPODECLASE.SPINNING);
		
		Cliente cliente1 = new Cliente(16555125, "Pablo", "Fernandez");
		Reserva reserva1 = new Reserva(cliente1, clase1);
		Reserva reserva2 = new Reserva(cliente1, clase2);
		centro.registrarCliente(cliente1);
		centro.registrarClaseGrupal(clase1);
		centro.registrarClaseGrupal(clase2);
		centro.registrarReserva(reserva1);
		centro.registrarReserva(reserva2);
		HashSet<Reserva> reservas = centro.obtenerReservasDeUnCliente(cliente1);

	    assertEquals(2, reservas.size());
	    assertTrue(reservas.contains(reserva1));
	    assertTrue(reservas.contains(reserva2));
	}
	
	@Test(expected = ClienteSinReservasException.class)
	public void queElClienteNoTengaReservas() throws ClienteSinReservasException, CupoYaNoDisponibleException, ReservaDuplicadaException, ClaseIndividualOcupadaException{
		Cliente cliente1 = new Cliente(16555125, "Pablo", "Fernandez");
		centro.obtenerReservasDeUnCliente(cliente1);

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


	@Test
	public void queSeCalculeCorrectamenteElCostoDeUnaClaseDeSpinning() {

		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 1.0,
				TIPODECLASE.SPINNING);

		Double duracion = 1.0;
		Double precio = 5000.0;
		Double equipamiento = 1800.0;
		Double precioEsperado = (duracion * precio) + equipamiento;

		assertEquals(precioEsperado, clase1.calcularPrecio());
	}

	@Test
	public void queSeCalculeCorrectamenteElCostoDeUnaClaseDeFuncional() {
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 1.0,
				TIPODECLASE.FUNCIONAL);

		Double duracion = 1.0;
		Double precio = 1000.0;
		Double equipamiento = 1800.0;
		Double precioEsperado = (duracion * precio) + equipamiento;

		assertEquals(precioEsperado, clase1.calcularPrecio());
	}

	@Test
	public void queSeCalculeCorrectamenteElCostoDeUnMasajePorMinutos() {
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez", "Masajista");
		ClaseIndividual clase1 = new ClaseIndividual(profesional1, 1.0, LocalDate.of(2026, 6, 15), LocalTime.of(13, 0),
				TIPODECLASE.MASAJES);

		Double duracion = 1.0;
		Double precio = 10000.0;
		Double equipamiento = 2800.0;
		Double precioEsperado = (duracion * precio) + equipamiento;

		assertEquals(precioEsperado, clase1.calcularPrecio());
	}

	@Test
	public void queSeApliqueElPrimerBloqueDeDescuentoAlCompletarTresActividades() {
		DescuentoPorBloques descuento = new DescuentoPorBloques();

		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 2.0,
				TIPODECLASE.YOGA);

		Integer cantidadClases = 3;
		Double precioClase = clase1.calcularPrecio();
		Double esperado = 800.0;

		Double resultado = descuento.aplicarDescuento(precioClase, cantidadClases);

		assertEquals(esperado, resultado);
	}

	@Test
	public void queSeApliqueElSegundoBloqueDeDescuentoAlCompletarSeisActividades() {
		DescuentoPorBloques descuento = new DescuentoPorBloques();

		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 2.0,
				TIPODECLASE.YOGA);

		Integer cantidadClases = 6;
		Double precioClase = clase1.calcularPrecio();
		Double esperado = 700.0;

		Double resultado = descuento.aplicarDescuento(precioClase, cantidadClases);

		assertEquals(esperado, resultado);
	}

	@Test
	public void queSeApliqueElTercerBloqueDeDescuentoAlCompletarNueveActividades() {
		DescuentoPorBloques descuento = new DescuentoPorBloques();

		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 2.0,
				TIPODECLASE.YOGA);

		Integer cantidadClases = 9;
		Double precioClase = clase1.calcularPrecio();
		Double esperado = 500.0;

		Double resultado = descuento.aplicarDescuento(precioClase, cantidadClases);

		assertEquals(esperado, resultado);
	}

	@Test
	public void queLaInterfaceTieneDescuentoSeaImplementadaPorDescuentoPorBloques() {

	}

	@Test
	public void queSePuedaCalcularElMontoFinalDeUnClienteAplicandoDescuentos() {
		DescuentoPorBloques descuento = new DescuentoPorBloques();

		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 2.0,
				TIPODECLASE.YOGA);
		ClaseGrupal clase2 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(15, 0), 2.0,
				TIPODECLASE.YOGA);
		ClaseGrupal clase3 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(17, 0), 2.0,
				TIPODECLASE.YOGA);

		Integer cantidadClases = 3;
		// cada clase 500, pero al ser 2 horas 1000 c/u
		Double totalClases = clase1.calcularPrecio() + clase2.calcularPrecio() + clase3.calcularPrecio(); // 3000.0
		Double esperado = 2400.0;

		Double resultado = descuento.aplicarDescuento(totalClases, cantidadClases);

		assertEquals(esperado, resultado);
	}
	
	
	
	@Test
	public void queSePuedanOrdenarLosClientesPorApellido() throws ClienteDuplicadoException {
		CentroDeBienEstar centro = new CentroDeBienEstar();
		Cliente cliente1 = new Cliente(77755125, "Pablo", "Fernandez");
		Cliente cliente2 = new Cliente(16555234, "Juan", "González");
		Cliente cliente3 = new Cliente(16555456, "Mateo", "Martínez");
		
		centro.registrarCliente(cliente1);
		centro.registrarCliente(cliente2);
		centro.registrarCliente(cliente3);
		
		ArrayList<Cliente> clientesOrdenadosObtenida = centro.obtenerListaDeClientesOrdenadasPorApellido();
		Cliente primerClienteEsperado = clientesOrdenadosObtenida.get(0);
		Cliente ultimoClienteEsperado = clientesOrdenadosObtenida.get(2);
		assertEquals(primerClienteEsperado, cliente1);
		assertEquals(ultimoClienteEsperado, cliente3);
	}
	
	@Test
	public void queSePuedanOrdenarLasClasesPorFecha() throws ClaseRepetidaEnHorario, CostoInvalidoException {
		CentroDeBienEstar centro = new CentroDeBienEstar();
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez",
				"Lic. educacion Fisica");
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 24), LocalTime.of(12, 0), 2.0,
				TIPODECLASE.YOGA);
		ClaseGrupal clase2 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(13, 0), 2.0,
				TIPODECLASE.YOGA);
		ClaseGrupal clase3 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 26), LocalTime.of(14, 0), 2.0,
				TIPODECLASE.YOGA);
		centro.registrarClaseGrupal(clase1);
		centro.registrarClaseGrupal(clase2);
		centro.registrarClaseGrupal(clase3);
		
		ArrayList<Clase> ClasesOrdenadasObtenida = centro.obtenerListaDeClasesOrdenadasPorFecha();
		
		Clase primeraClaseEsperada = ClasesOrdenadasObtenida.get(0);
		Clase ultimaClaseEsperada = ClasesOrdenadasObtenida.get(2);
		assertEquals(primeraClaseEsperada, clase1);
		assertEquals(ultimaClaseEsperada, clase3);
	}
	
	@Test
	public void queSePuedaObtenerUnClientePorDni() throws ClienteDuplicadoException {
		CentroDeBienEstar centro = new CentroDeBienEstar();
		Cliente cliente1 = new Cliente(77755125, "Pablo", "Fernandez");
		centro.registrarCliente(cliente1);
		
		Cliente clienteObtenido = centro.obtenerClientePorDNI(77755125);
		assertEquals(cliente1,clienteObtenido);
	}
	
	@Test
	public void queSePuedanListarLasClasesOrdenadasPorCostoDeMenorAMayor() throws ClaseRepetidaEnHorario, ClaseIndividualException, CostoInvalidoException, DuracionInvalidaException {
		CentroDeBienEstar centro = new CentroDeBienEstar();
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez","Lic. educacion Fisica");
		Profesional profesional2 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez", "Masajista");		
				
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 24), LocalTime.of(12, 0), 2.0,
				TIPODECLASE.YOGA); // 1000 $
		ClaseIndividual clase2 = new ClaseIndividual(profesional2, 1.0, LocalDate.of(2026, 6, 15), LocalTime.of(13, 0),
				TIPODECLASE.MASAJES); // 12800 $
		ClaseGrupal clase3 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(14, 0), 1.0,
				TIPODECLASE.SPINNING); // 6800 $
		
		centro.registrarClaseGrupal(clase1);
		centro.registrarClaseIndividual(clase2);
		centro.registrarClaseGrupal(clase3);
		
		ArrayList<Clase> ClasesOrdenadasObtenida = centro.obtenerListaDeClasesOrdenadaPorCostosAsc();
		
		Clase primeraClaseEsperada = ClasesOrdenadasObtenida.get(0);
		Clase ultimaClaseEsperada = ClasesOrdenadasObtenida.get(2);
		Double precioEsperado1 = 1000.0;
		Double precioEsperado2 = 12800.0;
		assertEquals(primeraClaseEsperada.calcularPrecio(), precioEsperado1);
		assertEquals(ultimaClaseEsperada.calcularPrecio(), precioEsperado2);
	}
	
	@Test
	public void queSePuedanListarLosClientesPorCantidadDeActividadesRealizadasDeMayorAMenor() throws ClaseRepetidaEnHorario, ClaseIndividualException, ClienteDuplicadoException, CupoYaNoDisponibleException, ReservaDuplicadaException, ClaseIndividualOcupadaException, ClienteInexistenteException, ClaseInexistenteException, CostoInvalidoException, DuracionInvalidaException {
		CentroDeBienEstar centro = new CentroDeBienEstar();
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez","Lic. educacion Fisica");
		Profesional profesional2 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez", "Masajista");		
				
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 24), LocalTime.of(12, 0), 2.0,
				TIPODECLASE.YOGA); // 1000 $
		ClaseIndividual clase2 = new ClaseIndividual(profesional2, 1.0, LocalDate.of(2026, 6, 15), LocalTime.of(13, 0),
				TIPODECLASE.MASAJES); // 12800 $
		ClaseGrupal clase3 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 25), LocalTime.of(14, 0), 1.0,
				TIPODECLASE.SPINNING); // 6800 $
		centro.registrarClaseGrupal(clase1);
		centro.registrarClaseIndividual(clase2);
		centro.registrarClaseGrupal(clase3);
		
		Cliente cliente1 = new Cliente(77755125, "Pablo", "Fernandez");
		Cliente cliente2 = new Cliente(16555234, "Juan", "González");
		Cliente cliente3 = new Cliente(16555456, "Mateo", "Martínez");
		centro.registrarCliente(cliente1);
		centro.registrarCliente(cliente2);
		centro.registrarCliente(cliente3);
		
		Reserva reserva1 = new Reserva(cliente1, clase1);
		Reserva reserva2 = new Reserva(cliente1, clase2);
		Reserva reserva3 = new Reserva(cliente1, clase3);
		Reserva reserva4 = new Reserva(cliente2, clase1);
		Reserva reserva5 = new Reserva(cliente2, clase3);
		Reserva reserva6 = new Reserva(cliente3, clase1);
		
		centro.registrarReserva(reserva1);
		centro.registrarReserva(reserva2);
		centro.registrarReserva(reserva3);
		centro.registrarReserva(reserva4);
		centro.registrarReserva(reserva5);
		centro.registrarReserva(reserva6);
		
		ArrayList<Cliente> clientesObtenidos = centro.obtenerListaDeClientesOrdenadoPorCantidadDeActividadesDesc();
		
		Cliente primerClienteEsperado = clientesObtenidos.get(0);
		Cliente ultimoClienteEsperado = clientesObtenidos.get(2);
		assertEquals(primerClienteEsperado, cliente1);
		assertEquals(ultimoClienteEsperado, cliente3);
	}
	
	@Test(expected = ClienteInexistenteException.class)
	public void queNoSePuedaReservarUnaClaseParaUnClienteInexistente() throws ClaseRepetidaEnHorario, ClienteInexistenteException, CupoYaNoDisponibleException, ReservaDuplicadaException, ClaseIndividualOcupadaException, ClaseInexistenteException, CostoInvalidoException {
		CentroDeBienEstar centro = new CentroDeBienEstar();
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez","Lic. educacion Fisica");
		
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 24), LocalTime.of(12, 0), 2.0,
				TIPODECLASE.YOGA);
		centro.registrarProfesional(profesional1);
		centro.registrarClaseGrupal(clase1);
		
		Cliente cliente1 = new Cliente(77755125, "Pablo", "Fernandez");
		//centro.registrarCliente(cliente1);
		
		Reserva reserva = new Reserva(cliente1, clase1);
		centro.registrarReserva(reserva);
	}
	
	@Test(expected = ClaseInexistenteException.class)
	public void queNoSePuedaReservarUnaClaseQueNoExiste() throws ClienteDuplicadoException, CupoYaNoDisponibleException, ReservaDuplicadaException, ClaseIndividualOcupadaException, ClienteInexistenteException, ClaseInexistenteException {
		CentroDeBienEstar centro = new CentroDeBienEstar();
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez","Lic. educacion Fisica");
		
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 24), LocalTime.of(12, 0), 2.0,
				TIPODECLASE.YOGA);
		centro.registrarProfesional(profesional1);
		// centro.registrarClaseGrupal(clase1);
		
		Cliente cliente1 = new Cliente(77755125, "Pablo", "Fernandez");
		centro.registrarCliente(cliente1);
		
		Reserva reserva = new Reserva(cliente1, clase1);
		centro.registrarReserva(reserva);
	}
	
	@Test(expected = CostoInvalidoException.class)
	public void queNoSePuedaCrearUnaClaseConCostoNegativo() throws ClaseRepetidaEnHorario, CostoInvalidoException, ClaseIndividualException {
		CentroDeBienEstar centro = new CentroDeBienEstar();
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez","Lic. educacion Fisica");
		Profesional profesional2 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez", "Masajista");
		
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 24), LocalTime.of(12, 0), -200.0,
				TIPODECLASE.YOGA);
		
		centro.registrarProfesional(profesional1);
		centro.registrarProfesional(profesional2);
		centro.registrarClaseGrupal(clase1);
	}
	
	@Test(expected = DuracionInvalidaException.class)
	public void queNoSePuedaCrearUnMasajeConDuracionNegativa() throws ClaseIndividualException, CostoInvalidoException, DuracionInvalidaException {
		CentroDeBienEstar centro = new CentroDeBienEstar();
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez", "Masajista");
		ClaseIndividual clase1 = new ClaseIndividual(profesional1, -1.0, LocalDate.of(2026, 6, 15), LocalTime.of(13, 0),
				TIPODECLASE.MASAJES); 
		
		centro.registrarProfesional(profesional1);
		centro.registrarClaseIndividual(clase1);
	}
	
	@Test(expected = ReservaDuplicadaException.class)
	public void queNoSePuedaAgregarDosVecesLaMismaReserva() throws ClienteDuplicadoException, ClaseRepetidaEnHorario, CostoInvalidoException, CupoYaNoDisponibleException, ReservaDuplicadaException, ClaseIndividualOcupadaException, ClienteInexistenteException, ClaseInexistenteException, ReservaDuplicadaException {
		CentroDeBienEstar centro = new CentroDeBienEstar();
		Profesional profesional1 = new Profesional("JLP-2805", 16966458, "Susana", "Fernandez","Lic. educacion Fisica");
		
		ClaseGrupal clase1 = new ClaseGrupal(profesional1, LocalDate.of(2026, 3, 24), LocalTime.of(12, 0), 2.0,
				TIPODECLASE.YOGA);
		centro.registrarProfesional(profesional1);
		centro.registrarClaseGrupal(clase1);
		
		Cliente cliente1 = new Cliente(77755125, "Pablo", "Fernandez");
		centro.registrarCliente(cliente1);
		
		Reserva reserva = new Reserva(cliente1, clase1);
		centro.registrarReserva(reserva);
		centro.registrarReserva(reserva);
	}
	
	
	
}
