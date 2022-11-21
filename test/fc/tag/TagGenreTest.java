package test.fc.tag;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fc.tag.ClassificationTag;
import fc.tag.GenreTag;

class TagGenreTest {

	private final static GenreTag genre = new GenreTag("Adulte");
	private String s=new String("SELECT * from FILMS where GENRE LIKE '%Adulte%'");
	@Test
	void testQuery() {
		assertEquals(s,genre.query());
	}
	@Test
	void testConstructor() {
		assertEquals(genre,new GenreTag(genre.getTag()));
		
	}
	@Test
	void testEquals() {
		assertTrue(genre.equals(new GenreTag(genre)));
		
	}
	
	@Test
	void testPriorty() {
		assertEquals(constant.Priority.CINQ,genre.getPrio());
	}
}
