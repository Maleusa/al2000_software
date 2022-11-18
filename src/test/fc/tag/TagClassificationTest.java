package test.fc.tag;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fc.tag.ClassificationTag;
import fc.tag.YearTag;

class TagClassificationTest {
	private final static ClassificationTag classification = new ClassificationTag("Adulte");
	private String s=new String("SELECT * from FILMS where CLASSIFICATION LIKE '%Adulte%'");
	@Test
	void testQuery() {
		assertEquals(s,classification.query());
	}
	@Test
	void testConstructor() {
		assertEquals(classification,new ClassificationTag(classification.getTag()));
		
	}
	@Test
	void testEquals() {
		assertTrue(classification.equals(new ClassificationTag(classification)));
		
	}
	
	@Test
	void testPriorty() {
		assertEquals(constant.Priority.QUATRE,classification.getPrio());
	}
}
