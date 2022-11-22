package fc.user;

import java.util.HashMap;

import fc.Al2000;
import fc.movie.FilmLouable;

public class Client extends User {
	
	private HashMap<Integer, Abonnement> abonnement; //<id, abonnement>
	public int moneyInTheBank=0; //Voué à disparaitre une fois les tests validé
	
	@Override
	public boolean checkIdentity(String login, String password) {
		return abonnement.get(0).checkIdentity(login, password);
	}
	
	public void louerFilm(Al2000 al, FilmLouable film) {
		abonnement.get(al.getAboActuel().getId()).louerFilm(al, film);
	}
	
	public boolean payementCB(int cout) {
		//Pour l'instant le payement CB est toujours valide #UnlimitedMoney
		moneyInTheBank-=cout;
		return true;
	}

}
