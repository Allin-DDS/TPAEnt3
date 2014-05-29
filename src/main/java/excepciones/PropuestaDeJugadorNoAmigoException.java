package excepciones;


public class PropuestaDeJugadorNoAmigoException extends RuntimeException {
	public PropuestaDeJugadorNoAmigoException(String mensaje){
		super(mensaje);
	}
}