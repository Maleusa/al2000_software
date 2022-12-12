package fc.searchengine;

import fc.constants.Priority;

public class NameTag extends Tag {

	public NameTag()  {
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

	public NameTag clone() {
		return new NameTag(this);

	}

	@Override
	public String query() {
		String s=new String("TITLE LIKE '%"+this.getTag()+"%'");
		return s;
	}
}
