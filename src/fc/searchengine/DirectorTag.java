package fc.searchengine;

import fc.constants.Priority;

public class DirectorTag extends Tag {

	public DirectorTag() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DirectorTag(String string) {
		super(string,Priority.TROIS);
	}

	public DirectorTag(DirectorTag director) {
		super(director.getTag(),Priority.TROIS);
	}

	@Override
	public String query() {
		String s=new String("REALISATEUR LIKE '%"+this.getTag()+"%'");
		return s;
	}
}
