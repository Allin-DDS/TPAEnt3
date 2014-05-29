package utn.dss.TPA;


public interface Observador {
	public void notificarNuevaInscripcion(Jugador jugador, Partido partido);
	public void notificarReemplazoDeInscSinSustituto(Partido partido);
	public void notificarleCritica(Jugador jugador);
}
