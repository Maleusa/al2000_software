package fc.searchengine;

import java.util.ArrayList;

import fc.MediatorFC;
import fc.TagBuilder;
import fc.machine.BluRay;
import fc.machine.Machine;
import fc.machine.QRCode;
/*
 * TODO CONSTRUCTEUR DEPENDANCE C DE LA MERDE
 */

public class BasicSearch extends SearchEngine{
	protected Machine machine;
	protected MediatorFC searchToStateMachine;
	protected TagBuilder tagBuilder;
	@Override
	/*
	 * EVENT_TYPE :
	 * 	SEARCH_BY_TAG
	 * 	SEARCH_NO_TAG
	 */
	public void update(String EVENT_TYPE, ArrayList<String> data) {
		switch(EVENT_TYPE) {
				case "SEARCH_BY_TAG":
					ArrayList<Tag> searchTag = new TagBuilder().buildListTag(data);
					ArrayList<BluRay> resultStock =stockResearch(searchTag);
					ArrayList<QRCode> resultDataBase = null;//ici query la db
					machine.getSearchResultStock().setStock(resultDataBase); //POUR UML
					//TRI du result
					ArrayList<ArrayList> dataSent = new ArrayList<ArrayList>();
					dataSent.add(resultStock);
					dataSent.add(resultDataBase);
					if (dataSent.isEmpty()) searchToStateMachine.notify("NO_RESULT",dataSent);
					searchToStateMachine.notify("RESEARCH_RESULT", dataSent);
					break;
				case "SEARCH_NO_TAG":
					searchTag = new TagBuilder().buildAllTag(data);
					resultStock =stockResearch(searchTag);
					resultDataBase = null;//ici query la db
					dataSent = new ArrayList<ArrayList>();
					dataSent.add(resultStock);
					dataSent.add(resultDataBase);
					if (dataSent.isEmpty()) searchToStateMachine.notify("NO_RESULT",dataSent);
					searchToStateMachine.notify("RESEARCH_RESULT", dataSent);
					break;
				default :
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
