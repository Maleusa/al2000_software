package fc.user;

import java.lang.reflect.Array;
import java.util.ArrayList;

import fc.Al2000;
import fc.movie.FilmLouable;

public abstract class Abonnement {
	protected int id;
	protected FilmLouable[] locations;
	
	public abstract boolean checkIdentity(String login, String password);
	public abstract void louerFilm(Al2000 al, FilmLouable film);
	public abstract void rendreFilm(Al2000 al, FilmLouable film) throws RenduFilmException;
	
	public int getId() {
		return id;
	}
	
	public FilmLouable[] getLocations(){
		return locations;
	}
	
	public abstract boolean mustEndALocationFirst();
	
}
