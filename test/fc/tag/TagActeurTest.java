package test.fc.tag;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fc.tag.ActorTag;
import fc.tag.Tag;

class TagActeurTest {
	private final static ActorTag actor = new ActorTag("Fugain M.");
	private String s=new String("SELECT * from FILMS where ACTEURS LIKE '%Fugain M.%'");
	@Test
	void testQuery() {
		assertEquals(s,actor.query());
	}
	@Test
	void testConstructor() {
		assertEquals(actor,new ActorTag(actor.getTag()));
		
	}
	@Test
	void testEquals() {
		assertTrue(actor.equals(new ActorTag(actor)));
		
	}
	
	@Test
	void testPriorty() {
		assertEquals(constant.Priority.DEUX,actor.getPrio());
	}

}
