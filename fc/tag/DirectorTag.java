package fc.tag;

public class DirectorTag extends Tag {

	public DirectorTag() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DirectorTag(String string) {
		super(string,constant.Priority.TROIS);
	}

	public DirectorTag(DirectorTag director) {
		super(director.getTag(),constant.Priority.TROIS);
	}

	@Override
	public String query() {
		String s=new String("REALISATEUR LIKE '%"+this.getTag()+"%'");
		return s;
	}
}
