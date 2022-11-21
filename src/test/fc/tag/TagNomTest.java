package test.fc.tag;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import fc.tag.NameTag;

class TagNomTest {

	private final static NameTag name = new NameTag("Batman");
	private String s=new String("SELECT * from FILMS where NOM LIKE '%Batman%'");
	@Test
	void testQuery() {
		assertEquals(s,name.query());
	}
	@Test
	void testConstructor() {
		assertEquals(name,new NameTag(name.getTag()));
		
	}
	@Test
	void testEquals() {
		assertTrue(name.equals(new NameTag(name)));
		
	}
	
	@Test
	void testPriorty() {
		assertEquals(constant.Priority.UN,name.getPrio());
	}

}
