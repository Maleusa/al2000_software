package test.fc.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fc.Al2000;
import fc.movie.FilmLouable;
import fc.movie.FilmPhysique;
import fc.user.GuestAbonnement;
import fc.user.RenduFilmException;

class GuestAbonnementTest {
	final GuestAbonnement guest = new GuestAbonnement(0);
	final Al2000 al = new Al2000(0);
	@Test
	void testLoginAlwaysTrue() {
		assertTrue(guest.checkIdentity(null, null));
		assertTrue(guest.checkIdentity("WrongLog", null));
		assertTrue(guest.checkIdentity(null,"WrongPassword"));
		assertTrue(guest.checkIdentity("WrongLog", "WrongPassword"));
	}
	
	@Test
	void testLocation() {
		FilmPhysique f = new FilmPhysique("Bad boys");
		assertTrue(guest.getLocations()[0]==null);
		guest.louerFilm(al, f);
		assertTrue(guest.getLocations()[0]!=null);
		assertTrue(guest.getLocations()[0].equals(f));
		guest.getLocations()[0]=null;
	}
	
	@Test
	void testRendu() {
		FilmPhysique f = new FilmPhysique("Bad boys");
		FilmPhysique f2 = new FilmPhysique("bad boys 2");
		assertTrue(guest.getLocations()[0]==null);
		try {
			guest.rendreFilm(al, f);
			fail("Guest ne doit pas pouvoir rendre le film puisque sa location est null");
		} catch (RenduFilmException e) {
			//Suite du test uniquement si il n'a pas fail
			assertTrue(guest.getLocations()[0]==null);
			guest.getLocations()[0]=f;
			try {
				guest.rendreFilm(al, f);
				assertTrue(guest.getLocations()[0]==null);
			} catch (RenduFilmException e1) {
				// TODO Auto-generated catch block
				fail("Ne devrait pas renvoyer d'exception car on rend f avec comme location le film f");
			}
			guest.getLocations()[0]=f;
			try {
				guest.rendreFilm(al, f2);
				fail("On rend le mauvais film : f2 au lieu de f");
			} catch (RenduFilmException e1) {
				assertTrue(guest.getLocations()[0].equals(f));
			}
		}
		
	}
	
	@Test
	void testConnexionAvecLocationOuNon() {
		FilmPhysique f = new FilmPhysique("Bad boys");
		for(FilmLouable fLouer : guest.getLocations())
			assertTrue(fLouer==null);
		assertFalse(guest.mustEndALocationFirst());
		guest.getLocations()[0]=f;
		assertTrue(guest.mustEndALocationFirst());
		guest.getLocations()[0]=null;
		//Uniquement si le test Louer et Rendre sont juste
		guest.louerFilm(al, f);
		assertTrue(guest.mustEndALocationFirst());
		try {
			guest.rendreFilm(al, f);
		} catch (RenduFilmException e) {
			fail("Il ne devrait pas y avoir d'erreur soulever");
		}
		assertFalse(guest.mustEndALocationFirst());
	}
	

}
