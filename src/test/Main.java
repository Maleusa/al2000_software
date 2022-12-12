package test;
import fc.uiInterface.UIObserver;
import ui.stateMachine.StateMachine;
import fc.TagBuilder;
import fc.machine.DAORequestHandlerExample;
import fc.machine.DAORequestHandlerMachine;
import fc.machine.Machine;
import fc.searchengine.BasicSearch;
import fc.uiInterface.UIObserver;

public class Main {

	public static void main(String[] args) {
		Machine al2000 = Machine.getInstance();
		StateMachine stateM = new StateMachine();
		UIObserver uiObserver = new UIObserver();
		stateM.setUIObserver(uiObserver);
		/*
		 * Partie DAO
		 */
		DAORequestHandlerExample daoRequestHandler = new DAORequestHandlerExample();
		TagBuilder builder = new TagBuilder();
		BasicSearch search = new BasicSearch();
		search.setMachine(al2000);
		search.setTagBuilder(builder);
		al2000.setStateMachine(stateM);
		al2000.setSearchEngine(search);
		al2000.setDAORequestHandler(daoRequestHandler);
		al2000.getStockBluRayFromDAO();

		al2000.setUIObserver(uiObserver);
		al2000.subscribeAllListener();
		
		al2000.launch();
	}

}
