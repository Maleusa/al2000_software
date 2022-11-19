package test.fc.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fc.Al2000;
import fc.user.CarteAbonnement;
import fc.user.PasSuffisamentArgentCompteAboException;

class CarteAbonnementTest {
	final Al2000 al = new Al2000(0);
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
		
	}
	
	@Test
	void testRendu() {
		
	}
	
	@Test
	void testConnexionAvecLocationOuNon() {
		
	}
	
}
