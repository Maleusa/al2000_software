package fc.tag;

public class DescriptionTag extends Tag {

	public DescriptionTag() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String query() {
		String s=new String("SELECT * from FILMS where DESCRIPTION LIKE '%"+this.getTag()+"%'");
		return s;
	}
}
