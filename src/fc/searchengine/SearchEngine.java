package fc.searchengine;

import java.util.ArrayList;

import fc.ComponentFC;

public abstract class SearchEngine implements ComponentFC{
	
	protected ArrayList<ClickeableTag> tagSelection;
	
	public void setTagClicked(String tag) {
		for (ClickeableTag t: tagSelection) {
			if (t.query()==tag) t.setClickedStatus(true);
		}
	}
	
	public void setTagNotClicked(String tag) {
		for (ClickeableTag t: tagSelection) {
			if (t.query()==tag) t.setClickedStatus(false);
		}
	}
	public ArrayList<ClickeableTag> getTagSelection() {
		return tagSelection;
	}

	public void setTagSelection(ArrayList<ClickeableTag> tagSelection) {
		this.tagSelection = tagSelection;
	}
	
}
