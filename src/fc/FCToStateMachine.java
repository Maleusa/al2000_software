package fc;

import java.util.ArrayList;

import fc.machine.BluRay;
import fc.machine.QRCode;
import fc.searchengine.SearchEngine;
import ui.SearchRepresentation;
import ui.StockRepresentation;
import ui.stateMachine.StateMachine;
@Deprecated //slated for removal
public class FCToStateMachine  implements MediatorFC{
	protected SearchEngine searchEngine;
	protected StateMachine stateMachine;

	@Override
	public void notify(String EVENT, Object data) {
		switch (EVENT) {
		case "RESEARCH_RESULT":
			ArrayList<ArrayList> results=(ArrayList<ArrayList>) data;
			//IAM% HERE
			resultsRepresentation(results);
			
			break;

		default:
			break;
		}
		// TODO Auto-generated method stub
		
	}
	
	private void resultsRepresentation(ArrayList<ArrayList> results){
		ArrayList<BluRay> resultStock=results.get(0);
		ArrayList<QRCode> resultDatabase=results.get(1);
		StockRepresentation resultStockRepresentation=new StockRepresentation();
		SearchRepresentation resultDatabaseRepresentation = new SearchRepresentation();
		//TRANSLATION TO BE DONE
		
		stateMachine.updateRepresentation(StateMachine.STOCK_UPDATE, resultStockRepresentation);
		stateMachine.updateRepresentation(StateMachine.SEARCH_UPDATE, resultDatabaseRepresentation);
	
		
	}
	public SearchEngine getSearchEngine() {
		return searchEngine;
	}

	public void setSearchEngine(SearchEngine searchEngine) {
		this.searchEngine = searchEngine;
	}

	public StateMachine getStateMachine() {
		return stateMachine;
	}

	public void setStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

}
