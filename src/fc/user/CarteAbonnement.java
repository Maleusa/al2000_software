package fc.user;

import java.util.ArrayList;
import java.util.Date;

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
		this.historique=new Historique();
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
		al.louerFilm(film);//Garde à modifier (if)
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
//		Date date = new Date();
//		this.historique.ajouterLocation(date, film);
		al.modificationOnSubscriber(this);
	}

	@Override
	public boolean mustEndALocationFirst() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	protected void rendreFilmEndommage(Al2000 al, FilmPhysique film) throws RenduFilmException {
		verificationCompatibiliteRendu(film);
		
	}

	@Override
	protected void rendreFilmNonEndommage(Al2000 al, FilmPhysique film) throws RenduFilmException {
		verificationCompatibiliteRendu(film);
		
	}


}
