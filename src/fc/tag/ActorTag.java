package fc.tag;

public class ActorTag extends Tag {

	public ActorTag() throws Exception {
		super();
		this.setPrio(Priority.Deux);
	}

	@Override
	public String query() {
		String s=new String("SELECT * from FILMS where ACTEURS LIKE '%"+this.getTag()+"%'");
		return s;
	}

}
