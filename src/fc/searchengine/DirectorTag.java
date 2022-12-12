package fc.searchengine;

import fc.constants.Priority;

public class DirectorTag extends Tag {

	public DirectorTag()  {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DirectorTag(String string) {
		super(string,Priority.TROIS);
	}

	public DirectorTag(DirectorTag director) {
		super(director.getTag(),Priority.TROIS);
	}
	public DirectorTag clone() {
		return new DirectorTag(this);

	}
	@Override
	public String query() {
		String s=new String("REALISATEUR LIKE '%"+this.getTag()+"%'");
		return s;
	}
}
