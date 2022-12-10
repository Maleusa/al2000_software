package fc.user;

import java.util.HashMap;

import fc.Al2000;
import fc.movie.FilmLouable;

public class Client extends User {
	
	private HashMap<Integer, Abonnement> abonnements;
	private Historique historique;
	
	
	public void louerFilm(Al2000 al, FilmLouable film) {
	}
	
	public boolean payementCB(int cout) {
		return true;
	}

}
