package fc;

import java.util.ArrayList;

import fc.searchengine.ClickeableTag;
import fc.searchengine.Tag;

public class TagBuilder {
	
	
	public ArrayList<Tag> buildListTag(ArrayList<String> data, ArrayList<ClickeableTag> clikeableTag){
	ArrayList<Tag> tagResearch =new ArrayList<Tag>();
		for(ClickeableTag ct: clikeableTag) {
			if (ct.isClickedStatus()) {
				for(String s :data) {
					ct.getTag().setTag(s);;
					Tag t = ct.getTag().clone();
					tagResearch.add(t);
				}
			}
		}
		return tagResearch;
	}
	public ArrayList<Tag> buildAllTag(ArrayList<String> data,ArrayList<ClickeableTag> clikeableTag) {
		ArrayList<Tag> tagResearch =new ArrayList<Tag>();
		for(ClickeableTag ct: clikeableTag) {
			for(String s :data) {
					ct.getTag().setTag(s);;
					Tag t = ct.getTag().clone();
					tagResearch.add(t);
				}
			}
		
		return tagResearch;
	}
}
