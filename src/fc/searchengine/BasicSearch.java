package fc.searchengine;

import java.util.ArrayList;

import fc.TagBuilder;
import fc.machine.BluRay;
import fc.machine.Machine;
import fc.machine.QRCode;
/*
 * TODO CONSTRUCTEUR DEPENDANCE C DE LA MERDE
 */

public class BasicSearch extends SearchEngine{
	protected Machine machine;
	protected TagBuilder tagBuilder;
	
	public BasicSearch() {
		this.tagSelection=new ArrayList<ClickeableTag>();
		ClickeableTag ct= new ClickeableTag(new NameTag());
		tagSelection.add(ct);
		ClickeableTag ct2= new ClickeableTag(new ActorTag());
		tagSelection.add(ct2);
		ClickeableTag ct3= new ClickeableTag(new DirectorTag());
		tagSelection.add(ct3);
		ClickeableTag ct4= new ClickeableTag(new GenreTag());
		tagSelection.add(ct4);
		ClickeableTag ct5= new ClickeableTag(new YearTag());
		tagSelection.add(ct5);
		ClickeableTag ct6= new ClickeableTag(new DescriptionTag());
		tagSelection.add(ct6);
		ClickeableTag ct7= new ClickeableTag(new AllTag());
		tagSelection.add(ct7);
		}
	
	@Override
	/*
	 * EVENT_TYPE :
	 * 	SEARCH_BY_TAG
	 * 	SEARCH_NO_TAG
	 */
	public void update(String EVENT_TYPE, ArrayList<String> data) {
		switch(EVENT_TYPE) {
				case "SEARCH_BY_TAG":
					ArrayList<Tag> searchTag = new TagBuilder().buildListTag(data,this.getTagSelection());
					ArrayList<BluRay> resultStock =stockResearch(searchTag);
					ArrayList<QRCode> resultDataBase = new ArrayList<QRCode>();//ici query la db
					machine.getSearchResultStock().setStock(resultDataBase); //POUR UML
					machine.getStock().setSearchStock(resultStock);
					for(ClickeableTag ct : tagSelection)
						ct.setClickedStatus(false);
					break;
				case "SEARCH_NO_TAG":
					ArrayList<Tag> searchAllTag = new TagBuilder().buildAllTag(data,this.getTagSelection());
					ArrayList<BluRay> resultAllStock =stockResearch(searchAllTag);
					ArrayList<QRCode> resultAllDataBase = new ArrayList<QRCode>();//ici query la db
					machine.getSearchResultStock().setStock(resultAllDataBase); //POUR UML
					machine.getStock().setSearchStock(resultAllStock);
					for(ClickeableTag ct : tagSelection)
						ct.setClickedStatus(false);
					break;
				default :
					for(ClickeableTag ct : tagSelection)
						ct.setClickedStatus(false);
					break;
		}
		
	
	
	 }
	
	private ArrayList<BluRay> stockResearch(ArrayList<Tag> searchTag) {
		ArrayList<BluRay> tempPrio1 =new ArrayList<BluRay>();
		ArrayList<BluRay> tempPrio2 =new ArrayList<BluRay>();
		ArrayList<BluRay> tempPrio3 =new ArrayList<BluRay>();
		ArrayList<BluRay> tempPrio4 =new ArrayList<BluRay>();
		ArrayList<BluRay> tempPrio5 =new ArrayList<BluRay>();
		ArrayList<BluRay> tempPrio6 =new ArrayList<BluRay>();
		ArrayList<BluRay> tempPrio7 =new ArrayList<BluRay>();
		ArrayList<BluRay> resultStock =new ArrayList<BluRay>();
		for (Tag t : searchTag) {
			for(BluRay b :machine.getStock().getRentableMovies()) {
				switch (b.getMovie().getPriority(t)) {
					case 1:
						if(!tempPrio1.contains(b))tempPrio1.add(b);
						break;
					case 2:
						if(!tempPrio2.contains(b))tempPrio2.add(b);
						break;
					case 3:
						if(!tempPrio3.contains(b))tempPrio3.add(b);
						break;
					case 4:
						if(!tempPrio4.contains(b))tempPrio4.add(b);
						break;
					case 5:
						if(!tempPrio5.contains(b))tempPrio5.add(b);
						break;
					case 6:
						if(!tempPrio6.contains(b))tempPrio6.add(b);
						break;
					case 7:
						if(!tempPrio7.contains(b))tempPrio7.add(b);
						break;
					default:
						break;
				}
			}
		}
		resultStock.addAll(tempPrio1);
		resultStock.addAll(tempPrio2);
		resultStock.addAll(tempPrio3);
		resultStock.addAll(tempPrio4);
		resultStock.addAll(tempPrio5);
		resultStock.addAll(tempPrio6);
		resultStock.addAll(tempPrio7);
		return resultStock;
		
	}

}
