package fc.tag;

public class NameTag extends Tag {

	public NameTag() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public NameTag(String tag) {
		super(tag);
		this.setPrio(constant.Priority.UN);
	}
	public NameTag(NameTag name) {
		super(name.getTag(),constant.Priority.UN);
	}

	

	@Override
	public String query() {
		String s=new String("SELECT * from FILMS where NOM LIKE '%"+this.getTag()+"%'");
		return s;
	}
}
