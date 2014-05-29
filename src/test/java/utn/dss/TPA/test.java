package utn.dss.TPA;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test; 
import java.time.LocalDate;
import java.time.LocalTime;
import excepciones.Hay10EstandarException;
import excepciones.NoHay10InscriptosParaGenerarEquiposException;

public class test {
	private Partido semifinal;
	private Jugador juan;
	private InscripcionEstandar inscripcionJuan;
	private Jugador esteban;
	private InscripcionEstandar inscripcionEsteban;
	private Jugador ramiro;
	private InscripcionEstandar inscripcionramiro;
	private Jugador mario;
	private InscripcionEstandar inscripcionmario;
	private Jugador adrian;
	private InscripcionEstandar inscripcionadrian;
	private Jugador marcos;
	private InscripcionEstandar inscripcionmarcos;
	private Jugador carlos;
	private InscripcionEstandar inscripcioncarlos;
	private Jugador turco;
	private	InscripcionEstandar inscripcionturco;
	private Jugador coqui;
	private	InscripcionEstandar inscripcioncoqui;
	private Jugador mati;
	private	InscripcionEstandar inscripcionmati;
	
	private Jugador jose;
	private InscripcionCondicional inscripcionJose;
	private StubCondicion condicionJose;
	private Jugador franco;
	private InscripcionCondicional inscripcionfranco;
	private StubCondicion condicionfranco;
	private Jugador dani;
	private InscripcionCondicional inscripciondani;
	private StubCondicion condiciondani;
	
	private Jugador maria;
	private InscripcionSolidaria inscripcionMaria;
	private Jugador gordo;
	private InscripcionSolidaria inscripciongordo;
	
	private MailAAdministrador mailAAdministradorMock;
	private MailAAmigos mailAAmigosMock;
	private MailAOtroJugador mailAOtroJugadorMock;
	
	@Before
	public void init(){
		LocalDate hoy=LocalDate.now();
		LocalTime hora=LocalTime.of(22,00);
		semifinal= new Partido(hoy,hora,"calleFalsa1234");
		
		juan= new Jugador();
		juan.setEdad(21);
		inscripcionJuan= new InscripcionEstandar(juan);
		esteban= new Jugador();
		esteban.setEdad(21);
		inscripcionEsteban= new InscripcionEstandar(esteban);
		ramiro= new Jugador();
		ramiro.setEdad(21);
		inscripcionramiro= new InscripcionEstandar(ramiro);
		mario= new Jugador();
		mario.setEdad(21);
		inscripcionmario= new InscripcionEstandar(mario);
		adrian= new Jugador();
		adrian.setEdad(21);
		inscripcionadrian= new InscripcionEstandar(adrian);
		marcos= new Jugador();
		marcos.setEdad(21);
		inscripcionmarcos= new InscripcionEstandar(marcos);
		carlos= new Jugador();
		carlos.setEdad(21);
		inscripcioncarlos= new InscripcionEstandar(carlos);
		turco= new Jugador();
		turco.setEdad(21);
		inscripcionturco= new InscripcionEstandar(turco);
		coqui= new Jugador();
		coqui.setEdad(21);
		inscripcioncoqui= new InscripcionEstandar(coqui);
		mati= new Jugador();
		mati.setEdad(21);
		inscripcionmati= new InscripcionEstandar(mati);
		
		jose= new Jugador();
		jose.setEdad(22);
		condicionJose = new StubCondicion();
		inscripcionJose= new InscripcionCondicional(jose,condicionJose);
		franco= new Jugador();
		franco.setEdad(22);
		condicionfranco = new StubCondicion();
		inscripcionfranco= new InscripcionCondicional(franco,condicionfranco);
		dani= new Jugador();
		dani.setEdad(22);
		condiciondani = new StubCondicion();
		inscripciondani= new InscripcionCondicional(jose,condiciondani);
	
		maria= new Jugador();
		maria.setEdad(22);
		inscripcionMaria= new InscripcionSolidaria(maria);
		gordo= new Jugador();
		gordo.setEdad(22);
		inscripciongordo= new InscripcionSolidaria(gordo);
		
		mailAAdministradorMock= mock(MailAAdministrador.class);
		mailAAmigosMock= mock(MailAAmigos.class);
		mailAOtroJugadorMock= mock(MailAOtroJugador.class);
	
	}
	
