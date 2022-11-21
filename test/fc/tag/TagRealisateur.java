package test.fc.tag;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fc.tag.ClassificationTag;
import fc.tag.DirectorTag;

class TagRealisateur {

	private final static DirectorTag director = new DirectorTag("Bay Michael");
	private String s=new String("SELECT * from FILMS where REALISATEURS LIKE '%Bay Michael%'");
	@Test
	void testQuery() {
		assertEquals(s,director.query());
	}
	@Test
	void testConstructor() {
		assertEquals(director,new DirectorTag(director.getTag()));
		
	}
	@Test
	void testEquals() {
		assertTrue(director.equals(new DirectorTag(director)));
		
	}
	
	@Test
	void testPriorty() {
		assertEquals(constant.Priority.TROIS,director.getPrio());
	}
	
	@Test
	void testCompareTo() {
		assertTrue(director.compareTo(director)==0);
		assertTrue(director.compareTo(new DirectorTag("Bay"))>0);
		assertTrue(director.compareTo(new ClassificationTag("Bay Michael"))<0);
	}
}
