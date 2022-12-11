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
		String s=new String("genre_un LIKE '%"+this.getTag()+"%' OR genre_deux LIKE '%"+this.getTag()+"%' OR"
				+ " genre_trois LIKE '%"+ this.getTag()+ "%' OR genre_quatre LIKE '%"+this.getTag()+"%' OR genre_cinq LIKE '%"+this.getTag()+"%'");
		return s;
	}
}
