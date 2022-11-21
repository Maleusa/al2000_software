package test.fc.tag;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fc.tag.ActorTag;
import fc.tag.YearTag;

class TagAnneeTest {

	private final static YearTag year = new YearTag("1995");
	private String s=new String("SELECT * from FILMS where ANNEE LIKE '%1995%'");
	@Test
	void testQuery() {
		assertEquals(s,year.query());
	}
	@Test
	void testConstructor() {
		assertEquals(year,new YearTag(year.getTag()));
		
	}
	@Test
	void testEquals() {
		assertTrue(year.equals(new YearTag(year)));
		
	}
	
	@Test
	void testPriorty() {
		assertEquals(constant.Priority.SEPT,year.getPrio());
	}


}
