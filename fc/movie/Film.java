package fc.movie;

import fc.tag.*;

public abstract class Film {
	private NameTag name;
	private ActorTag actors;
	private DirectorTag directors;
	private GenreTag genre;
	private ClassificationTag classification;
	private YearTag year;
	public Film(String name,String actors,String directors,String genre,String classification,String year) {
		this.name=new NameTag(name);
		this.actors=new ActorTag(actors);
		this.directors=new DirectorTag(directors);
		this.genre=new GenreTag(genre);
		this.classification=new ClassificationTag(classification);
		this.year=new YearTag(year);
		
		
	}
	
	public boolean containsTag(Tag tag) {
		if(tag.similareTo(actors)||tag.similareTo(classification)||tag.similareTo(directors)||tag.similareTo(genre)||tag.similareTo(name)||tag.similareTo(year)) return true;
		else return false;
	}
	public int getPriority(Tag tag) {
		if (!containsTag(tag)) return -1;
		else if(actors.similareTo(tag)) return actors.getPrio();
		else if(name.similareTo(tag)) return name.getPrio();
		else if(directors.similareTo(tag)) return directors.getPrio();
		else if(genre.similareTo(tag)) return genre.getPrio();
		else if(classification.similareTo(tag)) return classification.getPrio();
		else return year.getPrio();
	}

}