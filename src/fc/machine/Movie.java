package fc.machine;

import fc.searchengine.*;
import java.util.Date;


public class Movie {
	private int id;
	private NameTag title;
	private ActorTag actors;
	private DirectorTag directors;
	private GenreTag genre;
	private DescriptionTag description;
	private YearTag year;
	private String url;
	
  
  
  	public Movie() {
		
 	}
  
	public Movie(int id, String name,String actors,String directors,String genre,String description,String year) {
		this.title=new NameTag(name);
		this.actors=new ActorTag(actors);
		this.directors=new DirectorTag(directors);
		this.genre=new GenreTag(genre);
		this.description=new DescriptionTag(description);
		this.year=new YearTag(year);


	}
  
  
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean containsTag(Tag tag) {
		if(tag.similareTo(actors)||tag.similareTo(description)||tag.similareTo(directors)||tag.similareTo(genre)||tag.similareTo(title)||tag.similareTo(year)) return true;
		else return false;
	}
	public int getPriority(Tag tag) {
		if (!containsTag(tag)) return -1;
		else if(actors.similareTo(tag)) return actors.getPrio();
		else if(title.similareTo(tag)) return title.getPrio();
		else if(directors.similareTo(tag)) return directors.getPrio();
		else if(genre.similareTo(tag)) return genre.getPrio();
		else if(description.similareTo(tag)) return description.getPrio();
		else return year.getPrio();
	}
	
	/*
	 * GetNam
	 */
	public NameTag getTitle() {
		return title;
	}
	
	
	
	public void setTitle(String name) {
		this.title = new NameTag(name);
	}

	public ActorTag getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = new ActorTag(actors);
	}

	public DirectorTag getDirectors() {
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = new DirectorTag(directors);
	}

	public GenreTag getGenre() {
		return genre;
	}

	public void setGenre(GenreTag genre) {
		this.genre = new GenreTag(genre);
	}

	public DescriptionTag getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = new DescriptionTag(description);
	}

	public YearTag getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = new YearTag(year);
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public void setTitle(NameTag name) {
		this.title = name;
	}

	public void setActors(ActorTag actors) {
		this.actors = actors;
	}

	public void setDirectors(DirectorTag directors) {
		this.directors = directors;
	}

	public void setDescription(DescriptionTag description) {
		this.description = description;
	}

	public void setYear(YearTag year) {
		this.year = year;
	}
}
	
	