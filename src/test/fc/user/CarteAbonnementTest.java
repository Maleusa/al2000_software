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
/*
 * @author Hugo APELOIG
 * 
 * Classe JUnit de test pour la classe CarteAbonnement. La classe CarteAbonnement extends Abonnement et représente un abonnement
 * parmis n d'un Client. Pour plus d'informations sur CarteAbonnement : voir package : fc.user.
 * 
 * Cette classe de tests réalise plusieurs tests unitaires sur CarteAbonnement.
 * 
 * Les commentaires au sein des méthodes de tests suivent le dérouler du test, si un test fail à la moitié, les 
 * commentaires en dessous de résultats ne sont pas valides tant que le test n'est pas un succès. On peut aussi 
 * se référer aux commentaires des fail() pour comprendre le test.
 */

class CarteAbonnementTest {
	/*
	 * On initialise tous les objets nécessaires à notre test :
	 * 	-Un client (qui en réalité est le propriétaire de cette CarteAbonnement)
	 * 	-Al2000 : la machine avec laquelle le client/abonné intéragit
	 * 	-La CarteAbonnement testé avec son id, son login et son password préinitialisés.
	 */
	final Client client = new Client();
	final Al2000 al = new Al2000(0,client);
	String login="login", password="password";
	final CarteAbonnement c = new CarteAbonnement(0, login, password);
	
	/*
	 * testLogin permet de vérifier le comportement de notre vérificateur d'identité.
	 * Il est testé avec : le bon login et bon password, le bon login et le mauvais password,
	 * 	le mauvais login et le bon password et avec le mauvais login et le mauvais password.
	 */
	@Test
	void testLogin() {
		String wLogin = "wrongLog", wPassword = "wrongPassword";
		assertTrue(c.checkIdentity(login, password));
		assertFalse(c.checkIdentity(login, wPassword));
		assertFalse(c.checkIdentity(wLogin, password));
		assertFalse(c.checkIdentity(wLogin, wPassword));
	}
	
	/*
	 * testModifBasiqueSolde effectue une batterie de tests sur le solde de l'abonnement.
	 */
	@Test
	void testModifBasiqueSolde() {
		/*
		 * On test la méthode addToSolde qui est un simple ajout.
		 */
		int baseValue = c.getSolde(), value = 10, wValue = -10;
		c.addToSolde(value);
		assertTrue(c.getSolde()==(baseValue+value));
		
		/*
		 * On test la méthode créditerCompte qui effectue un simple appel à add
		 */
		c.setSolde(0);
		c.crediterCompte(value);
		assertTrue(c.getSolde()==(baseValue+value));
		
		/*
		 * On tente de créditer le compte d'une valeur négative
		 */
		c.setSolde(0);
		try {
			c.crediterCompte(wValue);
			fail("On a créditer du négatif sur le compte");
		}catch(RuntimeException e) {
			assertTrue(c.getSolde()==0);

			/*
			 * Le solde n'a pas été crédité et l'exception bien levé.
			 * 
			 * On test le prélèvement d'une somme égale au montant du compte.
			 */
			c.setSolde(value);
			try {
				c.preleverCompte(value);
				assertTrue(c.getSolde()==0);
			} catch (PasSuffisamentArgentCompteAboException e1) {
				fail("Il y a suffisament d'argent");
			}
			/*
			 * Le solde a bien été prélevé et aucune exception n'a été levé
			 *
			 * On test le prélevement d'une valeur négative sur le compte.
			 */
			try {
				c.preleverCompte(wValue);
				fail("L'exception d'un prelevement negatif devrait être levé");
			} catch (PasSuffisamentArgentCompteAboException e1) {
				fail("L'exception d'un prelevement négatif devrait être levé avant.");
			}catch (RuntimeException r) {
				assertTrue(c.getSolde()==0);
				
				/*
				 * On test le prélèvement d'une valeur supérieur à l'argent disponible sur le compte.
				 */
				try {
					c.preleverCompte(value);
					fail("On a plus suffisament d'argent, une exception devrait être levé.");
				} catch (PasSuffisamentArgentCompteAboException e1) {
					assertTrue(c.getSolde()==0);
				}
			} 
		}
		
	}
	
