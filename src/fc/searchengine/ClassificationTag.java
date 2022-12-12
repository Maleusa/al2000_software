package fc.searchengine;

import fc.constants.Priority;

public class ClassificationTag extends Tag {

	public ClassificationTag() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClassificationTag(String string) {
		super(string,Priority.QUATRE);
	}

	public ClassificationTag(Tag classification) {
		super(classification.getTag(),Priority.QUATRE);
		
	}
	public ClassificationTag clone() {
		return new ClassificationTag(this);

	}

	@Override
	public String query() {
		String s=new String("SELECT * from FILMS where CLASSIFICATION LIKE '%"+this.getTag()+"%'");
		return s;
	}

}
