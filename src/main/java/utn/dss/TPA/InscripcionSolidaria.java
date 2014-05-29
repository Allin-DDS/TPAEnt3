package utn.dss.TPA;

public class InscripcionSolidaria extends Inscripcion{
	private static int contadorDeInstacias=100;
	
	public InscripcionSolidaria(Jugador jugador){
		InscripcionSolidaria.setContadorDeInstacias(InscripcionSolidaria.getContadorDeInstacias() - 1);
		this.jugador=jugador;
		this.prioridad=getContadorDeInstacias();				
	}

	public static int getContadorDeInstacias() {
		return contadorDeInstacias;
	}

	public static void setContadorDeInstacias(int contadorDeInstacias) {
		InscripcionSolidaria.contadorDeInstacias = contadorDeInstacias;
	}
}



