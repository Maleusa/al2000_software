package fc.searchengine;

import fc.constants.Priority;

public class DescriptionTag extends Tag {

	public DescriptionTag() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DescriptionTag(String tag) {
		super(tag,Priority.SIX);
	}

	public DescriptionTag(DescriptionTag description) {
		super(description.getTag(),Priority.SIX);
	}

	public DescriptionTag clone() {
		return new DescriptionTag(this);

	}

	@Override
	public String query() {
		String s=new String("DESCRIPT LIKE '%"+this.getTag()+"%'");
		return s;
	}
}
