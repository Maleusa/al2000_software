package fc.tag;

public class ClassificationTag extends Tag {

	public ClassificationTag() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String query() {
		String s=new String("SELECT * from FILMS where classification LIKE '%"+this.getTag()+"%'");
		return s;
	}

}
