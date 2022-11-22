package fc.user;

import java.util.ArrayList;

import fc.Al2000;
import fc.movie.*;

public class TechnicalUser extends User {
	
	public TechnicalUser(String login, String password) {
		this.login=login;
		this.password=password;
	}
	
	
	public boolean checkIdentity(String login, String password) {
		boolean id = this.login.equalsIgnoreCase(login);
		id = id && this.password.equals(password);
		return id;
	}
	
	public void dammageVerification(FilmPhysique film, DammageState trueState) throws TechnicalUseError {
		if(film.getState()!=DammageState.UNVERIFIED) throw new TechnicalUseError("The movie should'nt be verify");
		film.setState(trueState);
	}
	
	public void changementStockFilm(ArrayList<FilmPhysique> stock, ArrayList<FilmPhysique> filmsARetirer, ArrayList<FilmPhysique> nouveauFilms) {
		for(FilmPhysique f : filmsARetirer) stock.remove(f);
		for(FilmPhysique f : nouveauFilms) stock.add(f);
	}
	
	public boolean deleteSubscriber(Al2000 al2000, int idSubscriber) {
		return al2000.deleteSubscriber(idSubscriber);
	}
	
	public boolean setSoldeAbonnement(Al2000 al2000, CarteAbonnement a, int nouveauSolde) {
		a.setSolde(nouveauSolde);
		return al2000.modificationOnSubscriber(a);
	}
	
	
	public void setLogin(String login) {
		this.login=login;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}


	@Override
	public void louerFilm(Al2000 al, FilmLouable film) {
		//ask the user to enter an abonnement id
		CarteAbonnement s = new CarteAbonnement(0, "abo0", "password0");
		louerFilm(al, film, s);
	}
	
	public void louerFilm(Al2000 al, FilmLouable film, CarteAbonnement s) {
		//For now it's public but it gonna be private when we gonna have the IHM
		s.louerFilm(al, film);
		
	}
	
	
}