	@Test 
	public void verificarPrioridadesDeLas2InscripcionesSolidariosCreadas(){
		
		assertEquals(99,inscripcionMaria.getPrioridad());
		assertEquals(98,inscripciongordo.getPrioridad());	
	}
	
	@Test 
	public void agregar1Condicional_ContarLosCondicionales(){
		semifinal.altaInscripcion(inscripcionJose);
		assertEquals(1,semifinal.cantidadInscriptosCondicionales());
	}
	
	@Test 
	public void agregar2Estandar_ContarLosEstandar(){
		semifinal.altaInscripcion(inscripcionJuan);
		semifinal.altaInscripcion(inscripcionEsteban);
		assertEquals(2,semifinal.cantidadInscriptosEstandar());
	}
	
	@Test 
	public void agregar2Solidarios_ContarLosSolidarios(){
		semifinal.altaInscripcion(inscripciongordo);
		semifinal.altaInscripcion(inscripcionMaria);
		assertEquals(2,semifinal.cantidadInscriptosSolidarios());
	}
	
	
	@Test 
	public void agregar2Estandar2SolidariaY1Condicional_cantidadTotalInscriptos(){
		semifinal.altaInscripcion(inscripcionJuan);
		semifinal.altaInscripcion(inscripcionEsteban);
		
		semifinal.altaInscripcion(inscripcionJose);
		
		semifinal.altaInscripcion(inscripcionMaria);
		semifinal.altaInscripcion(inscripciongordo);
		
		assertEquals(5,semifinal.cantidadTotalInscriptos());
	}
	
	@Test
	public void agregar2Solidarios1Estandar1Condicional_VerificarOrdenCorrecto(){
		semifinal.altaInscripcion(inscripcionMaria);
		semifinal.altaInscripcion(inscripciongordo);
		semifinal.altaInscripcion(inscripcionJuan);
		semifinal.altaInscripcion(inscripcionJose);
		
		assertEquals(inscripcionJuan,semifinal.getInscripciones().poll());
		assertEquals(inscripcionJose,semifinal.getInscripciones().poll());
		assertEquals(inscripciongordo,semifinal.getInscripciones().poll());
		assertEquals(inscripcionMaria,semifinal.getInscripciones().poll());		
	}



	@Test
	public void agregar5Estandar3Condicional2Solidario_generarEquipos(){
		semifinal.altaInscripcion(inscripcionJuan);
		semifinal.altaInscripcion(inscripcionEsteban);
		semifinal.altaInscripcion(inscripcionramiro);
		semifinal.altaInscripcion(inscripcionmario);
		semifinal.altaInscripcion(inscripcionadrian);
	
		semifinal.altaInscripcion(inscripciondani);
		semifinal.altaInscripcion(inscripcionfranco);
		semifinal.altaInscripcion(inscripcionJose);
	
		semifinal.altaInscripcion(inscripcionMaria);
		semifinal.altaInscripcion(inscripciongordo);
		semifinal.generarEquipos();
	
		assertTrue(semifinal.getEquipo1().contains(inscripcionJuan));
		assertTrue(semifinal.getEquipo1().contains(inscripcionEsteban));
		assertTrue(semifinal.getEquipo1().contains(inscripcionramiro));
		assertTrue(semifinal.getEquipo1().contains(inscripcionmario));
		assertTrue(semifinal.getEquipo1().contains(inscripcionadrian));
		assertTrue(semifinal.getEquipo2().contains(inscripciondani));
		assertTrue(semifinal.getEquipo2().contains(inscripcionfranco));
		assertTrue(semifinal.getEquipo2().contains(inscripcionMaria));
		assertTrue(semifinal.getEquipo2().contains(inscripcionJose));
		assertTrue(semifinal.getEquipo2().contains(inscripciongordo));
}


