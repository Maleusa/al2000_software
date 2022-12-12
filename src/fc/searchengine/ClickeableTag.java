package fc.searchengine;

public class ClickeableTag {

	protected Tag tag;
	protected boolean clickedStatus;
	
	public ClickeableTag(Tag tag) {
		this.tag=tag;
		clickedStatus=false;
	}

	

	public Tag getTag() {
		return tag;
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
