package fc.searchengine;

public class ClickeableTag extends Tag{

	protected Tag tag;
	protected boolean clickedStatus;
	public ClickeableTag(Tag tag) throws Exception {
		super(tag);
		clickedStatus=false;
	}

	@Override
	public String query() {
		
		return tag.query();
	}

	public String getTag() {
		return tag.getTag();
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
	

	public boolean isClickedStatus() {
		return clickedStatus;
	}

	public void setClickedStatus(boolean clickedStatus) {
		this.clickedStatus = clickedStatus;
	}
	
	
}
