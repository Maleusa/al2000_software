package test.fc.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import fc.Al2000;
import fc.movie.DammageState;
import fc.movie.Film;
import fc.movie.FilmLouable;
import fc.movie.FilmPhysique;
import fc.user.*;

class TechnicalUserTest {
	String login = "testLog";
	String password = "testLog";
	
	final TechnicalUser technical = new TechnicalUser(login,password);
	final Al2000 al = new Al2000(0);
	final CarteAbonnement c = new CarteAbonnement(0,"log","mdp");
	
	@Test
	void testCoupleLoginValid() {
		assertTrue(technical.checkIdentity(login, password));
		assertFalse(technical.checkIdentity(login, "wrongPassword"));
		assertFalse(technical.checkIdentity("wrongLogin", password));
		assertFalse(technical.checkIdentity("wrongLogin", "wrongPassword"));
		
	}
	@Test
	void testDammageVerification() {
		FilmPhysique film = new FilmPhysique();
		film.setState(DammageState.UNVERIFIED);
		try {
			technical.dammageVerification(film, DammageState.DAMAGE);
			assertTrue(film.getState()==DammageState.DAMAGE);
		} catch (TechnicalUseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		film.setState(DammageState.DAMAGE);
		try {
			technical.dammageVerification(film, DammageState.DAMAGE);
			fail("L'erreur n'a pas été throw");
		} catch (TechnicalUseError e) {
			assertTrue(true);
		}
		
	}
	
	@Test
	void testChangementStockFilm() {
		ArrayList<FilmPhysique> anciensFilm = new ArrayList<FilmPhysique>();
		for(int i=0; i<5; i++) {
			FilmPhysique f = new FilmPhysique("film : "+i);
			anciensFilm.add(f);
		}
		
		ArrayList<FilmPhysique> nouveauxFilms1 = new ArrayList<FilmPhysique>();
		for(int i=0; i<3; i++) {
			FilmPhysique f = new FilmPhysique("nouveau film : "+i);
			nouveauxFilms1.add(f);
		}
		
		ArrayList<FilmPhysique> filmsAprèsTest = new ArrayList<FilmPhysique>();
		ArrayList<FilmPhysique> filmARetirer = new ArrayList<FilmPhysique>();
		
		filmARetirer.add(anciensFilm.get(0));
		filmARetirer.add(anciensFilm.get(3));
		filmARetirer.add(anciensFilm.get(4));
		
		filmsAprèsTest.add(anciensFilm.get(1));
		filmsAprèsTest.add(anciensFilm.get(2));
		for(FilmPhysique f : nouveauxFilms1) filmsAprèsTest.add(f);
		
		//batterie de tests :
		for(int i=0; i<nouveauxFilms1.size(); i++) 
			for(int j=0; j<anciensFilm.size(); j++)
				assertFalse(nouveauxFilms1.get(i).equals(anciensFilm.get(j)));
		
		int nbFilmSimilaire =0;
		for(FilmPhysique f : anciensFilm)
			for(FilmPhysique f2 : filmsAprèsTest) 
				if(f.equals(f2)) nbFilmSimilaire ++;
		assertTrue(nbFilmSimilaire==2, ""+nbFilmSimilaire);
		
		nbFilmSimilaire  =0;
		for(FilmPhysique f : nouveauxFilms1)
			for(FilmPhysique f2 : filmsAprèsTest) 
				if(f.equals(f2)) nbFilmSimilaire ++;
		assertTrue(nbFilmSimilaire==3, ""+nbFilmSimilaire);
		
		technical.changementStockFilm(anciensFilm, filmARetirer, nouveauxFilms1);
		
		nbFilmSimilaire =0;
		for(FilmPhysique f : anciensFilm)
			for(FilmPhysique f2 : filmsAprèsTest) 
				if(f.equals(f2)) nbFilmSimilaire ++;
		assertTrue(nbFilmSimilaire==5, "Nb films Similaires : "+nbFilmSimilaire);
		
	}
	
	@Test
	void testDeletingPropagation() {
		//True if the suppression has been made by DAO
		//Maybe too dependant on DAO
		assertTrue(technical.deleteSubscriber(al, 11));
	}
	
	@Test
	void testModificationSoldeAbonnement() {
		c.setSolde(0);
		boolean retour = technical.setSoldeAbonnement(al, c, 20);
		assertTrue(retour);
		assertTrue(c.getSolde()==20);
		
	}
	
	@Test
	void testLouerFilmPourUnAutreAbo() {
		FilmPhysique film1 = new FilmPhysique("Transformers 4");
		FilmPhysique film2 = new FilmPhysique("GI JOE retaliation");
		for(FilmLouable f : c.getLocations())
			assertTrue(f==null);
		
		c.getLocations()[0]=film2;
		assertTrue(c.getLocations()[0].equals(film2));
		for(FilmLouable f : c.getLocations()) 
			if(!film2.equals(f))
			assertTrue(f==null);
		
		technical.louerFilm(al, film1, c);
		assertTrue(c.getLocations()[1].equals(film1));
		
		fail("Location chez carteAbonnement ne passe pas encore le test");
		
	}
	
	@Test
	void testLogOut() {
		assertTrue(technical.logOut(al));
	}
}
