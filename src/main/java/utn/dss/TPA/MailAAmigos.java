package utn.dss.TPA;



public class MailAAmigos implements Observador{

	public void notificarReemplazoDeInscSinSustituto(Partido partido) {
		// TODO Auto-generated method stub
		// método vacío
	}
	
	public void notificarNuevaInscripcion(Jugador emisor, Partido partido) {
		// TODO Auto-generated method stub
		//Aca avisara a los amigos del jugador inscripto 
	}

	@Override
	public void notificarleCritica(Jugador jugador) {
		// TODO Auto-generated method stub
		//método vacío
	}
}
