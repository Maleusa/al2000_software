package fc.tag;

public class YearTag extends Tag {

	public YearTag() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	public YearTag(String tag) {
		super(tag);
		this.setPrio(constant.Priority.SEPT);
	}
	public YearTag(Tag tag) {
		super(tag);
		this.setPrio(constant.Priority.SEPT);
		
	}
	@Override
	public String query() {
		String s=new String("SELECT * from FILMS where ANNEE LIKE '%"+this.getTag()+"%'");
		return s;
	}
}
