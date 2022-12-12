package fc.searchengine;

public class AllTag extends Tag {
	public AllTag() {
		
	}
	public AllTag(Tag tag) {
		super(tag);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String query() {
		
		return "ALL";
	}
	public AllTag clone() {
		return new AllTag(this);

	}

}
