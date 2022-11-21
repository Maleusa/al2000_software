package test.fc.tag;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fc.tag.DescriptionTag;

class TagDescriptionTest {
	private final static DescriptionTag description = new DescriptionTag("espace");
	private String s=new String("SELECT * from FILMS where DESCRIPTION LIKE '%espace%'");
	@Test
	void testQuery() {
		assertEquals(s,description.query());
	}
	@Test
	void testConstructor() {
		assertEquals(description,new DescriptionTag(description.getTag()));
		
	}
	@Test
	void testEquals() {
		assertTrue(description.equals(new DescriptionTag(description)));
		
	}
	
	@Test
	void testPriorty() {
		assertEquals(constant.Priority.SIX,description.getPrio());
	}
}
