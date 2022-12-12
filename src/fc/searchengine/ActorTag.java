package fc.searchengine;

import fc.constants.Priority;

public class ActorTag extends Tag {

	public ActorTag() throws Exception {
		super();
		this.setPrio(Priority.DEUX);
	}
	public ActorTag(String tag) {
		super(tag);
		this.setPrio(Priority.DEUX);
	}
	public ActorTag(ActorTag actor) {
		super(actor);
		this.setPrio(Priority.DEUX);
	}
	@Override
	public String query() {
		String s = new String("ACTEURS LIKE '%"+this.getTag()+"%'");
		return s;
	}
	
	public ActorTag clone() {
		return new ActorTag(this);

	}
}


