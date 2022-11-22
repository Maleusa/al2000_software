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
 * Classe JUnit de test pour la classe CarteAbonnement. La classe CarteAbonnement extends Abonnement et repr�sente un abonnement
 * parmis n d'un Client. Pour plus d'informations sur CarteAbonnement : voir package : fc.user.
 * 
 * Cette classe de tests r�alise plusieurs tests unitaires sur CarteAbonnement.
 * 
 * Les commentaires au sein des m�thodes de tests suivent le d�rouler du test, si un test fail � la moiti�, les 
 * commentaires en dessous de r�sultats ne sont pas valides tant que le test n'est pas un succ�s. On peut aussi 
 * se r�f�rer aux commentaires des fail() pour comprendre le test.
 */

class CarteAbonnementTest {
	/*
	 * On initialise tous les objets n�cessaires � notre test :
	 * 	-Un client (qui en r�alit� est le propri�taire de cette CarteAbonnement)
	 * 	-Al2000 : la machine avec laquelle le client/abonn� int�ragit
	 * 	-La CarteAbonnement test� avec son id, son login et son password pr�initialis�s.
	 */
	final Client client = new Client();
	final Al2000 al = new Al2000(0,client);
	String login="login", password="password";
	final CarteAbonnement c = new CarteAbonnement(0, login, password);
	
	/*
	 * testLogin permet de v�rifier le comportement de notre v�rificateur d'identit�.
	 * Il est test� avec : le bon login et bon password, le bon login et le mauvais password,
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
		 * On test la m�thode addToSolde qui est un simple ajout.
		 */
		int baseValue = c.getSolde(), value = 10, wValue = -10;
		c.addToSolde(value);
		assertTrue(c.getSolde()==(baseValue+value));
		
		/*
		 * On test la m�thode cr�diterCompte qui effectue un simple appel � add
		 */
		c.setSolde(0);
		c.crediterCompte(value);
		assertTrue(c.getSolde()==(baseValue+value));
		
