package utn.dss.TPA;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import excepciones.FueraDeLimitesException;
import excepciones.PropuestaDeJugadorNoAmigoException;

public class Jugador {
	private int edad;
	private int cantidadInfracPorFaltar;
	private int cantidadInfracPorNoTenerSustituto;
	private Collection<Jugador> amigos = new ArrayList<Jugador>();
	private int calificacion;
	
	public Collection<Jugador> mostrar(){
		return amigos;
	}

	public void agregar(Jugador jugador){
		amigos.add(jugador);	
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public void incrementarcantidadInfracPorNoTenerSustituto(){
		cantidadInfracPorNoTenerSustituto++;
	}
	
	public int getCantidadInfracPorNoTenerSustituto(){
		return cantidadInfracPorNoTenerSustituto;
	}
	
	public void proponer(Inscripcion inscripcion,Partido partido){
		if(amigos.contains(inscripcion.getJugador())){
			partido.agregarInscripcionPropuesta(inscripcion);
		}
		else{
			throw new PropuestaDeJugadorNoAmigoException("no se puede proponer a un jugador que no es amigo");
		}
	}

	public void calificarA(Jugador jugador, int i) {

		if(i<0 || i>10){
			throw new FueraDeLimitesException("La calificación ingresada no es correcta");
		}
		
		jugador.calificacion = jugador.calificacion + i;
		
	}

	public int calificacion() {
		return this.calificacion;
	}


	
}
