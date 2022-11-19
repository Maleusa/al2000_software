package fc.user;

import java.util.ArrayList;

import fc.Al2000;
import fc.movie.FilmLouable;

public class CarteAbonnement extends Abonnement {
	private int solde=0;
	private String login;
	private String password;
	
	public CarteAbonnement(int id, String login, String password) {
		this.id = id;
		this.login=login;
		this.password=password;
		this.locations=new FilmLouable[3];
	}
	
	@Override
	public boolean checkIdentity(String login, String password) {
		if(this.login==null || this.password==null) throw new NullPointerException();
		return this.login.equalsIgnoreCase(login) && this.password.equals(password);
	}
	
	public void crediterCompte(int v) {
		if(v<=0) throw new RuntimeException("Le crédit à déposer est null ou négatif");
		addToSolde(v);
	}
	
	public void preleverCompte(int v) throws PasSuffisamentArgentCompteAboException {
		if(v<=0) throw new RuntimeException("Le prelevement est null ou négatif");
		if(this.solde-v<0) throw new PasSuffisamentArgentCompteAboException("L'exception sera gerer au niveau supérieur : au lieu de retire de l'argent à abo, on prélève sur la cb.");
		addToSolde(-v);
	}
	
	public void addToSolde(int v) {
		//Will became private
		solde+=v;
	}
	
	public void setSolde(int nouveauSolde) {
		solde=nouveauSolde;
	}
	
	public int getSolde() {
		return solde;
	}

	@Override
	public void louerFilm(Al2000 al, FilmLouable film) {
		// TODO Auto-generated method stub
		//BLAbla je loue un film
		
		
	}

	@Override
	public boolean mustEndALocationFirst() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void rendreFilm(Al2000 al, FilmLouable film) throws RenduFilmException {
		// TODO Auto-generated method stub
		
	}

}
