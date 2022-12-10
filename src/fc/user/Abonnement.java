package fc.user;

import java.lang.reflect.Array;
import java.util.ArrayList;

import fc.Al2000;
import fc.movie.FilmLouable;
import fc.movie.FilmPhysique;

public abstract class Abonnement {
	protected int id;
	protected FilmLouable[] locations;
	protected Historique historique;
	
	public abstract boolean checkIdentity(String login, String password);
	
	public abstract boolean mustEndALocationFirst();

	//L'utilisateur a toujours un emplacement de location libre lors de l'appel
	//de louerFilm car mustEndALocationFirst() a été vérifier au préalable
	public abstract void louerFilm(Al2000 al, FilmLouable film);
	
	
	public void rendreFilm(Al2000 al, FilmPhysique film, boolean endommage) throws RenduFilmException{
		if(endommage)
			rendreFilmEndommage(al, film);
		else
			rendreFilmNonEndommage(al, film);
	};
	protected abstract void rendreFilmEndommage(Al2000 al, FilmPhysique film) throws RenduFilmException;
	protected abstract void rendreFilmNonEndommage(Al2000 al, FilmPhysique film) throws RenduFilmException;
	
	public int getId() {
		return id;
	}
	
	public FilmLouable[] getLocations(){
		return locations;
	}
	
	protected void verificationCompatibiliteRendu(FilmLouable film) throws RenduFilmException {
		boolean in = false;
		for(FilmLouable filmLoue : locations) if(film.equals(filmLoue)) in = true;
		if(!in) throw new RenduFilmException("Pas de correspondance de rendu");
	}
}
