package test.fc.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fc.Al2000;
import fc.Constants;
import fc.movie.FilmDemat;
import fc.movie.FilmLouable;
import fc.movie.FilmPhysique;
import fc.user.Client;
import fc.user.GuestAbonnement;
import fc.user.RenduFilmException;
/*
 * @author Hugo APELOIG
 * 
 * Classe JUnit de test pour les GuestAbonnement.
 * La classe GuestAbonnement est une sous classe d'abonnement qui est plus restrictif que CarteAbonnement.
 * Pour plus d'informations sur CarteAbonnement : voir package : fc.user.
 * 
 * Cette classe de tests réalise plusieurs tests unitaires sur CarteAbonnement.
 * 
 * Les commentaires au sein des méthodes de tests suivent le dérouler du test, si un test fail à la moitié, les 
 * commentaires en dessous de résultats ne sont pas valides tant que le test n'est pas un succès. On peut aussi 
 * se référer aux commentaires des fail() pour comprendre le test.
 */
class GuestAbonnementTest {
	/*
	 * On initialise toutes les variables nécessaires pour nos tests:
	 * 	-Un client (qui en réalité est la personne connecté en guest)
	 * 	-Al2000 : la machine avec laquelle le client/abonné intéragit
	 * 	-L'abonnement guest : qui a uniquement un ID (le login et le password sont toujours guest/guest)
	 */
	final GuestAbonnement guest = new GuestAbonnement(0);
	final Client client = new Client();
	final Al2000 al = new Al2000(0,client);
	
	/*
	 * La méthode testLoginAlwaysTrue s'assure que le test de login soit toujours correcte.
	 */
	@Test
	void testLoginAlwaysTrue() {
		assertTrue(guest.checkIdentity(null, null));
		assertTrue(guest.checkIdentity("WrongLog", null));
		assertTrue(guest.checkIdentity(null,"WrongPassword"));
		assertTrue(guest.checkIdentity("WrongLog", "WrongPassword"));
	}
	
	@Test
	void testLocation() {
		//La location ne nécessite pas d'argent c'est le rendu pour les films physiques
		//Payment instantané pour film demat
				
		try {
			
			FilmPhysique f = new FilmPhysique("Bad boys");
			assertTrue(guest.getLocations()[0]==null);
			guest.louerFilm(al, f);
			assertTrue(guest.getLocations()[0]!=null);
			assertTrue(guest.getLocations()[0].equals(f));
			guest.getLocations()[0]=null;
			

			int argent = client.moneyInTheBank;
			
			FilmDemat fd = new FilmDemat("Bad boys 2");
			assertTrue(guest.getLocations()[0]==null);
			guest.louerFilm(al, fd);
			assertTrue(guest.getLocations()[0]!=null);
			assertTrue(guest.getLocations()[0].equals(fd));
			
			//Comme filmDemat et non abo on test le compte en banque
			assertTrue(client.moneyInTheBank==(argent-Constants.PRIXDEMAT),"Money = "+client.moneyInTheBank+" devrait être :"+(argent-Constants.PRIXDEMAT));
			
		}catch(RuntimeException r) {
			fail("La location n'a pas accepté le payment");
		}
		
	}
	
	@Test
	void testRenduNonEndommage() {
		FilmPhysique f = new FilmPhysique("Bad boys");
		FilmPhysique f2 = new FilmPhysique("bad boys 2");
		assertTrue(guest.getLocations()[0]==null);
		try {
			guest.rendreFilmNonEndommage(al, f);
			fail("Guest ne doit pas pouvoir rendre le film puisque sa location est null");
		} catch (RenduFilmException e) {
			//Suite du test uniquement si il n'a pas fail
			assertTrue(guest.getLocations()[0]==null);
			guest.getLocations()[0]=f;
			try {
				guest.rendreFilmNonEndommage(al, f);
				assertTrue(guest.getLocations()[0]==null);
			} catch (RenduFilmException e1) {
				fail("Ne devrait pas renvoyer d'exception car on rend f avec comme location le film f");
			}
			guest.getLocations()[0]=f;
			try {
				guest.rendreFilmNonEndommage(al, f2);
				fail("On rend le mauvais film : f2 au lieu de f");
			} catch (RenduFilmException e1) {
				assertTrue(guest.getLocations()[0].equals(f));
				fail("IL faut tester les débitement");
			}
		}
		
	}
	
	@Test 
	void testRenduFilmEndommage(){
		//on effectue la même batterie de test mais cette fois 
		//La méthode est renduFilmEndommage
		FilmPhysique f = new FilmPhysique("Bad boys");
		FilmPhysique f2 = new FilmPhysique("bad boys 2");
		assertTrue(guest.getLocations()[0]==null);
		try {
			guest.rendreFilmEndommage(al, f);
			fail("Guest ne doit pas pouvoir rendre le film puisque sa location est null");
		} catch (RenduFilmException e) {
			//Suite du test uniquement si il n'a pas fail
			assertTrue(guest.getLocations()[0]==null);
			guest.getLocations()[0]=f;
			try {
				guest.rendreFilmEndommage(al, f);
				assertTrue(guest.getLocations()[0]==null);
			} catch (RenduFilmException e1) {
				fail("Ne devrait pas renvoyer d'exception car on rend f avec comme location le film f");
			}
			guest.getLocations()[0]=f;
			try {
				guest.rendreFilmEndommage(al, f2);
				fail("On rend le mauvais film : f2 au lieu de f");
			} catch (RenduFilmException e1) {
				assertTrue(guest.getLocations()[0].equals(f));
				fail("IL faut tester les débitement");
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
			guest.rendreFilmNonEndommage(al, f);
		} catch (RenduFilmException e) {
			fail("Il ne devrait pas y avoir d'erreur soulever");
		}
		assertFalse(guest.mustEndALocationFirst());
	}
	

}
