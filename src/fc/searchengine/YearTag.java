package fc.tag;

import fc.constants.Priority;

public class YearTag extends Tag {

	public YearTag() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	public YearTag(String tag) {
		super(tag);
		this.setPrio(Priority.SEPT);
	}
	public YearTag(Tag tag) {
		super(tag);
		this.setPrio(Priority.SEPT);
		
	}
	@Override
	public String query() {
		String s = new String("ANNEESORTIE LIKE '%"+this.getTag()+"%'");
		return s;
	}
}
