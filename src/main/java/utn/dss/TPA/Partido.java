package utn.dss.TPA;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.PriorityQueue;

import excepciones.Hay10EstandarException;
import excepciones.NoHay10InscriptosParaGenerarEquiposException;

public class Partido {
	private LocalTime horario;
	private LocalDate fecha;
	private String lugar;
	private boolean decisionSobrePropuesta;
	private String motivoRechazo; 
	
	private Collection<Observador> observadores = new ArrayList<>();
	private PriorityQueue<Inscripcion> inscripciones=(new PriorityQueue<>(Comparator.comparing(inscripcion->inscripcion.getPrioridad())));
	private Collection<Inscripcion> equipo1 = new ArrayList<>();
	private Collection<Inscripcion> equipo2 = new ArrayList<>();
	private Collection<Inscripcion> inscripcionesPropuestas = new ArrayList<>();
	private HashMap<String,LocalDate> inscripcionesRechazadas= new HashMap<>();
	
	public Partido(LocalDate dia, LocalTime hora,String lugar) {
		this.fecha= dia;
		this.horario= hora;
		this.lugar=lugar;
	}	
	
	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	public boolean getDecisionSobrePropuesta() {
		return decisionSobrePropuesta;
	}

	public void setDecisionSobrePropuesta(boolean decisionSobrePropuesta) {
		this.decisionSobrePropuesta = decisionSobrePropuesta;
	}
	
	public Collection<Inscripcion> getEquipo1() {
		return equipo1;
	}

	public void agregarEquipo1(Inscripcion insc) {
		this.equipo1.add(insc);
	}

	public Collection<Inscripcion> getEquipo2() {
		return equipo2;
	}

	public void agregarEquipo2(Inscripcion insc) {
		this.equipo1.add(insc);
	}
	
	public Collection<Observador> getObservadores() {
		return observadores;
	}

	public void agregarObservador(Observador observador) {
		observadores.add(observador);
	}
	
	public Collection<Inscripcion> getInscripcionesPropuestas() {
		return inscripcionesPropuestas;
	}

	public void agregarInscripcionPropuesta(Inscripcion inscripcion) {
		inscripcionesPropuestas.add(inscripcion);
	}
	
	public HashMap<String, LocalDate> getInscripcionesRechazadas() {
		return inscripcionesRechazadas;
	}

	public void agregarInscripcionRechazada(String motivo, LocalDate fecha) {
		inscripcionesRechazadas.put(motivo,fecha);
	}
	
	public  PriorityQueue<Inscripcion> getInscripciones(){
		return inscripciones;
	}
	
	public int cantidadInscriptosEstandar() {
		return (int) inscripciones.stream().filter(inscripcion -> inscripcion instanceof InscripcionEstandar).count();
	}
	
	public int cantidadInscriptosCondicionales() {
		return (int) inscripciones.stream().filter(inscripcion -> inscripcion instanceof InscripcionCondicional).count();
	}
	
	public int cantidadInscriptosSolidarios() {
		return (int) inscripciones.stream().filter(inscripcion -> inscripcion instanceof InscripcionSolidaria).count();
	}
	
	public int cantidadTotalInscriptos(){
		return inscripciones.size();  
	}
	
	public void altaInscripcion(Inscripcion inscripcion) {
		if(cantidadInscriptosEstandar()<10){
			inscripciones.add(inscripcion);
			 for (Observador observador : observadores)  
				 observador.notificarNuevaInscripcion(inscripcion.jugador,this);
		}
		else
		{
			throw new Hay10EstandarException("No se puede realizar la inscripcion porque ya hay 10 inscriptos en modo estandar para el partido");
		}
		
	}
	
	public void BajaInscripcion(Inscripcion inscripcionBaja, Inscripcion inscripcionAlta){
		inscripciones.remove(inscripcionBaja);
		if(inscripcionAlta!= null)
			this.altaInscripcion(inscripcionAlta);
		else{
			inscripcionBaja.jugador.incrementarcantidadInfracPorNoTenerSustituto();
			for (Observador observador : observadores)  
				observador.notificarReemplazoDeInscSinSustituto(this);
		}
	}
	
	public void generarEquipos(){
		if(this.cantidadTotalInscriptos()>=10){
			for(int i=1; i<=5; i++)
				equipo1.add(inscripciones.poll());
			for(int i=1; i<=5; i++)
				equipo2.add(inscripciones.poll());
		}
			else
				throw new NoHay10InscriptosParaGenerarEquiposException("No se puede realizar generar ");			
	}
	
	public void procesarInscripcionesPropuestas(){
		for(Inscripcion inscripcion : inscripcionesPropuestas){
			if(decisionSobrePropuesta)
				this.altaInscripcion(inscripcion);
			else
				this.agregarInscripcionRechazada(motivoRechazo,LocalDate.now());
		}
	}


}

