package fc.tag;

import constant.Priority;

public class ActorTag extends Tag {

	public ActorTag() throws Exception {
		super();
		this.setPrio(constant.Priority.DEUX);
	}
	public ActorTag(String tag) {
		super(tag);
		this.setPrio(constant.Priority.DEUX);
	}
	public ActorTag(ActorTag actor) {
		super(actor);
		this.setPrio(constant.Priority.DEUX);
	}
	@Override
	public String query() {
		String s=new String("SELECT * from FILMS where ACTEURS LIKE '%"+this.getTag()+"%'");
		return s;
	}

}
