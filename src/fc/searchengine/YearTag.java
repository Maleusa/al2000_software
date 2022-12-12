package fc.searchengine;

import fc.constants.Priority;

public class YearTag extends Tag {

	public YearTag()  {
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
	
	public YearTag clone() {
		return new YearTag(this);

	}
	@Override
	public String query() {
		String s = new String("ANNEESORTIE LIKE '%"+this.getTag()+"%'");
		return s;
	}
	
	
}
