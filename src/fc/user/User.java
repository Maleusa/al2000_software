package fc.user;

import fc.Al2000;
import fc.movie.FilmLouable;

public abstract class User {
	protected String login, password;
	public abstract boolean checkIdentity(String login, String password);
	public abstract void louerFilm(Al2000 al, FilmLouable film);
	
	public boolean logOut(Al2000 al) {
		return al.logOut(this);
	}
}
