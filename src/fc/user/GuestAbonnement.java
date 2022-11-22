package fc.user;

import java.util.ArrayList;

import fc.Al2000;
import fc.movie.FilmDemat;
import fc.movie.FilmLouable;
import fc.movie.FilmPhysique;
import fc.user.*;

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
		if(film instanceof FilmDemat) {
			Client c = (Client)al.getUserActuel();
			if(!c.payementCB(film.getPrix())) throw new RuntimeException("Pas d'argent sur CB");
		}
		locations[0] = film;
		al.louerFilm(film);
		al.modificationOnSubscriber(this);
	}
	@Override
	public boolean mustEndALocationFirst() {
		return locations[0]!=null;
	}
	@Override
	public void rendreFilmNonEndommage(Al2000 al, FilmPhysique film) throws RenduFilmException {
		verificationCompatibiliteRendu(film);
		locations[0]=null;
		al.rendreFilmBonEtat(film);
		al.modificationOnSubscriber(this);
		
	}
	@Override
	public void rendreFilmEndommage(Al2000 al, FilmPhysique film) throws RenduFilmException {
		verificationCompatibiliteRendu(film);
		locations[0]=null;
		al.rendreFilmEndommage(film);
		al.modificationOnSubscriber(this);
	}
	
	
	

}