	/*
	 * testLocation effectue une batterie de test sur la méthode location afin de s'assurer de son bon comportement.
	 * 
	 * Pour rappel : 
	 * 	-La location peut s'effectuer sur un FilmLouable qui pour l'instant peut être : un FilmPhysique ou un FilmDemat
	 * 	-La location ne nécessite pas de payement pour un film physique (il sera payé au moment du rendu).
	 * 	-Le payement est effectué dans cette méthode pour un film dématérialisé.
	 * 	-Une même carte abonnement ne peut louer que 3 films au même instant : ce point est géré par la méthode
	 * 		mustEndALocationFirst() et dans la méthode location nous partons du principe que ce point a été testé
	 * 		au préalable.
	 * 	-La location doit remonté à Al2000 de manière à ce qu'il modifie l'état du film et de la database (non testé ici) 
	 * 	-L'historique de location doit être mis à jour. 
	 */
	@Test
	void testLocation() {
		/*
		 * On effectue des test pour les locations de FilmPhysique :
		 * On s'assure que nos trois locations sont bien effectuées et les films ajoutés
		 * au tableau de location.
		 */
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
		
		/*
		 * On effectue des test pour les locations de FilmDemate :
		 * On s'assure que nos trois locations sont bien effectuées et les films ajoutés
		 * au tableau de location.
		 * 
		 * Dans le cadre des films dématérialisés, le payement est effectué de la manière suivante :
		 * 	-Si le solde de la carte d'abonnement est suffisant : celle-ci est débitée
		 * 	-Sinon : le client (propriétaire de la carte) est débité directement sur sa CB
		 * 
		 * Pour les premiers tests (qui tests uniquement la locations : on part du principe que le
		 * payement fonctionne entièrement.
		 */
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
		
		/*
		 * On vide maintenant le tableau des locations, on part du principe que les locations fonctionnent 
		 * et on test uniquement les variations de prix.
		 */
		c.setSolde(0);
		for(int i=0; i<c.getLocations().length; i++)
			c.getLocations()[i]=null;
		for(FilmLouable fl : c.getLocations()) assertTrue(fl==null);
		int solde = 10, soldeInsuffisant=1;
		int prixFilmDemat=Constants.PRIXDEMAT;
		int reductionAbonnement=Constants.REDUCTIONABONNEMENT;
		int moneyOnCB = client.moneyInTheBank;
		c.setSolde(10);
		
		/*
		 * On loue un film physique : le solde de la carte comme du client ne devrait être modifié.
		 */
		c.louerFilm(al, f);
		assertTrue(solde==c.getSolde());
		assertTrue(moneyOnCB==client.moneyInTheBank);
		
		/*
		 * On loue un film dématérialisé, nécessitant donc un payement. Le solde de la carte est sensé être
		 * suffisant, le solde sera donc modifié et la CB du client non prélevée.
		 */
		c.louerFilm(al, fd);
		if(solde>prixFilmDemat-reductionAbonnement) {
			assertTrue(c.getSolde()==solde-(prixFilmDemat-reductionAbonnement),""+c.getSolde());
			assertTrue(moneyOnCB==client.moneyInTheBank);
	}
		else fail("Le test n'est pas effectué avec le bon budget/prix");
		
		/*
		 * On loue un film dématérialisé avec cette fois un budget sur la carte d'abonnement insuffisant.
		 * Le solde de la carte d'abonnement devrait être inchangé et le prélèvement devrait s'effectuer
		 * sur la CB.
		 */
		c.setSolde(soldeInsuffisant);
		c.louerFilm(al, fd2);
		if(c.getSolde()<prixFilmDemat-reductionAbonnement) {
			assertTrue(c.getSolde()==soldeInsuffisant,""+c.getSolde());
			assertTrue(client.moneyInTheBank==moneyOnCB-(prixFilmDemat-reductionAbonnement));
		}else fail("Le test n'est pas effectué avec le bon budget/prix");
		
		/*
		 * Il faut effectuer une batterie de tests sur l'historique des locations
		 */
		fail("L'historique n'a pas encore été implémenté");
		
	}
	
	/*
	 * La méthode testRenduFilmPhysiqueNonEndommage effectue des tests pour le rendu d'un film (qui doit forcément
	 *  être physique car un QR code n'est jamais rendu).
	 * Cette méthode test le rendu d'un film non endommage :
	 * 	-La location doit donc être payé par la carte abonnement ou par CB
	 * 	-La liste des locations doit forcément contenir le film rendu (on ne peut rendre un film que l'on a pas loué au
	 * 		préalable)
	 * 	-La liste des locations doit être mise à jour.
	 * 	-La location doit remonté à Al2000 de manière à ce qu'il modifie l'état du film et de la database (non testé ici)
	 * 	-L'historique de location doit être mis à jour. 
	 * 	
	 */
	@Test
	void testRenduFilmPhysiqueNonEndommage() {
		c.setSolde(0);
		for(FilmLouable f : c.getLocations()) f=null;
		for(FilmLouable f : c.getLocations()) assertTrue(f==null);
		
		fail("Not yet Implemented");
	}
	
	/*
	 * La méthode testRenduFilmPhysiqueEndommage effectue des tests pour le rendu d'un film.
	 * Cette méthode test le rendu d'un film endommage de manière similaire à celui non endommagé.
	 * 
	 * Cependant : 
	 * 	-La carte abonnement est prélevé mais l'historique est mis à jour comme endommagé.
	 * 	
	 */
	@Test
	void testRenduFilmPhysiqueEndommage() {
		fail("Not yet Implemented");
	}
	
	/*
	 * La méthode testConnexionAvecLocationOuNon vérifie que l'abonnement n'a pas déjà atteint sont nombre de locations
	 * maximum. Si celui-ci à atteint le nombre maximum, le client ne peut rien effectuer d'autres que se déconnecter
	 * ou rendre une de ses locations.
	 */
	@Test
	void testConnexionAvecLocationOuNon() {
		c.setSolde(0);
		for(FilmLouable f : c.getLocations()) f = null;
		
		fail("Not yet Implemented");
	}
	
	/*
	 * Il faut une batterie de tests pour la gestion du compte : 
	 * 	-Changement login/password
	 * 	-Restrictions et préférences
	 */
	@Test
	void testGestionCompte() {
		fail("Not yet Implemented and multiple tests missing");
	}
	
}
