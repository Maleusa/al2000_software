package fc.user;

import java.util.ArrayList;

import fc.Al2000;
import fc.movie.FilmLouable;

public class GuestAbonnement extends Abonnement {
	
	public GuestAbonnement(int id) {
		this.id = id;
		this.locations = new FilmLouable[1];
	}
	@Override
	public boolean checkIdentity(String login, String password) {
		return true;
	}

	@Override
	public void louerFilm(Al2000 al, FilmLouable film) {
		// TODO Auto-generated method stub
		locations[0] = film;
		al.modificationOnSubscriber(this);
	}
	@Override
	public boolean mustEndALocationFirst() {
		return locations[0]!=null;
	}
	@Override
	public void rendreFilm(Al2000 al, FilmLouable film) throws RenduFilmException {
		if(locations[0]==null || !locations[0].equals(film)) throw new RenduFilmException("Pas de correspondance de rendu");
		locations[0]=null;
		al.modificationOnSubscriber(this);
		
	}
	

}
