package utn.dss.TPA;

import java.util.ArrayList;
import java.util.Collection;

public class Fixture {	
	Collection<Partido> partidos = new ArrayList<Partido>();

	public Collection<Partido> mostrar(){
		return partidos;
	}

	public void agregar(Partido partido){
		partidos.add(partido);	
	}
}


