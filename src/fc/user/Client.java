package fc.user;

import java.util.HashMap;

import fc.Al2000;
import fc.movie.FilmLouable;

public class Client extends User {
	
	private HashMap<Integer, Abonnement> abonnement; //<id, abonnement>
	@Override
	public boolean checkIdentity(String login, String password) {
		return abonnement.get(0).checkIdentity(login, password);
	}
	
	public void louerFilm(Al2000 al, FilmLouable film) {
		abonnement.get(al.getAboActuel().getId()).louerFilm(al, film);
	}

}
