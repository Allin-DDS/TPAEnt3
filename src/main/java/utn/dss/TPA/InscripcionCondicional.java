package utn.dss.TPA;


public class InscripcionCondicional extends Inscripcion {
	private StubCondicion condicion;
	
	public InscripcionCondicional(Jugador jugador,StubCondicion condicion){
		this.jugador=jugador;
		this.condicion= condicion;
		this.prioridad=2;
	}
	
	public StubCondicion getCondicion() {
		return condicion;
	}

	public void setCondicion(StubCondicion condicion) {
		this.condicion = condicion;
	}
	
}

