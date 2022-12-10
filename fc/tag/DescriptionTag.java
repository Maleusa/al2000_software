package fc.tag;

public class DescriptionTag extends Tag {

	public DescriptionTag() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DescriptionTag(String tag) {
		super(tag,constant.Priority.SIX);
	}

	public DescriptionTag(DescriptionTag description) {
		super(description.getTag(),constant.Priority.SIX);
	}

	

	@Override
	public String query() {
		String s=new String("DESCRIPT LIKE '%"+this.getTag()+"%'");
		return s;
	}
}
