package fc.tag;

public class DirectorTag extends Tag {

	public DirectorTag() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String query() {
		String s=new String("SELECT * from FILMS where DIRECTEURS LIKE '%"+this.getTag()+"%'");
		return s;
	}
}
