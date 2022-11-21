package fc.tag;

public class GenreTag extends Tag {

	public GenreTag() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenreTag(String tag) {
		super(tag);
		this.setPrio(constant.Priority.CINQ);
	}
	public GenreTag(GenreTag genre) {
		super(genre.getTag(),constant.Priority.CINQ);
	}

	@Override
	public String query() {
		String s=new String("SELECT * from FILMS where GENRE LIKE '%"+this.getTag()+"%'");
		return s;
	}
}
