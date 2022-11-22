package fc.user;

import java.util.ArrayList;

import fc.Al2000;
import fc.Constants;
import fc.movie.FilmDemat;
import fc.movie.FilmLouable;
import fc.movie.FilmPhysique;

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
		if(v<=0) throw new RuntimeException("Le cr�dit � d�poser est null ou n�gatif");
		addToSolde(v);
	}
	
	public void preleverCompte(int v) throws PasSuffisamentArgentCompteAboException {
		if(v<=0) throw new RuntimeException("Le prelevement est null ou n�gatif");
		if(this.solde-v<0) throw new PasSuffisamentArgentCompteAboException("L'exception sera gerer au niveau sup�rieur : au lieu de retire de l'argent � abo, on pr�l�ve sur la cb.");
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
		if(film instanceof FilmDemat) {
			int prix = film.getPrix()-Constants.REDUCTIONABONNEMENT;
			try {
				preleverCompte(prix);
			}catch(PasSuffisamentArgentCompteAboException e){
				Client c = (Client)al.getUserActuel();
				if(!c.payementCB(prix)) throw new RuntimeException("Pas d'argent sur CB");	
			}
		}
		for(int i=0; i<locations.length; i++) {
			if(locations[i]==null) {
				locations[i] = film;
				break;
			}
		}
		al.louerFilm(film);
		al.modificationOnSubscriber(this);
	}

	@Override
	public boolean mustEndALocationFirst() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void rendreFilmEndommage(Al2000 al, FilmPhysique film) throws RenduFilmException {
		verificationCompatibiliteRendu(film);
		
	}

	@Override
	public void rendreFilmNonEndommage(Al2000 al, FilmPhysique film) throws RenduFilmException {
		verificationCompatibiliteRendu(film);
		
	}

}
