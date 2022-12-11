package fc.searchengine;

import fc.constants.Priority;

public class NameTag extends Tag {

	public NameTag() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public NameTag(String tag) {
		super(tag);
		this.setPrio(Priority.UN);
	}
	public NameTag(NameTag name) {
		super(name.getTag(),Priority.UN);
	}

	

	@Override
	public String query() {
		String s=new String("TITLE LIKE '%"+this.getTag()+"%'");
		return s;
	}
}