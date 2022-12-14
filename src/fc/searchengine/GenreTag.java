package fc.searchengine;

import fc.constants.Priority;

public class GenreTag extends Tag {

	public GenreTag()  {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenreTag(String tag) {
		super(tag);
		this.setPrio(Priority.CINQ);
	}
	public GenreTag(GenreTag genre) {
		super(genre.getTag(),Priority.CINQ);
	}
	
	public GenreTag clone() {
		return new GenreTag(this);

	}
	
	@Override
	public String query() {
		String s=new String("SELECT * from FILMS where GENRE LIKE '%"+this.getTag()+"%'");
		return s;
	}
}
