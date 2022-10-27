package fc;

import java.util.ArrayList;

import fc.movie.Film;
import fc.movie.FilmPhysique;
import fc.user.Abonnement;
import fc.user.Historique;
import fc.user.SuperUser;
import fc.user.User;

/**
 * Classe principale qui vas servir de "chef d'orchestre" pour l'ensemble des différentes
 * parties du projet 
 * @author yazid
 *
 */
public class Al2000 {
	private int id;
	private User userActuel;
	private Abonnement aboActuel;
	ArrayList<SuperUser> superUsersActuelle;
	//Ne pas oublier de mettre une instance du database listener
	ArrayList<FilmPhysique> filmsPhysique;
	ArrayList<Film> filmsChargés;

	public int rendreFilm() {
		return 0;
	}
	
	public int louerFilm() {
		return 0;
	}
	
	public int rendreFilmEndommage() {
		return 0;
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
	 * Getter et Setters en dessous de ça 
	 * @return
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUserActuel() {
		return userActuel;
	}

	public void setUserActuel(User userActuel) {
		this.userActuel = userActuel;
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

	public ArrayList<Film> getFilmsChargés() {
		return filmsChargés;
	}

	public void setFilmsChargés(ArrayList<Film> filmsChargés) {
		this.filmsChargés = filmsChargés;
	}

}
