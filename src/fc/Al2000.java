package fc;

import java.util.ArrayList;


import fc.movie.Film;
import fc.movie.FilmLouable;
import fc.movie.FilmPhysique;
import fc.user.Abonnement;
import fc.user.CarteAbonnement;
import fc.user.Historique;
import fc.user.SuperUser;
import fc.user.User;

/**
 * Classe principale qui vas servir de "chef d'orchestre" pour l'ensemble des diffÃ©rentes
 * parties du projet 
 * @author yazid
 *
 */
public class Al2000 {
	private int id;
	private User currentUser;
	private Abonnement aboActuel;
	ArrayList<SuperUser> superUsersActuelle;
	private MemoryBuffer buffer;
	//Ne pas oublier de mettre une instance du database listener
	ArrayList<FilmPhysique> filmsPhysique;
	ArrayList<Film> filmsCharge;
	public Al2000(int id) {
		this.id=id;
		filmsPhysique=initStockFilm();
		filmsCharge=initFilmCharge();
		buffer=new MemoryBuffer();
	}
	
	public Al2000(int id, User u) {
		this(id);
		this.currentUser=u;
	}

	private ArrayList<Film> initFilmCharge() {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<FilmPhysique> initStockFilm() {
		// TODO Auto-generated method stub
		return null;
	}

	public void louerFilm(FilmLouable film) {
		film.louer(this);
	}
	
	public void louerFilmPhysique() {
		//Modifie la liste de filmPhysique avec loué ou non
		System.out.println("Sortie d'un bluray par la trappe");
	}
	
	public void louerFilmDemat() {
		System.out.println("Sortie d'un qr code");
	}

	public void rendreFilm(FilmLouable film) {
		film.rendre(this);
	}
	
	public void rendreFilmBonEtat(FilmLouable film) {
		
	}
	public void rendreFilmEndommage(FilmLouable film) {
		
	}
	
	public int arriveClient() {
		return 0;
	}
	
	public int creerAbonnement() {
		return 0;
	}
	public int supprimerAbonnement() {
		return 0;
		
	}
	
	public Historique retourneHistorique() {
		return new Historique();
	}
	
	public Statistique retourneStatistique() {
		return new Statistique();
	}
	
	public int verifierFilmEndomage() {
		return 0;
	}
	
	public int changementStockFilm() {
		return 0;
		
	}
	
	
	
	/**
	 * Getter et Setters en dessous de Ã§a 
	 * @return
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUserActuel() {
		return currentUser;
	}

	public void setUserActuel(User userActuel) {
		this.currentUser = userActuel;
	}

	public Abonnement getAboActuel() {
		return aboActuel;
	}

	public void setAboActuel(Abonnement aboActuel) {
		this.aboActuel = aboActuel;
	}

	public ArrayList<SuperUser> getSuperUsersActuelle() {
		return superUsersActuelle;
	}

	public void setSuperUsersActuelle(ArrayList<SuperUser> superUsersActuelle) {
		this.superUsersActuelle = superUsersActuelle;
	}

	public ArrayList<FilmPhysique> getFilmsPhysique() {
		return filmsPhysique;
	}

	public void setFilmsPhysique(ArrayList<FilmPhysique> filmsPhysique) {
		this.filmsPhysique = filmsPhysique;
	}

	public ArrayList<Film> getFilmsCharges() {
		return filmsCharge;
	}

	public void setFilmsCharges(ArrayList<Film> filmsCharges) {
		this.filmsCharge = filmsCharges;
	}
	
	public void setBuffer(MemoryBuffer buffer) {
		this.buffer=buffer;
	}
	/*
	 * Méthodes appelé lors d'interaction avec la machine Al2000
	 */
	
	public boolean checkUserIdentity() {
		//Not implemented
		currentUser.checkIdentity(null, null);
		return true;
	}
	
	public boolean deleteSubscriber(int id) {
		//Send an order to the buffer to delete the subscriber with id : id
		return buffer.deleteSubscriber(id);
	}
	
	public boolean modificationOnSubscriber(Abonnement s) {
		return buffer.modificationOnSubscriber(s);
	}
	
	public boolean logOut(User u) {
		return buffer.logOut();
	}
}
