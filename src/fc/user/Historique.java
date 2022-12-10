package fc.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import fc.movie.FilmLouable;


public class Historique {
	Map<Date, FilmLouable> historique;
	
	public Historique() {
		historique = new HashMap<Date, FilmLouable>();
	}
	
	public FilmLouable getFilm(Date date) {
		return historique.get(date);
	}
	
	public void ajouterLocation(Date date, FilmLouable film) {
		historique.put(date, film);
		historique.
	}

}
