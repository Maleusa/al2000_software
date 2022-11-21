package fc.tag;

import constant.Priority;

public class ClassificationTag extends Tag {

	public ClassificationTag() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClassificationTag(String string) {
		super(string,constant.Priority.QUATRE);
	}

	public ClassificationTag(Tag classification) {
		super(classification.getTag(),constant.Priority.QUATRE);
		
	}

	@Override
	public String query() {
		String s=new String("SELECT * from FILMS where CLASSIFICATION LIKE '%"+this.getTag()+"%'");
		return s;
	}

}
