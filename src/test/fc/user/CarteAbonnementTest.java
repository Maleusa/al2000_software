package test.fc.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fc.Al2000;
import fc.user.CarteAbonnement;

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
		int baseValue = c.getSolde(), addValue = 10;
		c.addToSolde(addValue);
		assertTrue(c.getSolde()==(baseValue+addValue));
		
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