		/*
		 * On tente de cr�diter le compte d'une valeur n�gative
		 */
		c.setSolde(0);
		try {
			c.crediterCompte(wValue);
			fail("On a cr�diter du n�gatif sur le compte");
		}catch(RuntimeException e) {
			assertTrue(c.getSolde()==0);

			/*
			 * Le solde n'a pas �t� cr�dit� et l'exception bien lev�.
			 * 
			 * On test le pr�l�vement d'une somme �gale au montant du compte.
			 */
			c.setSolde(value);
			try {
				c.preleverCompte(value);
				assertTrue(c.getSolde()==0);
			} catch (PasSuffisamentArgentCompteAboException e1) {
				fail("Il y a suffisament d'argent");
			}
			/*
			 * Le solde a bien �t� pr�lev� et aucune exception n'a �t� lev�
			 *
			 * On test le pr�levement d'une valeur n�gative sur le compte.
			 */
			try {
				c.preleverCompte(wValue);
				fail("L'exception d'un prelevement negatif devrait �tre lev�");
			} catch (PasSuffisamentArgentCompteAboException e1) {
				fail("L'exception d'un prelevement n�gatif devrait �tre lev� avant.");
			}catch (RuntimeException r) {
				assertTrue(c.getSolde()==0);
				
				/*
				 * On test le pr�l�vement d'une valeur sup�rieur � l'argent disponible sur le compte.
				 */
				try {
					c.preleverCompte(value);
					fail("On a plus suffisament d'argent, une exception devrait �tre lev�.");
				} catch (PasSuffisamentArgentCompteAboException e1) {
					assertTrue(c.getSolde()==0);
				}
			} 
		}
		
	}
	
	/*
	 * testLocation effectue une batterie de test sur la m�thode location afin de s'assurer de son bon comportement.
	 * 
	 * Pour rappel : 
	 * 	-La location peut s'effectuer sur un FilmLouable qui pour l'instant peut �tre : un FilmPhysique ou un FilmDemat
	 * 	-La location ne n�cessite pas de payement pour un film physique (il sera pay� au moment du rendu).
	 * 	-Le payement est effectu� dans cette m�thode pour un film d�mat�rialis�.
	 * 	-Une m�me carte abonnement ne peut louer que 3 films au m�me instant : ce point est g�r� par la m�thode
	 * 		mustEndALocationFirst() et dans la m�thode location nous partons du principe que ce point a �t� test�
	 * 		au pr�alable.
	 * 	-La location doit remont� � Al2000 de mani�re � ce qu'il modifie l'�tat du film et de la database (non test� ici) 
	 * 	-L'historique de location doit �tre mis � jour. 
	 */
	@Test
	void testLocation() {
		/*
		 * On effectue des test pour les locations de FilmPhysique :
		 * On s'assure que nos trois locations sont bien effectu�es et les films ajout�s
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
		 * On s'assure que nos trois locations sont bien effectu�es et les films ajout�s
		 * au tableau de location.
		 * 
		 * Dans le cadre des films d�mat�rialis�s, le payement est effectu� de la mani�re suivante :
		 * 	-Si le solde de la carte d'abonnement est suffisant : celle-ci est d�bit�e
		 * 	-Sinon : le client (propri�taire de la carte) est d�bit� directement sur sa CB
		 * 
		 * Pour les premiers tests (qui tests uniquement la locations : on part du principe que le
		 * payement fonctionne enti�rement.
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
		 * On loue un film physique : le solde de la carte comme du client ne devrait �tre modifi�.
		 */
		c.louerFilm(al, f);
		assertTrue(solde==c.getSolde());
		assertTrue(moneyOnCB==client.moneyInTheBank);
		
		/*
		 * On loue un film d�mat�rialis�, n�cessitant donc un payement. Le solde de la carte est sens� �tre
		 * suffisant, le solde sera donc modifi� et la CB du client non pr�lev�e.
		 */
		c.louerFilm(al, fd);
		if(solde>prixFilmDemat-reductionAbonnement) {
			assertTrue(c.getSolde()==solde-(prixFilmDemat-reductionAbonnement),""+c.getSolde());
			assertTrue(moneyOnCB==client.moneyInTheBank);
	}
		else fail("Le test n'est pas effectu� avec le bon budget/prix");
		
		/*
		 * On loue un film d�mat�rialis� avec cette fois un budget sur la carte d'abonnement insuffisant.
		 * Le solde de la carte d'abonnement devrait �tre inchang� et le pr�l�vement devrait s'effectuer
		 * sur la CB.
		 */
		c.setSolde(soldeInsuffisant);
		c.louerFilm(al, fd2);
		if(c.getSolde()<prixFilmDemat-reductionAbonnement) {
			assertTrue(c.getSolde()==soldeInsuffisant,""+c.getSolde());
			assertTrue(client.moneyInTheBank==moneyOnCB-(prixFilmDemat-reductionAbonnement));
		}else fail("Le test n'est pas effectu� avec le bon budget/prix");
		
		/*
		 * Il faut effectuer une batterie de tests sur l'historique des locations
		 */
		fail("L'historique n'a pas encore �t� impl�ment�");
		
	}
	
	/*
	 * La m�thode testRenduFilmPhysiqueNonEndommage effectue des tests pour le rendu d'un film (qui doit forc�ment
	 *  �tre physique car un QR code n'est jamais rendu).
	 * Cette m�thode test le rendu d'un film non endommage :
	 * 	-La location doit donc �tre pay� par la carte abonnement ou par CB
	 * 	-La liste des locations doit forc�ment contenir le film rendu (on ne peut rendre un film que l'on a pas lou� au
	 * 		pr�alable)
	 * 	-La liste des locations doit �tre mise � jour.
	 * 	-La location doit remont� � Al2000 de mani�re � ce qu'il modifie l'�tat du film et de la database (non test� ici)
	 * 	-L'historique de location doit �tre mis � jour. 
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
	 * La m�thode testRenduFilmPhysiqueEndommage effectue des tests pour le rendu d'un film.
	 * Cette m�thode test le rendu d'un film endommage de mani�re similaire � celui non endommag�.
	 * 
	 * Cependant : 
	 * 	-La carte abonnement est pr�lev� mais l'historique est mis � jour comme endommag�.
	 * 	
	 */
	@Test
	void testRenduFilmPhysiqueEndommage() {
		fail("Not yet Implemented");
	}
	
	/*
	 * La m�thode testConnexionAvecLocationOuNon v�rifie que l'abonnement n'a pas d�j� atteint sont nombre de locations
	 * maximum. Si celui-ci � atteint le nombre maximum, le client ne peut rien effectuer d'autres que se d�connecter
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
	 * 	-Restrictions et pr�f�rences
	 */
	@Test
	void testGestionCompte() {
		fail("Not yet Implemented and multiple tests missing");
	}
	
}
