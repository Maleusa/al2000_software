package test.fc.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fc.Al2000;
import fc.Constants;
import fc.movie.FilmDemat;
import fc.movie.FilmLouable;
import fc.movie.FilmPhysique;
import fc.user.CarteAbonnement;
import fc.user.Client;
import fc.user.PasSuffisamentArgentCompteAboException;

class CarteAbonnementTest {
	final Client client = new Client();
	final Al2000 al = new Al2000(0,client);
	String login="login", password="password";
	final CarteAbonnement c = new CarteAbonnement(0, login, password);
	@Test
	void testLogin() {
		String wLogin = "wrongLog", wPassword = "wrongPassword";
		assertTrue(c.checkIdentity(login, password));
		assertFalse(c.checkIdentity(login, wPassword));
		assertFalse(c.checkIdentity(wLogin, password));
		assertFalse(c.checkIdentity(wLogin, wPassword));
	}
	
	@Test
	void testModifBasiqueSolde() {
		int baseValue = c.getSolde(), value = 10, wValue = -10;
		c.addToSolde(value);
		assertTrue(c.getSolde()==(baseValue+value));
		
		//Crediter compte :
		c.setSolde(0);
		c.crediterCompte(value);
		assertTrue(c.getSolde()==(baseValue+value));
		
		c.setSolde(0);
		try {
			c.crediterCompte(wValue);
			fail("On a créditer du négatif sur le compte");
		}catch(RuntimeException e) {
			assertTrue(c.getSolde()==0);
			
			//Prelever Compte :
			c.setSolde(10);
			try {
				c.preleverCompte(value);
				assertTrue(c.getSolde()==0);
			} catch (PasSuffisamentArgentCompteAboException e1) {
				fail("Il y a suffisament d'argent");
			}
			try {
				c.preleverCompte(wValue);
				fail("L'exception d'un prelevement negatif devrait être levé");
			} catch (PasSuffisamentArgentCompteAboException e1) {
				fail("L'exception d'un prelevement négatif devrait être levé avant.");
			}catch (RuntimeException r) {
				assertTrue(c.getSolde()==0);
				
				try {
					c.preleverCompte(value);
					fail("On a plus suffisament d'argent, une exception devrait être levé.");
				} catch (PasSuffisamentArgentCompteAboException e1) {
					assertTrue(c.getSolde()==0);
					//Si on est ici : Tous les tests sont un succès
				}
			} 
		}
		
	}
	
	@Test
	void testLocation() {
		//La location ne nécessite pas d'argent c'est le rendu pour les films physiques
		//Payment instantané pour film demat
		
		
		//Films Physiques :
		c.setSolde(0);
		for(FilmLouable f : c.getLocations()) f=null;
		for(FilmLouable f : c.getLocations()) assertTrue(f==null);
		
		FilmPhysique f = new FilmPhysique("The Rock");
		c.louerFilm(al, f);
		assertTrue(f.equals(c.getLocations()[0]),""+c.getLocations()[0]);
		assertTrue(c.getLocations()[1]==null,""+c.getLocations()[1]);
		assertTrue(c.getLocations()[2]==null,""+c.getLocations()[2]);
		
		FilmPhysique f2 = new FilmPhysique("Bad boys");
		c.louerFilm(al, f2);
		assertTrue(f.equals(c.getLocations()[0]),""+c.getLocations()[0]);
		assertTrue(f2.equals(c.getLocations()[1]),""+c.getLocations()[1]);
		assertTrue(c.getLocations()[2]==null,""+c.getLocations()[2]);
		
		FilmPhysique f3 = new FilmPhysique("Ambulance");
		c.louerFilm(al, f3);
		assertTrue(f.equals(c.getLocations()[0]),""+c.getLocations()[0]);
		assertTrue(f2.equals(c.getLocations()[1]),""+c.getLocations()[1]);
		assertTrue(f3.equals(c.getLocations()[2]),""+c.getLocations()[2]);
		
		//Film Demat :
		c.setSolde(0);
		for(int i=0; i<c.getLocations().length; i++)
			c.getLocations()[i]=null;
		for(FilmLouable fl : c.getLocations()) assertTrue(fl==null);
		
		FilmDemat fd = new FilmDemat("The Rock");
		c.louerFilm(al, fd);
		assertTrue(fd.equals(c.getLocations()[0]),""+c.getLocations()[0]);
		assertTrue(c.getLocations()[1]==null,""+c.getLocations()[1]);
		assertTrue(c.getLocations()[2]==null,""+c.getLocations()[2]);
		
		FilmDemat fd2 = new FilmDemat("Bad boys");
		c.louerFilm(al, fd2);
		assertTrue(fd.equals(c.getLocations()[0]),""+c.getLocations()[0]);
		assertTrue(fd2.equals(c.getLocations()[1]),""+c.getLocations()[1]);
		assertTrue(c.getLocations()[2]==null,""+c.getLocations()[2]);
		
		FilmDemat fd3 = new FilmDemat("Ambulance");
		c.louerFilm(al, fd3);
		assertTrue(fd.equals(c.getLocations()[0]),""+c.getLocations()[0]);
		assertTrue(fd2.equals(c.getLocations()[1]),""+c.getLocations()[1]);
		assertTrue(fd3.equals(c.getLocations()[2]),""+c.getLocations()[2]);
		
		//On reboot et on regarde l'argent :
		c.setSolde(0);
		for(int i=0; i<c.getLocations().length; i++)
			c.getLocations()[i]=null;
		for(FilmLouable fl : c.getLocations()) assertTrue(fl==null);
		int solde = 10, soldeInsuffisant=1;
		int prixFilmDemat=Constants.PRIXDEMAT;
		int reductionAbonnement=Constants.REDUCTIONABONNEMENT;
		c.setSolde(10);
		
		//On loue un film physique : si le solde est modifié c'est une erreur
		c.louerFilm(al, f);
		assertTrue(solde==c.getSolde());
		
		//On loue un film demat en ayant le budget :
		c.louerFilm(al, fd);
		if(solde>prixFilmDemat-reductionAbonnement) 
			assertTrue(c.getSolde()==solde-(prixFilmDemat-reductionAbonnement),""+c.getSolde());
		else fail("Le test n'est pas effectué avec le bon budget/prix");
		//On loue un film demat en ayant pas le budget :
		//Le solde devrait être le même car on passe par la cb du user
		c.setSolde(soldeInsuffisant);
		int moneyOnCB = client.moneyInTheBank;
		c.louerFilm(al, fd2);
		if(c.getSolde()<prixFilmDemat-reductionAbonnement) {
			assertTrue(c.getSolde()==soldeInsuffisant,""+c.getSolde());
			assertTrue(client.moneyInTheBank==moneyOnCB-(prixFilmDemat-reductionAbonnement));
		}else fail("Le test n'est pas effectué avec le bon budget/prix");
		
		
		//fail("Manque la location de filmDemat : payment");
		
		
	}
	@Test
	void testRenduFilmPhysiqueNonEndommage() {
		c.setSolde(0);
		for(FilmLouable f : c.getLocations()) f=null;
		for(FilmLouable f : c.getLocations()) assertTrue(f==null);
		
		fail("Not yet Implemented");
	}
	
	@Test
	void testRenduFilmPhysiqueEndommage() {
		fail("Not yet Implemented");
	}
	
	
	@Test
	void testConnexionAvecLocationOuNon() {
		c.setSolde(0);
		for(FilmLouable f : c.getLocations()) f = null;
		
		fail("Not yet Implemented");
	}
	
}