	@Test(expected=NoHay10InscriptosParaGenerarEquiposException.class)
	public void agregar5Estandar3CondicionalYGenerarEquipos_SeGeneraExcepcionPqNoHay10Inscriptos(){
		semifinal.altaInscripcion(inscripcionJuan);
		semifinal.altaInscripcion(inscripcionEsteban);
		semifinal.altaInscripcion(inscripcionramiro);
		semifinal.altaInscripcion(inscripcionmario);
		semifinal.altaInscripcion(inscripcionadrian);
	
		semifinal.altaInscripcion(inscripciondani);
		semifinal.altaInscripcion(inscripcionfranco);
		semifinal.altaInscripcion(inscripcionJose);
		
		semifinal.generarEquipos();
}
	
	@Test (expected=Hay10EstandarException.class)
	public void agregar10EstandarY1Solidaria_Hay10EstandarException(){
		semifinal.altaInscripcion(inscripcionJuan);
		semifinal.altaInscripcion(inscripcionEsteban);
		semifinal.altaInscripcion(inscripcionramiro);
		semifinal.altaInscripcion(inscripcionmario);
		semifinal.altaInscripcion(inscripcionadrian);
		semifinal.altaInscripcion(inscripcionmarcos);
		semifinal.altaInscripcion(inscripcioncarlos);
		semifinal.altaInscripcion(inscripcionturco);
		semifinal.altaInscripcion(inscripcioncoqui);
		semifinal.altaInscripcion(inscripcionmati);
		
		semifinal.altaInscripcion(inscripcionMaria);
	}
	
	@Test
	public void reemplazo1SolidarioSinSustituto_AgregarInfraccion(){
		semifinal.altaInscripcion(inscripcionMaria);
		semifinal.BajaInscripcion(inscripcionMaria,null);
		
		assertEquals(1,maria.getCantidadInfracPorNoTenerSustituto());	
	}
	
	@Test
	public void agregarInscripcion_AvisarAAdmin(){
		semifinal.altaInscripcion(inscripcionJuan);
		mailAAdministradorMock.notificarNuevaInscripcion(juan,semifinal);
		verify(mailAAdministradorMock).notificarNuevaInscripcion(juan,semifinal);
	}
	
	@Test
	public void agregarInscripcion_AvisarAAMigos(){
		semifinal.altaInscripcion(inscripcionJuan);
		mailAAmigosMock.notificarNuevaInscripcion(juan,semifinal);
		verify(mailAAmigosMock).notificarNuevaInscripcion(juan,semifinal);
	}
	
	@Test
	public void reemplazarInscripcionSinSusitucion_AvisarAAdmin(){
		semifinal.altaInscripcion(inscripcionJuan);
		semifinal.BajaInscripcion(inscripcionJuan, null);
		mailAAdministradorMock.notificarReemplazoDeInscSinSustituto(semifinal);
		verify(mailAAdministradorMock).notificarReemplazoDeInscSinSustituto(semifinal);
	}
	
	@Test
	public void reemplazarInscripcionSinSusitucion_AvisarAAmigos(){
		semifinal.altaInscripcion(inscripcionJuan);
		semifinal.BajaInscripcion(inscripcionJuan, null);
		mailAAmigosMock.notificarReemplazoDeInscSinSustituto(semifinal);
		verify(mailAAmigosMock).notificarReemplazoDeInscSinSustituto(semifinal);
	}
	
	@Test
	public void calificarJugadores(){
		
		semifinal.altaInscripcion(inscripcionJuan);
		semifinal.altaInscripcion(inscripcionEsteban);
		semifinal.altaInscripcion(inscripcionramiro);
		semifinal.altaInscripcion(inscripcionmario);
		semifinal.altaInscripcion(inscripcionadrian);
		semifinal.altaInscripcion(inscripcionmarcos);
		semifinal.altaInscripcion(inscripcioncarlos);
		semifinal.altaInscripcion(inscripcionturco);
		semifinal.altaInscripcion(inscripcioncoqui);
		semifinal.altaInscripcion(inscripcionmati);
		
		
		semifinal.generarEquipos();
		
		inscripcionJuan.jugador.calificarA(inscripcionadrian.jugador,9);
		
		mailAOtroJugadorMock.notificarleCritica(inscripcionadrian.jugador);
		
		assertEquals(9,inscripcionadrian.jugador.calificacion());
		verify(mailAOtroJugadorMock).notificarleCritica(inscripcionadrian.jugador);

		
	}
}
