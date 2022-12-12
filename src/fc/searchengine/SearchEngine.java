package fc.searchengine;

import java.util.ArrayList;

import fc.ComponentFC;

public abstract class SearchEngine implements ComponentFC{
	
	protected ArrayList<ClickeableTag> tagSelection;
	

	public ArrayList<ClickeableTag> getTagSelection() {
		return tagSelection;
	}

	public void setTagSelection(ArrayList<ClickeableTag> tagSelection) {
		this.tagSelection = tagSelection;
	}
	
	
	/**
	 * This is the interface to get a model of the current search engine
	 * that you use in yout interface
	 * @return this SearchEngine
	 */
	public SearchEngine getSearchEngineModels() {
		return this;
	}
	
}
