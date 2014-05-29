package utn.dss.TPA;


public class MailAAdministrador implements Observador {
	public void notificarReemplazoDeInscSinSustituto(Partido partido) {
		// TODO Auto-generated method stub
		//si dejaron de ser 10 (partido.cantidadtotalInscriptos()==9), aca se avisará que el partido esta confiramdo al admin
	}
	
	public void notificarNuevaInscripcion(Jugador emisor, Partido partido) {
		// TODO Auto-generated method stub
		//si llegaron a 10 (partido.cantidadtotalInscriptos()==10), aca se avisará que el partido esta confiramdo al admin
	}

	@Override
	public void notificarleCritica(Jugador jugador) {
		// TODO Auto-generated method stub
		//no hace nada
	}
}
